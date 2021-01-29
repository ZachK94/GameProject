import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bomb {
    int x;
    int y;
    int sizeX = 75;
    int sizeY = 75;
    boolean visibleBomb = true;
    boolean visibleExplosion = false;
    int vY = 2;
    int yUp = y+20;
    int yDown = y-20;

    public Bomb (int x, int y){
        this.x = x;
        this.y = y;
    }

    BufferedImage img = null;

    {
        try {
            img = ImageIO.read(getClass().getResource("bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void drawBomb(Graphics g) {
        if (visibleBomb){

            g.drawImage(img, x, y, sizeX, sizeY, null);

        }
    }


    public void isVisibleBomb(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX && panda.y + panda.sizeY > y && panda.y < y + sizeY) {
            visibleBomb = false;
            visibleExplosion = true;
        }
        else {
            visibleBomb = true;
            visibleExplosion = false;
        }
    }

    //ruch bomb
    public void bombMoving(){
        if (y > yUp || y < yDown){
            vY = -vY;
        }
        y += vY;
    }


}
