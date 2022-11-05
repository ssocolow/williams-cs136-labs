/*
 * Recursion.java
 *
 * Starter code for the recursion lab.
 *
 */
import structure5.*;
import java.util.Arrays;

public class Recursion {

  // Note: Warmup questions are not graded, but you may wish to
  // complete & test them since later, graded questions build
  // on them.

  /***** Warmup 0.1 ********************************************/

  /**
   * Takes a non-negative integer and returns the sum of its digits.
   * @param n a non-negative integer
   * @return the sum of n's digits
   * @pre n is a nonnegative integer
   * @post the digit can't be smaller than number itself
   */
  public static int digitSum(int n) {
    Assert.pre(n >= 0, "n cannot be negative.");
    if (n < 10) {
      return n;
    } else {
      int o = n % 10;
      Assert.post(o + n >= n, "digit can't be smaller than number itself.");
      n = n / 10;
      return o + digitSum(n);
    }

  }

  /***** Warmup 0.2 ********************************************/

  /**
   * Given a set of integers and a target number, determines
   * whethere there is a subset of those numbers that sum to the
   * target number.
   *
   * @param setOfNums a set of numbers that may appear in the subset
   * @param targetSum the target value for the subset
   * @return true if some subset of numbers in setOfNums sums to targetSum
   * @pre setOfNums is not null
   * @post setOfNums is the same length afterwards
   */
  public static boolean canMakeSum(int[] setOfNums, int targetSum) {
    Assert.pre(setOfNums != null, "neither arguments can be null.");
    int l = setOfNums.length;
    int[] holderArray = new int[l];
    boolean result = constructSubSet(holderArray, 0, setOfNums, targetSum);
    Assert.post(l == setOfNums.length, "setOfNums should not change length");
    return result;
  }

  //helper function
   /**
   * Given a set of integers and a target number, determines
   * whethere there is a subset of those numbers that sum to the
   * target number.  Also uses other stuff to work
   *
   * @param subset subset of ints
   * @param index keep track of where you are
   * @param ogSet the original array
   * @param targetSum the target sum
   * @return true if some subset of numbers in setOfNums sums to targetSum
   */
  public static boolean constructSubSet(int[] subSet, int index, int[] ogSet, int targetSum) {
    if (index >= ogSet.length) {
      //printIntSet(subSet);
      return (sum(subSet) == targetSum);
    }
    boolean r1 = constructSubSet(subSet.clone(), index + 1, ogSet, targetSum);
    subSet[index] = ogSet[index];
    boolean r2 = constructSubSet(subSet.clone(), index + 1, ogSet, targetSum);

    return (r1 || r2);
  }

  //print an array of integers
   /**
   * Given a set of integers and a target number, determines
   * whethere there is a subset of those numbers that sum to the
   * target number.
   *
   * @param set the array of ints to be printed
   */
  public static void printIntSet(int[] set) {
    System.out.print("[");
    for (int i = 0; i < set.length; i++) {
      System.out.print(set[i] + " ");
    }
    System.out.print("]");
  }
  
  /**
   * Given a set of integers, sum it together.
   * @param set is a set of integers
   * @return sum of the set
   * @pre there should be less than Integer.MAX_VALUE elements in set and the sum shouldn't go above MAX_VALUE or below MIN_VALUE
   * @post the set length is unchanged
   */
  public static int sum(int[] set) {
    int sum = 0;
    int l = set.length;
    for (int i = 0; i < set.length; i++) {
      Assert.pre(sum < Integer.MAX_VALUE  && sum > Integer.MIN_VALUE, "can't wrap around with integers.");
      sum += set[i];
    }
    Assert.post(l == set.length, "set length should not have changed");
    return sum;
  }


  /*****  1  ***************************************************/

  /**
   * Return number of cannoballs in pyramid with the given `height`.
   *
   * @param height the height of the cannonball tower
   * @return the number of cannonballs in the entire tower
   * @pre height is not negative
   * @post height squared is not greater than max value
   */
  public static int countCannonballs(int height) {
    //precondition
    Assert.pre(height >= 0, "height can't be negative");
    //base cases
    if (height == 1) {
      return 1;
    }
    if (height == 0) {
      return 0;
    }
    //postcondition
    Assert.post(height < 46340, "height has to be less than sqrt(Integer.MAX_VALUE");
    //recursive step
    return countCannonballs(height - 1) + height * height;
  }


  /*****  2  ***************************************************/

  /**
   * A method that determines if a string reads the same forwards
   * and backwards.
   *
   * @param str the string to check
   * @return true if `str` is a palindrome.
   * @pre str is a not a null string
   * @post makes the string smaller
   */
  public static boolean isPalindrome(String str) {
    //assert that the string is not null
    Assert.pre(str != null, "string can't be null");
    //base case is if it is the empty string or one character
    if (str.equals("") || str.length() == 1) {
      return true;
    }
    //if the first and last characters match
    if (str.charAt(0) == str.charAt(str.length() - 1)) {
      //make sure the string will be smaller
      Assert.post(str.substring(1, str.length() - 1).length() < str.length(), "string should get smaller");
      //recurse, this time without the first and last characters
      return isPalindrome(str.substring(1, str.length() - 1));
    }
    //return false if it isn't true
    return false;
  }

