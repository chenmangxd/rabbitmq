package com.du.nio;

public class rt {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("111");
            System.out.println( Thread.currentThread().getName());

        },String.valueOf(1)).start();

    }
}