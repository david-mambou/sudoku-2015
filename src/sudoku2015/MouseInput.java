package sudoku2015;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput  implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
          /**
          playButton = new Rectangle (350,250,100,50);
          helpButton = new Rectangle (350,350,100,50);
          creditsButton = new Rectangle (350,450,100,50); 
         **/
        int mx=e.getX();
        int my= e.getY();
        //Play Button
        if (mx>=350 && mx<=450)
        {
            if (my>=250 && my <=300) 
            {
                //Pressed Play Button
                PartieC partieDeSudoku = new PartieC();

            }
        }
        
        //Help Button
        if (mx>=350 && mx<=450)
        {
            if (my>=350 && my <=400) 
            {
                //Pressed Help Button
                Help help;
                help = new Help();
            }
        }
        //Credits Button
        if (mx>=350 && mx<=450)
        {
            if (my>=450 && my <=500) 
            {
                //Pressed Credits Button

                Credits credits;
                credits = new Credits();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
     
        
    }
    

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}

