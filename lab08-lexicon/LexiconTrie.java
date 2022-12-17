import structure5.*;
import java.util.Iterator;
import java.util.Scanner;

import structure.FileStream;


public class LexiconTrie implements Lexicon {
    //hold the array for the first letters
    protected LexiconNode firstNode;

    //hold the number of words in the LexiconTrie
    protected int numWords;

    /**
     * default constructor initializes the trie with a root node with character ' '
     */
    public LexiconTrie() {
        firstNode = new LexiconNode(' ', false);
        numWords = 0;
    }

    /**
     * add a word to the trie
     * walk through the trie and only add nodes if you have to
     */
    public boolean addWord(String word) {
        word = word.toLowerCase();
        //check if the lexicon already has the word
        if (containsWord(word)) {
            return false;
        } else {
            //start at root node
            LexiconNode currentNode = firstNode;

            //walk through each character in the word
            for (int i = 0; i < word.length(); i++) {
                //if the place for the character in the current node is null
                if (currentNode.getChild(word.charAt(i)) == null) {
                    //add a new node for that letter
                    currentNode.addChild(new LexiconNode(word.charAt(i), false));
                }
                currentNode = currentNode.getChild(word.charAt(i));
            }
            currentNode.setIsWord(true);
            numWords++;
            return true;
        }
    }

    /**
     * helper method to get 0 to 25 from character
     * @param l character
     * @return int corresponding to l
     * @pre l is a lower case character
     */
    public int numFromChar(char l) {
        Assert.pre((int) l >= 97 && (int) l <= 122, "l has to be a lower case letter");
        return (int) l - 97;
    }
    
    /**
    * This method opens the specified file, expecting a
    * text file containing one word per line, and adds each word
    * to this lexicon. The file must be in the same folder as
    * the executable to be found. The value returned is the count of
    * new words that were added. If the file doesn't exist or was unable
    * to be opened, the function returns 0.
    */
    public int addWordsFromFile(String filename) {
        int count = 0;
        Scanner in = new Scanner(new FileStream(filename));

        while (in.hasNextLine()) {
            String line = in.nextLine();
            line = line.toLowerCase();
            if (!line.equals("")) {
                addWord(line);
            }
            count++;
        }
        return count;
    }
    
    /**
    * This method attempts to remove a specified word from
    * this lexicon. If the word appears in the lexicon, it is removed,
    * and true is returned. If the word was not contained in the lexicon,
    * the lexicon is unchanged and false is returned.  This operation
    * runs in time proportional to the length of the word being removed.
    *
    * Note that you do not need to remove unneeded nodes from the tree
    * (this is an optional extension).  Does not remove unneeded nodes
    */
    public boolean removeWord(String word) {
        LexiconNode n = find(word);
        if (n == null) {
            return false;
        } else {
            n.setIsWord(false);
            //decrement word count
            numWords--;
            return true;
        }
    }

    /**
     * this version does remove the nodes of that word if not used by other words
     */
    public boolean removeWordNodes(String word) {
        LexiconNode n = find(word);
        if (n == null || !n.isWord()) {
            return false;
        }
        StackList<LexiconNode> l = findNodes(word);

        //if it has children then can keep it but make set its isWord to false
        if (n.hasChildren()) {
            n.setIsWord(false);
            return true;
        } else {
            //n doesn't have children so can start deleting nodes above it in path
            //until we hit one that is a word
            //first we delete this node
            int s = l.size();
            LexiconNode first = l.pop();
            l.get().removeChild(first.getChar());
            //decrement word count
            numWords--;

            for (int i = 0; i < s; i++) {
                LexiconNode node = l.pop();
                //if the node has no children and is not a word, remove it
                if (!node.hasChildren() && !node.isWord()) {
                    LexiconNode upperNode = l.get();
                    upperNode.removeChild(node.getChar());
                //else we are done
                } else {
                    return true;
                }
            }
            return true;
        }
    }

    /**
     * returns the number of words in the lexicon trie
     */
    public int numWords() {
        return numWords;
    }

    /**
     * helper method that returns the LexiconNode of the end of that word or null if doesn't exist
     */
    public LexiconNode find(String word) {
        word = word.toLowerCase();
        LexiconNode currentNode = firstNode;

        for (int i = 0; i < word.length(); i++) {
            if (currentNode == null) {
                return null;
            }
            currentNode = currentNode.getChild(word.charAt(i));
        }
        return currentNode;
    }

