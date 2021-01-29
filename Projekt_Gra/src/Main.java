import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame menu = new JFrame();
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(new Menu(menu));
        menu.setTitle("MENU");
        menu.pack();
    }
}
