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


public class Credits extends JFrame {

    JLabel jLabelIntro;
    JLabel jLabelNom1;
    JLabel jLabelNom2;
    JLabel jLabelNom3;
    JLabel jLabelNom4;
    
    JButton menuButton;
    
   
 
    
    public Credits (){
       
        setSize(800,600);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
                
        setBak();        
        
        jLabelIntro = new JLabel ("Projet Informatique-ASINSA 2014-2015");
        jLabelIntro.setSize(50,50);
        jLabelIntro.setLocation(20, 20);
        jLabelIntro.setFont(new Font ("arial", Font.BOLD, 50 ));
        this.add(jLabelIntro);
        
        jLabelNom1= new JLabel("MAMBOU FOTIE David");
        jLabelNom2= new JLabel("WANG Mengying");
        jLabelNom3= new JLabel("ZENG Yiran");          
        jLabelNom4= new JLabel("VU Duc Quy");
        
        jLabelNom1.setSize(20,20);
        jLabelNom1.setLocation(20,100);
        jLabelNom1.setFont(new Font ("arial", Font.BOLD, 20 ));
        this.add(jLabelNom1);
        
        jLabelNom2.setSize(20,20);
        jLabelNom2.setFont(new Font ("arial", Font.BOLD, 20 ));
        this.add(jLabelNom2);
        
        jLabelNom3.setSize(20,20);
        jLabelNom3.setFont(new Font ("arial", Font.BOLD, 20 ));
        this.add(jLabelNom3);
        
        jLabelNom4.setSize(20,20);
        jLabelNom4.setFont(new Font ("arial", Font.BOLD, 20 ));
        this.add(jLabelNom4);
        
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

        Credits credits;
        credits = new Credits();

    }
}
