# CSX42: Assignment 4
## Name: Troubleshoot Search

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in loadbalancer/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile troubleShootSearch/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile troubleShootSearch/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile troubleShootSearch/src/build.xml run -Darg0="technicalInfo.txt" -Darg1="synonyms.txt" -Darg2="userInput.txt" -Darg3="output.txt" -Darg4="0"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:
Data Structures: MyArrayList, MyTree, MySynonyms
- MyArrayList contains a reference of MySynonyms
- MyTree consists of an unbalanced binary search tree

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [11/18/2019]


