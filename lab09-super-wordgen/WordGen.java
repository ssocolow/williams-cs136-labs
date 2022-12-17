// Students, please implement this class
import structure5.*;
import java.util.Scanner;
/**
 * class to run the main method of the super word gen program
 */
public class WordGen {
    public static final int OUTPUT_LENGTH = 500;

    /**
     * processes the text by sliding the window and adding the frequencies to the table
     * @param text the input corpus
     * @param k the level of depth (the width of sliding window)
     * @param table (hashtable relating Strings to Frequency table)
     */
    public static void learnFromText(String text, int k, Table table) {
        text += text.substring(0, k);
        for (int i = 0; i < text.length() - k; i++) {
            String input = text.substring(i, i + k);
            char next = text.charAt(i + k);
            //System.out.println(input + ", " + next);
            table.add(input, next);
        }
    }

    /**
     * generate OUTPUT_LENGTH characters of text and print it out
     */
    public static void genText(String seed, Table table, int k) {
        String ret = seed;
        for (int i = 0; i < OUTPUT_LENGTH; i++) {
            char nextChar = table.choose(ret.substring(i, i + k));
            ret += nextChar;
        }
        System.out.println(ret);
    }

    /**
     * Main method that prints out 500 more characters based on input text
     * @param args k (what level) and the input text file
     */
    public static void main(String[] args) {
        int k;
        //get the arg value
        try {
            k = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Usage: java WordGen <k> < <input.txt>");
            System.exit(1);
            //stop vscode complaining
            k = 0;
        }
        Scanner in = new Scanner(System.in);
        StringBuffer textBuffer = new StringBuffer();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            textBuffer.append(line);
            textBuffer.append("\n");
        }
        String text = textBuffer.toString();
        // 'text' now contains the full contents of the input.

        Table table = new Table();
        //learn from the text
        learnFromText(text, k, table);
        //System.out.println(table);
        //generate new text
        genText(text.substring(0, k), table, k);
    }
}
