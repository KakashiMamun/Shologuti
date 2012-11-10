/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author school
 */


/*
public class Players{
 
    public Player[] player = new Player[2] ;
    
    public Players()
    {
        this.player[0] = new Player();
        this.player[1] = new Player();
    }
   
   
    public void setPlayer(Court c)
    {
        
      //Sets default Chips for Player1
      this.player[0].ImgLink = "C:\\a/5.png"; // Image of chip
        
      this.player[0].chips[0].Pos = c.nodes[0];
      this.player[0].chips[1].Pos = c.nodes[1];
      this.player[0].chips[2].Pos = c.nodes[2];
      
      this.player[0].chips[3].Pos = c.nodes[3];
      this.player[0].chips[4].Pos = c.nodes[4];
      this.player[0].chips[5].Pos = c.nodes[5];
      
      this.player[0].chips[6].Pos = c.nodes[6];
      this.player[0].chips[7].Pos = c.nodes[7];
      this.player[0].chips[8].Pos = c.nodes[8];
      this.player[0].chips[9].Pos = c.nodes[9];
      this.player[0].chips[10].Pos = c.nodes[10];
      
      
      this.player[0].chips[11].Pos = c.nodes[11];
      this.player[0].chips[12].Pos = c.nodes[12];
      this.player[0].chips[13].Pos = c.nodes[13];
      this.player[0].chips[14].Pos = c.nodes[14];
      this.player[0].chips[15].Pos = c.nodes[15];
      
      for(int i=0;i<16;i++) c.nodes[i].status = NodeStatus.GutiOfP1;
     
      //Sets default Chips for Player1
      this.player[1].ImgLink = "C:\\a/3.png"; //Image of chip
      
      this.player[1].chips[0].Pos = c.nodes[21];
      this.player[1].chips[1].Pos = c.nodes[22];
      this.player[1].chips[2].Pos = c.nodes[23];
      this.player[1].chips[3].Pos = c.nodes[24];
      this.player[1].chips[4].Pos = c.nodes[25];
      
      this.player[1].chips[5].Pos = c.nodes[26];
      this.player[1].chips[6].Pos = c.nodes[27];
      this.player[1].chips[7].Pos = c.nodes[28];
      this.player[1].chips[8].Pos = c.nodes[29];
      this.player[1].chips[9].Pos = c.nodes[30];
      
      this.player[1].chips[10].Pos = c.nodes[31];
      this.player[1].chips[11].Pos = c.nodes[32];
      this.player[1].chips[12].Pos = c.nodes[33];
      
      this.player[1].chips[13].Pos = c.nodes[34];
      this.player[1].chips[14].Pos = c.nodes[35];
      this.player[1].chips[15].Pos = c.nodes[36];
      
      
      for(int i=21;i<37;i++) c.nodes[i].status = NodeStatus.GutiOfP2;
 
    }

}



class Player{
    
    public Chip[] chips;
    public String ImgLink;
    public Player()
    {
        this.chips = new Chip[16];
        for(int i=0;i<16;i++)
            this.chips[i] = new Chip();
        this.ImgLink = new String();
    }
}


class Chip
{
    public Node Pos;
    public boolean state;
    public boolean isOut; 
    
    public Chip()
    {
        this.Pos = new Node();
    }
}
 */