package io;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/18.
 */
public class LoadWebPageUseSelector {
    public void load(Set<URL> urls) throws IOException {
        Map<SocketAddress, String> mapping = urlToSocketAddress(urls);
        Selector selector = Selector.open();
        for (SocketAddress address : mapping.keySet()) {
            register(selector, address);
        }

    }

    private void register(Selector selector, SocketAddress address) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(address);
        channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
    }

    private Map<SocketAddress, String> urlToSocketAddress(Set<URL> urls) {
        Map<SocketAddress, String> mapping = new HashMap<>();
        for (URL url : urls) {
            int port = url.getPort() != -1? url.getPort() : url.getDefaultPort();
            String path = url.getPath();
            if (url.getQuery() != null) {
                path = path + "?" + url.getQuery();
            }
        }
        return mapping;
    }

}
