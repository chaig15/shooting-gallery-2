import java.util.Random;
import java.util.ArrayList;
public class BlinkingTarget extends Target
{
    int time = 0;
    // instance variables - replace the example below with your own

    BlinkingTarget (double x, double y, double v, double theta) {
        this.x=x;
        this.y=y;
        this.v=v;
        this.theta=theta;
    }

    public void move(){
        if (time > 5) {
            Random r = new Random();
            x = (double)r.nextInt(500);
            y = (double)r.nextInt(500);
            v = 10;
            theta = 10;
            time =0;
        }
        else {
        time++;
    }
    }
}