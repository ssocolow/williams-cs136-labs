import structure5.AbstractIterator;
import structure5.Vector;
import structure5.Assert;

public class SubsetIterator<E> extends AbstractIterator<Vector<E>> {
    //hold the current subset represented in bits
    private long currentSubset;
    //hold the vector with all elements
    private Vector<E> vec;

    /**
     * builts the subsetiterator
     * @param v the vector of objects to store and generate subsets from
     */
    public SubsetIterator(Vector<E> v) {
        vec = v;
    }

    /**
     * reset the iterator back to the first subset
     */
    public void reset() {
        currentSubset = 0;
    }

    /**
     * test if there is a next element
     * @return true if there is next element, false otherwise
     */
    public boolean hasNext() {
        return (Math.pow(2, vec.size())) > currentSubset;
    }

    /**
     * gets the current subset and returns it
     * @return current subset
     */
    public Vector<E> get() {
        Vector<E> ret = new Vector<>();
        for (int i = 0; i < vec.size(); i++) {
            if ((currentSubset & (1L << i)) == (1L << i)) {
                ret.add(vec.elementAt(i));
            }
        }
        return ret;
    }

    /**
     * @return current subset
     * then advances to the next subset
     * @pre has to have a next element
     */
    public Vector<E> next() {
        Assert.pre(hasNext(), "has to have next element");
        Vector<E> ret = get();
        currentSubset++;
        return ret;
    }

    /**
     * @return representation of the current subset
     */
    public long getCurrentSubset() {
        return currentSubset;
    }

    /**
     * tests the subsetiterator by printing all subsets in [0, 1, 2, 3, 4, 5, 6, 7]
     * @param args
     */
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();
        v.add(0);
        v.add(1);
        v.add(2);
        v.add(3);
        v.add(4);
        v.add(5);
        v.add(6);
        v.add(7);

        SubsetIterator<Integer> hi = new SubsetIterator<Integer>(v);
        for (Vector<Integer> i : hi) {
            System.out.println(i);
        }
    }
}
