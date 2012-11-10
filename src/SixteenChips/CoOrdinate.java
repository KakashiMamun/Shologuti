/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;

/**
 *
 * @author Mamun
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mamun
 */

//To calculate specific CoOrdinate
public class CoOrdinate {
    
    private int X,Y;
    public CoOrdinate()
    {
        this.X= 0;
        this.Y = 0;
    }
    public CoOrdinate(int x,int y)
    {
        this.X = x;
        this.Y = y;
        
    }
    public void setX(int x){this.X = x;}
    public void setY(int y) {this.Y = y;}
    public int getX(){return this.X;}
    public int getY(){return this.Y;}
    public void set(int x, int y){this.X = x; this.Y = y;}
    
    public boolean check(CoOrdinate c)
    {
        if((c.getX() == this.X) && (c.getY() == this.Y) )
            return true;
        else
            return false;
    }
    
     public boolean check(int a, int b)
    {
        if((a == this.X) && (b == this.Y) )
            return true;
        else
            return false;
    }
    
}
