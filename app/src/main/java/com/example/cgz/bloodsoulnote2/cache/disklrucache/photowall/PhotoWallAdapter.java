package com.example.cgz.bloodsoulnote2.cache.disklrucache.photowall;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.cache.disklrucachesourcecode.DiskLruCache;
import com.example.cgz.bloodsoulnote2.util.MD5Util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhotoWallAdapter extends BaseAdapter {

    private final GridView mPhotoWall;
    private List<String> mDatas;
    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskCache;
    private final LayoutInflater mLayoutInflater;
    private int mItemHeight;
    private Set<BitmapWorkerTask> mTaskCollection;

    public PhotoWallAdapter(Context context, List<String> datas, GridView photoWall) {
        mContext = context;
        mDatas = datas;
        mPhotoWall = photoWall;
        initMemoryCache();
        initDiskCache();
        mLayoutInflater = LayoutInflater.from(context);
        mTaskCollection = new HashSet<>();
    }

    private void initMemoryCache() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cacheSize = (int) (maxMemory / 8);
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    private void initDiskCache() {
        File dir = getDiskCacheDir("PhotoWall");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        int appVersion = getAppVersion();
        try {
            mDiskCache = DiskLruCache.open(dir, appVersion, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getDiskCacheDir(String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = mContext.getExternalCacheDir().getPath();  // /sdcard/Android/data/<application package>/cache
        } else {
            cachePath = mContext.getCacheDir().getPath();  // /data/data/<application package>/cache
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private int getAppVersion() {
        try {
            PackageInfo packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String url = getItem(position);
        PhotoWallHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_grid_photo_wall, null);
            holder = new PhotoWallHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PhotoWallHolder) convertView.getTag();
        }

        if (holder.mImageView.getLayoutParams().height != mItemHeight) {
            holder.mImageView.getLayoutParams().height = mItemHeight;
        }
        // 给ImageView设置一个Tag，保证异步加载图片时不会乱序
        holder.mImageView.setTag(url);
        holder.mImageView.setImageResource(R.drawable.star);
        loadBitmaps(holder.mImageView, url);
        return convertView;
    }

    private class PhotoWallHolder {
        private ImageView mImageView;
        private PhotoWallHolder(View view) {
            mImageView = (ImageView) view.findViewById(R.id.photo);
        }
    }

    /**
     * 设置item子项的高度。
     */
    public void setItemHeight(int height) {
        if (height == mItemHeight) {
            return;
        }
        mItemHeight = height;
        notifyDataSetChanged();
    }

    /**
     * 加载Bitmap对象。此方法会在LruCache中检查所有屏幕中可见的ImageView的Bitmap对象，
     * 如果发现任何一个ImageView的Bitmap对象不在缓存中，就会开启异步线程去下载图片。
     */
    public void loadBitmaps(ImageView imageView, String imageUrl) {
        Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
        if (bitmap == null) {
            BitmapWorkerTask task = new BitmapWorkerTask();
            mTaskCollection.add(task);
            task.execute(imageUrl);
        } else {
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     */
    public void cancelAllTasks() {
        if (mTaskCollection != null) {
            for (BitmapWorkerTask task : mTaskCollection) {
                task.cancel(false);
            }
        }
    }

    /**
     * 将缓存记录同步到journal文件中。
     */
    public void fluchCache() {
        if (mDiskCache != null) {
            try {
                mDiskCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    /**
     * 异步下载图片的任务。
     */
    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {

        private String mImageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            mImageUrl = params[0];
            // 生成图片URL对应的key
            final String key = MD5Util.encrypt(mImageUrl);

            DiskLruCache.Snapshot snapshot;
            FileDescriptor fileDescriptor = null;
            FileInputStream fileInputStream = null;
            try {
                snapshot = mDiskCache.get(key);
                if (snapshot == null) {
                    DiskLruCache.Editor editor = mDiskCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        // 下载
                        if (downloadUrlToStream(mImageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    // 缓存被写入后，再次查找key对应的缓存
                    snapshot = mDiskCache.get(key);
                }

                if (snapshot != null) {
                    fileInputStream = (FileInputStream) snapshot.getInputStream(0);
                    fileDescriptor = fileInputStream.getFD();
                }

                // 将缓存数据解析成Bitmap对象
                Bitmap bitmap = null;
                if (fileDescriptor != null) {
                    bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                }

                if (bitmap != null) {
                    // 将Bitmap对象添加到内存缓存当中
                    addBitmapToMemoryCache(params[0], bitmap);
                }

                return bitmap;

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileDescriptor == null && fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。
            ImageView imageView = (ImageView) mPhotoWall.findViewWithTag(mImageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTaskCollection.remove(this);
        }
    }

    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
