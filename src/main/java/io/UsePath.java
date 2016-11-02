package io;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2016/3/18.
 */
public class UsePath {
    public static void main(String[] args) throws IOException {
        UsePath up = new UsePath();
        up.usePath();
        up.listFiles();
    }

    public void usePath() {
        System.out.println("Current Method ====> "+Thread.currentThread().getStackTrace()[1].getMethodName());
        Path path1 = Paths.get("folder1", "sub1");
        Path path2 = Paths.get("folder1", "sub2");

        System.out.println(path1.resolve(path2));
        System.out.println(path1.resolveSibling(path2));
        System.out.println(path1.relativize(path2));
        System.out.println(path1.subpath(0, 1));
        System.out.println(Paths.get("folder1/./../folder2/my.text").normalize());
    }

    public void listFiles() throws IOException {
        System.out.println("Current Method ====> "+Thread.currentThread().getStackTrace()[1].getMethodName());
        Path path = Paths.get("src/main/java");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                System.out.println(entry);
                System.out.println(entry.toFile().length());
            }
        }

    }
}
