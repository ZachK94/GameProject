import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Panda {
    int sizeY = 100;
    int sizeX = 80;
    int x = 0;
    int y = 700;
    boolean collisionWithBomb;

    BufferedImage img = null;

    {
        try {
            img = ImageIO.read(getClass().getResource("panda.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //wyrysowanie pandy
    public void drawPanda(Graphics g) {
        if (!collisionWithBomb){

            g.drawImage(img, x, y, sizeX, sizeY,null );
//                g.drawImage(ImageIO.read(new File("Resource/panda.png")), x, y, sizeX, sizeY,null);

        }


    }

    //wyrysowanie eksplozji
    public void drawPandaExplosion(Graphics g) {

        try {
            g.drawImage(ImageIO.read(getClass().getResource("boom.png")), x, y, sizeX, sizeY, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //sprawdzenie kolizji z bomba
    public void explosion(Bomb bomb){
        if (bomb.x + bomb.sizeX > x && bomb.x < x + sizeX && bomb.y + bomb.sizeY > y && bomb.y < y + sizeY)
            collisionWithBomb = true;
        else
            collisionWithBomb = false;
    }





}




