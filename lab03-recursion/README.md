<!--
---
layout: page
title: 'Lab 3: Recursion'
---

<style>
  strong {
    font-size: larger;
    font-variant: small-caps;
    font-weight: bold;
  }
  table {
    border: solid 1px grey;
    border-collapse: collapse;
    border-spacing: 0;
  }
  table thead th {
    background-color: grey;
    border: solid 1px grey;
    color: white;
    padding: 10px;
    text-align: left;
  }
  table tbody td {
    border: solid 1px grey;
    color: #333;
    padding: 10px;
    text-shadow: 1px 1px 1px #fff;
  }
  blockquote {
    margin-left: 2em;
    margin-right: 2em;
  }
  .red {
	color: red;
  }
  .blue {
	color: blue;
  }
  hr.style12 {
	height: 6px;
	background: url(../../images/hr-12.png) repeat-x 0 0;
    border: 0;
  }
  b {
	font-weight: 900;
  }
  .center {
	margin: auto;
	width: 100%;
	text-align: center;
  }
</style>
-->
Recursion is a powerful design technique. Because it can be a difficult concept to master, we concentrate on employing it in small programs before using it in larger programs. Therefore, this week's lab is structured as a set of small problems that can be solved separately from one another.

The goals of this lab are:
* to practice writing recursive programs; 
* to solve a variety of interesting algorithmic problems; and
* to train your brain to begin to think recursively.

Recursive solutions can often be formulated in just a few concise, elegant lines.
A small error in a recursive method gets magnified by the many recursive calls, and so an incorrect method can have somewhat surprising behavior.
Rest assured, all of the problems in this lab have short solutions.
Nevertheless, finding that solution may take you some time--don't rush!

<hr style="border-color: purple;" />

## Partner Lab

This is a partner lab.
You may work with <em>one</em> other person of your choosing, or if you are looking for an extra challenge you may work entirely by yourself.
Although you are permitted to jointly develop code with your partner, each of you must independently submit your code.
_No copying of code is permitted._
_You must independently retype any code you develop with your partner._

