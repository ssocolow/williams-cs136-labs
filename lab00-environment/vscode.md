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

# Installing Visual Studio Code

Visual Studio Code, also known as "VSCode", is a widely-used [open-source](https://en.wikipedia.org/wiki/Open-source_software) program specifically designed to assist with software development.
This document explains how to download and install VSCode on your computer.

To proceed, you should have access to a laptop or desktop computer.
Tablet and phone devices are not appropriate for software development.
If you do not have a laptop or desktop computer, you may instead use one of our lab computers, all of which come with VSCode preinstalled.

The procedure for installing VSCode varies depending on your operating system.
Follow the appropriate instructions below.

- <a href="#macos">I have a macOS computer.</a>
- <a href="#windows">I have a Windows computer.</a>
- <a href="#linux">I have a Linux computer.</a>
- <a href="#freebsd">I have a FreeBSD computer.</a>

<hr style="border-color: purple;" />

## <a name="macos"></a>Installing VSCode on a macOS computer

1. In your web browser (e.g., Safari), navigate to the [VSCode download page](https://code.visualstudio.com/).

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/macos-vscode-site.png" alt="vscode download site" style="max-width:75%;">

1. Click on the button titled "Download Mac Universal".
1. You may be prompted to allow the download. If you are, click "Allow."

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/macos-vscode-perm.png" alt="safari permission dialog" style="max-width:50%;">

1. You will be redirected to a "Getting Started" page. You can close this page, since we will explain what to do next.
1. Look in your `Downloads` folder for a file called `VSCode-darwin-universal.zip`.
1. Double-click on that file. This will "decompress" the ZIP archive and extract its contents into the current folder. Once that's done, you should see an application file called `Visual Studio Code`.
1. Drag this file into the `Applications` folder on your computer. If you can't find your `Applications` folder, click on the little macOS smiley face

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/macos-finder-icon.png" style="max-width:10%;">

   and then press &#8984;-SHIFT-G and type `/Applications` and press the Enter key.

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/macos-finder-go.png" style="max-width:50%;">

   Then drag the `Visual Studio Code` icon into the `Applications` folder.

1. Start `Visual Studio Code` by double-clicking on the icon.

<hr style="border-color: purple;" />

## <a name="windows"></a>Installing VSCode on a Windows computer

1. In your web browser (e.g., Edge), navigate to the [VSCode download page](https://code.visualstudio.com/).

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/windows-vscode-site.png" alt="vscode download site" style="max-width:75%;">

1. Click on the button titled "Download for Windows".
1. You will be redirected to a "Getting Started" page. You can close this page, since we will explain what to do next.
1. Look in your `Downloads` folder for a file named something like `VSCodeUserSetup-x64-1.71.0.exe`. If you don't know where to look for your downloads folder, you can sometimes ask your web browser. E.g., when using Edge, look for the three dots in the upper right corner of your browser window, click on them, then click on `Downloads`.

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/windows-edge-downloads.png" alt="edge downloads" style="max-width:30%;">

   After that, move your mouse to the right of the `VSCodeUserSetup-x64-1.71.0.exe` filename and click on the little folder icon.

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/windows-edge-downloads-2.png" alt="open edge download folder" style="max-width:37%;">

   This will open your `Downloads` folder.

1. Double-click on the file named `VSCodeUserSetup-x64-1.71.0.exe`. This will start the VSCode installer.

   <img src="https://williams-cs.s3.amazonaws.com/vscode-images/windows-vscode-installer.png" alt="vscode installer" style="max-width:50%;">

1. Click `I accept the agreement` and click `Next`.
1. Click `Next` again.
1. Click `Install`.
1. Start `Visual Studio Code` by double-clicking on the icon.
1. Click `Finish`.
1. To start VSCode, press the Windows key and type `Visual Studio Code` and then click on the icon that appears.

<hr style="border-color: purple;" />

## <a name="linux"></a>Installing VSCode on a Linux computer

If you're running Linux, we're going to assume that you're already an über hacker, so [just follow these instructions](https://code.visualstudio.com/docs/setup/linux).

<hr style="border-color: purple;" />

## <a name="freebsd"></a>Installing VSCode on a FreeBSD computer

LOL, really?

```
$ sudo pkg install vscode
```

<hr style="border-color: purple;" />
