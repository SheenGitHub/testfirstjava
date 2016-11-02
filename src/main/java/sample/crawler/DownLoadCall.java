package sample.crawler;

import com.sun.webkit.network.URLs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DownLoadCall implements Callable<String> {

    private URL url;

    public DownLoadCall(URL url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        String content = null;
        return content;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<URL> urls = new ArrayList<>();
        int threads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Set<Future<String>> set =new HashSet<>();

        for (final URL url : urls) {
            DownLoadCall task = new DownLoadCall(url);
            Future<String> future = executorService.submit(task);
            set.add(future);
        }

        for (Future<String> future : set) {
            String content =future.get();
        }
    }
}
