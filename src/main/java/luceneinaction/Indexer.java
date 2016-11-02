package luceneinaction;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/16.
 */
public class Indexer {
    private IndexWriter writer;
    public Indexer(String indexDir) throws IOException{
        Analyzer analyzer = new StandardAnalyzer();
        Directory dir = FSDirectory.open(new File(indexDir).toPath());
        writer = new IndexWriter(dir, new IndexWriterConfig(new StandardAnalyzer()));

    }

    public void close() throws IOException {
        writer.close();
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() +
                    "<index dir> <data dir>");
        }

        String indexDir = args[0];
        String dataDir = args[1];

        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir);
        int numberIndexed;

        try {
            numberIndexed = indexer.index(dataDir, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();

        System.out.println("Indexing " + numberIndexed + " files took " + (end - start) + " milliseconds");


    }

    private int index(String dataDir, FileFilter filter) throws Exception{
        File[] files = new File(dataDir).listFiles();
        System.out.println("file length:"+files.length);
        for (File f : files) {
            if(!f.isDirectory()&&
                !f.isHidden()&&
                f.exists()&&
                f.canRead()&&
                (filter == null || filter.accept(f))){
                indexFile(f);
            }
        }
        return writer.numDocs();
    }

    private void indexFile(File f) throws Exception{
        System.out.println("Indexing " + f.getCanonicalPath());
        Document doc = getDocument(f);
        writer.addDocument(doc);
    }

    private Document getDocument(File f) throws Exception {
        Document doc = new Document();
        FieldType ft = new FieldType();
        ft.setIndexOptions(IndexOptions.DOCS);
        ft.setStored(false);
        ft.setOmitNorms(true);
        ft.setTokenized(true);
        ft.freeze();
        doc.add(new DoubleDocValuesField("price",15.15));
        doc.add(new Field("contents",new FileReader(f), ft));
        doc.add(new Field("filename",f.getName(), TextField.TYPE_STORED));
        doc.add(new Field("fullpath",f.getCanonicalPath(), TextField.TYPE_STORED));
        return doc;
    }


    private static class TextFilesFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {

            return pathname.getName().toLowerCase().endsWith(".txt")
                    ||pathname.getName().toLowerCase().endsWith(".java");
        }
    }
}

