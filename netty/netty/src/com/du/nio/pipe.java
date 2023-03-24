package com.du.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class pipe {
    public static void main(String[] args)  throws Exception{
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sink = pipe.sink();
        ByteBuffer writebuffer = ByteBuffer.allocate(1024);
        writebuffer.clear();
        writebuffer.put("eqwqe".getBytes());
        writebuffer.flip();
        sink.write(writebuffer);
        Pipe.SourceChannel source = pipe.source();
        ByteBuffer readbuffer= ByteBuffer.allocate(1024);

        int read = source.read(readbuffer);
        readbuffer.flip();
       while (readbuffer.hasRemaining())
       {
           System.out.print( (char) readbuffer.get());
       }
        sink.close();
        source.close();

    }
}