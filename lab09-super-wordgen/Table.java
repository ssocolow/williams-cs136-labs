// Students, please implement this class
import structure5.*;
import java.util.Iterator;
/**
* A Table holds a collection of strings, each of which has an
* associated FrequencyTable
*/
public class Table {

  private Hashtable<String, FrequencyTable> ht;
  /** Construct an empty table */
  public Table() {
    ht = new Hashtable<>();
  }

  /**
  * Updates the table as follows
  * If key already exists in the table, update its FrequencyTable
  * by adding value to it
  * Otherwise, create a FrequencyTable for key and add value to it
  * @param key is the desired k-letter sequence
  * @param value is the character to add to the FrequencyTable of key
  */
  public void add(String key, char value) {
    if (ht.containsKey(key)) {
      ht.get(key).add(value);
    } else {
      FrequencyTable ft = new FrequencyTable();
      ft.add(value);
      ht.put(key, ft);
    }
  }

  /**
  * If key is in the table, return one of the characters from
  * its FrequencyTable with probability equal to its relative frequency
  * Otherwise, determine a reasonable value to return
  * @param key is the k-letter sequence whose frequencies we want to use
  * @return a character selected from the corresponding FrequencyTable
  */
  public char choose(String key) {
    return ht.get(key).choose();
  }

  /** Produce a string representation of the Table
  * @return a String representing this Table
  */
  public String toString() {
    String ret = "";
    //iterate over the keys and add them and their values to the string
    Iterator<String> it = ht.keys();
    while (it.hasNext()) {
      String k = it.next();
      ret += "Key: " + k + ", ";
      FrequencyTable ft = ht.get(k);
      ret += "Value: " + ft.toString();
      ret += "\n";
    }
    return ret;

  }

  /**
   * a main method to test the table class
   */
  public static void main(String[] args) {

  }

}
