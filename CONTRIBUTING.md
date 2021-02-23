# Contributing

* [git](#git)
    + [Branches](#branches)
        - [Switching Branches](#switching-branches)
        - [Creating a branch](#creating-a-branch)
    + [Making a commit](#making-a-commit)
    + [Pushing commits](#pushing-commits)
        - [Make commits often](#make-commits-often)
    * [Code Style](#code-style)
        + [Classes and Interfaces](#classes-and-interfaces)
        + [Methods](#methods)
        + [Fields and Attributes](#fields-and-attributes)
        + [Variables and Parameters](#variables-and-parameters)
        + [Acronyms](#acronyms)
            - [Bad](#bad)
            - [Good](#good)
        + [Declarations](#declarations)
            - [Bad](#bad-1)
            - [Good](#good-1)
        + [Classes](#classes)
        + [Spacing](#spacing)
            - [Bad](#bad-2)
            - [Good](#good-2)
        + [Brace Style](#brace-style)
            - [Bad](#bad-3)
            - [Good](#good-3)
        + [Conditional Statements](#conditional-statements)
            - [Bad](#bad-4)
            - [Good](#good-4)
        + [Switch Statements](#switch-statements)

## git

### Branches

Before writing any code, please make sure that you are on the correct git branch. 

You can check which branch you are on by running `git branch` (which displays all the project's branches):

```shell
> git branch
  ai
  audio
  dev
  game-logic
  gui
* master
  networking
  physics
  renderer
```

The current branch is indicated with an `*`. 

After cloning this project for the first time, the current branch is most likely `master`.

#### Switching Branches

To switch to a different branch, you can run `git checkout branch-name`. E.g.:

```shell
> git checkout networking
> git branch 
  ai
  audio
  dev
  game-logic
  gui
  master
* networking
  physics
  renderer
```

The `*` indicates that the current branch is indeed now `networking`.

#### Creating a branch
You **must not** work on the `master` or `dev` branch.

If a branch for your part doesn't exist already e.g. `networking`, then create one **off the dev branch**.

E.g.
```shell
> git checkout dev
> git branch networking
> git checkout networking
```

You'll now be on the `networking` branch. Check this by running `git branch` again.

### Making a commit
First, run `git status`. This outputs which files are staged for a commit and which are not. 

To stage a file (or an entire directory) for a commit, use `git add file_name_1, file_name_2, dir_name_1, dir_name_2, ...`.

Please avoid using `git add --all` because it might add something unexpected.

After adding the desired files, run `git status` again to make sure that the desired files are staged for a commit.

When ready to commit, run the `git commit` command as follows:

```shell
> git commit -m "commit message goes here"
```

Please use a sensible commit message that tells the rest of the team what change or feature has been added.

E.g. `git commit -m "renderer can now display shapes"`.

### Pushing commits

```shell
> git push origin your-current-branch
```

Please do not push to master; push to your own branch instead!

#### Make commits often
Please make commits often. This ensures that the rest of the team can see the steps you've taken to implement
a new feature, and it makes it easier to reset to a previous version of your code.

## Code Style

### Classes and Interfaces
Written in **UpperCamelCase**. E.g. `ClientController`.

### Methods
Written in **lowerCamelCase**. E.g. `displayCoordinates`.

### Fields and Attributes
Written in **lowerCamelCase**.

Static fields should be written in **uppercase**, with an underscore separating the words:

```java
public static final int THE_ANSWER = 42;
```

### Variables and Parameters
Written in **lowerCamelCase**.

Single character names should be avoided apart from temporary looping variables e.g. `i`.

### Acronyms
In code, acronyms should be treated as words. E.g.

#### Bad
```java
XMLHTTPRequest
String URL
findPostByID
```

#### Good
```java
XmlHttpRequest
String url
findPostById
```

### Declarations
Single declarations per line. E.g.

#### Bad
```java
String username, twitterHandle;
```

#### Good
```java
String username;
String twitterHandle;
```

### Classes
Exactly **one** class per source file, except [inner classes](https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html).

### Spacing
Although the university does not teach good spacing practices, it is **VERY** important.

#### Bad
```java
String myName="Dave";
renderer.display(image,400,400);
```

#### Good
```java
String myName = "Dave";
renderer.display(image, 400, 400);
```

This is **not** down to personal preference. Literally every company forces their employees to use good spacing 
practice. 

### Brace Style
Only trailing closing-braces should get their own line. All others appear on the same line as their preceding code.

#### Bad
```java
class MyClass
{
  void doSomething()
  {
    if (someTest)
    {
      // ...
    }
    else
    {
      // ...
    }
  }
}
```

**NEVER** do this.

#### Good
```java
class MyClass {
  void doSomething() {
    if (someTest) {
      // ...
    } else {
      // ...
    }
  }
```

### Conditional Statements

Conditional statements are always required to be enclosed with braces, irrespective of the number of lines required.

`else` statements should be on the same line as the preceding `if` closing-brace.


#### Bad
```java
if (someTest)
    doSomething();
if (someTest) doSomethingElse();

if (someTest) {
    doSomething();
}
else {
    doSomethingElse();    
}
```

#### Good
```java
if (someTest) {
    doSomething();    
}

if (someTest) {
    doSomething();    
} else {
    doSomethingElse();    
}
```

### Switch Statements
Always include the `default` case even if it's just a `break` statement.