package io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class FileAttribute {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileAttribute fa = new FileAttribute();
        fa.useFileAttributeView();
        fa.calculate();
    }

    public void useFileAttributeView() throws IOException {
        System.out.println("Current Method ===> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Path path = Paths.get("content.txt");
        DosFileAttributeView view = Files.getFileAttributeView(path, DosFileAttributeView.class);
        if (view != null) {
            DosFileAttributes attrs = view.readAttributes();
            System.out.println(attrs.isReadOnly());
        }
    }

    public void calculate() throws IOException, InterruptedException {
        System.out.println("Current Method ===> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        WatchService service = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("").toAbsolutePath();
        ((Runnable) () -> {
            try {
                manipulateFiles();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).run();
        path.register(service,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE
        );
        while (true) {
            WatchKey key = service.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                Path createdPath = (Path) event.context();
                createdPath = path.resolve(createdPath);
                long size = Files.size(createdPath);
                System.out.println(createdPath+" ==> "+size);
            }
            key.reset();
        }
    }

    public void manipulateFiles() throws IOException {
        System.out.println("Current Method ===> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        Path newFile = Files.createFile(Paths.get("new.txt").toAbsolutePath());
        List<String> content = new ArrayList<>();
        content.add("Hello");
        content.add("World");
        Files.write(newFile, content, Charset.forName("UTF-8"));
        System.out.println(Files.size(newFile));
        byte[] bytes = Files.readAllBytes(newFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Files.copy(newFile, outputStream);
        Files.delete(newFile);
    }
}
