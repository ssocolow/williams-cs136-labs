<!-- uncomment for jekyll
---
layout: page
title: 'Lab 4: Linked Lists'
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
</style>
-->

# Lab 4: Linked Lists

The purpose of this week's lab is to give you some hands-on experience writing code that manipulates a linked structure.
Your task is to complete the Laboratory Assignment from <b>Section 9.10</b> of your text (Bailey), entitled _Lists with Dummy Nodes_ (pp. 215&mdash;217).
You will start with code that already works: a class called `LinkedList` that extends the `structure5` class `DoublyLinkedList`.
The goal is to produce a new class that has the same interface as the old class, but with a simpler implementation.
To achieve this, you will add two "dummy" nodes to your `LinkedList`.

The Laboratory Assignment in <b>Section 9.10</b> walks you through the refactoring process.
You will have the best experience if you follow the instructions in that description carefully! 

<hr style="border-color: purple;" />

## PRE-LAB: Step 0

This is a partner lab.
You may work with <em>one</em> other person of your choosing, or if you are looking for an extra challenge you may work entirely by yourself.
Although you are permitted to jointly develop code with your partner, each of you must independently submit your code.
_No copying of code is permitted._
_You must independently retype any code you develop with your partner._

Indicate your partnering arrangement (including those flying solo) by filling out [the following form](https://forms.gle/UZ9FeTZRkqTKdJfH7).

You would help finding a partner, please indicate so on the form and we will do our best to find you one.

<hr style="border-color: purple;" />

## PRE-LAB: Step 1

Before lab, please do the following:

* Read Chapter 9 up to and including 9.5 _Implementation: Doubly Linked Lists_, and bring your questions to lab.
* Study the code in the [`LinkedList.java`](../../code/dummy-starter/LinkedList.java.txt) file before coming to lab, and think carefully about how you might modify the various methods as described in the assignment. A copy of the file will also be provided in your starter repository.

<hr style="border-color: purple;" />

## Lab Assignment

1. Complete Laboratory <b>Assignment 9.10</b>, which begins on p. 215 of Bailey. The starter file `LinkedList.java` will be included in your team's private GitLab repository in addition to a file, `LinkedListTests.java`, that includes tests. The tests are not exhaustive, so please add additional tests as you consider the various edge cases.
1. In the comment block for each method in your `LinkedList` class, provide the running time (in Big-O notation) for each method, along with a brief justification.
<!-- 1. Answer the five Thought Questions on page 217 of your text in a file called `PROBLEMS.md`, and submit it with the rest of your code this week.  -->

<hr class="style12" />

### Big-O and Pre/Post-conditions

As with your previous lab, be sure to provide Javadoc annotations at the top of every method.
We talk more about Javadocs below if you need a refresher.

In addition to `@pre` and `@post` annotations, you should also provide a `@big-o` annotation that states the asymptotic runtime of the method.

For example:

```
  /**
   * A method that randomly prints out the same number over
   * and over.
   * @pre 0 <= low < 10.
   * @post Prints 10 - low numbers.
   * @big-o O(1).
   */
  public static void iSwearItIsRandom(int low) {
    Assert.pre(low >= 0 && low < 10, "low must be between 0 inclusive and 10 exclusive.");
    Random random = new Random(441287210);
    for (int i = low; i < 10; i++) {
      System.out.print(random.nextInt(10) + " ");
    }
  }
```

<hr style="border-color: purple;" />

## Style

As usual, we will be checking for good code style.
We will be checking that you follow the same style guidelines we've introduced in the past.
This section describes some additional guidelines that we'd like you to follow.

<hr class="style12" />

### Requirement: `checkstyle`

This week, we are expanding `checkstyle` to encourage you to write modular and reusable code.

<!-- Let's first motivate that decision, and then describe
the new `checkstyle` rule. -->

<hr class="style12" />

#### Rule: Don't Repeat Yourself (DRY)

As a programmer, you should never type the same code over and over again.
That would be a waste of your time.
The ability to copy/paste useful code makes this tempting, but it is *a bad practice*!
When you copy code, you copy errors.
If you ever fix those errors, you have to remember to fix them in every location.
This "fixing" rarely happens.
Trust us.
We've been there.
We're not really any smarter than you, we've just made all the mistakes already.

So what should you do instead?
Write a helper method!
Whenever you find yourself writing the same code more than once (it could be a common loop, searching a data structure, computing an equation) you should refactor that code into a method.
Then you can call that method many times, but only ever have to fix bugs in one place.

Similarly, if you find yourselves writing a function that is very long, that is problematic.
The long function can probably be broken into smaller parts that can then be composed to solve a larger problem.
As a bonus, you can now reuse those parts to solve other problems.

Breaking long functions into byte-size chunks also has the benefit of making your code easier to read and debug.
By isolating functionality inside independent units, you can convince yourself that each of those units is correct in isolation.
Then, to reason about the entire program, you only need to convince yourself that the combination of those correct units is correct.

<hr class="style12" />

#### What checkstyle looks for

Since `checkstyle` does not directly check for copy/pasted code, instead we encourage concise methods.

The `checkstyle` tool will report an `[ERROR]` if your program has a method that is more than 40 lines long (excluding whitespace and single-line comments).
This new rule is in addition to previous style requirements.

In total, checkstyle will enforce the following guidelines:
 * All class variables that are not `final` must be declared `private` or `protected` (i.e., no `public` member variables unless they are constants).
 * All `public` methods must include a "Javadoc" comment (starts with `/**` and ends with `*/`; it should include descriptions of the function, the arguments, and any pre/post conditions).
 * No method should be longer than 40 lines (exluding whitespace and single-line comments).

To run `checkstyle`, type the following command at the terminal:
```
$ ./checkstyle
```

The `./` is peculiar to Unix: it tells the terminal to look for the `checkstyle` program in the current directory.
This command runs `checkstyle` on every Java program in your directory.
To run `checkstyle` on a specific Java file, type:
```
$ ./checkstyle SomeFile.java
```

<hr class="style12" />

### Requirement: `javac`

In addition to `checkstyle`, we will continue to enforce proper use of Java generics.
  * Compiling your code with `javac` must not produce the following message:
```
    Note: YourProgram.java uses unchecked or unsafe operations.
    Note: Recompile with -Xlint:unchecked for details.
```

This message tells you how to get more information.
Recompile your program as follows (replace with the appropriate file name):
```
$ javac -Xlint:unchecked YourProgram.java 
```
The output will give you details about the issue.

To fix this problem, make sure that you specify type parameters for every generic class, both when *declaring* the variable's type and when *instantiating* an object.
For example, to create a `Vector` that stores `Integer` objects, one would type:

```
    Vector<Integer> intVec = new Vector<Integer>();
```

<hr style="border-color: purple;" />


## Lab Deliverables

By the start of lab, you should see a new private repository called `lab04-dummylists` in your GitLab account.

For this lab, please submit the following: 

    lab04-dummylists/
        README.md
        LinkedList.java
        TestLinkedList.java

The `LinkedList.java` file contains starter code, and you should write all of your functions inside that file.
The `TestLinkedList.java` file contains a convenient `main` method pre-populated with a variety of helpful tests that should help you get started.

As with all labs, you will be graded on design, documentation, style, and correctness.
Be sure to document your program appropriately:
include pre/post conditions and assertions where appropriate. We will also be looking at how well you organize your code.
Whenever you see yourself duplicating functionality, consider moving that code to a helper method.
There are several opportunities in this lab to simplify your code by using helper methods.

<hr style="border-color: purple;" />

## Submitting Your Lab

As you complete portions of this lab, you should `commit` your changes and `push` them.
<u>Commit early and often.</u>
When the deadline arrives, we will retrieve the latest version of your code.
If you are confident that you are done, please use the phrase `"Lab Submission"` as the commit message for your final commit.
If you later decide that you have more edits to make, it is OK.
We will look at the latest commit before the deadline.

* <u>Be sure to push your changes to GitLab</u>.
* <u>Verify your changes on GitLab.</u> Navigate in your web browser to your private repository on GitLab. It should be available at [https://evolene.cs.williams.edu/cs136-labs/[your username]/lab04-dummylists.git](https://evolene.cs.williams.edu/cs136-labs/[your username]/lab04-dummylists.git) You should see all changes reflected in the files that you `push`. If not, go back and make sure you have both committed and pushed.

We will know that the files are yours because they are in _your_ `git` repository.
<u>Do not include identifying information in the code that you submit.</u>
We grade your lab programs anonymously to avoid bias.

<hr style="border-color: purple;" />

<!-- ## The squeaky wheel gets the grease

We are always looking to make our labs better.  Please submit answers to the following questions using the [anonymous feedback form](http://barowy.net/cs136s20/) for this class:

1. How difficult was this assignment on a scale from 1 to 5 (1 = super easy, ..., 5 = super hard).
1. Did you take advantage of any TA help hours or professor office hours?
1. If you did go, was the assistance helpful?  If you did not go, why not?
1. What part of this assignment did you like best?  Why?
1. Is there _one question_ about computers / computer science that you wish you had the answer to?  What is that question?

<hr style="border-color: purple;" />

## Bonus: Functional Linked List

An alternative way to implement a linked list is in the _functional style_ associated with the LISP programming language.  For the bonus, implement this functional list.  You may earn up to 10 bonus points for this problem, however your total score will not exceed 100%.

A functional list has an elegant, recursive definition.  A list is either:

* `null`, or
* a `ListNode<E>`, where `E` is a generic type.

The _head_ of a functional list is the _first element_ (which has type `E`).

The _rest_ of a functional list is the _entire list after the element that contains the head_ (which has type `ListNode<E>`).

Your `ListNode<E>` class should have two instance variables:

1. An instance variable of type `E` called `data`.
1. An instance variable of type `ListNode<E>` called `next`.

Both of these variables should be declared `final`.  Note that the fact that both variables are `final` will influence your constructor design.

A functional list is a _singly linked list_ where the `next` variable of each `ListNode<E>` points to the next `ListNode<E>` in the list.  The last `ListNode<E>` should point to `null`.

A `ListNode<E>` also has the following methods:

1. `public static <E> ListNode<E> prepend(E e, ListNode<E> ls)`

    which prepends a new `ListNode<E>` to the list `ls` and returns the new node.  Note that `prepend` should _not_ make new copies of any of the `ListNode<E>` elements.  Furthermore, it should be possible for the user to create a new list by calling `prepend` with `ls` set to `null`.
1. `public static <E> E head(ListNode<E> ls)`

    which returns the _first element_ (of type `E`) in the list.
1. `public static <E> ListNode<E> rest(ListNode<E> ls)`

    which returns the _entire list after the element that contains the head_ (of type `ListNode<E>`).
1. `public String toString()`

    which returns a `String` representation of the list.  This representation should be of the form
    `[ e1, e2, ..., en ]`
    In other words, the returned `String` should begin and end with square bracket characters, and each element should be separated by a comma and a space.  You should call `toString()` on each data element (of type `E`) in the list to get its own `String` representation.
1. `public static <E> int length(ListNode<E> ls)`

    which returns an `int` representing the number of `ListNode<E>`s in the list.
1. `public static <E> ListNode<E> reverse(ListNode<E> ls)`

    which returns a new list with all of the elements of the list in reverse order.  This method will need to make copies of the `ListNode<E>` elements.
1. Finally, provide a `main` method that
    * Reads in a text file specified by the user,
    * adds each word to a list, one at a time,
    * reverses the list,
    * and then prints the list back out.

    After compiling, you should be able to run this program with a text file like so:

    `ListNode < file.txt`

Be sure to commit your implementation to your repository in a file called `ListNode.java`.

<hr style="border-color: purple;" /> -->

<!-- ## Bonus: Mistakes

Did you find any mistakes in this writeup?  If so, add a file called `MISTAKES.md` to your GitLab repository and describe the mistakes using bullets.  For example, you might write

    * Where it says "bypass the auxiliary sensor" you should have written "bypass the primary sensor".
    * You spelled "college" wrong ("collej").
    * A quadrilateral has four edges, not "too many to count" as you state.

You will receive 1 bonus point on this assignment for each mistake we are able to validate. -->
