package zuozhen;

public class TrieTest {
    /*
    前缀树
    应用：前缀匹配、词频统计
     */
    /*
    Trie{
        1.root节点 root=Trie()（一般无具体意义）
        2.孩子节点 HashMap<Character,Trie>
        3.结束flag boolean isEnd
        4.对应的值 String val
    }
    */
    /*
    对应leetcode题目
    208/720/692
     */
    //leetcode208
    class Trie {

        boolean isEnd;
        Trie[] children = new Trie[26];

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie p = this;
            char[] w = word.toCharArray();
            for (int i = 0; i < w.length; i++) {
                if (p.children[w[i] - 'a'] == null) p.children[w[i] - 'a'] = new Trie();
                p = p.children[w[i] - 'a'];
            }
            p.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie p = this;
            char[] w = word.toCharArray();
            for (int i = 0; i < w.length; i++) {
                if (p.children[w[i] - 'a'] == null) return false;
                p = p.children[w[i] - 'a'];
            }
            return p.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie p = this;
            char[] w = prefix.toCharArray();
            for (int i = 0; i < w.length; i++) {
                if (p.children[w[i] - 'a'] == null) return false;
                p = p.children[w[i] - 'a'];
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
