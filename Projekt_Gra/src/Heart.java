import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Heart {
    int x;
    int y;
    int sizeX = 40;
    int sizeY = 40;
    boolean visibleHeart = true;



    public Heart (int x, int y){
        this.x = x;
        this.y = y;
    }

    BufferedImage img = null;

    {
        try {
            img = ImageIO.read(getClass().getResource("Heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void drawHeart(Graphics g) {
        if (visibleHeart){

            g.drawImage(img, x, y, sizeX, sizeY,null);

        }

    }


    public void isVisible(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX && panda.y + panda.sizeY > y && panda.y < y + sizeY)
            visibleHeart = false;
        else
            visibleHeart = true;
    }

    public boolean isVisibleHeart(){
        return visibleHeart;
    }


}
