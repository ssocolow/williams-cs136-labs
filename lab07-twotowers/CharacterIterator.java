// import structure5.*;

// public class CharacterIterator extends AbstractIterator<Character> {
//         private String s1;
//         private int count;

//         public CharacterIterator(String str) {
//             s1 = str;
//             count = -1;
//         }
//         public Character next() {
//             count++;
//             return s1.charAt(count);
//         }
//         public boolean hasNext() {
//             return count < s1.length() - 1;
//         }
//         public void reset() {
//             count = 0;
//         }
//         public Character get() {
//             return s1.charAt(count);
//         }
//         public static void main(String[] args) {
// 	    CharacterIterator ci = new CharacterIterator("Hello world!");
// 	    for (char c : ci) {
// 		    System.out.println(c);
// 	    }
//     }
// }