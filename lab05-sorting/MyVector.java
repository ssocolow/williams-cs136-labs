//imports
import structure5.*;
import java.util.Comparator;

public class MyVector<E> extends Vector<E> {
    /**
     * constructor sets up the vector in MyVector
     */
    public MyVector() {
        super();
    }

    /**
     * sorts the vector with a comparator
     * uses insertion sort
     * @pre comparator is not null
     */
    public void sort(Comparator<E> c) {
        Assert.pre(c != null, "comparator can't be null");
        int numSorted = 1;
        int index;
        while (numSorted < this.size()) {
            E temp = this.get(numSorted);
            for (index = numSorted; index > 0; index--) {
                if (c.compare(temp, this.get(index - 1)) < 0) {
                    this.set(index, this.get(index - 1));
                } else {
                    break;
                }
            }
            //reinsert value
            this.set(index, temp);
            numSorted++;
        }
    }
}
