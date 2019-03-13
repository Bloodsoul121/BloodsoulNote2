package com.example.cgz.bloodsoulnote2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

        int[] arr = new int[10];
        arr[0] = 0;
        System.out.println(arr.length);

        long totalDataLen = 2084;
        byte[] header = new byte[4];
        header[0] = (byte) (totalDataLen & 0xff);//数据大小
        header[1] = (byte) ((totalDataLen >> 8) & 0xff);
        header[2] = (byte) ((totalDataLen >> 16) & 0xff);
        header[3] = (byte) ((totalDataLen >> 24) & 0xff);
        for (byte b : header) {
            System.out.println(Long.toHexString(b));
        }
    }
}