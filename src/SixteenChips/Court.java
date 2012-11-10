/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mamun
 */
import java.awt.*;


//To Calculate Line Numbers
enum Line
{
    Line1(1),Line2(2),Line3(3),Line4(4), Line5(5),
    Line6(6), Line7(7),Line8(8),Line9(9), Line10(10),
    Line11(11), Line12(12),Line13(13),Line14(14), Line15(15),
    Line16(16),Line17(17),Line18(18),Line19(19),Line20(20);

//Value to Constants
    private final int No;

    Line(int n)
    {
    	this.No = n;
    }
//to get Constants Value
    public int getNo()
    {
    	return this.No;
    }
}

//Constants Node Status

//Properties of a Node;
enum Status{
NoValue,PropertiesOfPlayer1,PropertiesOfPlayer2;
}

//Properties of a Node;
class Node
{

    public CoOrdinate Pos;      //Position of a Node
    public Status status;       //Save Node's Status
    public boolean clickedOn;   //Check wheather The Node is clicked of not
    public Image[] Guti;        //Image of both Chips
    public Node[] Next;         //Next Nodes Thats are connected with this
    public Line[] inline;       //No of Lines that Goes through the Node
    public int no;              //Serial and Identification Number of the Node

    //Constructor Of a Node. Accepts an Integer that sets the Serial Number of the Node

    public Node(int n)
    {
        Next = new Node[8];
        inline = new Line[4];
        Guti = new Image[2];
        no = n;
        this.status = Status.NoValue;
        for(int i=0;i<8;i++)this.Next[i]=null;
        for(int i=0;i<4;i++)this.inline[i]=null;
        this.clickedOn = false;
    }

    //Set Node's Position to X,Y

    public void setPosition(int x,int y)
    {
    	this.Pos = new CoOrdinate(x,y);
    }

    //Checks if the CoOrdinate(a,b) is present in the Node

    public boolean ifPresent(int a,int b)
    {
        if(((a-this.Pos.getX())*(a-this.Pos.getX())+(b-this.Pos.getY())*(b-this.Pos.getY())-15*15)<=0)
            return true;
        return false;
    }

     //Checks if the CoOrdinate c is present in the Node

    public boolean ifPresent(CoOrdinate c)
    {
        if(((c.getX()-this.Pos.getX())*(c.getX()-this.Pos.getX())+
                (c.getY()-this.Pos.getY())*(c.getX()-this.Pos.getY())-15*15)<=0)
            return true;
        return false;
    }
}


//Sets the Game Court and other variables

public class Court  {

    public  Node[] nodes = new Node[37];                     //Creates Spaces for 37 Nodes

    //public static Node currentnode; //To save the present position.

    public static int[] player = new int[2];                 //Creates Spaces for counters of CHips of individual players
    public static String[] playerImg = new String[2];         //Creates spaces for chips Image Link
    public static Image guti1,guti2;                          //Image of Both Chips
    public static final int NULL = -1;                         //Programmer defined NULL value
   // public Undo undobuffer = new Undo();                       //Buffer for Undo
    public Node[] possiblemove = new Node[8];                   //buffer to hold possible move of a Node
    public static boolean isgameOn = true;                      //Denodes wheather the game is on of off
   // public static boolean isPlayerOn[] = new boolean[2];//detects wheather player is on of off.. 0 for player 1 and 1 for player 2
    public static Status currentplayer = Status.PropertiesOfPlayer1;    //Deects WHich player is on
    public static Status tempHolder = Status.NoValue;               //Holds temp status value of a node
    public static boolean chipkilled = false;                       //wheather a chip is killed or not
    public static boolean AI = false;
    public static boolean aiMove = false;

    //sets Player chips counter and Images link
    public void setPlayer()
    {
        Court.player[0] = 16;
        Court.player[1] = 16;
        Court.playerImg[0] = "resources/images/18.png";
        Court.playerImg[1] = "resources/images/12.png";
    }

    //sets player image and default Chips in default position
    public void setPlayer(Image one, Image two)
    {
        Court.guti1 = one;
        Court.guti2 = two;

        for(int i=0;i<16;i++)
        {
            this.nodes[i].status = Status.PropertiesOfPlayer1;
        }
        for(int i=21;i<37;i++)
        {
            this.nodes[i].status = Status.PropertiesOfPlayer2;
        }
        for(int i=0;i<37;i++)
        {
            this.nodes[i].Guti[0] = one;
            this.nodes[i].Guti[1] = two;
        }

    }


