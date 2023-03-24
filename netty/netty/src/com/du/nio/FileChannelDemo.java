package com.du.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws  Exception{
        RandomAccessFile afile = new RandomAccessFile("F:\\尚硅谷\\尚硅谷Java NIO课程（2021最新版）\\nio.txt","rw");
        FileChannel inchannel = afile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(4);
        int read = inchannel.read(buf);
        while (read!=-1)
        {
            System.out.println("读取"+read);
            buf.flip();
            while (buf.hasRemaining())
            {
                System.out.print((char)buf.get());
            }
            buf.clear();
           read=inchannel.read(buf);
        }
        afile.close();
        System.out.println("操作结束");


    }
}