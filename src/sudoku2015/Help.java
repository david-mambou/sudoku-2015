package sudoku2015;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Help extends JFrame {

    JLabel jLabelIntro;
    JLabel jLabelRegle1;
    JLabel jLabelRegle2;
    
    JButton menuButton;
    

    
    public Help (){
       
        setSize(800,600);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); 
        
        setBak();
        
        jLabelIntro = new JLabel ("REGLE");
        jLabelRegle1= new JLabel("Le but du jeu est de remplir la grille avec une série de chiffres tous différents, qui ne se trouvent jamais plus d’une fois sur une même ligne, dans une même colonne ou dans une même sous-grille.");
        jLabelRegle2= new JLabel("La plupart du temps, les symboles sont des chiffres allant de 1 à 9, les sous-grilles étant alors des carrés de 3 × 3. Quelques symboles sont déjà disposés dans la grille, ce qui autorise une résolution progressive du problème complet.");
        
        jLabelIntro.setSize(20,20);
        jLabelIntro.setLocation(350,50);
        jLabelIntro.setFont(new Font ("arial", Font.BOLD, 20 ));
        
        jLabelRegle1.setSize(20,20);
        jLabelRegle1.setLocation(20,100);
        jLabelRegle1.setFont(new Font ("arial", Font.BOLD, 20 ));
        
        jLabelRegle2.setSize(20,20);
        jLabelRegle2.setLocation(20,200);
        jLabelRegle2.setFont(new Font ("arial", Font.BOLD, 20 ));
        
        this.add(jLabelIntro);
        this.add(jLabelRegle1);
        this.add(jLabelRegle2);
        
        ImageIcon img1 =new ImageIcon("media/00menu.png");
        menuButton= new JButton (img1);
        
        menuButton.setBounds(new Rectangle(650,450,320,70));
        
        ActionListener MenuButtons = new BoutonsMenu();
        
        menuButton.addActionListener(MenuButtons);
             
        try {
            URL url;
            url = getClass().getResource("media/main.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
                        Clip music =AudioSystem.getClip();
                        music.open(audio);
                        music.loop(-1);
                } catch (Exception ex) {}
        
        
    } 
    
    //Ajouter Background
    
    public void setBak() { 
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("media/1.jpg");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
    } 
    
    public class BoutonsMenu implements ActionListener {


        /*Méthode pour gerer les actions sur les boutons 
         *Fermer le menu et ouvrir les fenentres correspondant aux boutons
         * @param e : ActionEvent
         * @param menu : Frame Menu 
         */
        
        
        public void actionPerformed (ActionEvent e ){
            if (e.getSource()==menuButton){
                setVisible(false);
                Menu menuA = new Menu();         
                
            }
        }
        
    }
        
    
    
    
    public static void main (String [] args){

        Help help;
        help = new Help();

    }
    
   
}