    //*constructor for Court
    //sets Nodes postion coordinate according to the screen positon
    //Nodes next Linked Nodes
    //Nodes intersecting line numbers
    public Court()
    {
     	for(int i=0;i<nodes.length;i++)
      		nodes[i]= new Node(i); //creates 37 Nodes

        // Court.currentnode=this.nodes[0];//statrting Node

         //this.isPlayerOn[0] = true;
         //this.isPlayerOn[1] = false;
         this.isgameOn = true;

        //Node1 connections and Settings

        this.nodes[0].Next[0]=this.nodes[1];
        this.nodes[0].Next[1]=this.nodes[3];

        this.nodes[0].inline[0]=Line.Line1;
        this.nodes[0].inline[1]=Line.Line15;


        //Node2 connections and Settings

        this.nodes[1].Next[0]=this.nodes[0];
        this.nodes[1].Next[1]=this.nodes[2];
        this.nodes[1].Next[2]=this.nodes[4];

        this.nodes[1].inline[0]=Line.Line1;
        this.nodes[1].inline[1]=Line.Line12;


        //Node3 connections and Settings

        this.nodes[2].Next[0]=this.nodes[1];
        this.nodes[2].Next[1]=this.nodes[5];

        this.nodes[2].inline[0]=Line.Line1;
        this.nodes[2].inline[1]=Line.Line18;


        //Node4 connections and Settings

        this.nodes[3].Next[0]=this.nodes[0];
        this.nodes[3].Next[1]=this.nodes[4];
        this.nodes[3].Next[2]=this.nodes[8];

        this.nodes[3].inline[0]=Line.Line2;
        this.nodes[3].inline[1]=Line.Line15;


        //Node5 connections and Settings

        this.nodes[4].Next[0]=this.nodes[1];
        this.nodes[4].Next[1]=this.nodes[3];
        this.nodes[4].Next[2]=this.nodes[5];
        this.nodes[4].Next[3]=this.nodes[8];

        this.nodes[4].inline[0]=Line.Line2;
        this.nodes[4].inline[1]=Line.Line12;


        //Node6 connections and Settings

        this.nodes[5].Next[0]=this.nodes[2];
        this.nodes[5].Next[1]=this.nodes[4];
        this.nodes[5].Next[2]=this.nodes[8];

        this.nodes[5].inline[0]=Line.Line2;
        this.nodes[5].inline[1]=Line.Line18;


        //Node7 connections and Settings

        this.nodes[6].Next[0]=this.nodes[7];
        this.nodes[6].Next[1]=this.nodes[11];
        this.nodes[6].Next[2]=this.nodes[12];

        this.nodes[6].inline[0]=Line.Line3;
        this.nodes[6].inline[1]=Line.Line10;
        this.nodes[6].inline[2]=Line.Line16;


        //Node8 connections and Settings

        this.nodes[7].Next[0]=this.nodes[6];
        this.nodes[7].Next[1]=this.nodes[8];
        this.nodes[7].Next[2]=this.nodes[12];

        this.nodes[7].inline[0]=Line.Line3;
        this.nodes[7].inline[1]=Line.Line11;




        //Node9 connections and Settings

        this.nodes[8].Next[0]=this.nodes[3];
        this.nodes[8].Next[1]=this.nodes[4];
        this.nodes[8].Next[2]=this.nodes[5];
        this.nodes[8].Next[3]=this.nodes[7];
        this.nodes[8].Next[4]=this.nodes[9];
        this.nodes[8].Next[5]=this.nodes[12];
        this.nodes[8].Next[6]=this.nodes[13];
        this.nodes[8].Next[7]=this.nodes[14];

        this.nodes[8].inline[0]=Line.Line3;
        this.nodes[8].inline[1]=Line.Line12;
        this.nodes[8].inline[2]=Line.Line15;
        this.nodes[8].inline[3]=Line.Line18;



        //Node10 connections and Settings

        this.nodes[9].Next[0]=this.nodes[8];
        this.nodes[9].Next[1]=this.nodes[10];
        this.nodes[9].Next[2]=this.nodes[14];

        this.nodes[9].inline[0]=Line.Line3;
        this.nodes[9].inline[1]=Line.Line13;


        //Node11 connections and Settings

        this.nodes[10].Next[0]=this.nodes[9];
        this.nodes[10].Next[1]=this.nodes[14];
        this.nodes[10].Next[2]=this.nodes[15];

        this.nodes[10].inline[0]=Line.Line3;
        this.nodes[10].inline[1]=Line.Line14;
        this.nodes[10].inline[2]=Line.Line19;



        //Node12 connections and Settings

        this.nodes[11].Next[0]=this.nodes[6];
        this.nodes[11].Next[1]=this.nodes[12];
        this.nodes[11].Next[2]=this.nodes[16];

        this.nodes[11].inline[0]=Line.Line4;
        this.nodes[11].inline[1]=Line.Line10;


        //Node13 connections and Settings

        this.nodes[12].Next[0]=this.nodes[6];
        this.nodes[12].Next[1]=this.nodes[7];
        this.nodes[12].Next[2]=this.nodes[8];
        this.nodes[12].Next[3]=this.nodes[11];
        this.nodes[12].Next[4]=this.nodes[13];
        this.nodes[12].Next[5]=this.nodes[16];
        this.nodes[12].Next[6]=this.nodes[17];
        this.nodes[12].Next[7]=this.nodes[18];

        this.nodes[12].inline[0]=Line.Line4;
        this.nodes[12].inline[1]=Line.Line11;
        this.nodes[12].inline[2]=Line.Line16;
        this.nodes[12].inline[3]=Line.Line18;



        //Node14 connections and Settings

        this.nodes[13].Next[0]=this.nodes[8];
        this.nodes[13].Next[1]=this.nodes[12];
        this.nodes[13].Next[2]=this.nodes[14];
        this.nodes[13].Next[3]=this.nodes[18];

        this.nodes[13].inline[0]=Line.Line4;
        this.nodes[13].inline[1]=Line.Line12;



        //Node15 connections and Settings

        this.nodes[14].Next[0]=this.nodes[8];
        this.nodes[14].Next[1]=this.nodes[9];
        this.nodes[14].Next[2]=this.nodes[10];
        this.nodes[14].Next[3]=this.nodes[13];
        this.nodes[14].Next[4]=this.nodes[15];
        this.nodes[14].Next[5]=this.nodes[18];
        this.nodes[14].Next[6]=this.nodes[19];
        this.nodes[14].Next[7]=this.nodes[20];

        this.nodes[14].inline[0]=Line.Line4;
        this.nodes[14].inline[1]=Line.Line13;
        this.nodes[14].inline[2]=Line.Line15;
        this.nodes[14].inline[3]=Line.Line19;



        //Node16 connections and Settings

        this.nodes[15].Next[0]=this.nodes[10];
        this.nodes[15].Next[1]=this.nodes[14];
        this.nodes[15].Next[2]=this.nodes[20];

        this.nodes[15].inline[0]=Line.Line4;
        this.nodes[15].inline[1]=Line.Line14;



        //Node17 connections and Settings

        this.nodes[16].Next[0]=this.nodes[11];
        this.nodes[16].Next[1]=this.nodes[12];
        this.nodes[16].Next[2]=this.nodes[17];
        this.nodes[16].Next[3]=this.nodes[21];
        this.nodes[16].Next[4]=this.nodes[22];

        this.nodes[16].inline[0]=Line.Line10;
        this.nodes[16].inline[1]=Line.Line17;
        this.nodes[16].inline[2]=Line.Line18;
        this.nodes[16].inline[3]=Line.Line5;




        //Node18 connections and Settings

        this.nodes[17].Next[0]=this.nodes[12];
        this.nodes[17].Next[1]=this.nodes[16];
        this.nodes[17].Next[2]=this.nodes[18];
        this.nodes[17].Next[3]=this.nodes[22];

        this.nodes[17].inline[0]=Line.Line5;
        this.nodes[17].inline[1]=Line.Line11;



        //Node19 connections and Settings

        this.nodes[18].Next[0]=this.nodes[12];
        this.nodes[18].Next[1]=this.nodes[13];
        this.nodes[18].Next[2]=this.nodes[14];
        this.nodes[18].Next[3]=this.nodes[17];
        this.nodes[18].Next[4]=this.nodes[19];
        this.nodes[18].Next[5]=this.nodes[22];
        this.nodes[18].Next[6]=this.nodes[23];
        this.nodes[18].Next[7]=this.nodes[24];

        this.nodes[18].inline[0]=Line.Line5;
        this.nodes[18].inline[1]=Line.Line12;
        this.nodes[18].inline[2]=Line.Line16;
        this.nodes[18].inline[3]=Line.Line19;




        //Node20 connections and Settings

        this.nodes[19].Next[0]=this.nodes[14];
        this.nodes[19].Next[1]=this.nodes[18];
        this.nodes[19].Next[2]=this.nodes[20];
        this.nodes[19].Next[3]=this.nodes[24];

        this.nodes[19].inline[0]=Line.Line5;
        this.nodes[19].inline[1]=Line.Line13;




        //Node21 connections and Settings

        this.nodes[20].Next[0]=this.nodes[14];
        this.nodes[20].Next[1]=this.nodes[15];
        this.nodes[20].Next[2]=this.nodes[19];
        this.nodes[20].Next[3]=this.nodes[24];
        this.nodes[20].Next[4]=this.nodes[25];

        this.nodes[20].inline[0]=Line.Line5;
        this.nodes[20].inline[1]=Line.Line14;
        this.nodes[20].inline[2]=Line.Line15;
        this.nodes[20].inline[3]=Line.Line20;


        //Node22 connections and Settings

        this.nodes[21].Next[0]=this.nodes[16];
        this.nodes[21].Next[1]=this.nodes[22];
        this.nodes[21].Next[2]=this.nodes[26];

        this.nodes[21].inline[0]=Line.Line6;
        this.nodes[21].inline[1]=Line.Line10;


        //Node23 connections and Settings

        this.nodes[22].Next[0]=this.nodes[16];
        this.nodes[22].Next[1]=this.nodes[17];
        this.nodes[22].Next[2]=this.nodes[18];
        this.nodes[22].Next[3]=this.nodes[21];
        this.nodes[22].Next[4]=this.nodes[23];
        this.nodes[22].Next[5]=this.nodes[26];
        this.nodes[22].Next[6]=this.nodes[27];
        this.nodes[22].Next[7]=this.nodes[28];

        this.nodes[22].inline[0]=Line.Line6;
        this.nodes[22].inline[1]=Line.Line11;
        this.nodes[22].inline[2]=Line.Line17;
        this.nodes[22].inline[3]=Line.Line19;


        //Node24 connections and Settings

        this.nodes[23].Next[0]=this.nodes[18];
        this.nodes[23].Next[1]=this.nodes[22];
        this.nodes[23].Next[2]=this.nodes[24];
        this.nodes[23].Next[3]=this.nodes[28];

        this.nodes[23].inline[0]=Line.Line6;
        this.nodes[23].inline[1]=Line.Line12;


        //Node25 connections and Settings

        this.nodes[24].Next[0]=this.nodes[18];
        this.nodes[24].Next[1]=this.nodes[19];
        this.nodes[24].Next[2]=this.nodes[20];
        this.nodes[24].Next[3]=this.nodes[23];
        this.nodes[24].Next[4]=this.nodes[25];
        this.nodes[24].Next[5]=this.nodes[28];
        this.nodes[24].Next[6]=this.nodes[29];
        this.nodes[24].Next[7]=this.nodes[30];

        this.nodes[24].inline[0]=Line.Line6;
        this.nodes[24].inline[1]=Line.Line13;
        this.nodes[24].inline[2]=Line.Line16;
        this.nodes[24].inline[3]=Line.Line20;


        //Node26 connections and Settings

        this.nodes[25].Next[0]=this.nodes[20];
        this.nodes[25].Next[1]=this.nodes[24];
        this.nodes[25].Next[2]=this.nodes[30];

        this.nodes[25].inline[0]=Line.Line6;
        this.nodes[25].inline[1]=Line.Line14;


        //Node27 connections and Settings

        this.nodes[26].Next[0]=this.nodes[21];
        this.nodes[26].Next[1]=this.nodes[22];
        this.nodes[26].Next[2]=this.nodes[27];

        this.nodes[26].inline[0]=Line.Line7;
        this.nodes[26].inline[1]=Line.Line10;
        this.nodes[26].inline[2]=Line.Line19;


        //Node28 connections and Settings

        this.nodes[27].Next[0]=this.nodes[22];
        this.nodes[27].Next[1]=this.nodes[26];
        this.nodes[27].Next[2]=this.nodes[28];

        this.nodes[27].inline[0]=Line.Line7;
        this.nodes[27].inline[1]=Line.Line11;


        //Node29 connections and Settings

        this.nodes[28].Next[0]=this.nodes[22];
        this.nodes[28].Next[1]=this.nodes[23];
        this.nodes[28].Next[2]=this.nodes[24];
        this.nodes[28].Next[3]=this.nodes[27];
        this.nodes[28].Next[4]=this.nodes[29];
        this.nodes[28].Next[5]=this.nodes[31];
        this.nodes[28].Next[6]=this.nodes[32];
        this.nodes[28].Next[7]=this.nodes[33];

        this.nodes[28].inline[0]=Line.Line7;
        this.nodes[28].inline[1]=Line.Line12;
        this.nodes[28].inline[2]=Line.Line17;
        this.nodes[28].inline[3]=Line.Line20;



        //Node30 connections and Settings

        this.nodes[29].Next[0]=this.nodes[24];
        this.nodes[29].Next[1]=this.nodes[28];
        this.nodes[29].Next[2]=this.nodes[30];

        this.nodes[29].inline[0]=Line.Line7;
        this.nodes[29].inline[1]=Line.Line13;



        //Node31 connections and Settings

        this.nodes[30].Next[0]=this.nodes[24];
        this.nodes[30].Next[1]=this.nodes[25];
        this.nodes[30].Next[2]=this.nodes[29];

        this.nodes[30].inline[0]=Line.Line7;
        this.nodes[30].inline[1]=Line.Line14;
        this.nodes[30].inline[2]=Line.Line16;



        //Node32 connections and Settings

        this.nodes[31].Next[0]=this.nodes[28];
        this.nodes[31].Next[1]=this.nodes[32];
        this.nodes[31].Next[2]=this.nodes[34];

        this.nodes[31].inline[0]=Line.Line8;
        this.nodes[31].inline[1]=Line.Line20;



        //Node33 connections and Settings

        this.nodes[32].Next[0]=this.nodes[28];
        this.nodes[32].Next[1]=this.nodes[31];
        this.nodes[32].Next[2]=this.nodes[33];
        this.nodes[32].Next[3]=this.nodes[35];

        this.nodes[32].inline[0]=Line.Line8;
        this.nodes[32].inline[1]=Line.Line12;



        //Node34 connections and Settings

        this.nodes[33].Next[0]=this.nodes[28];
        this.nodes[33].Next[1]=this.nodes[32];
        this.nodes[33].Next[2]=this.nodes[36];

        this.nodes[33].inline[0]=Line.Line8;
        this.nodes[33].inline[1]=Line.Line17;



        //Node35 connections and Settings

        this.nodes[34].Next[0]=this.nodes[31];
        this.nodes[34].Next[1]=this.nodes[35];

        this.nodes[34].inline[0]=Line.Line9;
        this.nodes[34].inline[1]=Line.Line20;


        //Node36 connections and Settings

        this.nodes[35].Next[0]=this.nodes[32];
        this.nodes[35].Next[1]=this.nodes[34];
        this.nodes[35].Next[2]=this.nodes[36];

        this.nodes[35].inline[0]=Line.Line9;
        this.nodes[35].inline[1]=Line.Line12;



        //Node37 connections and Settings

        this.nodes[36].Next[0]=this.nodes[33];
        this.nodes[36].Next[1]=this.nodes[35];

        this.nodes[36].inline[0]=Line.Line9;
        this.nodes[36].inline[1]=Line.Line17;






        // Setting the co-ordinate of all nodes according to the screen size and position

        this.nodes[0].setPosition(104,48);
        this.nodes[1].setPosition(248,48);
        this.nodes[2].setPosition(386,48);

        this.nodes[3].setPosition(161,87);
        this.nodes[4].setPosition(247,87);
        this.nodes[5].setPosition(333,87);

        this.nodes[6].setPosition(46,149);
        this.nodes[7].setPosition(144,149);
        this.nodes[8].setPosition(247,149);
        this.nodes[9].setPosition(356,149);
        this.nodes[10].setPosition(457,149);

        this.nodes[11].setPosition(46,222);
        this.nodes[12].setPosition(144,222);
        this.nodes[13].setPosition(247,222);
        this.nodes[14].setPosition(356,222);
        this.nodes[15].setPosition(457,222);

        this.nodes[16].setPosition(46,296);
        this.nodes[17].setPosition(144,296);
        this.nodes[18].setPosition(247,296);
        this.nodes[19].setPosition(356,296);
        this.nodes[20].setPosition(457,296);

        this.nodes[21].setPosition(46,370);
        this.nodes[22].setPosition(144,370);
        this.nodes[23].setPosition(247,370);
        this.nodes[24].setPosition(356,370);
        this.nodes[25].setPosition(457,370);

        this.nodes[26].setPosition(46,449);
        this.nodes[27].setPosition(144,449);
        this.nodes[28].setPosition(247,449);
        this.nodes[29].setPosition(356,449);
        this.nodes[30].setPosition(457,449);

        this.nodes[31].setPosition(162,510);
        this.nodes[32].setPosition(247,510);
        this.nodes[33].setPosition(326,510);

        this.nodes[34].setPosition(104,552);
        this.nodes[35].setPosition(247,552);
        this.nodes[36].setPosition(384,552);



        /*
        this.nodes[0].setPosition(81,33);
        this.nodes[1].setPosition(196,33);
        this.nodes[2].setPosition(310,33);

        this.nodes[3].setPosition(127,74);
        this.nodes[4].setPosition(196,74);
        this.nodes[5].setPosition(266,74);

        this.nodes[6].setPosition(31,143);
        this.nodes[7].setPosition(109,143);
        this.nodes[8].setPosition(197,143);
        this.nodes[9].setPosition(281,143);
        this.nodes[10].setPosition(364,143);

        this.nodes[11].setPosition(30,219);
        this.nodes[12].setPosition(110,219);
        this.nodes[13].setPosition(197,219);
        this.nodes[14].setPosition(280,219);
        this.nodes[15].setPosition(366,219);

        this.nodes[16].setPosition(30,296);
        this.nodes[17].setPosition(110,296);
        this.nodes[18].setPosition(197,297);
        this.nodes[19].setPosition(281,296);
        this.nodes[20].setPosition(365,296);

        this.nodes[21].setPosition(29,374);
        this.nodes[22].setPosition(110,374);
        this.nodes[23].setPosition(195,374);
        this.nodes[24].setPosition(281,374);
        this.nodes[25].setPosition(366,374);

        this.nodes[26].setPosition(29,452);
        this.nodes[27].setPosition(110,453);
        this.nodes[28].setPosition(193,454);
        this.nodes[29].setPosition(281,454);
        this.nodes[30].setPosition(367,454);

        this.nodes[31].setPosition(128,517);
        this.nodes[32].setPosition(195,517);
        this.nodes[33].setPosition(252,517);

        this.nodes[34].setPosition(77,562);
        this.nodes[35].setPosition(195,563);
        this.nodes[36].setPosition(298,560);
        */

        //for(int i=0; i<undobuffer.length; i++)
          //  undobuffer[i] = new Undo();
    }

