//Adding this to the required package
package sg.edu.nus.cs2020;

/**
 * class ImageEncode
 * @author dcsslg
 * Description: Encodes and decodes images using a simple shift-register scheme
 */

public class ImageEncode {

	/**
	 * transform
	 * @param image is the SimpleImage to manipulate
	 * @param s
	 * @throws Exception
	 */
	static void transform(SimpleImage image, ILFShiftRegister shiftReg) {		

		// If no image, do nothing and return
		if (image == null) return;
		
		// If no shiftregister, do nothing and return
		if (shiftReg == null) return;
		
		// Get the height and width of the image
		int iWidth = image.getImgWidth();
		int iHeight = image.getImgHeight();
				
		// Catch all exceptions
		try{
			// Iterate over every pixel in the image
			for (int i=0; i<iWidth; i++){
				for (int j=0; j<iHeight; j++){							
					// For each pixel, get the red, green, and blue components of the color
					int red = image.getRed(j,i);
					int green = image.getGreen(j,i);
					int blue = image.getBlue(j,i);
					
					// For each color component, XOR the value with 8 bits generated from the shift register
					red = (red ^ shiftReg.generate(8));
					green = (green ^ shiftReg.generate(8));
					blue = (blue ^ shiftReg.generate(8));
										
					// Update the image
					image.setRGB(j,i,red,green,blue);
				}
			}		
		}
		catch(Exception e){
			// Print out any errors
			System.out.println("Error with transformation: " + e);
		}		
	}		
		
	/**
	 * main procedure
	 * @param args
	 */	
	static public void main(String[] args){
		// Open an image
		SimpleImage image = new SimpleImage("Mystery Image", "mystery-image-2013.bmp");
				
		// Create a new shift register
		// Update this to use *your* shift-register class
		// Update the tap with the correct code.
		ILFShiftRegister shiftReg = new ShiftRegister(11, 8);		
		try{
			// Create the seed for the shift register
			// Update the seed with the correct code
			int[] seed = {1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1};			
			                
			// Set the seed for the shift register
			shiftReg.setSeed(seed);
			
			// Transform the image
			transform(image, shiftReg);	
		}
		catch(Exception e){
			System.out.println("Error in transforming image: " + e);
		}
	}	
}


