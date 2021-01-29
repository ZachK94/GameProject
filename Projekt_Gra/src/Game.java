import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game();
        window.add(game);
        window.addKeyListener(game);
        window.setTitle("Panda World");
        window.setVisible(true);
        window.pack();
        game.run();

    }



    double dt = 0.2;
    double gravity = 9.81;
    double speedY = 6.0; //predkosc po y
    double speedX = 1.0; //predkosc po x
    double delta;

    int score = 0;
    int live = 1;

    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;

    boolean win = false;
    boolean gameOver = false;
    boolean run = true;

    boolean explosion;
    boolean starSound = false;
    boolean explosionSound = false;
    boolean musicHeart = false;

    Panda panda;

    Platform platform1;
    Platform platform2;
    Platform platform3;
    Platform platform4;

    ArrayList<Stars> stars;
    ArrayList<Bomb> bombs;
    ArrayList<Heart> heart;

    AudioInputStream audioInputStream;
    AudioInputStream audioInputStream2;
    AudioInputStream audioInputStream3;
    Clip clip;
    Clip clip2;
    Clip clip3;

    int level = 1;
    boolean nextLevel = false;




    public Game() {
//        setFocusable(true);
//        requestFocus();
//        setVisible(true);
//        requestFocusInWindow();
//        addKeyListener(this);
//        this.run();
//        beginning();


    }





    public void beginning(){
        panda = new Panda();
        platform1 = new Platform(1, 300, 400, 30);
        platform2 = new Platform(200, 550, 300, 30);
        platform3 = new Platform(700, 400, 300, 30);
        platform4 = new Platform(400, 150, 400, 30);

        stars = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 4; j++) {
                stars.add(new Stars(j * 200, i * 200));
                stars.add(new Stars(j * 300, i * 250));
            }
        }
        for (int i = 1; i < 6; i++) {
            stars.add(new Stars(i * 160, 90));
        }
        for (int i = 1; i < 5; i++) {
            stars.add(new Stars(i * 200, 690));
        }