 //checks if the CoOrdinate (a,b) is in the existing Nodes
    public int checkGutiOn(int a, int b)
    {
        for(int i=0;i<37;i++)
        {
            if(this.nodes[i].ifPresent(a, b)==true)
            {
                return this.nodes[i].no;
            }
        }
            return NULL;
    }

    //Checks if b number Node is in the Next Nodes of a number Node
    public boolean inNext(int a, int b)
    {
        for(int i=0;i<this.nodes[a].Next.length && this.nodes[a].Next[i] != null;i++)
        {
            if(this.nodes[b] == this.nodes[a].Next[i])
                return true;
        }
        return false;
    }


   //checks if No a and No b nodes are in the same line
    public boolean inLine(int a, int b)
    {
        for(int i=0;i<this.nodes[b].inline.length && this.nodes[b].inline[i] != null;i++)
        {
            for(int j=0;j<this.nodes[a].inline.length && this.nodes[a].inline[j] != null;j++)
            {
                if(this.nodes[b].inline[i] == this.nodes[a].inline[j])
                    return true;//this.nodes[a].inline[i];
            }
        }
    return false;
    }

    //Checks if the inLine is one of the intersecting Line of Node a
    public boolean isInTheLine(Node a,Line inline)
    {
        for(int i=0;i<a.inline.length && a.inline[i] != null;i++)
        {
                if(a.inline[i] == inline)
                    return true;
        }
    return false;
    }

