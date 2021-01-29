import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu extends JPanel{

    JFrame menu;

    BufferedImage backgroundImage;
    BufferedImage pandaImage;
    BufferedImage logo;
    BufferedImage flower;
    {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("back1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            pandaImage = ImageIO.read(getClass().getResource("pandaMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    {
        try {
            logo = ImageIO.read(getClass().getResource("logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    {
        try {
            flower = ImageIO.read(getClass().getResource("star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, (int) getPreferredSize().getWidth(), (int) getPreferredSize().getHeight()+10, null);
        g.drawImage(pandaImage, 50, 500, 200, 300, null);
        g.drawImage(logo, 140, 100, 700, 103, null);
        g.drawImage(flower, 460, 210, 70, 70,  null);
        g.drawImage(flower, 460, 570, 70, 70,  null);
    }

    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        drawBackground(g);
        g.drawString("@Kinga", 950, 790);

    }

    public Menu(JFrame menu){
        this.menu = menu;
        setLayout(null);

        JButton toGame = new JButton("NEW GAME");
        JButton toSettings = new JButton("SETTINGS");
        JButton toExit = new JButton("QUIT");





            toGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame gra = new JFrame();
                gra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Game game = new Game();
                gra.add(game);
                gra.setFocusable(true);
                gra.requestFocusInWindow();
                gra.addKeyListener(game);
                gra.setVisible(true);
                gra.pack();
                menu.dispose();

                game.run();



//                menu.requestFocus();
//                menu.setVisible(true);
//
//                gra.addKeyListener(game);
//                gra.pack();
//                game.run();
//                menu.dispose();
//                game.run();
//                manu = new Thread(game);


//                game.beginning();
//                game.run();
//                menu.dispose();

            }
        });
        toSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame settings = new JFrame();
                settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                settings.add(new Settings());
                settings.setVisible(true);
                settings.pack();
                menu.dispose();

            }
        });



        toExit.addActionListener(e -> System.exit(0));

        toGame.setBounds(350, 300, 300, 50);
        toSettings.setBounds(350, 400, 300, 50);
        toExit.setBounds(350, 500, 300, 50);

        toGame.setBackground(Color.decode("#714D6B"));
        toSettings.setBackground(Color.decode("#714D6B"));
        toExit.setBackground(Color.decode("#714D6B"));

        toGame.setForeground(Color.WHITE);
        toGame.setFont(new Font("Tahoma", Font.BOLD, 18));
        toSettings.setForeground(Color.WHITE);
        toSettings.setFont(new Font("Tahoma", Font.BOLD, 18));
        toExit.setForeground(Color.WHITE);
        toExit.setFont(new Font("Tahoma", Font.BOLD, 18));


        add(toGame);
        add(toSettings);
        add(toExit);

    }

    public Dimension getPreferredSize(){
        return new Dimension(1000, 800);
    }




}

