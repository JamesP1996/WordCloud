package ie.gmit.sw;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {


	
	private static Scanner sc = new Scanner(System.in);
	private String inputFileName;
	private String url;
	private int maxWords = 20;
	private int FileType;
	private String ImageName;
	
	

	

	public Menu() {
		System.out.println("/////////////////////////////////////////////////////");
		System.out.println("-------------- *Word Cloud Generator*  --------------");
		System.out.println("/////////////////////////////////////////////////////");		
	}

	// Returns the maximum number of words to display.
	public int getMaxWords() {
		return maxWords;
	}

   //Sets the Maximum Amount of Words to Display by Asking for User Input
	public void setMaxWords() {
		
		boolean invalid = true;

		do {
			try {
				System.out.printf("Enter the maximum number of words to display in the wordcloud (currently it's set to %d): ", maxWords);
				maxWords = Math.abs(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please enter only a number.");
				sc.next();
				continue;
			}
			invalid = false;
		} while(invalid);
		
		
	}
	
	// Menu With Few Options To Guide the User on What to Press for Their Desired Output
	public void displayMenu() {
		String option;
		boolean keepGoing = true;
		
		System.out.println("******************************************************************************");
		System.out.println("Type one of the Following Numbers:");
		System.out.println("1. Generate a word cloud from a File");
		System.out.println("2. Generate a word cloud from a URL");
		System.out.println("3. To Exit.");
		System.out.println("******************************************************************************");

		do {
			System.out.print("Select an option listed above: ");
			option = sc.next();
			// Only Allow Numerical Numbers between 1 and 3
			if (!(option).matches("[1-3]+")) {
				System.out.println("Please Only Enter one of the Numbers Provided");
				continue;
			}

			keepGoing = handleOption(option);
		} while (keepGoing);
	}
	//Depending on The Users Choice
	private boolean handleOption(String option) {
		switch (option) {
			case "1": //Parse File
				//Setting up Max Words by user Input
				System.out.println("Please First Set Up Maximum Words You wish to Display "
						+ "<Failure To Enter in a Correct Number will set the Default to 20>");
				setMaxWords();
				setFileName(inputFileName);
				FileType = 1;
				//Naming the Outputted Image
				System.out.println("What Do You Wish to Name Your Outputted Image?");
				setImageName(sc.next());
				return false;
			case "2": // Parse URL
				//Setting up Max Words by user Input
				System.out.println("Please First Set Up Maximum Words You wish to Display "
						+ "<Failure To Enter in a Correct Number will set the Default to 20>");
				setMaxWords();
				setUrl(url);
				FileType = 2;
				//Naming the Outputted Image
				System.out.println("What Do You Wish to Name Your Outputted Image?");
				setImageName(sc.next());
				return false;
			case "3": //Exit Program
				System.out.println("Exiting Program");
				System.exit(0);
				
			default:
				break;
		}	
		
		return true;
	}

	
	//Set the File Name that the User Wishes to Parse
	public void setFileName(String inputFileName) {
		System.out.println("Please Enter the Name of The Name/Path of the File you wish to make into a word cloud");
		try {
			inputFileName = sc.next();
		} catch (Exception e) {
			System.out.println("File Could Not Be Found, Please Ensure You Entered it Correctly");
		}
		this.inputFileName = inputFileName;
		
	}
	//Retrieving the File Name the User Wishes to Parse
	public String getFileName() {
		return inputFileName;
	}

	//Retrieving the URL the User Wishes to Parse
	public String getUrl() {
		return url;
	}

	//Set the URL that the User Has Entered for Parsing
	public void setUrl(String url) {
		System.out.println("Please Enter the URL you wish to make into a word cloud");
		try {
			url = sc.next();
		} catch (Exception e) {
			System.out.println("URL could not be Found , Please Ensure You Entered it Correctly");
		}
		this.url = url;
	}
	//For Retrieving the File Type 1 - File or 2 - URL so the program knows what to do depending on the number in the runner
	public int getFileType() {
		return FileType;
	}
	public String getImageName() {
		return ImageName;
	}

	public void setImageName(String imageName) {
		ImageName = imageName;
	}

	
	
}