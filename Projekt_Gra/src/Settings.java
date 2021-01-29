import javax.swing.*;
import java.awt.*;

public class Settings extends JPanel {

    public Settings(){
        setLayout(null);
        JLabel label = new JLabel("USTAWIENIA");
        label.setBounds(50, 50, 100, 100);
        add(label);
    }

    public Dimension getPreferredSize(){
        return new Dimension(1000, 800);
    }
}

