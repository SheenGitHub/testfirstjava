package io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/3/17.
 */
public class StreamReuse {
    private InputStream input;

    public StreamReuse(InputStream input) {
        if (!input.markSupported()) {
            this.input = new BufferedInputStream(input);
        } else {
            this.input = input;
        }
    }

    public InputStream getInput() {
        input.mark(Integer.MAX_VALUE);
        return input;
    }

    public void markUsed() throws IOException {
        input.reset();
    }
}
