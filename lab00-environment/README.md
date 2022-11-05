<!-- Originally a warmup lab written by Bill Jannen and Dan Barowy in spring of 2020.
     Extensively updated in fall of 2020 by Bill Jannen to include platform-specific installation and configuration steps.
     Updated with VSCode and other copyedits by Dan Barowy in 2022.
-->

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
        background: url(https://williams-cs.s3.amazonaws.com/common/hr-12.png) repeat-x 0 0;
    border: 0;
  }
  b {
    font-family: sans-serif;
        font-weight: 900;
  }
  .center {
        margin: auto;
        width: 100%;
        text-align: center;
  }
</style>

# Lab 0: Setting Up the Environment

## Introduction

This lab introduces us to some of the tools, techniques, and workflows used throughout this course.
Many of the approaches here are the same tools currently used by industrial software developers.
This lab handout walks through all of the steps that we will take in a typical week to acquire lab starter code and to submit your completed lab assignment.

Before we start working, we need to configure our computers so we can create, compile, and run Java programs.
In the future, you will have access to lab computers where you can perform your work if you wish.
This week, we're asking you to set up your own computer.
There are a few reasons:

- We want you to have the opportunity to practice installing and configuring programming tools.
- To safeguard against the small chance that we need to work remotely, your computer will already be ready to use.
- You may just prefer to use your own personal computer.

_Note that our computing environment has a bias toward Unix-like operating systems like Linux and the macOS.
If you have a different operating system, like Windows, that is OK.
Some setup tasks will be slightly different&mdash;be sure to look out for the extra steps we provide for Windows users.
Do your best, but if you need help, you are welcome to reach out to us._

<hr style="border-color: purple;" />

## Step 1: Install a Code Editor

