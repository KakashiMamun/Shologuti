/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;


/**
 *
 * @author Mamun
 */
public class AI {

    public boolean mode;
    public Status type;
    public Court c;
    public Node[] nodes = new Node[37];
    public static AiNode[] ainodes = new AiNode[16];
    public static Node[] tempBuffer = new Node[8];
    public static int humanNewMove;
    public static Status aiPlayer = Status.PropertiesOfPlayer2;
    public static int aiplayerChips;
    public static int tempVar;
    public static Queue movesBuffer =  new Queue(Court.NULL);
    public static AiNode nodeToMove;

    public AI(Court c)
    {

        this.mode = true;
        this.c = c;

        for(int i=0;i<37;i++)
            this.nodes[i] = c.nodes[i];

        findAIPlayerNode();

    }

    private void flushAINodes()
    {
        for(int i=0;i<AI.aiplayerChips;i++)
            AI.ainodes[i] = null;

    }

    private AiNode getAiNode(Node a)
    {
        for(int i=0;i<16 && AI.ainodes[i] != null;i++)
            if(AI.ainodes[i].node == a)
                return AI.ainodes[i];
        return null;
    }
    public static void flushTempBuffer()
    {

        for(int i=0;i<8;i++)
            tempBuffer[i] = null;
    }

    public void findAIPlayerNode()
    {
        flushAINodes();
        for(int i=0,j=0;i<37;i++)
        {
            if(this.nodes[i].status == AI.aiPlayer)
            {
                AI.ainodes[j++] =new AiNode(this.nodes[i]);
            }
            AI.aiplayerChips = j;
        }
        //for(int i=0;i<AI.aiplayerChips ;i++)
                //System.out.println(""+ainodes[i].node.no);
    }


     public int countPossibleKillingNumber(Node a)
    {

        System.out.println("In killer counter");
        for(int i=0;i<8 && a.Next[i] != null;i++)
        {
           if(a.Next[i].status == Status.PropertiesOfPlayer2)
           {
               System.out.println(""+a.Next[i].no);
               Node node = findInLineNextNextFreeNode(a,a.Next[i]);

               if(node !=null)
                   return 1;
           }
        }
        return 0;
    }


    public void findVulnarable(int n)
    {
        findAIPlayerNode();

        AI.humanNewMove = n;
        AI.flushTempBuffer();
        int j=0;
        for(int i=0;i<this.nodes[n].Next.length
                && this.nodes[n].Next[i] != null;i++)
        {
            if(this.nodes[n].Next[i].status == AI.aiPlayer )
            {
               // System.out.println(""+this.nodes[n].node.Next[i].no+" "+ this.nodes[n].node.Next[i].status);
                   tempBuffer[j++] = this.nodes[n].Next[i];
            }
        }
        /*System.out.println("in Findvulnarable");
        for(int i=0;i<8 && tempBuffer[i]!=null;i++)
        {
            System.out.println(""+tempBuffer[i].no);
        }*/

        for(int i=0;AI.tempBuffer[i]!=null;i++)
        {
            Node nc = findInLineNextNextFreeNode(this.nodes[n], AI.tempBuffer[i]);

            if(nc != null)
            {
               // System.out.println("free Node: "+nc.no);
                int temp = 0;
                temp= countPossibleKillingNumber(nc)+1;

                //.out.println(temp);
                AiNode ainode = getAiNode(tempBuffer[i]);
                //System.out.println("Temp vul:"+ tempBuffer[i].no);
                //System.out.println(+ainode.node.no);
                if(ainode != null)
                {
                if(ainode.vulnarable_value <= temp)
                    ainode.vulnarable_value = temp;

                System.out.println(ainode.node.no + "Node's Vulnarability value is:"+ ainode.vulnarable_value);
                }
            }
        }
        countNormalMove();
       // countKillerValue();
        calculatePriority();
        calculateMoves();
    }


    public void calculateMoves()
    {
        AI.nodeToMove = AI.ainodes[0];
        for(int i=AI.aiplayerChips-1;i>=0 && AI.ainodes[i] != null; i--)
        {
          // System.out.print(AI.ainodes[i].node.no+"("+AI.ainodes[i].priorityValue+") ");
            if(AI.nodeToMove.priorityValue < AI.ainodes[i].priorityValue)
                AI.nodeToMove = AI.ainodes[i];
            //System.out.println(AI.nodeToMove.node.no+" "+AI.nodeToMove.priorityValue);

        }

        System.out.print("Node to be moved:"+AI.nodeToMove.node.no);
        AI.movesBuffer.Enqueue(AI.nodeToMove.node.no);

        for(int i=0;i<8 && AI.nodeToMove.node.Next[i] != null;i++)
        {
            //System.out.print("next of ntm:"+AI.nodeToMove.node.Next[i].no + " ");
            if(AI.nodeToMove.node.Next[i].status == Status.NoValue)
            {
                if(movableNode(AI.nodeToMove.node.Next[i]) == true)
                {
                    AI.movesBuffer.Enqueue(AI.nodeToMove.node.Next[i].no);
                    System.out.print(AI.nodeToMove.node.Next[i].no);
                    break;
                }
            }
        }
        System.out.println();
    }