//
////        for (int i = 1; i<22; i++){
////            stars.add(new Stars((int)(Math.random()*900), (int)(Math.random()*700)));
////        }
//
        bombs = new ArrayList<>();
        bombs.add(new Bomb(700, 710));
        bombs.add(new Bomb(380, 50));
        bombs.add(new Bomb(100, 200));
        bombs.add(new Bomb(210, 470));

        heart = new ArrayList<>();
        for (int i = 1; i < 2; i++) {
            heart.add(new Heart(i * 550, i * 50));
        }
    }


    //wielkosc okna
    public Dimension getPreferredSize() {
        return new Dimension(1000, 800);
    }

    //tlo
    BufferedImage backgroundImage;

    {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("back1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight(), null);
    }

    //obrazy do tablicy wynikow
    BufferedImage miniHeart = null;
    BufferedImage miniStar = null;

    {
        try {
            miniHeart = ImageIO.read(getClass().getResource("Heart.png"));
            miniStar = ImageIO.read(getClass().getResource("star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    //wyrysowanie
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //tło
        setBackground(Color.LIGHT_GRAY);
        drawBackground(g);
        g.setColor(Color.decode("#99004C"));
        g.fillRect(0, (int) getPreferredSize().getHeight() - 10, (int) getPreferredSize().getWidth(), 10);

        //platformy
        platform1.drawPlatform(g);
        platform2.drawPlatform(g);
        platform3.drawPlatform(g);
        platform4.drawPlatform(g);


        //wybuch
        if (explosion){
            if (live < 1){
                panda.drawPandaExplosion(g);
            }
            if (live >= 1) {
                try {
                    panda.drawPandaExplosion(g);
                    Thread.sleep(30);
                    panda.drawPandaExplosion(g);
                    Thread.sleep(30);
                    panda.drawPandaExplosion(g);
                    Thread.sleep(30);
                    panda.drawPandaExplosion(g);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            //glowny bohater
            panda.drawPanda(g);
        }


        //wyrysowanie obiektow do zbierania
        for (Stars s : stars){
            if (s.visible)
                s.drawStars(g);
        }

        //wyrysowanie bomb
        for (Bomb b : bombs){
            if (b.visibleBomb)
                b.drawBomb(g);
        }

        //wyrysowanie serca
        for (Heart h : heart){
            if (h.isVisibleHeart())
                h.drawHeart(g);
        }

        //tablica wyników
        g.setFont(new Font("BOLD", Font.BOLD, 17));
        g.setColor(Color.decode("#99004C"));

        g.drawImage(miniHeart,905, 46, 15, 15,null);
        g.drawImage(miniStar, 902, 26, 20, 20,null);

        g.drawString("Level: " + level, 905, 20);
        g.drawString( score +" / 21", 940, 40);
        g.drawString(" "+live, 935, 60);

        //inf o wygranej
        if (win){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.setColor(Color.RED);
            g.setFont(new Font("win", Font.BOLD, 100));
            g.drawString("WINNER", 300, 450);
        }

        //inf o przegranej
        if (gameOver){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g.setColor(Color.RED);
            g.setFont(new Font("win", Font.BOLD, 100));
            g.drawString("Game Over", 250, 450);
        }

        //inf o nastemnym poziomie gry
        if (nextLevel) {
            try {
                g.setFont(new Font("win", Font.BOLD, 100));
                g.drawString("Level " + level, 250, 450);
                Thread.sleep(30);
                g.drawString("Level " + level, 250, 450);
                Thread.sleep(30);
                g.drawString("Level " + level, 250, 450);
                Thread.sleep(30);
                g.drawString("Level " + level, 250, 450);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        update();
    }

    public void update(){
        stars.removeIf(s -> !s.visible);

        bombs.removeIf(b -> b.visibleExplosion);

        heart.removeIf(h -> !h.isVisibleHeart());
    }

    //dzwieki
    public void musicStar(){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("star.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public  void musicExplosion(){
        try {
            audioInputStream2 = AudioSystem.getAudioInputStream(getClass().getResource("bombSound.wav"));
            clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream2);
            clip2.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void musicHeart(){
        try {
            audioInputStream3 = AudioSystem.getAudioInputStream(getClass().getResource("extraHeart.wav"));
            clip3 = AudioSystem.getClip();
            clip3.open(audioInputStream3);
            clip3.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    
    public void run() {
        //czas
        long previous = 0;
        long start = 0;

        beginning();

        while (run) {

            //poziomy, zwiekszenie utrudnien
            if (level == 2) {
                platform2.platformMoving();
            }

            if (level == 3) {
                platform2.platformMoving();
                platform3.platformMoving();
            }

            if (level == 4) {
                platform1.platformMoving();
                platform2.platformMoving();
                platform3.platformMoving();
                platform4.platformMoving();
            }

            start = System.nanoTime();

            //ruch w lewo
            if (left) {
                if ((panda.x + panda.sizeX <= getPreferredSize().getWidth() && panda.x >= 0) || !platform1.checkCollisionFromLeft(panda)
                        || !platform2.checkCollisionFromLeft(panda) || !platform3.checkCollisionFromLeft(panda) || !platform4.checkCollisionFromLeft(panda)) {
                    panda.x -= speedX;
                    if (panda.x < 0)
                        panda.x = 0;
                }
            }

            //ruch w prawo
            if (right) {

                if ((panda.x + panda.sizeX <= getPreferredSize().getWidth() && panda.x >= 0) || !platform1.checkCollisionFromRight(panda)
                        || !platform2.checkCollisionFromRight(panda) || !platform3.checkCollisionFromRight(panda) || platform4.checkCollisionFromRight(panda)) {
                    panda.x += speedX;
                    if (panda.x + panda.sizeX > getPreferredSize().getWidth()) {
                        panda.x = (int) getPreferredSize().getWidth() - panda.sizeX;
                    }

                }
            }

            //skok
            if (previous != 0 && up) {
                down = false;
                delta = start - previous;
                panda.y -= (delta / 1000000000) * speedY;
                speedY -= (delta / 1000000000) * gravity;


                if (speedY <= 2 || panda.y <= 0 || platform1.checkCollisionFromBottom(panda) || platform2.checkCollisionFromBottom(panda)
                        || platform3.checkCollisionFromBottom(panda) || platform4.checkCollisionFromBottom(panda)) {
                    up = false;
                    speedY = 5.0;
                    down = true;
                }
            }

            //spadek
            if (down) {
                if (panda.y + panda.sizeY >= getPreferredSize().getHeight() || platform1.checkCollisionFromTop(panda)
                        || platform2.checkCollisionFromTop(panda) || platform3.checkCollisionFromTop(panda) || platform4.checkCollisionFromTop(panda)) {
                    down = false;
                }
                if (panda.y + panda.sizeY < getPreferredSize().getHeight()) {
                    delta = start - previous;
                    speedY += (delta / 1000000000) * gravity;
//                    panda.y += (delta / 1000000000) * speedY;
                    panda.y += dt * speedY;
                }

            }
            //spadek przy ruszajacych sie platformach
            if (platform1.checkCollisionFromTop(panda)
                    || platform2.checkCollisionFromTop(panda) || platform3.checkCollisionFromTop(panda) || platform4.checkCollisionFromTop(panda)){
                down = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            previous = start;

            //ruch bomb
            for (Bomb b : bombs){
                b.bombMoving();
            }

            //sprawdzenie zebrania obiektow, naliczanie wyniu
            for (Stars s : stars) {
                s.isVisible(panda);
                if (!s.visible) {
                    score++;
                    starSound = true;
                    //nastepny poziom
                    if (score == 21 && level < 4) {
                        nextLevel = true;
                    }
                    //wygrana
                    if (score == 21 && level == 4) {
                        win = true;
                        run = false;
                    }
                }
            }

            //sprawdzenie kolizji z bomba
            for (Bomb b : bombs) {
                b.isVisibleBomb(panda);
                panda.explosion(b);
                if (panda.collisionWithBomb) {
                    explosion = true;
                    explosionSound = true;
                }

                //utrata życia
                if (b.visibleExplosion) {
                    live--;
                    //pwarunki przegranej
                    if (live == 0) {
                        gameOver = true;
                        run = false;
                    }
                }
            }

            //zebranie dodatkowego życia
            for (Heart h : heart) {
                h.isVisible(panda);
                if (!h.visibleHeart) {
                    live++;
                    musicHeart = true;
                }
            }
            //uruchamianie efektów muzycznych
            if (starSound) {
                musicStar();
                starSound = false;
            }
            if (explosionSound) {
                musicExplosion();
                explosionSound = false;
            }
            if (musicHeart) {
                musicHeart();
                musicHeart = false;
            }

            //przejście do następnego poziomu gry
            if (nextLevel){
                score = 0;
                level++;
                beginning();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            repaint();

            nextLevel = false;

            //kolizja z bomba
            if (explosion) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                explosion = false;
            }

            //przegrana
            if (gameOver) {
                left = false;
                right = false;
                up = false;
                down = false;
                lose();
            }
            //wygrana
            if (win){
                left = false;
                right = false;
                up = false;
                down = false;
                win();
            }

            update();
        }
    }

    //gdy przegrana, nowa gra
    public void lose() {
        JOptionPane.showMessageDialog(null, String.format("Game Over\nZdobyłeś : %d/21 punktów ", score));
        score = 0;
        live = 1;
        level = 1;
        gameOver = false;
        run = true;
        beginning();
        new Game();
        repaint();
    }

    // gdy wygrana, nowa gra
    public void win(){
        JOptionPane.showMessageDialog(null, String.format("Wygrałeś\nZdobyłeś : %d/21 punktów\n Zagraj ponownie", score));
        run = true;
        score = 0;
        live = 1;
        win = false;
        level = 1;
        beginning();
        new Game();
        repaint();
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            right = true;

        }

        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (key == KeyEvent.VK_SPACE) {
            up = true;

        }

        if (key == KeyEvent.VK_DOWN) {
            down = true;

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            right = false;
            down = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            left = false;
            down = true;
        }

        if (key == KeyEvent.VK_SPACE) {
            up = false;
            down = true;
        }

    }
}
