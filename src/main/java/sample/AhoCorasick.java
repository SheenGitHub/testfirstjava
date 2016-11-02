package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class AhoCorasick {

    TreeNode _root;

    public AhoCorasick(String[] keywords) {
        buildTree(keywords);
        addFailure();
    }

    public static final String text ="然而100年后的今天，我们必须正视黑人还没有得到自由这一悲惨的事实。100年后的今天，在种族隔离的镣铐和种族歧视的枷锁下，黑人的生活备受压榨。100年后的今天，黑人仍生活在物质充裕的海洋中一个穷困的孤岛上。100年后的今天，黑人仍然蜷缩在美国社会的角落里，并且意识到自己是故土家园中的流亡者。今天我们在这里集会，就是要把这种骇人听闻的情况公诸世人";
    public static final  String[] words = new String[]{"黑人","自由","物质","种族","美国","社会"};
    public static final String demotext = "sdmfhsgnshejfgnihaofhsrnihao";
    public static final String[] demowords =new String[]{"nihao","hao","hs","hsr"};
    public static final String[] testwords = new String[]{"she","her","sherry"};

    public static void main(String[] args) {
        AhoCorasick search = new AhoCorasick(demowords);
        StringSearchResult[] results = search.findAll(demotext);
        System.out.println(Arrays.asList(results));
    }

    public void depthSearch(TreeNode node, StringBuilder ret, int depth) {

        if (node != null) {
            for (int i = 0; i < depth; i++) {
                ret.append("|");
            }
            ret.append("--");
            ret.append(node.getChar());
            ret.append("\n");
            for (TreeNode child : node.getTransitions()) {
                depthSearch(child,ret,depth+1);
            }
        }
    }

    public StringSearchResult[] findAll(String text) {
        List<StringSearchResult> ret = new ArrayList<>();
        TreeNode ptr = _root;
        int index = 0;
        while (index < text.length()) {
            TreeNode trans = null;
            while (trans == null) {
                trans = ptr.getTransition(
                        text.charAt(index));

                if(ptr == _root)
                    break;

                if(trans == null)
                    ptr = ptr._failure;
            }
            if(trans !=null)
                ptr = trans;
            final int intI = index;
            ptr.getResult().stream()
                    .map(s -> new StringSearchResult(intI - s.length()+1 ,s))
                    .forEach(ret::add);
            index++;
        }
        return ret.toArray(new StringSearchResult[ret.size()]) ;
    }

    private void buildTree(String[] keywords) {
        _root = new TreeNode(' ', null);
        for (String p : keywords) {
            TreeNode nd = _root;
            for (char c : p.toCharArray()) {
                TreeNode ndNew = null;
                if(nd.containTrans(c))
                    ndNew = nd.getTransition(c);

                if (ndNew == null) {
                    ndNew = new TreeNode(c, nd);
                    nd.addTransition(ndNew);
                }

                nd = ndNew;
            }
            nd.addResult(p);
        }
    }

    private void addFailure() {
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode node : _root.getTransitions()) {
            node.failure(_root);
            Arrays.asList(node.getTransitions()).stream().forEach(nodes::add);
        }

        while (nodes.size() > 0) {
            List<TreeNode> newNodes = new ArrayList<>();
            for (TreeNode nd : nodes) {
                TreeNode r = nd.getParent()._failure;
                char c = r.getChar();
                while (r!=null && !r.containTrans(c))
                    r = r._failure;

                if(r == null)
                    nd.failure(_root);
                else{
                    TreeNode t = r.getTransition(c);
                    nd.failure(t);
                    t.getResult().stream().forEach(nd::addResult);

                    for(TreeNode child : nd.getTransitions())
                        newNodes.add(child);
                }
            }
            nodes = newNodes;
        }
        _root.failure(_root);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        TreeNode node = _root;
        int depth = 0;
        depthSearch(node,ret,depth);
        return ret.toString();
    }

    static class TreeNode {
        private char _char;
        private TreeNode _parent;
        private TreeNode _failure;
        private TreeNode[] _transitionsAr;
        private List<String> _result;
        private Hashtable<Character, TreeNode> _transHash;

        public TreeNode(char _char, TreeNode _parent) {
            this._char = _char;
            this._parent = _parent;
            this._result = new ArrayList<>();
            this._transitionsAr = new TreeNode[]{};
            this._transHash = new Hashtable<>();
        }

        public void addResult(String result) {
            if(_result.contains(result))
                return;
            _result.add(result);
        }

        public void addTransition(TreeNode trans) {
            _transHash.put(trans._char, trans);
            _transitionsAr = new TreeNode[_transHash.size()];
            int index = 0;
            for (TreeNode node : _transHash.values())
                _transitionsAr[index++] = node;

        }

        public TreeNode getTransition(char c) {
            return _transHash.get(c);
        }

        public boolean containTrans(char c) {
            return getTransition(c) != null;
        }

        public List<String> getResult() {
            return _result;
        }

        public char getChar() {
            return _char;
        }

        public TreeNode[] getTransitions() {
            return _transitionsAr;
        }

        public TreeNode getParent() {
            return _parent;
        }

        public void failure(TreeNode node) {
            _failure = node;
        }
    }

    static class StringSearchResult {
        private int index;
        private String found;

        public StringSearchResult(int index, String found) {
            this.index = index;
            this.found = found;
        }

        @Override
        public String toString() {
            return found +"@"+index;
        }
    }


}