    boolean movableNode(Node a)
    {
        for(int i=0;i<8 && a.Next[i] != null;i++)
        {
            if(a.Next[i].status != AI.aiPlayer && a.Next[i].status != Status.NoValue)
            {
                Node node = findInLineNextNextFreeNode(a.Next[i], a);

                if(node != null)
                {
                    //System.out.print("In movable:"+ node.no + " ");
                    return false;
                }
            }
        }
        return true;
    }


    public void countNormalMove()
    {
        for(int i=0,k=0;i<16 && AI.ainodes[i] != null;i++)
        {
          for(int j=0;j<8 && AI.ainodes[i].node.Next[j] != null;j++)
          {
              if(AI.ainodes[i].node.Next[j].status == Status.NoValue)
              {
                  Node node = findInLineNextNextNode(AI.ainodes[i].node,AI.ainodes[i].node.Next[j]);

                  if(node != null)
                  {
                     // System.out.print("In NormalMove:"+ node.no+ " ");
                      if(node.status != AI.aiPlayer && node.status != Status.NoValue)
                      {
                          continue;
                      }
                      else
                      {
                          boolean x = movableNode(AI.ainodes[i].node.Next[j]);
                          if(x == true)
                              AI.ainodes[i].move_value++;

                      }
                  }
                  else
                  {
                      //System.out.print("in else:"+ AI.ainodes[i].node.Next[j].no);
                      boolean x = movableNode(AI.ainodes[i].node.Next[j]);
                      if(x == true)
                          AI.ainodes[i].move_value++;
                  }
              }
          }

          System.out.println("Node No:"+AI.ainodes[i].node.no +" Has "+AI.ainodes[i].move_value+" safe Moves");
        }
    }

    private void checkIfCanKill(Node a,AiNode ainode)
    {
        for(int i=0;i<8 && a.Next[i] != null;i++)
        {
            if(a.Next[i].status != AI.aiPlayer && a.Next[i].status != Status.NoValue)
            {
                System.out.print(a.no+" ");
                Node node = findInLineNextNextFreeNode(a,a.Next[i]);
                if(node != null)
                {
                    System.out.print("("+a.Next[i].no+","+AI.humanNewMove+") ");
                    if(a.Next[i].no == AI.humanNewMove)
                    {
                       ainode.priorityValue = 10;
                       System.out.print("("+ainode.node.no+"|"+ainode.priorityValue+")");
                    }
                    AI.tempVar++;
                    checkIfCanKill(node,ainode);
                }
            }
        }
    }

    public void countKillerValue()
    {
        for(int i=0;i<16 && AI.ainodes[i] != null; i++)
        {
            AI.tempVar = 0;
            checkIfCanKill(AI.ainodes[i].node,AI.ainodes[i]);
            System.out.println();
            AI.ainodes[i].killer_value = AI.tempVar;
        }
    }

    public void calculatePriority()
    {
        for(int i=0;i<16 && AI.ainodes[i] != null;i++)
        {
            if(AI.ainodes[i].move_value != 0)
            {
                AI.ainodes[i].priorityValue += 5*AI.ainodes[i].vulnarable_value + 2* AI.ainodes[i].move_value;
            }
            System.out.println(AI.ainodes[i].node.no + " has "+AI.ainodes[i].priorityValue+" move value.");
        }
    }


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
    public boolean isInTheLine(Node a, Line inline)
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
    public Node findInLineNextNextNode(Node a, Node b)
    {
        Line L = this.findCommonLine(a,b);
        if(L != null)
        {
            for(int i=0;i<b.Next.length && b.Next[i] != null; i++)
            {
               if(this.isInTheLine(b.Next[i],L) == true && b.Next[i] !=a)
                     return b.Next[i];
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
                       b.Next[i] != a && b.Next[i].status == Status.NoValue)
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


}

class AiNode
{
    public Node node;
    public int vulnarable_value;
    public int killer_value;
    public int move_value;
    public boolean movePossible;
    public int priorityValue;



    public AiNode()
    {
        this.node = null;
        this.killer_value = this.move_value = this.priorityValue = this.vulnarable_value = 0;
    }
    public AiNode(Node a)
    {
        this.node = a;
        this.vulnarable_value = this.killer_value = this.move_value = 0;
    }



}