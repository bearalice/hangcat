package hangman;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * This class is used to load images from the Resource folder.
 */
public class Resource {
    /**
     * Method to load image from the given file path.
     * @param path file path of the image.
     * @return an image of BufferedImage.
     */
    public BufferedImage getResourceImage(String path){
        BufferedImage img = null;
        try{
            System.out.println(path);
            img = ImageIO.read(getClass().getClassLoader().getResource(path));
        } catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }
}