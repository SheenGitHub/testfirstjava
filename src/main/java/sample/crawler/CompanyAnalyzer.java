package sample.crawler;

import org.apache.lucene.analysis.Analyzer;

/**
 * Created by Administrator on 2016/7/16.
 */
public class CompanyAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String s) {
        return null;
    }
}
