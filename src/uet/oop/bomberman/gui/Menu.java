package uet.oop.bomberman.gui;


import javax.swing.*;
import java.awt.*;
import uet.oop.bomberman.Game;


public class Menu extends JFrame {
    private javax.swing.JLabel BG;
    private javax.swing.JTabbedPane Tabble;
    public Menu(){
            initComponents();
            setVisible(true);
	}


    private void initComponents() {
        Tabble = new javax.swing.JTabbedPane();
        
        seticon();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary_Rico");
        setSize(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        setResizable(false);
       
//        Tabble.setBackground(new java.awt.Color(153, 0, 153));
        
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgage/background_Menu.png")));
       
    }

    private void seticon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/textures/iconGame.png")));
    }
}
