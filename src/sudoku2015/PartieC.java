package sudoku2015;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import java.util.LinkedList;
import java.util.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;

public class PartieC extends JFrame {
    
    JPanel jp;
    JPanel jpjeu;
    JPanel jpmenu;
    JLabel bravo;
    ImageIcon a, b, d, e;
    
    boolean finjeu ;
    Timer timer ;
    long temps ;
    JButton[][] table ;
    int[][] tableval ;
    int[][] followingtableval;
    boolean[][] remplie;
    int n;
    ActionListener BoutonsVariables;
    JPanel PTable;
    Dimension dim;
    boolean fini;
    
    
    
    public PartieC() {
        
        // Gestion de l'ensemble de la fenêtre de jeu
        

        setSize(800,600);
        
           // Ajouter la musique 

                       
    	try {
            URL url;
            url = getClass().getResource("media/BGMusic.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			Clip music =AudioSystem.getClip();
			music.open(audio);
			music.loop(-1);
		} catch (Exception ex) {}
    
        
        
        
        //ajouter le background sur un panel non opaque
        setBak();   
        jp = new JPanel(); 
        jp.setOpaque(false);     
        getContentPane().add(jp);           
        //Layout null pour placer les panels de façon pratique !
        jp.setLayout(null);
        //localisation du panel contenant le jeu
        jpjeu=new JPanel();
        jpjeu.setBounds(new Rectangle(50,30,500,500));
        //localisation du panel contenant le menu
        jpmenu=new JPanel();
        jpmenu.setLayout(null);
        jpmenu.setBounds(new Rectangle(600,0,200,600));
        jpmenu.setOpaque(false);
        jpmenu.setBorder(BorderFactory.createEmptyBorder(10,10,50,25));
        //localisation des boutons dans le panel
        a = new ImageIcon("media/button1.png");
        JButton jb1 = new RButton(a);
        jb1.setBounds(new Rectangle(0,30,75,75));
        jb1.addActionListener(new ActionListener()    
            {public void actionPerformed(ActionEvent e){
                setVisible(false);
                Menu menu = new Menu();
            }
        });
        
        b = new ImageIcon("media/button13.png");
        JButton jb2 = new RButton(b);
        jb2.setBounds(new Rectangle(95,30,75,75));
        jb2.addActionListener(new ActionListener()    
            {public void actionPerformed(ActionEvent e){
                // à attribuer aussi
            }
        });
        
        d = new ImageIcon("media/button1xsolution.png");
        JButton jb3 = new RButton(d);
        jb3.setBounds(new Rectangle(0,120,75,75));
        jb3.addActionListener(new ActionListener()    
            {public void actionPerformed(ActionEvent e){
                ajouteUnChiffre();
            }
        });
        
        e = new ImageIcon("media/buttonsolution.png");
        JButton jb4 = new RButton(e);
        jb4.setBounds(new Rectangle(95,120,75,75));
        jb4.addActionListener(new ActionListener()    
            {public void actionPerformed(ActionEvent e){
                afficheSolution();
                fini = true;
                bravo.setText("Too bad...");
                bravo.setForeground(new Color(204,0,153));
                bravo.setFont(new Font("Ubuntu", Font.BOLD, 25));
                jpmenu.add(bravo);
                jpmenu.repaint();
            }
        });
        
        
        // Message de victoire !
        bravo = new JLabel("Congratulations !");
        bravo.setBounds(new Rectangle(0, 300, 200, 80));
        bravo.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        bravo.setForeground(new Color(102,0,0));
        
        //info de notre equipe
        JLabel membres = new JLabel("ASINSA 2015",JLabel.LEFT);
        membres.setBounds(new Rectangle(0,400,200,80));
        //ajout des boutons
        jpmenu.add(jb1);
        jpmenu.add(jb2);
        jpmenu.add(jb3);
        jpmenu.add(jb4);
        jpmenu.add(membres);
        

        //composition des deux panels
        jp.add(jpjeu);
        jp.add(jpmenu);
        add(jp);
        //temps commence
        
        
        // Gestion du tableau de jeu à proprement parler
        
        n = 9;
        
        
        table = new JButton[n][n];
        remplie = new boolean[n][n];
        finjeu = false ;
        
        PTable = new JPanel();
        PTable.setLayout(new GridLayout(n,n));
        
        
        // création de la table de valeurs par vrai backtracking (mode trois)
        
        tableval = new int[n][n];
        LinkedList l = new LinkedList();
        int indice = 0;
        for(int i=1; i<=n; i++)
            l.add(i);
        for(int i=0; i<n; i++) {
            indice = (int)(Math.random() * (l.size()-1));
            tableval[0][i] = (Integer)(l.get(indice));
            l.remove(indice);
        }
        estValide(1, 0, tableval);
        
        dim = new Dimension(500/9, 500/9);        // dimension des boutons.
        // création de l'actionlistener car s'il n'a pas de nom, on ne peut le retirer
        
        BoutonsVariables = new GestionBoutons();
        
        // création de la table de boutons vide 
            
            
        for(int i=0;i<n;i++) {
            for(int j=0; j<n;j++) {
                table[i][j] = new JButton();
                table[i][j].setPreferredSize(dim);
                PTable.add(table[i][j]);
                table[i][j].addActionListener(BoutonsVariables);
                table[i][j].setBackground(new Color(48,239,255));
                table[i][j].setFont(new Font("Liberation Sans", Font.PLAIN, 22));
                
            }
        }
        
        
        // ajout dans cette table de quelques chiffres au pif !
        // gestion des difficultés en parallèle
        
        
        followingtableval = new int[n][n]; // c'est un tableau contenant la valeur à l'instant t dans une case du tableau
        // parce qu'utiliser JButton.getText() s'avère cafouilleux...
        // edit: hardness level is not implemented in the app
        int difficulte = 3;
        
        if(difficulte == 3) {
            int i, j;
            for(int k=0;k<55;k++) {
                do {
                    i = (int)(Math.random() * (n));
                    j = (int)(Math.random() * (n));
                }
                while(remplie[i][j]);
                table[i][j].setText(Integer.toString(tableval[i][j]));
                followingtableval[i][j] = tableval[i][j];
                table[i][j].removeActionListener(BoutonsVariables);
                remplie[i][j] = true;
                table[i][j].setBackground(new Color(51,0,253));
                table[i][j].setForeground(Color.white);
            }
            
        }
        
        fini = false;
        
        jpjeu.add(PTable);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        // fin du constructeur.
    }
    
    
    // méthodes gérant la génération
    // fonctions qui permettent la vérification de la déjà-affectation d'une valeur dans une ligne, colonne, case
    
    /* Méthode vérifiant qu'un chiffre n'est pas déjà dans la ligne, uniquement pour la génération de tableau de valeurs
     * @param le tableau de valeurs à examiner, le numéro de ligne et la valeur à chercher
     * @return false si la valeur y est déjà, true sinon
     */
    public boolean LigneOk(int[][] tval, int l, int val) {
    	for(int i=0; i<n;i++) {
    		if(tval[l][i] == val) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /* Méthode vérifiant qu'un chiffre n'est pas déjà dans la colonne, uniquement pour la génération de tableau de valeurs
     * @param le tableau de valeurs à examiner, le numéro de colonne et la valeur à chercher
     * @return false si la valeur y est déjà, true sinon
     */
    public boolean ColonneOk(int[][] tval, int c, int val) {
    	for(int i=0; i<n;i++) {
    		if(tval[i][c] == val) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /* Méthode vérifiant qu'un chiffre n'est pas déjà dans la case 3x3, uniquement pour la génération de tableau de valeurs
     * @param le tableau de valeurs à examiner, les numéros de ligne et de colonne de la case, et la valeur à chercher
     * @return false si la valeur y est déjà, true sinon
     */
    public boolean CaseOk(int[][] tval, int l, int c, int val) {
        int lcase = 0, ccase = 0;
        if(0<=l && l<=2)
            lcase = 0;
        else if(3<=l && l<=5)
            lcase = 3;
        else if(6<=l && l<=8)
            lcase = 6;
        if(0<=c && c<=2)
            ccase = 0;
        else if(3<=c && c<=5)
            ccase = 3;
        else if(6<=c && c<=8)
            ccase = 6;
        
        for(int i=lcase; i<lcase+3; i++) {
    		for(int j=ccase; j<ccase+3; j++) {
    			if(tval[i][j] == val)
    				return false;
    		}
    	}
    	return true;
    }
    
    /* Méthode récursive permettant de générer un tableau de valeurs Sudoku-valide par backtracking.
     * Elle permet de tester chaque valeur et de parcourir le tableau, remontant jusqu'au point de blocage quand il y en a.
     * @param la ligne et la colonne de la case courante, ainsi que le tableau en cours de remplissage
     * @return true si la case est remplie avec succès, false sinon
     */
    boolean estValide(int ligne, int col, int [][] table) { // pour que la grille soit 100% valide, il faut obtenir une suite parfaite de true.
        if(ligne == 9) // si on est sorti de la grille tout est ok !
            return true;
        for(int i=1; i<=n; i++) { // on teste tous les chiffres.
            // intégrer la valeur si elle est acceptée
            if(LigneOk(table, ligne, i) && ColonneOk(table, col, i) && CaseOk(table, ligne, col, i)) {
                table[ligne][col] = i;
                if(col == 8) { // si on est à la dernière colonne on passe à la ligne suivante plutôt qu'à la colonne suivante
                    if(estValide(ligne+1, 0, table))
                        return true;
                }
                else {
                    if(estValide(ligne, col+1, table))
                        return true;
                    }
                }
            }
        // Si aucun chiffre ne marche :
        table[ligne][col] = 0;
        return false;
        
    }
    
    // méthodes gérant le jeu
    
    /* Méthode permettant d'afficher dans la grille la réponse, c'est-à-dire tous les bons chiffres
     */
    public void afficheSolution() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                table[i][j].setText(Integer.toString(tableval[i][j]));
                followingtableval[i][j] = tableval[i][j];
                table[i][j].removeActionListener(BoutonsVariables);
                if(!remplie[i][j]) {
                    table[i][j].setBackground(new Color(204,0,153));
                    table[i][j].setForeground(Color.white);
                }
                fini = true;
            }
        }
    }
    /* Méthode permettant d'ajouter à la grille affichée un chiffre exact
     */
    public void ajouteUnChiffre() {
        int i, j;
        if(!fini) {
            do {
                i = (int)(Math.random() * (n));
                j = (int)(Math.random() * (n));
            }
            while(remplie[i][j]);
            table[i][j].setText(Integer.toString(tableval[i][j]));
            table[i][j].removeActionListener(BoutonsVariables);
            followingtableval[i][j] = tableval[i][j];
            remplie[i][j] = true;
            table[i][j].setBackground(Color.green);
            table[i][j].setForeground(Color.white);
            estCeFini();
            if(fini) {
                bravo.setText("Dommage...");
                bravo.setForeground(new Color(204,0,153));
                bravo.setFont(new Font("Ubuntu", Font.BOLD, 25));
                jpmenu.add(bravo);
                jpmenu.repaint();
            }
        }
    }
    
    
    // À tous les clics, il faut vérifier si c'est fini !
    
    /* Méthode vérifiant si le jeu est fini
     */
    public void estCeFini() {
        for(int i=0; i<n;i++) {
            for(int j=0; j<n; j++) {
                if(followingtableval[i][j] != tableval[i][j]) {
                    fini = false;
                    return;
                }
            }
        }
        
        fini = true;
    }
    
    // Méthodes de vérification
    
    /* Méthode vérifiant si le tableau est correctement rempli
     */
    public void estCorrect(){
               
        for(int i = 0;i < 9;i++){
            for(int j=0; i<9; j++) {
                if(verifieColonne(i)&&verifieLigne(j)&&verifieCarre(i,j)){
                    fini = false;
                    return;
                }
            }
        }
        fini = true;
    }
      

    public boolean verifieLigne(int ligne){  
        for(int j = 0;j < n-1;j++){
            if(followingtableval[ligne][j] == 0){
                return false;
            }
            for(int k =j + 1;k< n;k++){
                if(followingtableval[ligne][j] == followingtableval[ligne][k]){
                    return false;
                }
            }
        }
        return true;
    }
      

    public boolean verifieColonne(int colonne){
        for(int j = 0;j < n-1;j++){
            if(followingtableval[j][colonne] == 0){
                return false;
            }
            for(int k =j + 1;k< n;k++){
                if(followingtableval[j][colonne] == followingtableval[k][colonne]){
                    return false;
                }
            }
        }
        return true;
    }
      
      
    public boolean verifieCarre(int ligne,int colonne){
        int j = ligne / 3 * 3;
            int k = colonne /3 * 3;
                for(int i = 0;i < n-1;i++){
                    for(int m = i+ 1;m < n;m++){
                        if(followingtableval[j + i/3][k + i % 3] == followingtableval[j + m/3][k + m % 3]){
                            return false;
                        }
                    }   
                }
                return true;
    }

                         
    // méthode purement graphique
    
    public void setBak() { 
     ((JPanel)this.getContentPane()).setOpaque(false);
      ImageIcon img = new ImageIcon("media/1.png");
      JLabel background = new JLabel(img);
      this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
      background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); 
    }
    
    
    
    public class GestionBoutons implements ActionListener {
        
        
        // à chaque clic dans une case, son chiffre change. C'est ce que font ces ActionListener là
        public void actionPerformed(ActionEvent e) {
            int val = 1;
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(e.getSource() == table[i][j])
                        if(table[i][j].getText().equals("")) {
                            table[i][j].setText("1");
                            followingtableval[i][j] = 1;
                        }
                        else {
                            val = Integer.parseInt(table[i][j].getText());
                            if(val < n) {
                                table[i][j].setText(Integer.toString(val+1));
                                followingtableval[i][j] = val+1;
                            }
                            else {
                                table[i][j].setText("");
                                followingtableval[i][j] = 0;
                            }
                        }
                }
            }
            estCeFini();
            //estCorrect();
            if(fini) {
                jpmenu.add(bravo);
                jpmenu.repaint();
                for(int i=0; i<n;i++) {
                    for(int j=0; j<n; j++) {
                        table[i][j].removeActionListener(BoutonsVariables);
                    }
                }
            }
                
        }
        
    }
}