    //Checks if Nodes are in the same Line or not.. if yes return Line ref. else null
    public Line findCommonLine(Node a, Node b)
            {
        for(int i=0;i<a.inline.length && a.inline[i] != null;i++)
        {
            for(int j=0;j<b.inline.length && b.inline[j] != null;j++)
            {
                if(a.inline[i] == b.inline[j])
                    return a.inline[i];
            }
        }
    return null;
    }


   //return ref of the Node which is inLine with a,and b and next to b which is next of a and has No Chips on it
    public Node findInLineNextNextFreeNode(Node a, Node b)
    {
        Line L = this.findCommonLine(a,b);
        if(L != null)
        {
            for(int i=0;i<b.Next.length && b.Next[i] != null; i++)
            {
               if(this.isInTheLine(b.Next[i],L) == true &&
                       b.Next[i] !=a && b.Next[i].status == Status.NoValue)
                     return b.Next[i];
            }
        }
        return null;
    }


    //Finds the common node which is In line with a and b and next of both a,b
    public Node commonNode(Node a, Node b)
    {
        Line L = findCommonLine(a,b);

        for(int i=0;i<a.Next.length && a.Next[i] != null; i++)
            for(int j=0;j<b.Next.length && b.Next[j] !=null;j++)
                if(a.Next[i] == b.Next[j] && isInTheLine(b.Next[j],L) && isInTheLine(a.Next[i],L))
                    return a.Next[i];
        return null;
    }

