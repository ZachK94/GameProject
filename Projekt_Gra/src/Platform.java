import java.awt.*;

public class Platform {
    int x;
    int y;
    int sizeX;
    int sizeY;
    int vX = 1;


    public Platform (int x, int y, int sizeX, int sizeY){
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }



    public void drawPlatform(Graphics g) {
        g.setColor(Color.decode("#99004C"));
        g.fillRect(x, y, sizeX, sizeY);
    }

    public void platformMoving(){
        if (x >= 1000 - sizeX || x <= 0) {
            vX = -vX;
        }
        x += vX;
    }



    public boolean checkCollisionFromRight(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX  && panda.y + panda.sizeY > y && panda.y < y + sizeY){
            panda.x = x - panda.sizeX;
            return true;
        }
        return false;
    }


    public boolean checkCollisionFromLeft(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX && panda.y + panda.sizeY > y && panda.y < y + sizeY){
            panda.x = x + sizeX;
            return true;
        }
        return false;
    }

    public boolean checkCollisionFromTop(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX && panda.y + panda.sizeY > y && panda.y < y + sizeY){
            panda.y = y - panda.sizeY;
            return true;
        }
        return false;
    }

    public boolean checkCollisionFromBottom(Panda panda){
        if (panda.x + panda.sizeX > x && panda.x < x + sizeX && panda.y < y + sizeY && panda.y > y){
            panda.y = y + panda.sizeY;
            return true;
        }
        else
            return false;
    }


}
