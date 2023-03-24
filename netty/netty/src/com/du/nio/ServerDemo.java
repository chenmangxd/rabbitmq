package com.du.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1",8000));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer readbuff = ByteBuffer.allocate(1024);
        ByteBuffer writebuff = ByteBuffer.allocate(128);
        writebuff.put("recevie".getBytes());
        writebuff.flip();
        while (true)
        {

            int select = selector.select();
            Set< SelectionKey > keys = selector.selectedKeys();
            Iterator< SelectionKey > iterator = keys.iterator();
            while (iterator.hasNext())
            {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable())
                {
                    SocketChannel channel = ssc.accept();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel)key.channel();
                    readbuff.clear();
                    int read = channel.read(readbuff);
                    readbuff.flip();
                    System.out.println(new String(readbuff.array(),0,read));
                    key.interestOps(SelectionKey.OP_WRITE);
                }else if(key.isWritable())
                {
                    writebuff.rewind();
                    SocketChannel channel = (SocketChannel)key.channel();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_WRITE);
                    channel.write(writebuff);
                    key.interestOps(SelectionKey.OP_READ);
                }

            }

        }

    }
}