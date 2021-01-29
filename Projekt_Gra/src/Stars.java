import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Stars {
    int x;
    int y;
    int sizeX = 40;
    int sizeY = 40;
    boolean visible = true;



    public Stars (int x, int y){
        this.x = x;
        this.y = y;
    }


    BufferedImage img = null;

    {
        try {
            img = ImageIO.read(getClass().getResource("star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void drawStars(Graphics g) {
        if (visible){
            g.drawImage(img, x, y, sizeX, sizeY,null);
        }
    }


    public void isVisible(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX && panda.y + panda.sizeY > y && panda.y < y + sizeY)
            visible = false;
        else
            visible = true;
    }

}
