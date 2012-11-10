/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;

/**
 *
 * @author Mamun
 */
public class Undo {

  public int from;
  public int to;
  public Status Guti;
  public boolean isused;

  public Undo()
  {
      this.from = 0;
      this.to = 0;
      this.Guti = Status.NoValue;
      this.isused = false;

  }
}

class UndoStack{

    public Undo[] undobuffer;
    public int tos;
    public int size;


    public UndoStack(int n)
    {
        this.tos = 0;
        this.size = n;
        this.undobuffer = new Undo[size];

        for(int i=0;i<size;i++)
            this.undobuffer[i] = new Undo();

    }

    public void push(int to, int from,Status guti)
    {
        this.undobuffer[this.tos] = new Undo();

        undobuffer[this.tos].Guti = guti;
        undobuffer[this.tos].from = from;
        undobuffer[this.tos].to = to;
        this.tos++;
    }

    public void push(Undo u)
    {
        this.undobuffer[this.tos].from = u.from;
        this.undobuffer[this.tos].to = u.to;
        this.undobuffer[this.tos].Guti = u.Guti;
        this.tos++;
    }

    public Undo pop()
    {
        if(this.tos != 0)
            return this.undobuffer[this.tos--];
        else
            return null;
    }

}