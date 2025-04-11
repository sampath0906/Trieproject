import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Trie root = new Trie(); 
        int n = sc.nextInt();
        
        while (n-- != 0) {
            int op = sc.nextInt();
            
            if (op == 1) {
                String word = sc.next();
                insert(root, word);
            } 
            else if (op == 2) {
                String word = sc.next();
                if (doesExist(root, word)) {
                    System.out.println("Exists");
                } else {
                    System.out.println("Does not exist");
                }
            } 
            else if (op == 3) {
                String ps = sc.next();
                List<String> li = new ArrayList<>();
                getAllWords(root, ps, li);
                for (String s : li) {
                    System.out.println(s);
                }
            } 
            else if (op == 4) {
                List<String> li = new ArrayList<>();
                help(root, li, "");
                for (String s : li) {
                    System.out.println(s);
                }
            } 
            else if (op == 5) {
                String prefix = sc.next();
                int count = countPrefix(root, prefix);
                System.out.println(count);
            }
        }
    }

    static void insert(Trie root, String s) {
        Trie te = root;
        for (char ci : s.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null) {
                te.ch[ind] = new Trie();
            }
            te = te.ch[ind];
            te.wc++;
        }
        te.ended = true;
    }

    static void help(Trie root, List<String> li, String te) {
        if (root.ended) {
            li.add(te);
        }
        for (int i = 0; i < 26; i++) {
            if (root.ch[i] != null) {
                char ch = (char) (i + 'a');
                help(root.ch[i], li, te + ch);
            }
        }
    }

    static boolean doesExist(Trie root, String s) {
        Trie te = root;
        for (char ci : s.toCharArray()) {
            int ind = ci - 'a';
            if (te.ch[ind] == null) {
                return false;
            }
            te = te.ch[ind];
        }
        return te.ended;
    }

    static void getAllWords(Trie root, String prefix, List<String> li) {
        Trie te = root;
        for (char c : prefix.toCharArray()) {
            int ind = c - 'a';
            if (te.ch[ind] == null) {
                return; 
            }
            te = te.ch[ind];
        }
        help(te, li, prefix); 
    }

    static int countPrefix(Trie root, String prefix) {
        Trie te = root;
        for (char c : prefix.toCharArray()) {
            int ind = c - 'a';
            if (te.ch[ind] == null) {
                return 0;
            }
            te = te.ch[ind];
        }
        return te.wc;
    }
}
class Trie {
    Trie ch[];
    int wc;
    boolean ended;

    Trie() {
        ch = new Trie[26];
        wc = 0;
        ended = false;
    }
}