There are many editors designed for writing code.
In this course, we encourage you to use a popular editor called Visual Studio Code (aka, "VSCode").
If you have a strong preference for another code editor ([emacs](https://en.wikipedia.org/wiki/Emacs), [vim](<https://en.wikipedia.org/wiki/Vim_(text_editor)>), etc.), you are welcome to use it _for future labs_.
For this lab, we will focus on demonstrating concepts using VSCode, and we'd like you to give it a try.

To put VSCode on your computer, [follow these instructions](vscode.html), which explain how to download and install it.
Please be aware that although the operating system of your computer does not matter much (Linux, macOS, and Windows machines are all OK), Android and iOS devices (e.g., phones and tablets) are not appropriate for software development.
If you do not have a laptop or desktop computer to use, please speak to us about using lab resources instead.

<hr style="border-color: purple;" />

## Step 2: Install Git

Developing a piece of software typically requires repeatedly entering code, testing, debugging, and rewriting.
As you will see, code changes a lot over time.
Without special tools, managing these changes can be problematic.
For example, software developers often need to revert their changes to a previous version of their code.
A _version control system_ (VCS) is a software tool that helps developers manage their code throughout its development.
For example, a VCS makes it easy to make a quick backup copy (a "snapshot") of software, and being able to easily restore an earlier snapshot can be a real life-saver!

In this course we will be using the [`git` version control system](https://git-scm.com/book/en/v2/Getting-Started-About-Version-Control).
You will use `git` to develop and submit your homework assignments.

There are many version control systems, but `git` is probably the most popular option.
We encourage you to spend some time understanding [what a version control system is](https://www.git-tower.com/learn/git/ebook/en/command-line/basics/what-is-version-control#start) and [why you might want to use one](https://www.git-tower.com/learn/git/ebook/en/command-line/basics/why-use-version-control).
If you are totally unfamiliar with `git`, don't worry: you'll get plenty of practice in this course.

Installing `git` is straightforward: [follow these instructions](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

As with many software development tools, usage can get complicated in certain scenarios.
Luckily for us, VSCode provides a simplified `git` interface that works well for most of our uses.
Nevertheless, you are always encouraged to try `git`'s command line interface;
you will find that your instructors and most professional software developers prefer the flexibility of command line interfaces.

<hr class="style12" />

## Additional `git` Installation Notes for Windows Users

Among the _many_ dialog boxes you will click through during this installation, you should accept the default suggested for most options. But be sure to select the following options when prompted:

- When asked which _git editor_ to use, select `Use Visual Studio Code as Git's default editor`.
- When asked which _SSH executable_ to use, select `Use bundled SSH`.
- When asked which _HTTPS transport backend_ to use, select `Use the native Windows Secure Channel library`.

<hr style="border-color: purple;" />

## Step 3: Install Java

In this step, you will install Java on your own computer.

_NOTE: There are many ways to install Java, even within the same operating system, so if you already installed Java on your computer, that's OK.
It will probably work just fine._

We want to install the `Java Development Kit`, or JDK.
Oracle's official instructions are unnecessarily long, so we suggest that you to go straight to the [JDK Downloads page](https://www.oracle.com/java/technologies/javase-jdk14-downloads.html) and select the appropriate download for your system.
You'll need to select your operating system (Linux, macOS, or Windows) from the appropriate tab:

<img src="https://williams-cs.s3.amazonaws.com/lab-environment/jdk-download.png" alt="JDK download screen" style="max-width: 75%">

_NOTE for macOS users: If you are on a new Macintosh with an M1 or M2 processor, you will want to download the "Arm 64 DMG Installer"; otherwise choose the "x64 DMG Installer." If you don't know what kind of Mac you have, choose the "x64 DMG Installer." It will work with either kind of processor._

_NOTE for Windows users: Choose the "x64 MSI Installer."_

After you have downloaded the appropriate installer, run it.

<hr class="style12" />

## Additional Java Installation Notes for Windows Users

After you have installed Java, you should check that the operating system can find it.
We can use the `Git Bash` program that was installed when you installed `git` earlier.
Press the Windows key and type `bash`, then press the `Enter` key.

Once `Git Bash` starts up, you will see a little text window.
We call this window "the terminal" or "the console."
At the `$` prompt in the window, type

```
$ java -version
```

(Note that you shouldn't type the `$`, but we show it to make it clear that you're working in the console.)

If Java is correctly installed, you should see something like the following print out:

```
java version "18.0.2.1" 2022-08-18
Java(TM) SE Runtime Environment (build 18.0.2.1+1-1)
Java HotSpot(TM) 64-Bit Server VM (build 18.0.2.1+1-1, mixed mode, sharing)
```

If you see something like that, you are ready to move on to [Step 4 below](#repos)!

If you see something else&mdash;like an error&mdash;you will need to tell Windows where to find Java.

To tell Windows where Java is, we need to edit a _system environment variable_.
The one you want to edit is called the `Path` variable.

1. First, we will find out where Java was installed.
   That location will have the form: `C:\Program Files\Java\jdk-VERSION` where `VERSION` is the version number of your java installation.
   For example, it might be `C:\Program Files\Java\jdk-15.0.2`.
   To know for sure, press the Windows key, then paste `C:\Program Files\Java` into the box and press `Enter`.
   In that folder, there may be multiple subfolders.
   The `jdk` with the largest number is what you want.
   For example, on my computer `jdk-18.0.2.1` has the largest version number.
   So the complete path to my Java installation is `C:\Program Files\Java\jdk-18.0.2.1`.
1. Next, we update our `Path` environment variable.
   To do this, press the Windows key and type `env` in the search text box, then select `Edit the system environment variables`.
   A dialog box appears.
   In the `Advanced` tab, select `Environment Variables...`.
   Another dialog box appears. Under `System variables` select the line that says `Path` then select `Edit...`.
   A dialog box will appear.
   It will contain a text area with a list of folders.
   Double-click in the text area _below_ the last item in the list.
   Here you will enter the location where Java was installed.
   In my case, I will type `C:\Program Files\Java\jdk-18.0.2.1` into the text input box.
1. We can check to see that we did this properly by closing the `Git Bash` program, starting it again, and running `java -version` a second time.
   If we get output as described above, we're good!
   Otherwise, you may have made a mistake in the steps above;
   retrace your steps until you discover where things went wrong.

<hr style="border-color: purple;" />

## Step 4: "Check Out" Your Repository

Every student is given their own private development space on the CS department GitLab infrastructure for each lab.
Each so-called "repository" is named using a combination of the course (`cs136`), the semester (`s22`), your CS account's username (e.g., `22abc1`), and the assignment (e.g., `environment`).

For this lab, your repository will be named using the form:

`https://evolene.cs.williams.edu/cs136-labs/<YOUR_USERNAME>/lab00-environment.git`

You should replace `<YOUR_USERNAME>` with your username.
For example, if your username is `22abc1`, your repository will be located at:

`https://evolene.cs.williams.edu/cs136-labs/22abc1/lab00-environment.git`

_Key ideas:
We will use the `git` version control system to \_clone_ a repository.
A clone is a copy of the original repository, stored on our local machine.
Note that the cloned repository will exist independently of the original version on the department server.
You will need to periodically synchronize the two.
We explain how to do all of these things in the steps below.\_

1.  To start, open a _command-line terminal_ on your local machine. We'll say "the terminal" for short from now on.

    - On the macOS, whenever we say "the terminal" we mean the `Terminal.app` program, located inside the `Utilies` folder in your `Applications` folder.
    - On Windows, whenever we say "the terminal" we mean the `Git Bash` program you installed above. We do not recommend that you use the `cmd.exe` or `Powershell` programs, because they are very different than `Git Bash`.

    It isn't a stretch to say that the terminal is among the most important applications for programmers.
    Any investment you make learning the terminal will pay off.
    You might be surprised that your instructors learn new terminal tricks all the time, even after decades of use in some cases!

1.  Once your terminal program has started, you should see a "prompt" where text will appear when you start typing.
    The prompt may look a little different depending on your settings.
    Don't worry if your terminal looks a little different than we one we show here.

    ![Terminal Window](https://williams-cs.s3.amazonaws.com/lab-environment/terminal.png)

    As a convention, we use the dollar sign (`$`) to signify a prompt.
    When you see instructions like the following,

        	$ ls

    it means that at the prompt, you should type the command `ls`.
    When I type `ls` and press `Enter`, I see:

        	$ ls
        	Desktop     Documents   Library     Music   notes.txt   Pictures

    This output means that there are files called `Desktop`, `Documents`, etc. in my _current working directory_.
    _Directory_ is a more precise, technical word for _folder_.
    Terminal commands often refer to files in your file system, so when you open a terminal, by default, it
    assumes that you want to work with files in your home folder.
    Therefore, your _current working directory_ is your "home folder" by default.
    The files shown above are the ones in my own home folder.
    `ls` is short for "list", so we call the output above a _directory listing_.

    You might recognize that some of the files in the directory listing above are, themselves, directories.
    How can you tell the difference?
    Try running `ls` with some extra _arguments_ (phonetically, you will type "ell ess `Space` minus ell"):

        $ ls -l       total 8
        drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Desktop
        drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Documents
        drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Library
        drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Music
        -rw-r--r--  1 cs136  staff  235 Jan 25 13:19 notes.txt
        drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Pictures

    Do you see the cryptic text on the left?
    It looks like `drwxr-xr-x`.
    This is a code that tells us a great deal about the contents of our directories.
    If that code starts with a `d`, corresponding entry is a `d`irectory.
    If it starts with a dash, `-`, like `-rw-r--r--`, then the entry is a regular file.
    The code tells us more, but let's leave it at that for now.
    Here, you can see that `notes.txt` is a regular file.

1.  Before we use `git` to clone our repository, we need to do some one-time setup.
    Type the following commands into your terminal, replacing the capitals
    with appropriate values:

        $ git config --global user.name 'YOUR NAME'
        $ git config --global user.email 'YOUR-EMAIL@williams.edu'
        $ git config --global push.default simple
        $ git config --global core.editor "atom --wait"

    _If you are a Windows user, you need to execute one additional line:_

        $ git config --global http.sslbackend schannel

    You should only ever need to do this git setup once.

1.  Now that we've explored our home directory and a few terminal commands, we need to _clone_ our repository.
    Cloning creates a copy of a repository on our local computer.

    I like to keep all of my files organized by course, and I would like all of my labs this semester to be kept in the
    same folder.
    I will create a new directory for this purpose using the `mkdir` command as follows:

        	$ mkdir cs136

    The `mkdir` command creates a new directory called `cs136` inside my current working directory.
    If I type `ls -l`, I now see `cs136` in the directory listing:

          $ ls -l       total 9
        	drwxr-xr-x  2 cs136  staff   64 Sep 04 13:11 cs136
        	drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Desktop
        	drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Documents
        	drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Library
        	drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Music
        	-rw-r--r--  1 cs136  staff  235 Jan 25 13:19 notes.txt
        	drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 Pictures

    I can now change my working directory to be `cs136` by using the `cd` command (short for "change directory"):

        	$ cd cs136

    By using the `ls` and `cd` commands, you should be able to navigate your files in exactly the same way that you navigate them when you are pointing and clicking with a mouse in your operating system's file explorer.
    It may seem like a pain now, but believe it or not, once you've gotten used to it, you will actually prefer to use your computer this way!

1.  You can verify that your current working directory is your `cs136` directory with the `pwd` command (which stands for "print working directory").

         $ pwd
         /Users/jannen/cs136

    Now that the current working directory is where we want to put our cloned repository, we clone it:

         $ git clone https://evolene.cs.williams.edu/cs136-labs/<YOUR-USERNAME-HERE>/lab00-environment.git

    (replacing `<YOUR_USERNAME>` with your CS account's username).

    If you type `ls`, you should see a copy of your newly cloned repository in the listing.
    Change the current working directory to your repository copy by typing

         $ cd lab00-environment

<hr style="border-color: purple;" />

## Step 5: Hello world!

At this point, you have installed the code editor, VSCode, the Java programming language, and a version control system called `git`.
Our next task is to create, compile, and run a simple Java program.

1.  Start by opening VSCode.
    Look for a section called `Start` and, under it, find and click on an option called either `Open Folder...` (on Windows) or `Open...` (on the Mac)

    <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-start.png" style="max-width: 20%">

1.  Navigate to the folder we just cloned (`lab00-environment`) using `git` and `Open` it.

    <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-folder-open.png" style="max-width: 75%">

1.  The first time you open a project folder, VSCode will ask you if you trust the authors of the code.
    Since we're authoring the code ourselves, the answer is "yes."
    But be aware that if you open somebody else's code, clicking "yes" can be dangerous.
    Click `Yes, I trust the authors`.

    <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-trust.png" style="max-width: 50%">

1.  Observe that the left panel of VSCode now shows the contents of our project folder, containing a single file called `README.md`.
    That `README.md` is this lab writeup!
    If you click on the file, VSCode will show you the code (written in the Markdown language).
1.  Let's create a new file and put some Java code into it. If you move your mouse over the name of the project in the project drawer, you should see an icon for creating a new file. Click that icon.

    <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-add-file.png" style="max-width: 30%">

    Alternatively, on the Mac, press &#8984;-N, or on Windows press `Ctrl`-N.

1.  Name the file `HelloWorld.java`. Note the correct use of captial letters.
1.  Type (don't just paste!) the code below into your code editor.
    Retyping code will encourage you to notice certain details that
    you might not ordinarily notice.

            public class HelloWorld {
              public static void main(String[] args) {
                System.out.println("Hello world!");
              }
            }

1.  Save the code by pressing &#8984;-S on the Mac or `Ctrl`-S on Windows.
    In VSCode, unsaved code has a little dot next to its name in the titlebar.
    When you save the code, the dot goes away.

    <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-unsaved.png" style="max-width: 50%">

1.  We've finally gotten to the fun part.
    We are going to _compile_ our `HelloWorld.java` program.
    Compilation converts a source code program into a form that the computer can run.
    We use the `javac` command (which stands for "Java compiler") to this.
    Remember, whenever we give instructions that start with a `$`, that means that we should type it into the terminal.

        $ javac HelloWorld.java

    If there are no errors in your program, you will see output like this:

        $ javac HelloWorld.java
        $

    In other words, you will see _no output_ when things _go right_.
    In the terminal, it is conventional for programs only to produce output when things _go wrong_!
    No news is good news!
    When `javac` prints something, that's its way of telling you that there is a problem that you should fix.
    `javac` sometimes prints a frightening amount of things to your screen.
    Don't be afraid of that output!
    Learn to read it, because it is designed to help you fix your problems.

    For example, if I see:

         $ javac HelloWorld.java
         HelloWorld.java:3: error: ';' expected
         System.out.println("Hello world!")
                                           ^
         1 error

    then `javac` is telling me that at the location indicated with the `^`, it was expecting a `;` character, but it did not find one.
    Putting in a `;` character at that location makes my program compile without errors.
    Wasn't that nice of `javac` to help us like that?

1.  After compiling your program, you should see a new file in your directory:

         $ ls -l
         total 8
         -rw-r--r--  1 cs136  staff  426 Jan 25 13:33 HelloWorld.class
         -rw-r--r--  1 cs136  staff  235 Jan 25 13:33 HelloWorld.java
         drwxr-xr-x  2 cs136  staff   64 Jan 25 13:11 README.md

    Do you see it?
    There is a new file called `HelloWorld.class`.
    This is a _compiled Java program_, which we can now run.
    Let's run it.

         $ java HelloWorld
         Hello world!

    We just ran our first program!
    Note we did not type `java HelloWorld.class`, we just typed the name of the class that we created, i.e., `HelloWorld`.
    If you have trouble, be sure to pay attention to copy our spelling and capitalization exactly.

<hr style="border-color: purple;" />

## Step 6: Submitting Your First Program

In this last step, we walk through the process of submitting our completed program, `HelloWorld.java`.
Note that you _will not be graded_ on this lab!
The purpose of this lab is to walk through the process that we will be using for labs throughout
the semester.
Hopefully, we can identify potential roadblocks now, and by resolving them during the first week of class, we can spend more time focusing on the coding for future labs.

1. First, open the `Source Control` tab by clicking on the icon to the left of the project drawer.
   The icon has three circles joined by two squiggly lines.

   <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-git.png" style="max-width: 30%">

   A new source control drawer will replace your project drawer.
   You can always toggle back to the project drawer by clicking on the file icon above the source control icon.

1. Next, tell `git` which files you want to track by clicking on the `+` sign next to that file.

   <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-stage-changes.png" style="max-width: 30%">

   We call this step "staging changes."
   Your `HelloWorld.java` file should move to a section called `Staged Changes`.

   <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-staged-changes.png" style="max-width: 33%">

1. Now, type a _commit message_ into the `Message` box.
   A commit message describes the changes you made.
   For example, I will type `completed my version of Hello World!`

   <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-commit-message.png" style="max-width: 33%">

1. Press the `Commit` button.
   _Committing_ your code saves a snapshot that we can recover at a later date.
   You should commit _frequently_.
   Most software developers commit whenever they've made an important change.
   It does not matter whether your code is incomplete&mdash;commit it anyway!
1. Our changes are committed, but they're still only stored on our local machine.
   To copy those changes to the repository stored on the CS department's server, we need to _push_ our changes.
   **If you don't push, we will not see your completed homework!**
   **Always be sure to push!**
   To push, click `Sync Changes` button.

   <img src="https://williams-cs.s3.amazonaws.com/lab-environment/vscode-push.png" style="max-width: 30%">

1. Now navigate to your GitLab repository in your web browser.
   Remember that it should have the form

   `https://evolene.cs.williams.edu/cs136-labs/<YOUR-USERNAME-HERE>/lab00-environment.git`

   (where `<YOUR-USERNAME-HERE>` is replaced with your CS username)

   You should see your changes reflected in your repository online.
   My repository is shown below.
   Notice that the "commit message" appears next to the files that changed during that commit.
   I can see "completed my version of Hello World!" next to the two files I've
   added: `HelloWorld.java` and `HelloWorld.class`. Once you confirm that
   you\'ve successfully submitted, you are done setting up your environment!

![](https://williams-cs.s3.amazonaws.com/lab-environment/gitlab-submit.png)

<hr style="border-color: purple;" />

## One Last Thing

Please fill out the [Getting to Know You](https://forms.gle/bMbJ27M6XhqEn2bY6) form.
This is a short form that we're just using to find out a little more about you.
Once this form is complete, you're done with the lab.
