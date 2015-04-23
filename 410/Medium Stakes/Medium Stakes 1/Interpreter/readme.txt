
These instructions are for UNIX-line operating systems. For installation on Windows, visit the following page:

http://tinman.cs.gsu.edu/~raj/elna-lab-2010/ch2/


1. Download the ra.zip file from muOnline.

2. Move this zip file to a directory of your choice. Unzip this file. You see a directory named ra

3. Open a terminal window and cd (i.e., change directory command) to ra directory

4. Move aql.jar file to /usr/local/lib
 
5. In the terminal window, execute the following command:

export CLASSPATH=/usr/local/lib/aql.jar:$CLASSPATH

6. In the terminal window, execute the following command:

java edu.gsu.cs.ra.RA student

Now, you will see the RA> prompt.

7. Now you can execute relational algebra queries against the student database of mid-stakes assignment 1.

For instructions, see ch2.pdf file in ra directory. You need to read sections 2.1 and 2.2 only in ch2.pdf document