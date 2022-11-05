<!--
---
layout: page
title: 'Lab 5: Sorting'
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

# Lab 5: Sorting

The goals of this week's lab are to gain experience with Java's `Comparator` interface, used
* for sorting objects of any kind, and
* for sorting objects by arbitrary criteria.

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

## PRE-LAB: Step 0

Before lab, please do the following:

* <b>Design document</b>: As in prior labs, you should develop a design document before coming to lab. 
* <b>Read the assigned pages from Chapter 6</b> (see the [Readings page](https://williams-cs.github.io/cs136-f22-www/readings.html)), and bring your questions to class.

<hr style="border-color: purple;" />

## Lab Assignment

A `Comparator` object's sole purpose is to compare two objects of a given type.
All we need is a comparison sorting algorithm modified to take a comparator.
`Comparator`s also have a desirable property, which is that we do not need to modify the class of the data being sorted in order to sort it.
This property is especially useful when the definitions of the objects we want to sort are not within our control.

For more information, see _Bailey_ Chapter 6.8&mdash;6.9 for a discussion of `Comparator`s and how to use them.

To get started, we will develop an extension of `Vector`, called `MyVector`, that includes a method called `sort`.
This `sort` method should order the elements of the `Vector` with the help of a `Comparator`.

Below are the basic steps you'll need to follow to complete the assignment using `Comparator`s.
You will need to implement multiple `Comparator` classes to finish this assignment.

<hr class="style12" />

#### Step 1: Get the code

Clone your private repository `lab05-sorting`.
This repository contains starter files for `MyVector.java` and `Student.java`, plus the data files `newphonebook.txt` and `testphonebook.txt`.
The phone book is a few years old, so don't expect to find yourself in it (but if you look carefully you might find a member of the CS faculty).
Also, this file is not for public distribution.
`testphonebook.txt` is a smaller phone book to make testing easier during development.

<hr class="style12" />

#### Step 2: Implement `MyVector.java`

Fill in the class, `MyVector`, which is declared to be an extension of the `structure5` `Vector` class. [[footnote: 1](#footnote1)]
Since we are using generic structures, the class declaration for `MyVector` should begin:

    public class MyVector<E> extends Vector<E>
		
You should write a default constructor for this class that simply calls `super();`.
This will force the constructor in `Vector` (the parent/super-class of `MyVector`) to be called.
This, in turn, will initialize the private and protected fields of the parent class. 

<hr class="style12" />

#### Step 3: Implement `sort`

Inside `MyVector.java`, construct a new method called `sort`.
It should have the following declaration:

    // pre: c is a valid comparator
    // post: sort this vector in the order determined by c
    public void sort(Comparator<E> c)


`Comparator` is a Java interface, which, essentially, looks like:

    public interface Comparator<E> {
        /*
         * Returns:  < 0  if a is smaller than b
         *             0  if a equals b
         *           > 0  if a is larger than b
         */
        int compare(E a, E b);
    }

You should develop some classes that implement the `Comparator` interface, one for each of the "phone book questions" printed below.
`Comparator` is parameterized by the type `E` of object that it compares.
In the case of the `sort` method, the type of `c` is `Comparator<E>`&mdash;that is, `c` must implement a comparator for the type of data (`E`) stored in the vector.
This sort method then uses the `Comparator` object `c` to perform comparisons of the values in `MyVector`.
You may use any sorting algorithm you wish in your `sort` implementation, although it may be best to start with a simple algorithm like insertion sort.
Our course textbook includes a number of `sort` implementations that you can borrow and modify.
If you want to try a more exotic sort method, like quicksort, we suggest that you be more ambitious once you have a solution to the basic assignment working OK.

When writing new comparators, you will specify what type they are defined for.
For example, we would define a `CardComparator` class to compare `Card` objects as follows:

    import java.util.Comparator;

    public class CardComparator implements Comparator<Card> {
        ...
    }

The `compare` method in that class would then take two `Card` objects as parameters.

Be sure to test `MyVector` thoroughly before going on to the next part.
`MyVector` inherits a `toString()` method from `Vector`, which should be handy for printing out the contents of your vectors during testing. 

<hr class="style12" />

#### Step 4: Reading the Phone Book

Now, write a program that reads Williams College phone data into a `MyVector` and answers some questions by sorting it with the appropriate `Comparator` applied.
The file `newphonebook.txt` contains student entries, represented by three lines, and separated by a line of dashes:

    Iluv C Science
    Poker Flats B5
    4135973427 3334 5406394821
    -----------------
    Jeannie Albrecht
    Thompson Chemistry Lab 304
    4135974251 1234 4134581234
    -----------------
	...

The first line is the name of the student, the second is their campus address, and the third contains the campus telephone number, SU box number, and home (or cell) phone number.
You will need to create a `Student` class to represent all the information for a single student.

Once you have your `Student` class working, you will write code to read in the data file of student information and create a `MyVector` of `Student` objects.
Your code should then perform whatever operations are necessary to answer the questions in step 5 below. [[footnote: 2]](#footnote2)

Note: you should read in phone numbers as `long` data (using the `Scanner`'s `nextLong()` method) rather than `int` data, because integer variables cannot store numbers much greater than 2 billion due to their internal representation.

<hr class="style12" />

#### Step 5: Answer Phone Book Questions

 Your program should print out answers to the following questions. Include the answers to these questions in your PROBLEMS.md file.

1. Which student appears first in a printed phone book if names are printed as they appear in the data file (i.e., first name first)?
1. Which student has the smallest SU box number? The largest?
1. Which student has the greatest number of vowels in their full name? You may ignore "y"s when counting vowels.
1. Which address is shared by the most students, and what are their names?  (Please list both the address, and the names of the students at that address.) You may find it useful to build a second vector that stores an `Association` between an address and the number of students living there. A special `Comparator` can then be used to sort that vector by comparing the number of students at each address. Once the most common address is known, you can consult the original vector of students to print out the people living at that address.

    Treat dorm <b>rooms</b> as unique addresses. For example, if two students shared a double in Morgan 13, they would have one address; however, those students would <b>not</b> share an address with any student living in Morgan 17.

    Note that some students have the address `UNKNOWN` because they are abroad, on leave, etc. These students should be ignored for this question. Other student entries with strange formatting should also be ignored (but please let your instructor know if you find any malformed entries).

1. What are the ten most common area codes for student home phone numbers?  Please print all ten area codes in decreasing order, along with the number of students who have a phone number with that area code. A phone number of `-1` indicates that information is not available. For this question, you should disregard students without a known home phone number.

<hr class="style12" />

### Optional: Simplifying Code with Lambda Expressions

As you are performing the above tasks, you may notice that implementing `Comparator` classes is cumbersome.
Writing a simple comparison method requires a great deal of notation to name the class, tell Java that it implements `Comparator`, create an object of that class, and so on.  

With this in mind, Java 8 (and later versions) have a more concise way to specify a comparator: *lambda expressions.* 
To use a lambda expression, we pass in a compact representation of the method itself, rather than the entire comparator.
In general, lambda expressions simplify notation in cases where we would otherwise need to create an object just to write a single method.

For example, let's say we wanted to sort a vector storing objects of type `Integer`.
Normally we would write a class called, say, `CompareInteger` that implements `Comparator<Integer>`.
Its compare method might look something like:

```
public int compare(Integer i1, Integer i2) {
	return i1 - i2;
}
```

Then, to sort, we might write something like:

```
CompareInteger compInt = new CompareInteger();
sort(vec, compInt);
```

Using a lambda expression, you would not create any class type at all, nor would you create any objects.
Instead, you pass in the method you want to use directly, as follows:

```
sort(vec, (Integer i1, Integer i2) -> {
            int x = Integer.valueOf(i1);
            int y = Integer.valueOf(i2);
            return x - y;
          }
);
```

That's a lot shorter!  (And it works great.)  

[But](But) in fact, it's still redundant: Java knows that the type of `i1` and `i2` are `Integer` since they must always match the type of `Vector`.
We can also take advantage of *unboxing* to just return `i1 - i2` rather than casting to type `int` explicitly; the method could just `return i1 - i2`.
Furthermore, if there's only one line in the method being passed, it's logical to assume it must be a return method, so that is also unnecessary, as are the curly braces and the final semicolon.
Java allows us to skip all of this, allowing us to sort a vector of `Integer`s with the following call:

```
sort(vec, (i1, i2) -> i1 - i2);
```

Pretty cool!
You are free to use lambda expressions in your lab rather than creating new classes if you find that it is easier to work with.

One thing to be aware of: you still don't want any lines of code to extend past 80 or so columns.
If your sort calls wind up getting long, remember to put in line breaks so that they are still readable.

<hr class="style12" />

## Lab Deliverables

By the start of lab, you should see a new private repository called `lab05-sorting` in your GitLab account.

For this lab, please submit the following: 

    cs136lab05_sorting-{USERNAMES}/
        README.md
        MyVector.java
        Student.java
        newphonebook.txt
        testphonebook.txt

Please also remember to submit the `.java` files for any additional comparator classes you write.

The `MyVector.java` and `Student.java` files contain starter code.
Recall in the previous lab that you had a `TestLinkedList.java` file that contained a convenient `main` method pre-populated with a variety of helpful tests.
It is always a good practice to create a small set of tests to facilitate development, and you are encouraged to do so here.

As with all labs, you will be graded on design, documentation, style, and correctness.
Be sure to document your program appropriately: include pre/post conditions and assertions where appropriate.
We will also be looking at how well you organize your code.
Whenever you see yourself duplicating functionality, consider moving that code to a helper method.
There are several opportunities in this lab to simplify your code by using helper methods.
Think carefully! 

<hr style="border-color: purple;" />

## Submitting Your Lab

As you complete portions of this lab, you should `commit` your changes and `push` them. <u>Commit early and often.</u> When the deadline arrives, we will retrieve the latest version of your code. If you are confident that you are done, please use the phrase `"Lab Submission"` as the commit message for your final commit. If you later decide that you have more edits to make, it is OK. We will look at the latest commit before the deadline.

* <u>Be sure to push your changes to GitLab</u>.
* <u>Verify your changes on GitLab.</u> Navigate in your web browser to your private repository on GitLab. It should be available at [https://evolene.cs.williams.edu/cs136-labs/[your username]/lab05-sorting.git](https://evolene.cs.williams.edu/cs136-labs/[your username]/lab05-sorting.git). You should see all changes reflected in the files that you `push`. If not, go back and make sure you have both committed and pushed.

We will know that the files are yours because they are in _your_ `git` repository. <u>Do not include identifying information in the code that you submit.</u> We grade your lab programs anonymously to avoid bias.

<hr style="border-color: purple;" />

 Lab notes:

<a id="footnote1">[1] Avoid the statement `import java.util.*` for this assignment because `java.util` also provides a `Vector` class. This lab uses the `Vector` class from `structure5`.  Instead, write `import java.util.Comparator` to import just `Comparator`.</a>

<a id="footnote2">[2] While you can write all of your code in the `main` method of your `Student` class, this is not a good solution because `Comparator`s allow us to avoid modifying `Student`, which is a good thing.  Instead, create a new class that will be responsible for reading in the data and performing each operation.</a>
