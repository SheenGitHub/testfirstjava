package luceneinaction;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/19.
 */
public class QueryParserTest {
    @Before
    public void setUp() {
        System.setProperty("index.dir", "d:/index");
    }
    @Test
    public void testQueryParser() throws IOException, ParseException {
        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(dir));
        QueryParser parser = new QueryParser("contents", new StandardAnalyzer());

        Query query = parser.parse("java");
        TopDocs docs = searcher.search(query, 10);
        Assert.assertEquals(1, docs.totalHits);
    }
}
