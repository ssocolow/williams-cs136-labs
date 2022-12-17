import structure5.*;
import java.util.Iterator;

/**
 * Class for nodes in the lexicon trie
*/
class LexiconNode implements Comparable<LexiconNode> {

    /* single letter stored in this node */
    protected char letter;

    /* true if this node ends some path that defines a valid word */
    protected boolean isWord;

    /* a data structure for keeping track of the children */
    protected LexiconNode[] array;

    /**
    * make a new lexiconnode
    * letters are all lower case
    * allows a space for the root node
    */
    LexiconNode(char letter, boolean isWord) {
        if (letter == ' ') {
            this.letter = ' ';
        } else {
            this.letter = Character.toLowerCase(letter);
        }

        this.isWord = isWord;
        array = new LexiconNode[26];
    }

    /**
    * Compare this LexiconNode to another.
    * compare the characters stored at the Lexicon nodes
    */
    public int compareTo(LexiconNode o) {
        return Character.compare(this.letter, o.letter);
    }

    /**
    * puts a lexicon node in this node's array depending on its letter
    */
    public void addChild(LexiconNode ln) {
        this.array[numFromChar(ln.letter)] = ln;
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
    * returns the node in this node's reference array that has the input character
    */
    public LexiconNode getChild(char ch) {
        return this.array[numFromChar(ch)];
    }

    /**
    * Removes LexiconNode child for 'ch' from child data structure
    */
    public void removeChild(char ch) {
        this.array[numFromChar(ch)] = null;
    }

    /**
     * isWord setter
     * @return void
     */
    public void setIsWord(boolean b) {
        isWord = b;
    }

    /**
     * helper method that checks if the node has no children
     * @return true if it has children, false otherwise
     */
    public boolean hasChildren() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                return true;
            }
        }
        return false;
    }

    /**
    * returns an iterator to go over this node's children
    */
    public Iterator<LexiconNode> iterator() {
        return new LexiconNodeIterator(array);
    }

    public LexiconNode[] getChildren() {
        return this.array;
    }

    public char getChar() {
        return this.letter;
    }

    public boolean isWord() {
        return isWord;
    }
}

/**
 * class for iterator over nodes inside of this node
 */
class LexiconNodeIterator implements Iterator<LexiconNode> {
    //instance variables
    protected LexiconNode[] arr;
    protected int counter;

    /*
     * constructs an iterator by refering the array and setting the counter to 0
     */
    public LexiconNodeIterator(LexiconNode[] arr) {
        this.arr = arr;
        counter = 0;
    }

    /**
     * iterates over the elements in the array, skipping null elements
     */
    public LexiconNode next() {
        LexiconNode n = arr[counter];
        while (n == null) {
            counter++;
            n = arr[counter];
        }
        counter++;
        return n;
    }

    /**
     * returns true if there are non null elemnent further in the array
     */
    public boolean hasNext() {
        if (counter >= arr.length || isEmptyFrom(counter)) {
            return false;
        }
        return true;
    }
    /**
     * determines if array is empty from the current counter onward
     */
    public boolean isEmptyFrom(int c) {
        for (int i = c; i < arr.length; i++) {
            if (arr[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * sets the counter back to 0
     */
    public void reset() {
        counter = 0;
    }
}