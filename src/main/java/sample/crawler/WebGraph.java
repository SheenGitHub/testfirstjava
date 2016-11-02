package sample.crawler;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityIndex;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.model.SecondaryKey;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;

/**
 * Created by Administrator on 2016/7/15.
 */
public class WebGraph {
    private PrimaryIndex<String, Link> outLinkIndex;
    private SecondaryIndex<String,String,Link> inLinkIndex;

    public void addLink(String fromLink, String toLink) throws DatabaseException {
        Link outLinks = new Link();
        outLinks.fromURL = fromLink;
        outLinks.toURL = new HashSet<>();
        outLinks.toURL.add(toLink);

        boolean inserted = outLinkIndex.putNoOverwrite(outLinks);

        if (!inserted) {
            outLinks = outLinkIndex.get(fromLink);
            outLinks.toURL.add(toLink);
            outLinkIndex.put(outLinks);
        }
    }

    public String[] inLinks(String URL) throws DatabaseException {
        EntityIndex<String, Link> subIndex = inLinkIndex.subIndex(URL);
        String[] linkList = new String[(int) subIndex.count()];
        int i = 0;
        EntityCursor<Link> cursor = subIndex.entities();
        try {
            for (Link entity : cursor) {
                linkList[i++] = entity.fromURL;
            }
        } finally {
            cursor.close();
        }
        return linkList;
    }

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        TokenStream ts = analyzer.tokenStream("myfield", new StringReader("带切分文本"));
        while (ts.incrementToken()) {
            System.out.println("token:" + ts);
        }
    }

}
