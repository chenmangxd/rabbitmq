package com.du.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {
    public static void main(String[] args) throws Exception{
        RandomAccessFile afile = new RandomAccessFile("F:\\尚硅谷\\尚硅谷Java NIO课程（2021最新版）\\nio.txt","rw");
        FileChannel channel = afile.getChannel();
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        while (buf.hasRemaining())
        {
            channel.write(buf);
        }
        channel.close();
    }
}