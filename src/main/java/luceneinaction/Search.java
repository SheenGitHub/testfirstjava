package luceneinaction;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/18.
 */
public class Search {
    public static void main(String[] args) throws IllegalArgumentException, IOException, ParseException {
//        if (args.length != 2) {
//            throw new IllegalArgumentException("Usage: java " +
//                    Search.class.getName() +
//                    " <index dir> <query>");
//        }
//        String indexDir = args[0];
//        String q = args[1];

            String indexDir = "d:\\index";

        search(indexDir, "java");
    }

    private static void search(String indexDir, String q) throws IOException, ParseException{

        Directory dir = FSDirectory.open(new File(indexDir).toPath());
        IndexSearcher is;
        QueryParser parser;
        Query query;
        long start;
        TopDocs hits;
        long end;
        try (DirectoryReader reader = DirectoryReader.open(dir)) {
            is = new IndexSearcher(reader);


            parser = new QueryParser("contents", new StandardAnalyzer());
            query = parser.parse(q);

            start = System.currentTimeMillis();
            hits = is.search(query, 10);
            end = System.currentTimeMillis();

            System.err.println("Found " + hits.totalHits +
                " document(s) (in " + (end - start) +
                " milliseconds) that matched query '" + q +"':");

            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = is.doc(scoreDoc.doc);
                System.out.println(doc.get("fullpath"));

            }
        }

    }
}
