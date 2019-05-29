package ie.gmit.sw;


import java.io.File;
import java.io.FileInputStream;
import java.util.Queue;
import javax.imageio.ImageIO;

public class Runner {

	
	public static void main(String[] args) throws Exception {
		
		//RunTime to Be Used Later
		double runTime;
		
		//Display The Menu To Introduce User to Program and Begin Asking for Inputs
		Menu menu = new Menu();
		menu.displayMenu();
		
		//Take Note of Current Time in Milliseconds <When Program Started>
		runTime = System.currentTimeMillis();
		
		//Parse The Ignore File and User Selected File
		Parser parser = new Parser();
		parser.parseIgnoreFile();
		if (menu.getFileType() == 1) {
			parser.parseInput(new FileInputStream(menu.getFileName()));
		}
		else if (menu.getFileType() == 2) {
			parser.parseURL(menu.getUrl());
		}
		
		
		//Retrieve the Word Q
		Queue<Word> Q = parser.getWordQueue();
		
		// Use Image Generating Class and begin drawing the Words
		ImageGenerator ig = new ImageGenerator();
		ig.printWords(Q, menu.getMaxWords());
		
		//Output the Image Generated as a PNG File , Named What the User set it to.
		ImageIO.write(ig.getImage(),"png",new File(menu.getImageName()+".png"));
		
		//How Many Unique Words have been Read and What Runtime the Overall Program had
		System.out.printf(parser.numUniqueWordsRead() + " Words Read and Outputted to Cloud in "  + calculateRunTime(runTime)+"s");
		
	}
	
	
	// Calculate Program Run Time
	private static double calculateRunTime(double start) {
		return (System.currentTimeMillis() - start) / 1000;
	}
	
	
}
