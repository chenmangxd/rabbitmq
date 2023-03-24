package com.du.nio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Demo1 {
    public static void main(String[] args)  throws  Exception{
        String input="atdema";
        System.out.println(input);
        ByteBuffer buf = ByteBuffer.wrap(input.getBytes());
        String path="F:\\尚硅谷\\尚硅谷Java NIO课程（2021最新版）\\nio.txt";
        Path pf = Paths.get(path);
        FileChannel fileChannel = FileChannel.open(pf, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        fileChannel.position(fileChannel.size()-1);
        FileLock lock = fileChannel.lock(0L, Long.MAX_VALUE, true);
        // FileLock lock = fileChannel.tryLock(0, Long.MAX_VALUE, false);
        System.out.println("共享锁"+lock.isShared());
        fileChannel.write(buf);
        fileChannel.close();
        readPrint(path);
    }
    public static void readPrint(String path) throws IOException {
        FileReader filereader = new FileReader(path);
        BufferedReader bufferedreader = new BufferedReader(filereader);
        String tr = bufferedreader.readLine();
        System.out.println("读取内容: ");
        while (tr != null) {
            System.out.println(" " + tr);
            tr = bufferedreader.readLine();}
        filereader.close();
        bufferedreader.close();
    }
}