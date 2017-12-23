import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapLoader {
    private BufferedImage mapImage;

    MapLoader() {

        try {
            mapImage = ImageIO.read(new File("resources\\img\\mapa_polski_kontur.bmp"));
        } catch (IOException e) {
            System.out.println("Image IOException");
        }
    }

    BufferedImage getMapImage(int width, int height){

        Image img = mapImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return castToBImage(img);

    }

    private BufferedImage castToBImage(Image img){
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
