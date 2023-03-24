package com.du.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientDemo {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8000));
        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        ByteBuffer readBuffer = ByteBuffer.allocate(32);
        writeBuffer.put("hello".getBytes());
        writeBuffer.flip();
        while (true)
        {
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            int read = socketChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(new String(readBuffer.array(),0,read));
        }
    }
}