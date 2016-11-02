package sample.crawler;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.RAMDirectory;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Administrator on 2016/7/16.
 */
public class RelatedEngine {
    public static final String TEXT_FIELD = "text";
    private File file;

    public RelatedEngine(File file) {
        this.file = file;
    }

    public static String[] filterRelated(HashSet<String> words, String word) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append(word.charAt(i));
            sb.append(" ");
        }

        RAMDirectory store = new RAMDirectory();

       return null;
    }
}
