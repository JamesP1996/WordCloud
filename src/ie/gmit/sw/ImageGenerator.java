package ie.gmit.sw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class ImageGenerator {

	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private static final double MAX_FONT_SIZE = 100;
	private static final double MIN_FONT_SIZE = 10;
	private int[] fontStyle = {Font.PLAIN, Font.BOLD, Font.ITALIC};
	private String[] fontFamily = {Font.SERIF, Font.SANS_SERIF};
	
	private BufferedImage image;
	
	// Make a Image Using the Width and Height Provided by the Static Int Variables
	public ImageGenerator() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
	}
	
	public void printWords(Queue<Word> q, int maxWords) {
		
		int j;
		int counter = 0;
		int max = 0, min = 0;
		int offset = 150;
		
		//Setting up the Image Graphics
		Graphics2D graphics = image.createGraphics(); 

		// Set the colour of the background
		graphics.setColor(Color.darkGray);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		Iterator<Word> i = q.iterator(); // wordQ.Iterator() returns an iterator over the words in wordQ. Calling iterator() is constant time.
		
		Word wordFreq = new Word();
		// Iterating over the elements in i is O(n) 
		while (i.hasNext() && counter < maxWords) { 
			j = i.next().getFrequency();
			if (j > max) max = j;
			if (j < min) min = j;
			
			// isEmpty runs in constant time O(1). 
			while ((!q.isEmpty()) && counter < maxWords) { 
				
				 // poll() will return the highest priority item in the queue by runtime O(log n)
				wordFreq = q.poll();
				
				// Setting up the Graphics for the Program
				graphics.setFont(new Font(fontFamily[rand(0, fontFamily.length - 1)], fontStyle[rand(0, fontStyle.length - 1)], scale(wordFreq.getFrequency(), min, max)));
				Color[] colors = {Color.red,Color.blue,Color.yellow,Color.green,Color.black,Color.pink,Color.orange,Color.CYAN,Color.MAGENTA,Color.WHITE};
				Color c = colors[rand(0, colors.length-1)]; 
			    //Color is set to a random colour in the colours array
				graphics.setColor(c);
				//Drawing the Strings
				graphics.drawString(wordFreq.getWord(), rand(0, WIDTH - offset), rand(0, HEIGHT));
				counter++;
			}	
		}
		graphics.dispose();
	}
	
	// Scale a word in accordance with its frequency
	private int scale(double freq, double min, double max) {
		return (int)Math.ceil((MAX_FONT_SIZE - MIN_FONT_SIZE) * (freq - min) / (max - min) + MIN_FONT_SIZE);
	}
	//Generates Random Number between a Range
	public static int rand(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	//Return the Buffered Image that was Made
		public BufferedImage getImage() {
			return image;
		}
		
}

