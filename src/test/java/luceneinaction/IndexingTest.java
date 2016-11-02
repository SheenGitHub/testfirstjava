package luceneinaction;

import junit.framework.Assert;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Administrator on 2016/4/19.
 */
public class IndexingTest {
    protected String[] ids = {"1", "2"};
    protected String[] unindexed = {"Netherlands", "Italy"};
    protected String[] unstored = {"Amsterdam has lots of bridges",
                                    "Venice has lots of canals"};
    protected String[] text = {"Amsterdam", "Venice"};

    private Directory directory;

    @Before
    public void setUp() throws Exception {
        directory = new RAMDirectory();

        IndexWriter writer = getWriter();

        for(int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            doc.add(new Field("id",ids[i],TextField.TYPE_STORED));
            doc.add(new Field("country",unindexed[i], TextField.TYPE_STORED));
            doc.add(new Field("contents", unstored[i], TextField.TYPE_STORED));
            doc.add(new Field("city", text[i], TextField.TYPE_STORED));
            writer.addDocument(doc);
        }
        writer.close();
    }

    private IndexWriter getWriter() throws IOException {
        return new IndexWriter(directory, new IndexWriterConfig(new WhitespaceAnalyzer()));
    }

    protected int getHitCount(String fieldName, String searchString) throws IOException {
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
        Term t = new Term(fieldName, searchString);
        Query query = new TermQuery(t);
        int hitcount = TestUtil.hitCount(searcher, query);
        return hitcount;
    }

    @Test
    public void testIndexWriter() throws IOException{
        IndexWriter writer = getWriter();
        assertEquals(ids.length, writer.numDocs());
        writer.close();
    }

    @Test
    public void testIndexReader() throws IOException{
        IndexReader reader = DirectoryReader.open(directory);
        assertEquals(ids.length, reader.maxDoc());
        assertEquals(ids.length, reader.numDocs());
        reader.close();
    }


}