  /*****  3  ***************************************************/

  /**
   * Checks whether `str` is a string of properly nested and matched
   * parens, brackets, and braces.
   *
   * @param str a string of parens, brackets, and braces
   * @return true if str is properly nested and matched
   * @pre str cannot be null
   * @post str.length() > 0 after checking
   */
  public static boolean isBalanced(String str) {
    //precondition
    Assert.pre(str != null, "string cannot be null");
    //base case
    if (str.equals("")) {
      return true;
    }
    //recursive steps

    if (str.contains("()")) {
      return isBalanced(str.replace("()", ""));
    }
    if (str.contains("[]")) {
      return isBalanced(str.replace("[]", ""));
    }
    if (str.contains("{}")) {
      return isBalanced(str.replace("{}", ""));
    }
    //postcondition
    Assert.post(str.length() > 0, "should only return false if the string has elements and has no closed bracket pairs");
    //another base case
    return false;
  }

  /*****  4  ***************************************************/

  /**
   * A method to print all subsequences of str (order does not matter).
   *
   * @param str string to print all subsequences of
   * @pre string is not null
   * @post str should not be null
   */
  public static void subsequences(String str) {
    //pre
    Assert.pre(str != null, "string cannot be null");
    //setup
    String soFar = "";
    //trigger recursive function
    subsequenceHelper(str, soFar);
    //post
    Assert.post(str != null, "string should not be null after");
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
  protected static void subsequenceHelper(String str, String soFar) {
    //precondition
    Assert.pre(str != null, "string cannot be null");
    //base case
    if (str.equals("")) {
      System.out.print(soFar + ", ");
      return;
    }
    //save the first character and length
    char c = str.charAt(0);
    int s = str.length();
    //get the rest of the string
    str = str.substring(1);
    //recurse
    subsequenceHelper(str, soFar);
    //add character
    soFar += c;
    //recurse
    subsequenceHelper(str, soFar);
    //post
    Assert.post(str.length() < s, "string length should have decreased.");
  }

  
  /*****  5  ***************************************************/

  /**
   * A method to print the binary digits of a number.
   *
   * @param number the number to print in binary
   * @pre number cannot be negative
   * @post number has to be 1 or 0 at final step of recursion
   */
  public static void printInBinary(int number) {
    Assert.pre(number >= 0, "number cannot be negative");
    if (number > 1) {
      printInBinary(number / 2);
      System.out.print(number % 2);
    } else {
      System.out.print(number % 2);
      Assert.post(number == 0 || number == 1, "number must be 0 or 1 when reached");
    }
  }


  /*****  6a  ***************************************************/


  /**
   * Return whether a subset of the numbers in nums add up to sum,
   * and print them out.
   *
   * @param nums array of integers that we will construct subsets from
   * @param targetSum the target integer we want the sum to be
   * @return true if a subset of the numbers in nums add up to sum, false otherwise
   * @pre nums[] is not null
   * @post nums is not empty (i.e. nums.length != 0)
   */
  public static boolean printSubsetSum(int[] nums, int targetSum) {
    // printIntSet(nums);
    // System.out.println("targetSum: " + targetSum);
    //precondition
    Assert.pre(nums != null, "nums cannot be null");
  
    //base case
    if (nums[0] == targetSum) {
      System.out.print(nums[0] + ", ");
      return true;
    }
    //base case
    if (nums.length == 1) {
      if (targetSum - nums[0] == 0) {
        System.out.print(nums[0] + ", ");
        return true;
      }
      return false;
    } else {
      //check if the array without the first number sums to the difference of target sum and the number, so print that number as it will be involved in the sum
      if (canMakeSum(Arrays.copyOfRange(nums, 1, nums.length), targetSum - nums[0])) {
        System.out.print(nums[0] + ", ");
      }
      //postcondition
      Assert.post(nums.length != 0, "nums cannot be empty");
      //recursive step
      return printSubsetSum(Arrays.copyOfRange(nums, 1, nums.length), targetSum - nums[0]) || printSubsetSum(Arrays.copyOfRange(nums, 1, nums.length), targetSum);
    }
  }

  /*****  6b  ***************************************************/

  /**
   * Return the number of different ways elements in nums can be
   * added together to equal sum (you do not need to print them all).
   *
   * @param nums array of integers that we will construct subsets from
   * @param targetSum integer to sum to
   * @return an integer that is the number of subsets of nums that sums to targetSum
   * @pre nums[] is not null
   * @post nums is not empty (i.e. nums.length != 0)
   */
  public static int countSubsetSumSolutions(int[] nums, int targetSum) {
    //precondition
    Assert.pre(nums != null, "nums cannot be null");
    //base case
    if (nums.length == 1) {
      if (targetSum - nums[0] == 0) {
        //System.out.print(nums[0] + ", ");
        return 1;
      }
      return 0;
    } else {
      //base case
      if (nums[0] == targetSum) {
      //System.out.print(nums[0] + ", ");
        return 1 + countSubsetSumSolutions(Arrays.copyOfRange(nums, 1, nums.length), targetSum);
      }
        //postcondition
        Assert.post(nums.length != 0, "nums cannot be empty");
        //recursive step
        return countSubsetSumSolutions(Arrays.copyOfRange(nums, 1, nums.length), targetSum - nums[0]) + countSubsetSumSolutions(Arrays.copyOfRange(nums, 1, nums.length), targetSum);
      }
    }


  /***********************************************************/

  /**
   * Add testing code to main to demonstrate that each of your
   * recursive methods works properly.
   *
   * Think about the so-called corner cases!
   *
   * Remember the informal contract we are making: as long as all
   * predconditions are met, a method should return with all
   * postconditions met.
   */

  protected static void testDigitSum() {
    System.out.println("Testing digitSum: ....");
    System.out.println(digitSum(3));
    System.out.println(digitSum(10));
    System.out.println(digitSum(101));
    System.out.println(digitSum(1234));
    System.out.println(digitSum(0));

  }

  protected static void testCannonballs() {
    System.out.println("Testing cannonballs: ....");
    System.out.println(countCannonballs(3));
    System.out.println(countCannonballs(10));
  }

  protected static void testPalindrome() {
    System.out.println("Testing isPalindrome: ....");
    System.out.println(isPalindrome("mom"));
    System.out.println(isPalindrome("deeded"));
    System.out.println(isPalindrome("ablewasIereIsawelba"));
    System.out.println(isPalindrome("somos"));
  }

  protected static void testBalanced() {
    System.out.println("Testing isBalanced: ....");
    System.out.println(isBalanced("[{[()()]}]"));
    System.out.println(isBalanced("[{[()()]}][{[()()]}]"));
    System.out.println(isBalanced("[{[()()]}{]{[()()]}]"));
  }

  protected static void testSubsequence() {
    System.out.println("Testing subsequences: ....");
    subsequences("abc");
    System.out.println();
    subsequences("CSCI136");
    System.out.println();
    subsequences("a");
    System.out.println();
    subsequences("");
    System.out.println();
  }

  protected static void testBinary() {
    System.out.println("Testing printInBinary: ....");
    printInBinary(0);
    System.out.println();
    printInBinary(30);
    System.out.println();
    printInBinary(1);
    System.out.println();
    printInBinary(110);
    System.out.println();
    printInBinary(2048);
    System.out.println();
    printInBinary(43);
    System.out.println();
      }

  protected static void testCanMakeSum() {
    System.out.println("Testing canMakeSum: ....");
    int[] numSet = {2, 5, 7, 12, 16, 21, 30};
    System.out.println(canMakeSum(numSet, 21));
    System.out.println(canMakeSum(numSet, 22));
    System.out.println(canMakeSum(numSet, 3));
    System.out.println(canMakeSum(numSet, 30));
    System.out.println(canMakeSum(numSet, 14));
    System.out.println(canMakeSum(numSet, 15));
  }

  protected static void testPrintSubsetSum() {
    System.out.println("Testing printSubsetSum: ....");
    int[] numSet = {2, 5, 7, 12, 16, 21, 30};
    System.out.println(printSubsetSum(numSet, 21));
    System.out.println(printSubsetSum(numSet, 22));
    System.out.println(printSubsetSum(numSet, 3));
    System.out.println(printSubsetSum(numSet, 30));
  }

  protected static void testCountSubsetSum() {
    System.out.println("Testing countSubsetSumSolutions: ....");
    int[] numSet = {2, 5, 7, 12, 16, 21, 30};
    int[] test = {100, 50, 50, 25, 25, 25, 25};
    System.out.println(countSubsetSumSolutions(numSet, 21));
    System.out.println(countSubsetSumSolutions(numSet, 22));
    System.out.println(countSubsetSumSolutions(numSet, 3));
    System.out.println(countSubsetSumSolutions(numSet, 30));
    System.out.println(countSubsetSumSolutions(test, 100));
  }

  /**
   * Main method that calls testing methods to verify
   * the functionality of each lab exercise.
   *
   * Please supplement the testing code with additional
   * correctness tests as needed.
   */
  public static void main(String[] args) {
    // int[] test = {1,2,3};
    // printSubsetSum(test, 3);
    testDigitSum();
    testCannonballs();
    testPalindrome();
    testBalanced();
    testSubsequence();
    testBinary();
    testCanMakeSum();
    testPrintSubsetSum();
    testCountSubsetSum();
    // System.out.println(countSubsetSumSolutions(test, 3));
  }
}
