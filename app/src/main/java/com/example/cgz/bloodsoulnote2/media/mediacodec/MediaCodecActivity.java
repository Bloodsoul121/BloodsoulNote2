package com.example.cgz.bloodsoulnote2.media.mediacodec;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.cgz.bloodsoulnote2.R;

import java.io.IOException;

public class MediaCodecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_codec);
    }

    public void decode(View view) {
        String audioPath = Environment.getExternalStorageDirectory() + "/aaa.3gp";
        String audioSavePath = Environment.getExternalStorageDirectory() + "/aaa.pcm";
        getPCMFromAudio(audioPath, audioSavePath);
    }

    public void encode(View view) {
        String pcmPath = Environment.getExternalStorageDirectory() + "/aaa.pcm";
        String audioPath = Environment.getExternalStorageDirectory() + "/aaa.m4a";
        pcmToAudio(pcmPath, audioPath);
    }

    /**
     * 将音频文件解码成原始的PCM数据
     * @param audioPath         MP3文件目录
     * @param audioSavePath     pcm文件保存位置
     */
    public void getPCMFromAudio(String audioPath, String audioSavePath){
        try {
            MediaExtractor mediaExtractor = new MediaExtractor();
            mediaExtractor.setDataSource(audioPath);

            int audioTrack = -1;
            boolean hasAudio = false;

            for (int i = 0; i < mediaExtractor.getTrackCount(); i++) {
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
                String type = trackFormat.getString(MediaFormat.KEY_MIME);
                if (type.startsWith("audio/")) {
                    audioTrack = i;
                    hasAudio = true;
                    break;
                }
            }

            if (hasAudio) {
                mediaExtractor.selectTrack(audioTrack);

                new Thread(new AudioDecodeRunnable(mediaExtractor, audioTrack, audioSavePath, new DecodeOverListener() {
                    @Override
                    public void decodeOver() {

                    }

                    @Override
                    public void decodeFail() {

                    }
                })).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface DecodeOverListener{
        void decodeOver();
        void decodeFail();
    }

    /**
     * 音频解码监听器：监听是否解码成功
     */
    public interface AudioDecodeListener{
        void decodeOver();
        void decodeFail();
    }

    public void pcmToAudio(String pcmPath, String audioPath) {
        new Thread(new AudioEncodeRunnable(pcmPath, audioPath, new AudioDecodeListener() {
            @Override
            public void decodeOver() {

            }

            @Override
            public void decodeFail() {

            }
        })).start();
    }

}
