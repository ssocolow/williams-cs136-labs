import structure5.*;

public class TwoTowers {
    /**
     * helper method to sum a vector
     * @param test the vector to sum
     * @return the sum of the vector
     */
    public static double sum(Vector<Double> test) {
        double s = 0;
        for (int i = 0; i < test.size(); i++) {
            s += test.get(i);
        }
        return s;
    }
    /**
     * turn square root vector back into original
     * @param v the square rooted vector
     * @return a vector of integers build from the square rooted vector
     */
    public static Vector<Integer> resquare(Vector<Double> v) {
        Vector<Integer> ret = new Vector<>();
        for (Double d : v) {
            ret.add((int)Math.round(d * d));
        }
        return ret;
    }

    /**
     * finds the best and second best subset towers that are less than half the total height but closeset to it
     * @param args the number of blocks in the total set
     * @pre the number of blocks can't exceed the number of bits in a long
     */
    public static void main(String[] args) {
        Assert.pre(Long.parseLong(args[0]) <= 64, "can't exceed the number of bits in a long");
        //make a double vector from the args
        Vector<Double> blocks = new Vector<>();
        for (long i = 1; i <= Long.parseLong(args[0]); i++) {
            blocks.add(Math.sqrt(i));
        }
        //find the target height and print it
        double targetHeight = sum(blocks) / 2; System.out.println("Half height (h/2) is: " + targetHeight);

        //find the best and second best subset
        Vector<Double> bestSubset = new Vector<>();
        Vector<Double> secondBestSubset = new Vector<>();
        //keep track of the best so can ignore it when looking for second best
        long bestSubsetl = 0;
        double bestDiff = 10000000;
        SubsetIterator<Double> tester = new SubsetIterator<>(blocks);
        //use the subset iterator to find the best subset

        for (Vector<Double> i : tester) {
            double h = sum(i);
            if (Math.abs(h - targetHeight) < bestDiff && h < targetHeight) {
                bestDiff = Math.abs(h - targetHeight);
                bestSubset = i;
                bestSubsetl = tester.getCurrentSubset();
            }
        }
        //reset and find second best
        bestDiff = 1000000; tester.reset();
        for (Vector<Double> i : tester) {
            double h = sum(i);
            if (Math.abs(h - targetHeight) < bestDiff && h < targetHeight && tester.getCurrentSubset() != bestSubsetl) {
                bestDiff = Math.abs(h - targetHeight);
                secondBestSubset = i;
            }
        }
        //print out findings and their heights
        System.out.println("The best subset is: " + resquare(bestSubset) + " = " + sum(bestSubset)); System.out.println("The second best subset is: " + resquare(secondBestSubset) + " = " + sum(secondBestSubset));
    }
}