    /**
     * variation on find that returns a stack of all the nodes visited on the path to the end of word
     * if the word is in the lexicon, otherwise returns null
     */
    public StackList<LexiconNode> findNodes(String word) {
        if (!containsWord(word)) {
            return null;
        }

        StackList<LexiconNode> list = new StackList<>();
        word = word.toLowerCase();
        LexiconNode currentNode = firstNode;

        for (int i = 0; i < word.length(); i++) {
            if (currentNode == null) {
                return null;
            }
            currentNode = currentNode.getChild(word.charAt(i));
            list.push(currentNode);
        }
        return list;
    }

    /**
     * returns true if the word exists in the lexicon (and the isWord is true)
     */
    public boolean containsWord(String word) {
        LexiconNode n = find(word);
        if (n == null || !n.isWord()) {
            return false;
        }
        return true;
    }
    
    /**
     * same as containsWord but doesn't check that the last node has isWord set to true
     */
    public boolean containsPrefix(String prefix) {
        LexiconNode n = find(prefix);
        if (n == null) {
            return false;
        }
        return true;
    }

    /**
     * make an interator over all the strings in the trie
     */
    public Iterator<String> iterator() {
        return new LexiconIterator(this, numWords);
    }

    /**
     * The returned set contains all words in the lexicon that are the same length as the target string
     * and are within the maximum distance
     * is a wrapper method that starts off recursion
     */
    public Set<String> suggestCorrections(String target, int maxDistance) {
        SetVector<String> sv = new SetVector<>();
        StackList<LexiconNode> st = new StackList<>();
        recurseSuggest(target, 0, maxDistance, sv, st, this.firstNode);
        return sv;
    }

    /**
     * recurse helper method to find close matches of same length
     * @param target the string we want to be close to
     * @param dist the current distance (different characters) we are from it
     * @param maxDistance the furthest we want to be
     * @param sv the set vector we will add the words to
     * @param st the stack list of nodes in the path
     * @param n the current node we are looking at
     */
    public void recurseSuggest(String target, int dist, int maxDistance, SetVector<String> sv, StackList<LexiconNode> st, LexiconNode n) {
        //get the nodes iterator
        Iterator<LexiconNode> it = n.iterator();
        //add the node to the stack
        st.add(n);
        //runs for all of the children
        //make sure that the stack is less than the length of the target
        if (st.size() - 1 < target.length()) {
        while (it.hasNext()) {
            //gets the child node
            LexiconNode ne = it.next();
            //if it is a word
            if (ne.isWord()) {
                //check how close it is to target
                int diff = checkDiff(currentString(st, ne), target);
                //if it is within the max distance, can recurse again and if the same length as target, add it
                if (diff <= maxDistance && currentString(st, ne).length() <= target.length()) {
                    if (target.length() == currentString(st, ne).length()) {
                        sv.add(currentString(st, ne));
                    } else {
                        recurseSuggest(target, dist, maxDistance, sv, st, ne);
                    }
                }
            } else {
                //if the node has children recurse
                if (ne.hasChildren()) {
                    //check how close it is to target
                    int diff = checkDiff(currentString(st, ne), target);
                    //if it is within the max distance, can recurse again
                    if (diff <= maxDistance && currentString(st, ne).length() <= target.length()) {
                        recurseSuggest(target, dist, maxDistance, sv, st, ne);
                    }
                }
                //otherwise we are at a leaf that isn't a word
            }
        }
    }
        //we reach here when all the node's children have been recursed through
        //so we pop it off the stack
        st.pop();

    }

