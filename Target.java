public abstract class Target
{
    double x,y, v, theta;

 
    int getX() { return (int)x; }
    int getY() { return (int)y; }
    
    public abstract void move();
}