Indicate your partnering arrangement (including those flying solo) by filling out [the following form](https://forms.gle/UZ9FeTZRkqTKdJfH7).

You would help finding a partner, please indicate so on the form and we will do our best to find you one.

<hr style="border-color: purple;" />

## The Tao of Recursion

Recursive solutions to computational problems always consist of two parts:

1. a "base" case that determines how the program terminates, and
1. a "recursive" case that (a) does some work, and (b) _reduces the problem toward the base case_.

Once you learn to think recursively, recursive solutions to problems seem very intuitive.
Spend some time on these problems and you'll be much better prepared when you are faced with more sophisticated recursive problems.
We will tackle some of these more sophisticated problems later in the semester.

Note that <b>all of your solutions in this lab must be recursive</b>.
Notably, that means that you *must not use* any kind of looping construct, like `for` or `while` loops.
Additionally, you should not need to create any new Java classes to solve these problems, although you may do so if you find them useful.
You must also use the methods *with the given parameters and return types*: if you want to use additional parameters, or a different return type, you must define a helper method.

Finally, your code must use the `Assert` methods from `structure5` that we learned about in class.
Be sure to include at least one pre-condition assertion and at least one post-condition assertion in each method definition.
Pre/post-condition assertions in helper methods are recommended but not required.
Be sure to modify the method's comments to include the pre/post-conditions in plain English.

<hr style="border-color: purple;" />

## PRE-LAB Step 0: Warm-up Problems

Given the structure of this lab, a full design document is not required this week.
However, as always, you should read through the lab carefully and think about how you will structure your solutions.
If possible, sketch a written design for the warm-up problems described below, and bring it to lab on Wednesday.

Brainstorming can be very useful when learning to think recursively.
You can work on the <b>pre-lab warm-up problems</b> before lab with a larger group, even if you do not work with that group during the rest of the lab. 

<hr class="style12" />

## PRE-LAB Warm-up Problem 0.1: Digit Sum

Write a recursive method `digitSum` that takes a non-negative integer and returns the sum of its digits.
For example, `digitSum(1234)` returns `1 + 2 + 3 + 4 = 10`.
Your method should take advantage of the fact that it is easy to break a number into two smaller pieces by dividing by `10` (i.e., `1234/10 = 123` and `1234%10 = 4`).
For these methods, we do not need to define any classes.
Therefore, you can declare them to be `static` methods and call them directly from your `main` method:

    public static int digitSum(int n) { ... }
      
<hr class="style12" />

## PRE-LAB Warm-up Problem 0.2: Subset Sum

<em>Subset sum</em> is an important and classic problem in computer theory.
Given a set of integers and a target number, your goal is to find a subset of those numbers that sum to the target number.
For example, given the set `{3, 7, 1, 8, -3}` and the target sum `4`, the subset `{3, 1}` sums to `4` and therefore the result would be `true`.
On the other hand, if the target sum were `2`, the result would be `false` since there is no subset that sums to `2`.
The prototype for this method is:

    public static boolean canMakeSum(int[] setOfNums, int targetSum)

Assume that the array contains `setOfNums.length` numbers (i.e., it is completely full).
Note that you are not asked to print the subset members, just return `true` or `false`.
You will likely need a helper method to pass additional information through the recursive calls.
What additional information would be useful to track? 

<hr style="border-color: purple;" />

## Lab Programs

For each problem below, you must thoroughly test your code to verify it correctly handles all reasonable cases.
For example, for the "Digit Sum" warm-up, you could write test code to call your method in a loop to allow the user to repeatedly enter numbers that are fed to your method until you are satisfied.
Testing is necessary to be sure you have handled all the different cases.
You can leave your testing code in the file you submit&mdash;there is no need to remove it. 

<hr style="border-color: purple;" />

#### Problem 1: Counting Cannonballs

Spherical objects, such as cannonballs, can be stacked to form a pyramid with one cannonball at the top, sitting on top of a square composed of four cannonballs, sitting on top of a square composed of nine cannonballs, and so forth.
Write a recursive method `countCannonballs` that takes as its argument the `height` of a pyramid of cannonballs and returns the number of cannonballs it contains.
The prototype for the method should be as follows:

    public static int countCannonballs(int height)

<hr class="style12" />

#### Problem 2: Palindromes

Write a recursive method `isPalindrome` that takes a `String` and returns `true` if it is the same when read forwards or backwards.
For example,

	isPalindrome("mom")   → true
	isPalindrome("cat")   → false
	isPalindrome("level") → true

The prototype for the method should be as follows:

    public static boolean isPalindrome(String str)  

You may assume the input string contains no spaces.

Special case: Is the empty string a palindrome? 

<hr class="style12" />

#### Problem 3: Balancing Parentheses

In the syntax of most programming languages, there are characters that occur only in nested pairs, called bracketing operators.
Java, for example, has these bracketing operators:

    ( . . . )
    [ . . . ]
    { . . . }
    

In a properly formed program, these characters will be properly nested and matched.
To determine whether this condition holds for a particular program, you can ignore all the other characters and look simply at the pattern formed by the parentheses, brackets, and braces.
In a legal configuration, all the operators match up correctly, as shown in the following example:

    { ( [ ] ) ( [ ( ) ] ) }
      

The following configurations, however, are illegal for the reasons stated:

	( ( [ ] )      → The line is missing a close parenthesis.
	) (            → The close parenthesis comes before the open parenthesis.
	{ ( } )        → The parentheses and braces are improperly nested.
      
Write a recursive method

    public static boolean isBalanced(String str)
      
that takes a `String str` from which all characters except the bracketing operators have been removed.
The method should return `true` if the bracketing operators in `str` are _balanced_, which means that they are correctly nested and aligned.
If the string is not balanced, the method returns `false`.

Although there are many other ways to implement this operation, you should code your solution so that it embodies the recursive insight that a string consisting only of bracketing characters is balanced if and only if one of the following conditions holds:

    The string is empty.
    The string contains "()", "[]", or "{}" as a substring and is still balanced if you remove that substring.

For example, the string `"[(){}]" `is shown to be balanced by the following chain of calls:

    isBalanced("[(){}]")   →
      isBalanced("[{}]")   →
        isBalanced("[]")   →
          isBalanced("")   → true
    
(_Hint_: Using the above example, can you reason backwards about how the code might be structured?) 

<hr class="style12" />

#### Problem 4: Subsequences

Write a method:

    public static void subsequences(String str)
    
that prints all subsets of the letters in `str`.
The printed string should be a comma-separated list of subsequences.
For example, the string `"ABC"` yields the possible subsequences, `""`, `"A"`, `"B"`, `"C"`, `"AB"`, `"AC"`, `"BC"`, `"ABC"`.
Therefore, the `subsequences` method should print 

	,A,B,C,AB,AC,BC,ABC

The order of the subsequences does not matter.
You may find it useful to write a helper method

    protected static void subsequenceHelper(String str, String soFar)
    
that is initially called with `subsequenceHelper(str, "")`.
The variable `soFar` keeps track of the characters currently in the subsequence you are building.
To process `str` you must:

* build all subsequences containing the first character (which you do by including that character in `soFar`), and
* build all subsequences not including the first character.

Continue until `str` has no more characters in it. 

<hr class="style12" />

#### Problem 5: Print In Binary

Computers represent integers as sequences of bits.
A bit is a single digit in the binary number system and can therefore have only the value 0 or 1.
The table below shows the first few integers represented in binary: 

|binary|decimal|
|------|-------|
|`0`|`0`|
|`1`|`1`|
|`10`|`2`|
|`11`|`3`|
|`100`|`4`|
|`101`|`5`|
|`110`|`6`|

Each entry in the left side of the table is written in its standard binary representation, in which each bit position counts for twice as much as the position to its right.
For instance, you can demonstrate that the binary value `110` represents the decimal number `6` by following this logic:

    place value   → 4   2   1
                    ×   ×   ×
    binary digits → 1   1   0
                    ↓   ↓   ↓ 
                    4 + 2 + 0 = 6

Binary is a _base-2 number system_ instead of the decimal (base-10) system we are familiar with.
Write a recursive method 

    public static String printInBinary(int number)
	
that returns the binary representation for a given integer as a `String`.
For example, calling `printInBinary(3)` would return  `"11"`.
Your method may assume the integer parameter is always non-negative. 

(_Hint_: You can identify the least significant binary digit by using the modulus operator with value `2` (ie., `number % 2`).
For example, given the integer `35`, the value `35 % 2 = 1` tells you that the last binary digit must be `1` (ie., the number is odd), and division by 2 gives you the remaining portion of the integer (`17`).
Remember, to "build up" a string, you can start with the empty string, `""`, and append new strings to it using the `+=` operator.) 

<hr class="style12" />

#### Problem 6: Extending Subset Sum

Write two modified versions of the `canMakeSum` method:

1. Change the method to print the members in a successful subset if one is found.
   Do this without adding any new data structures (i.e. don't build a second array to hold the subset).
   Just use the unwind of the recursive calls.

        public static boolean printSubsetSum(int[] nums, int targetSum)
1. Change the method to report not just whether any such subset exists, but the count of all such possible subsets.
   For example, in the set shown earlier, the subset `{7, -3}` also sums to `4`, so there are two possible subsets for target `4`.
   You do not need to print all of the subsets.

        public static int countSubsetSumSolutions(int[] nums, int targetSum)
    	
<!-- <hr class="style12" /> -->

<!--#### Bonus Problem: Extending Subset Sum Again

Write the method

    public static boolean printSubsetSumBonus(int[] nums, int targetSum)

that prints _all subsets_ that sum to `targetSum`. 

You may earn up to 3 bonus points for this problem, however your total score will not exceed 100%.

<hr class="style12" /> -->

<!-- #### Bonus Problem: Cell Phone Mind Reading

Before smartphones, entering text using the digit keys on a cell phone was problematic.  On a typical telephone keypad there are only 10 digits.  Because 26 letters and 10 numbers needed to be typed with only 10 digit keys, each key is mapped to several characters. Some cell phones required you to "multitap": tap the 2 key once for 'a', twice for 'b' and three times for 'c', and so on.  This gets tedious.

<div class="center">
  <image src="images/telephone-keypad.png" alt="telephone keypad" width="300" />
</div>

Technology like Tegic's "T9 predictive text" allowed the user to press each digit key once and, based on the user's sequence so far, it guessed which letters were intended, having found the possible completions for the sequence. For example, if the user typed the digit sequence "72", there are nine possible mappings (pa, pb, pc, ra, rb, rc, sa, sb, sc). Three of these mappings are promising (pa, ra, sa) because they are prefixes of words such as "party" and "sandwich", while the other mappings can be ignored since they do not correspond to prefixes of known English words. If the user enters "9956", there are 81 possible mappings, but you can be assured the user meant "xylo" since that is the only mapping that is a prefix of any English word.

Implement an algorithm to find the possible completions for a digit sequence. The `listCompletions` method is given the user's digit sequence and a `Lexicon` object. The method will print all English words that can be formed by extending that sequence. For example, here is the list of completions for "72547":

```
palisade
palisaded
palisades
palisading
palish
rakis
rakish
rakishly
rakishness
sakis
```

We provide a `Lexicon` class that serves as a dictionary of English words for you to use. Your recursive method will take the lexicon as a parameter. Here are the important parts of the lexicon interface:

```
        public class Lexicon {
          /**
           * Loads a lexicon from the specified file.
           */
          public Lexicon(String fileName)

          /**
           * Returns true if the word is contained in the lexicon.
           */
          public boolean contains(String word)

          /**
           * This method returns true if any words in the lexicon begin with the
           * specified prefix, false otherwise. A word is defined to be a prefix of
           * itself and the empty string is a prefix of everything.
           */
          public boolean containsPrefix(String prefix)

          ...
       }
```

Some hints to get you started:

* Start by reviewing the `Mnemonics` example. That method lists all possible mnemonics for the input number sequence, and is a very good starting point.
* `listCompletions` consists of two recursive pieces. They require similar, but not identical, code. The first is almost the same as `listMnemonics`, which gives you a way to convert the digit sequence into letters. Here, though, rather than printing each mnemonic found, we want to pass them to the second recursive method, which will extend that prefix in an attempt to build words. In the first case, the choices for the letters are constrained by the digit-to-letter mapping. In the second, what are the possible choices for letters that could be used to extend the sequence to built a completion?
* Be sure to take advantage of the `Lexicon` member function `containsPrefix`. This allows you check whether a sequence of letters is the prefix of any word contained in the lexicon. You will need to use this to avoid going down dead ends.

Your program should first create the lexicon and then pass it, along with a digit sequence to your method:

```
        public static void listCompletions(String digitSequence, Lexicon lex) { ... }

        public static void main(String args[]) {
          ...
          Lexicon lexicon = new Lexicon("lexicon.dat");
          listCompletions("72547", lexicon);
          ...
        }
```

You may earn up to 5 bonus points for this problem, however your total score will not exceed 100%. -->

<hr style="border-color: purple;" />

## Lab Deliverables

Your repository should contain the following files: 

    lab03_recursion-{USERNAMES}/
        Recursion.java
        README.md

The `Recursion.java` file contains starter code, and you should write all of your functions inside that file.
We do not need your `.class` files, as we will compile your code ourselves.
_Be sure that your code compiles before you submit it!_

Do not forget to include `pre` and `post` assertions to check pre- and post-conditions.

<hr style="border-color: purple;" />

## Submitting Your Lab

As you complete various milestones, you should commit your changes and push them.
**Commit early and often.** When the deadline arrives, we will retrieve the latest version of your code.
If you are confident that you are done, please include "Lab Submission" as the commit message for your final commit.
If you later decide that you have more edits to make, it is OK.
We will look at the latest commit before the deadline.

* **Be sure to push your changes to GitLab**
* **Verify your changes on GitLab.** Navigate in your web browser to your private repository on GitLab. It should be available at  `https://evolene.cs.williams.edu/cs136-labs/<YOUR-USERNAME-HERE>/lab03-recursion.git`
* You should see all changes reflected in the various files that you submit.
If not, go back and make sure you committed and pushed.

We will know that the files are yours because they are in _your_ git repository.
Do not include identifying information in the code that you submit!
Our goal is to grade the programs anonymously to avoid any bias.

<hr style="border-color: purple;" />
