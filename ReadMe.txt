
////////////////////////////////////////////////////////////
--What the User Sees----
////////////////////////////////////////////////////////////

-When the User Instantiates the program, it will ask the User to Enter one of three Number's Depending on What,
They wish to do. 

-Pressing the Number 1 on the keyboard and then pressing Enter will direct the User to Parsing a File Name, They User 
can enter the Directory for this File or Just the file Name if they put it into the Packaged Folder where the rest of the program
is.

-Pressing the Number 2 on the keyboard and then pressing Enter will do the same as one but ask the user to
paste in a URL. <Recommended to be a text only URL as it's simple URL parsing and does not work with JavaScript or Advanced Frameworks>

Pressing the Number 3 Then Enter Will Exit the Program Entirely.

////////////////////////////////////////////////////////////
<After the User Enters the Number of Choice>
////////////////////////////////////////////////////////////

Before the Program will ask for the user URL or File , it will ask the user for how many words they wish to display on the final word cloud image file.
The Default is 20 and the program will not allow the user to use non numerical values.
I do not recommend entering in a very high number for this part as it will cause the image to be cluttered
and may increase the runtime of the application.

After setting the Number of Words  Depending on the user's choice of URL or File, 
The Program will ask for the URL or File Directory.

Once the User Gives a Working and Correct URL or File Location,
The Program will ask for the Name the user wants to name the outputted word Cloud Image File.

///////////////////////////////////////////////////////////////////
Back-Ground Classes in the Program
//////////////////////////////////////////////////////////////////

The Program has 5 Classes.

•	Runner 
-The Main Class that instantiates all the other classes. This will run on program execution.

•	Menu
-This is the Class that runs the Menu for the User to Input the File Names , Number of Words for Output and Image File Names. It mainly runs off using the Scanner Library built into Java which allows the user to input data into variables. It also contains methods for setting and getting Various Details. The Set and Get’s are to allow the other classes to access the variable’s and let the program function as intended.

•	Parser
-This Class parses the file or URL the user has entered and sends it into a HashMap and then later on into a Word Que so that it can be sorted in terms of priority, this class also cuts out unnecessary punctuation and words using trims, splits, replaces and by comparing a 
IgnoreWord.txt file to the input from the user entered file/URL.

•	Word
-This is mainly used in finding Word Frequency’s , prioritizing more common words and returning outputs to other Classes.

•	Image Generator
-This Class is where the Image is setup , and the words are scaled based on their frequency.
It Iterates through the Word Que and randomly places the word and then outputs the Image into a buffer and then later this buffer is sent into the Runner Class and outputted as a real .PNG Image.

////////////////////////////////////////////////////////////////////////////////////////
///////////////////Thank you for Testing my Word Cloud Generator/////////////////////
//////////////////Written , Designed and Developed by James Porter///////////////////
////////////////////////////////////////////////////////////////////////////////////////

