package exercises.holyMoly;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public static void main (String[] Args){
        new GameWindow();
    }

    private GameWindow(){

        super.add(new Game());
        super.setTitle("HolyMoly");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(850,575);
        super.setBackground(Color.blue);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }
}

