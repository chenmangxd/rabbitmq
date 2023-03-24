package com.du.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class walkFileTree1 {
    public static void main(String[] args) {
        Path rootPath = Paths.get("d:\\atguigu");
        String fileToFind = File.separator + "001.txt";
        try {
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws
                        IOException {
                    String fileString = file.toAbsolutePath().toString();
                    if(fileString.endsWith(fileToFind)){
                        System.out.println("file found at path: " + file.toAbsolutePath());
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}