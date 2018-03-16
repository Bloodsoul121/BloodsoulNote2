package com.example.cgz.bloodsoulnote2.view.custom.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cgz.bloodsoulnote2.R;
import java.util.List;

public class DialogListViewAdapter extends BaseAdapter {

    private int mChosePosition;
    private Context mContext;
    private List<String> mDatas;

    public DialogListViewAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<String> datas, int chosePosition) {
        mDatas = datas;
        mChosePosition = chosePosition;
        checkChosePosition();
        notifyDataSetChanged();
    }

    private void checkChosePosition() {
        if (mDatas == null) {
            mChosePosition = -1;
        } else {
            if (mChosePosition >= mDatas.size()) {
                mChosePosition = 0;
            }
        }
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.dialog_listview_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mContentView.setText(mDatas.get(position));

        if (mChosePosition == position) {
            holder.mImageView.setImageResource(R.drawable.abc_btn_radio_on2);
        } else {
            holder.mImageView.setImageResource(R.drawable.abc_btn_radio_off2);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView mContentView;
        ImageView mImageView;
        private ViewHolder(View view) {
            mContentView = (TextView) view.findViewById(R.id.content);
            mImageView = (ImageView) view.findViewById(R.id.image);
        }
    }

    public void refresh(int chosePosition) {
        mChosePosition = chosePosition;
        checkChosePosition();
        notifyDataSetChanged();
    }

}
