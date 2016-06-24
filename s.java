
import java.awt.*;
import javax.swing.JApplet;
import java.util.*;
import java.awt.event.*;

public class s extends JApplet implements Runnable, MouseListener { 
    Thread t;   
    int timeStep = 100;
    ArrayList<Target> targs = new ArrayList<Target>();
    long currentTime;
    boolean done = false;
    public void init() {
        // populate Targets
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            targs.add(new HorizontalTarget(r.nextDouble()*500, r.nextDouble()*500, 5, r.nextDouble()*20)); 

        }

        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            targs.add(new BlinkingTarget(r.nextDouble()*500, r.nextDouble()*500, 5, r.nextDouble()*20));
        }
        currentTime= System.currentTimeMillis();
        addMouseListener(this);
        t = new Thread(this);
        t.start();
        repaint();
    }

    public void paint (Graphics g) {
        g.clearRect(0,0,800,800);
        if (done){
            g.clearRect (0,0,800,800);
            g.drawString ("You lose", 500, 500);
        }
        else{
            g.drawImage(getImage(getDocumentBase(),"Stadium.JPG"), 0, 0,800, 800, this);
            for (Target a: targs) {
                if(a instanceof HorizontalTarget) {

                    g.drawImage(getImage(getDocumentBase(),"football.png"),a.getX(),a.getY(), 40,40, this);
                }
                else if (a instanceof BlinkingTarget){

                    g.drawImage(getImage(getDocumentBase(),"Baseball.png"),a.getX(), a.getY(), 40,40, this);
                }   
            }
            g.drawString ("Timer: "+(30000-(System.currentTimeMillis()-currentTime))/1000,100, 100);
            
            // loop through the ArrayList, draw a circle at each element
        }
        
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < targs.size(); i++) {
            if (x >= targs.get(i).getX() && y >= targs.get(i).getY() && x <= targs.get(i).getX()+40 && y <= targs.get(i).getY()+40){
                targs.remove(i);}
        }
        for (int i = 0; i < targs.size(); i++) {
            if (x >= targs.get(i).getX() && y >= targs.get(i).getY() && x <= targs.get(i).getX()+40 && y <= targs.get(i).getY()+40){
                targs.remove(i);}
        }

        repaint();
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void run() {
        try {
            while(true) {
                if (System.currentTimeMillis() > currentTime + 30000){
                    done=true;
                    repaint();
                    break;
                }

                // loop through the ArrayList, calling the move() method for each
                for (Target a:targs){
                    a.move();
                }

                repaint();
                t.sleep(timeStep); 

            }
        }catch (InterruptedException e) {}

    }
}