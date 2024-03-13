import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import javax.imageio.ImageIO;

public class ASCIIArt {
    public static void main(String args[]) throws IOException {
        ASCIIArt newArt = new ASCIIArt();
        BufferedImage cat = ImageIO.read(new File("IMG_1866.jpg")); // Read in image as a BufferedImage
        int[][] finishedArr = newArt.imageTo2DArray(cat);
        newArt.print2DArrToFile(finishedArr);
    }

    // Create 2D array of greyscale values from image
    // Accepts image & Returns a 2D array
    public int[][] imageTo2DArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] arr = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color mycolor = new Color(image.getRGB(j, i)); // Create new Color Object from buffered image
                // Convert to greyscale using NTSC formula: 0.299∙Red + 0.587∙Green + 0.114∙Blue
                double NTSCvalue = 0.299 * mycolor.getRed() + 0.587 * mycolor.getGreen() + 0.114 * mycolor.getBlue();
                arr[i][j] = (int) NTSCvalue;
            }
        }
        return arr;
        
    }

    public void print2DArrToFile(int[][] arr) {

        // Try making a new txt file to write to
        try {
            File output = new File("Art.txt");
            if (output.createNewFile()) {
                System.out.println("File created:" + output.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("Art.txt");
            for (int[] x : arr) {
                for (int y : x) {
                    if (y < 50) {
                        myWriter.write(" ");
                    } else if (y <= 100) {
                        myWriter.write(" ");
                    } else if (y <= 150) {
                        myWriter.write(".");
                    } else if (y <= 200) {
                        myWriter.write("0");
                    } else if (y <= 255) {
                        myWriter.write("$");
                    }
                    
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        
        }
    }
}


