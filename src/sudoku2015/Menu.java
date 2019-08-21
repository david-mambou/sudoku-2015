package sudoku2015;

import javax.swing.JFrame;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.net.URL;
import javax.swing.ImageIcon;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Menu extends JFrame{

    JButton playButton;
    JButton helpButton;
    JButton creditsButton;
    
    
    
    public Menu () {
        setBak();   
        this.setLayout(null);
        setSize(800,600);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("SUDOKU");
        
        /*Ajouter le titre "Sudoku" 
        * Ajouter image pour le titre
        */
        JLabel titrejeu = new JLabel();
        ImageIcon image =new ImageIcon("media/sudoku.png");
        titrejeu.setIcon(image);
        titrejeu.setBounds(new Rectangle(200,80,500,180));
        this.add(titrejeu);
        
        /*Ajouter les boutons
        *Mettre les icons pour les boutons
        */
        ImageIcon img1 =new ImageIcon("media/00play.png");
        ImageIcon img2 =new ImageIcon("media/00help.png");
        ImageIcon img3 =new ImageIcon("media/00credits.png");
        playButton = new JButton(img1);
        helpButton = new JButton(img2);
        creditsButton = new JButton(img3);
        playButton.setBounds(new Rectangle(300,250,200,70));
        helpButton.setBounds(new Rectangle(300,350,200,70));
        creditsButton.setBounds(new Rectangle(250,450,320,70));
        this.add(playButton);
        this.add(helpButton);
        this.add(creditsButton);
        
        // Ajouter les ActionListener pour les boutons
        
        ActionListener MenuButtons = new BoutonsMenu();
        
        playButton.addActionListener(MenuButtons);
        helpButton.addActionListener(MenuButtons);
        creditsButton.addActionListener(MenuButtons);
               
        
        // Ajouter la musique 
        // I have to add here an instruction to stop any previously playing music.

                       
    	try {
            File musiqueDeFond = new File("media/BGMusic.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(musiqueDeFond);
			Clip music =AudioSystem.getClip();
			music.open(audio);
			music.loop(-1);
        } catch (Exception ex) {}
    
    }
    // Ajouter Background 

    public void setBak() { 
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("media/1.png");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
    }
    
    
    public class BoutonsMenu implements ActionListener {


        /*Méthode pour gerer les actions sur les boutons 
         *Fermer le menu et ouvrir les fenentres correspondant aux boutons
         * @param e : ActionEvent
         */
        
        
        public void actionPerformed (ActionEvent e ){
            if (e.getSource()==playButton){
		setVisible(false);
                PartieC NouvellePartie= new PartieC();         
                
            }   
	
            if (e.getSource() == helpButton) {
		
                JOptionPane.showMessageDialog(null, "Le but du jeu est de remplir la grille avec une série de chiffres tous différents, \nqui ne se trouvent jamais plus d’une fois sur une même ligne, dans une même colonne \nou dans une même sous-grille. \nLa plupart du temps, les symboles sont des chiffres allant de 1 à 9, \nles sous-grilles étant alors des carrés de 3 × 3. \nQuelques symboles sont déjà disposés dans la grille, \nce qui autorise une résolution progressive du problème complet.","HELP", JOptionPane.INFORMATION_MESSAGE);   
              
                
            }
        
            if (e.getSource() == creditsButton){
		
                JOptionPane.showMessageDialog(null, "    WANG Mengying \n    ZENG Yiran \n    VU Duc \n    MAMBOU FOTIE David  ","CREDITS", JOptionPane.PLAIN_MESSAGE);   
            }
        }
        
    }
	
    
    
    
    public static void main (String [] args){
        Menu menuA = new Menu();		
    }
}	
	
	