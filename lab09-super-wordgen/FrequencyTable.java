// Students, please implement this class

import java.util.Iterator;
import java.util.Random;
import structure5.*;

/**
 * A FrequencyTable stores a set of characters each of which has
 * an associated integer frequency
 */
public class FrequencyTable {
  private Hashtable<Character, Integer> ht;

  /** Construct an empty FrequencyTable */
  public FrequencyTable() {
    ht = new Hashtable<>();
  }

  /** add(char ch)
   * If ch is in the FrequencyTable, increment its associated frequency
   * Otherwise add ch to FrequencyTable with frequency 1
   * @param ch is the String to add to the FrequencyTable
   */
  public void add(char ch) {
    if (ht.containsKey(ch)) {
      ht.put(ch, ht.get(ch) + 1);
    } else {
      ht.put(ch, 1);
    }
  }

  /** Selects a character from this FrequencyTable with probabality equal to its relative frequency.
   * @return a character from the FrequencyTable
   */
  public char choose() {
    char[] cs = new char[ht.size()];
    Iterator<Character> it = ht.keys();
    int i = 0;
    while (it.hasNext()) {
      cs[i] = it.next();
      i++;
    }
    int sum = 0;
    for (int j = 0; j < cs.length; j++) {
      sum += ht.get(cs[j]);
    }
    double[] probs = new double[ht.size()];
    for (int j = 0; j < ht.size(); j++) {
      probs[j] = (double)ht.get(cs[j]) / (double)sum;
    }
    int index = 0;
    double r = Math.random();
    while (r > 0) {
      r = r - probs[index];
      index = index + 1;
    }
    index = index - 1;
    return cs[index];
  }

  /** Produce a string representation of the FrequencyTable
   * @return a String representing the FrequencyTable
   */
  public String toString() {
    String ret = "";
    Iterator<Character> it = ht.keys();
    while (it.hasNext()) {
      char c = it.next();
      ret += "K: " + c + " - ";
      int val = ht.get(c);
      ret += "V: " + val + ", ";
    }
    return ret;
  }

  /**
   * a main method to test the frequency table class
   */
  public static void main(String[] args) {

  }

}