    /**
     * returns the difference (in number of spots) two strings differ
     * just from the number of characters in the first string
     */
    public int checkDiff(String currentString, String target) {
        int count = 0;
        for (int i = 0; i < currentString.length(); i++) {
            if (currentString.charAt(i) != target.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * helper method that returns the string from stacklist and node
     */
    public String currentString(StackList<LexiconNode> st, LexiconNode ne) {
        String word = "";
        for (LexiconNode n : st) {
            word = n.getChar() + word;
        }
        //add the character of the last node
        word = word + ne.getChar();
        word = word.strip();
        return word;
    }

    //Optional (extra credit) implementation
    /**
     * return a set of the words in the lexicon that match the regex
     * wraps a recursive method
     */
    public Set<String> matchRegex(String pattern) {
        Vector<String> matches = new Vector<>();
        StackList<LexiconNode> st = new StackList<>();
        LexiconNode n = firstNode;
        recurseRegex(n, st, matches, pattern, false);
        //System.out.println("raw matches: " + matches);
        filterMatches(matches, pattern);
        //then turn matches into a set and return it
        SetVector<String> m = vecToSet(matches);
        return m;
    }

    /**
     * turn vector into a set
     */
    public SetVector<String> vecToSet(Vector<String> v) {
        SetVector<String> sv = new SetVector<>();
        for (String s : v) {
            sv.add(s);
        }
        return sv;
    }
    /**
     * recursively explore the trie and add words that match regex
     */
    public void recurseRegex(LexiconNode n, StackList<LexiconNode> st, Vector<String> matches, String pattern, boolean starred) {
        //get the nodes iterator
        Iterator<LexiconNode> it = n.iterator();
        //add the node to the stack
        st.add(n);
        if (!starred) {
            starred = isStarred(pattern.charAt(0));
        }
        //runs for all of the children
        while (it.hasNext()) {
            //gets the child node
            LexiconNode ne = it.next();
            //if this node matches the regex pattern we want to follow it
            //problem that once it is starred for a*, it will be starred for rest of letters at a's level
            if (starred || match(ne, pattern.charAt(0))) {
                //if it is a word
                if (ne.isWord()) {
                    //and doesn't have children
                    if (!ne.hasChildren()) {
                        //takes the current stack and child and adds that word to the set
                        matches.add(currentString(st, ne));
                    } else {
                        //add the word
                        matches.add(currentString(st, ne));
                        //and recurse if there is more left in the regex
                        if (pattern.length() > 1 || starred) {
                            recurseRegex(ne, st, matches, pattern.substring(1).length() > 0 ? pattern.substring(1) : "?", starred || isStarred(pattern.charAt(0)));
                        }
                    }
                } else if (pattern.length() > 1 || starred) {
                    //if the node has children recurse
                    if (ne.hasChildren()) {
                        recurseRegex(ne, st, matches, pattern.substring(1).length() > 0 ? pattern.substring(1) : "?", starred || isStarred(pattern.charAt(0)));
                    }
                    //otherwise we are at a leaf that isn't a word
                }
            }
        }
        //we reach here when all the node's children have been recursed through
        //so we pop it off the stack
        st.pop();
    }
    /**
     * returns true if it is starred
     */
    public boolean isStarred(char c) {
        return c == '*';
    }

    /**
     * check if the node's character matches the regex character
     */
    public boolean match(LexiconNode n, char c) {
        if (c == '*') {
            return true;
        } else if (c == '?') {
            return true;
        } else {
            return n.getChar() == c;
        }
    }

    /**
     * filters the matches by throwing out those that don't match the regex
     * because some will have been added because of a * that actually have an not matching later character
     * if there is another character in the pattern
     * uses subsequenceHelper to get the power set of the remaining characters when dealing with *
     */
    public void filterMatches(Vector<String> matches, String pattern) {
        //loop through matches
        for (int i = matches.size() - 1; i >= 0; i--) {
            if (!regexMatch(matches.get(i), pattern)) {
                matches.remove(i);
            }
        }
    }

    /**
     * Helper method for subsequences method
     * `soFar` keeps track of the characters currently in the substring
     *   being built
     * @param str the original string
     * @param soFar the built string so far
     * @pre string is not null
     * @post str length is smaller
     */
    protected static void subsequenceHelper(String str, String soFar, Vector<String> vs) {
        //precondition
        Assert.pre(str != null, "string cannot be null");
        //base case
        if (str.equals("")) {
            vs.add(soFar);
            return;
        }
        //save the first character and length
        char c = str.charAt(0);
        int s = str.length();
        //get the rest of the string
        str = str.substring(1);
        //recurse
        subsequenceHelper(str, soFar, vs);
        //add character
        soFar += c;
        //recurse
        subsequenceHelper(str, soFar, vs);
        //post
        Assert.post(str.length() < s, "string length should have decreased.");
    }


    /**
     * returns true if the string matches the regex, false otherwise
     */
    public boolean regexMatch(String s, String regex) {
        //for each character in the regex
        //for (int i = 0; i < regex.length(); i++) {
            //if hit a *
            if (regex.length() > 0) {
                if (regex.charAt(0) == '*') {
                    //loop through the string
                    boolean passed = false;
                    for (int j = 0; j < s.length(); j++) {
                        //test if regexMatch with any of combinations of last parts of the string
                        //characters works with the rest of the regex
                        if (regexMatch(lastNChars(s, j), regex.substring(1, regex.length()))) {
                            passed = true;
                        }
                    }
                    return passed;
                    // Vector<String> vs = new Vector<>();
                    // subsequenceHelper(s.substring(i,s.length()), "", vs);
                    // System.out.println("vs: " + vs);
                    // boolean passed = false;
                    // for (String s1 : vs) {
                    //     if (regexMatch(s1, regex.substring(i+1, regex.length()))) {
                    //         passed = true;
                    //     }
                    // }
                    // return passed;
                //hit a ?
                } else if (regex.charAt(0) == '?') {
                    //return regexMatch of the current string (? is 0 characters) or regexMatch of the current string without the first letter (? is one chatacter)
                    return regexMatch(s, regex.substring(1, regex.length())) || regexMatch(s.substring(1, s.length()), regex.substring(1, regex.length()));
                } else {
                    //otherwise it is a regular character
                    //s.length() == 0
                    if (s.length() == 0) {
                        return false;
                    }
                    if ((regex.length() == 1 && s.length() > 1) || s.charAt(0) != regex.charAt(0)) {
                        return false;
                    }
                    if (s.length() > 1) {
                        return regexMatch(s.substring(1, s.length()), regex.substring(1, regex.length()));
                    } else if (!regex.contains("*") && !regex.contains("?") && regex.length() > s.length()) {
                        return false;
                    }
                    // s.contains(s)
                //}
                }
            }
        // //if the regex length is 0, return false
        // if (!s.equals("")) {
        //     return false;
        // }
        return true;
    }

    /**
     * method to return the last j characters of string s
     */
    public String lastNChars(String s, int j) {
        String ret = "";
        for (int i = 0; i < j + 1; i++) {
            ret = s.charAt(s.length() - 1 - i) + ret;
        }
        return ret;
    }

    /**
     * tester method
     */
    public static void main(String[] args) {
        LexiconTrie lt = new LexiconTrie();
        lt.addWord("hi");
        System.out.println(lt.containsWord("hi"));
        lt.addWordsFromFile("small.txt");
        lt.addWord("fireman");
        lt.addWord("firetruck");
        System.out.println(lt.containsWord("ton"));
        lt.removeWordNodes("firetruck");
        System.out.println(lt.containsWord("firetruck"));
        Iterator<LexiconNode> it = lt.firstNode.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        Iterator<String> iter = lt.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}

/**
 * iterator over words inside the trie
 * assumes that nothing changes the trie during iteration
 */
class LexiconIterator implements Iterator<String> {
    //using these to keep track of state
    protected int nwords;
    protected LexiconTrie lexTrie;
    protected int counter;
    protected Vector<String> wordVec;

    /**
     * construct the iterator
     */
    public LexiconIterator(LexiconTrie l, int n) {
        lexTrie = l;
        nwords = n;
        counter = 0;
        wordVec = new Vector<String>();
        buildWords();
    }

    /**
     * puts all words in lexicon trie into wordVec
     * using a stack, starts the recursion by setting up the entrypoint
     */
    public void buildWords() {
        StackList<LexiconNode> st = new StackList<>();
        buildFrom(lexTrie.firstNode, st);
    }

    /**
     * recursive helper method that goes through each path in the trie
     * if the current node is a word, either:
     * 1) it has no children -> only add the word to wordvec and don't recurse
     * 2) it does have children -> both add the word and recurse
     */
    public void buildFrom(LexiconNode n, StackList<LexiconNode> st) {
        //get the nodes iterator
        Iterator<LexiconNode> it = n.iterator();
        //add the node to the stack
        st.add(n);
        //runs for all of the children
        while (it.hasNext()) {
            //gets the child node
            LexiconNode ne = it.next();
            //if it is a word
            if (ne.isWord()) {
                //and doesn't have children
                if (!ne.hasChildren()) {
                    //takes the current stack and child and adds that word to the wordvec
                    addWordtoVec(st, ne);
                } else {
                    //add the word
                    addWordtoVec(st, ne);
                    //and recurse
                    buildFrom(ne, st);
                }
            } else {
                //if the node has children recurse
                if (ne.hasChildren()) {
                    buildFrom(ne, st);
                }
                //otherwise we are at a leaf that isn't a word
            }
        }
        //we reach here when all the node's children have been recursed through
        //so we pop it off the stack
        st.pop();
    }

    /**
     * adds the word corresponding to the stack of lexicon nodes to the wordvec
     */
    public void addWordtoVec(StackList<LexiconNode> st, LexiconNode ne) {
        String word = "";
        for (LexiconNode n : st) {
            word = n.getChar() + word;
        }
        //add the character of the last node
        word = word + ne.getChar();
        //strip the whitespace
        word = word.strip();
        wordVec.add(word);
    }

    /**
     * returns the next word in the previously built word vector
     */
    public String next() {
        String word = wordVec.get(counter);
        counter++;
        return word;
    }

    /**
     * returns true if there is another word in the word vector, false otherwise
     */
    public boolean hasNext() {
        return counter < nwords ? true : false;
    }

}