    public void flushPossibleMoves()
    {
        for(int i=0;i<8;i++)
            this.possiblemove[i] = null;
    }

    public void insertInPossibleMove(Node a)
    {
        if(a!=null)
        {
        int i;
        for(i= 0;i<8 && this.possiblemove[i] != null;i++)
            if(possiblemove[i] == a)
                return ;
        possiblemove[i] = a;
        }
    }
    public Node[] getPossibleMoves(int node)
    {
        flushPossibleMoves();
        for(int i=0;i<this.nodes[node].Next.length && this.nodes[node].Next[i] !=null;i++)
            if(this.nodes[node].Next[i].status == Status.NoValue)
                insertInPossibleMove(this.nodes[node].Next[i]);
            else if(this.nodes[node].Next[i].status != this.nodes[node].status)
                insertInPossibleMove(this.findInLineNextNextFreeNode(this.nodes[node], this.nodes[node].Next[i]));
        return this.possiblemove;
    }

    public Node[] getPossibleKillerMoves(int node)
    {
        flushPossibleMoves();

        for(int i=0;i<this.nodes[node].Next.length && this.nodes[node].Next[i] !=null;i++)
            if((this.nodes[node].Next[i].status != this.nodes[node].status)
                    && this.nodes[node].Next[i].status != Status.NoValue)
                insertInPossibleMove(this.findInLineNextNextFreeNode(this.nodes[node], this.nodes[node].Next[i]));
        return this.possiblemove;


    }



   /* public boolean check(int a, int b)
    {

        for(int i=0;i<Court.currentnode.Next.length &&  Court.currentnode.Next[i]!=null;i++)
        {
            if(Court.currentnode.Next[i].ifPresent(a,b)==true){
                Court.currentnode = Court.currentnode.Next[i];
                return true;
            }
        }
        return false;
    }*/



    /*public static void main(String[] args)
    {
        Court c=new Court();
        for(int i=0;i<37;i++)
        {

            System.out.println();
            System.out.print(i);
            System.out.print(":");
            for(int j=0;j<8 && c.nodes[i].Next[j]!=null; j++ )
            {
                System.out.print(" ");
                System.out.print(c.nodes[i].Next[j].X);
            }
             System.out.print(" >>>");
            for(int j=0;j<4 && c.nodes[i].inline[j]!=null; j++ )
            {
                System.out.print(" ");
                System.out.print(c.nodes[i].inline[j].getNo());
            }

        }
    }*/



}



