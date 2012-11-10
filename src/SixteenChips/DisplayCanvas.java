/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;

/**
 *
 * @author Mamun
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.util.Scanner;

class DisplayCanvas extends JPanel {

    Court c = new Court();
    AI ai;
    String Player, P1_Chips, P2_Chips;
    int current, previous, temp;
    Node[] maymove = new Node[8];
    int currentBGImg = 0;
    Image[] BGImg;
    Image Guti1, Guti2, animateImg;
    Scanner input = new Scanner(System.in);
    int x, y;

    private ActionListener AImove = new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            if(Court.AI == true && Court.currentplayer == AI.aiPlayer)
            {
                System.out.println("Calls");
                current = AI.movesBuffer.Dequeue();
                inputProcess();
            }
        }
    };

    public DisplayCanvas() {


        URL[] url = new URL[5];
        url[0] = getClass().getResource("resources/images/BG.PNG");
        url[1] = getClass().getResource("resources/images/BG2.PNG");
        url[2] = getClass().getResource("resources/images/BG1.PNG");

        Toolkit tk = Toolkit.getDefaultToolkit();

        this.BGImg = new Image[3];
        this.BGImg[0] = tk.getImage(url[0]);
        this.BGImg[1] = tk.getImage(url[1]);
        this.BGImg[2] = tk.getImage(url[2]);


        addMouseListener(new MouseHandler());
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                if ((ke.getKeyChar() == 'b') | (ke.getKeyChar() == 'B')) {
                    currentBGImg += 1;
                    currentBGImg %= 3;
                    System.out.print(currentBGImg);
                    // System.out.println("kora hyse");
                    repaint();
                }
            }
        });

        addMouseWheelListener(new MouseHandler());


        c.setPlayer();
        url[3] = this.getClass().getResource(Court.playerImg[0]);
        url[4] = this.getClass().getResource(Court.playerImg[1]);
        Guti1 = tk.getImage(url[3]);
        Guti2 = tk.getImage(url[4]);
        animateImg = Guti1;
        c.setPlayer(Guti1, Guti2);
        current = previous = Court.NULL;

        this.ai = new AI(c);

        Dimension size = new Dimension(BGImg[currentBGImg].getHeight(this), BGImg[currentBGImg].getWidth(this));

       new Timer(100,AImove).start();
        //System.out.println(size);


        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(size);
        setSize(size);
        setLayout(null);


      //  MediaTracker mt = new MediaTracker(this);
      ///  mt.addImage(this.BGImg[0], 0);
      //  mt.addImage(this.BGImg[1], 0);
     //   mt.addImage(this.BGImg[2], 0);

      //  try {
        //    mt.waitForAll();
      //  } catch (Exception e) {
      //      System.out.println("Exception while loading image.");
      //  }
    }

    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void drawOpenNodes(Graphics g) {
        for (int i = 0; i <= 36; i++) {
            if (c.nodes[i].status != Status.NoValue) {
                if (c.nodes[i].status == Status.PropertiesOfPlayer1) {
                    g.drawImage(c.nodes[i].Guti[0], c.nodes[i].Pos.getX() - c.nodes[i].Guti[0].getWidth(this) / 2,
                            c.nodes[i].Pos.getY() - c.nodes[i].Guti[0].getHeight(this) / 2, this);
                } else {
                    g.drawImage(c.nodes[i].Guti[1], c.nodes[i].Pos.getX() - c.nodes[i].Guti[1].getWidth(this) / 2,
                            c.nodes[i].Pos.getY() - c.nodes[i].Guti[1].getHeight(this) / 2, this);
                }
            }
        }
    }

    public void drawPossibleNode(Graphics2D g2D) {
        Composite originalComposite = g2D.getComposite();
        g2D.setComposite(makeComposite(0.5F));

        if (current != Court.NULL) {
            for (int i = 0; i < 8 && maymove[i] != null; i++) {
                if (c.nodes[current].status == Status.PropertiesOfPlayer1) {
                    g2D.drawImage(maymove[i].Guti[0], maymove[i].Pos.getX() - maymove[i].Guti[0].getHeight(this) / 2,
                            maymove[i].Pos.getY() - maymove[i].Guti[0].getWidth(this) / 2, this);
                } else if (c.nodes[current].status == Status.PropertiesOfPlayer2) {
                    g2D.drawImage(maymove[i].Guti[1], maymove[i].Pos.getX() - maymove[i].Guti[1].getHeight(this) / 2,
                            maymove[i].Pos.getY() - maymove[i].Guti[1].getWidth(this) / 2, this);
                }
            }
        }
        g2D.setComposite(originalComposite);
    }

    public void drawGameInfo(Graphics2D g2D) {
        Font f = new Font("Tahoma", Font.BOLD, 30);
        g2D.setFont(f);
        P1_Chips = "" + Court.player[0];
        P2_Chips = "" + Court.player[1];
        g2D.setColor(Color.BLACK);
        g2D.drawString("Player 1 :", 550, 120);
        g2D.drawString("Player 2 :", 550, 250);
        f = new Font("Tahoma", Font.PLAIN, 50);
        g2D.setFont(f);
        g2D.drawString(P1_Chips, 700, 120);
        g2D.drawString(P2_Chips, 700, 250);
    }

    public void drawString(Graphics2D g2D) {
        String S = "" + current + " " + previous;
        Font f = new Font("Tahoma", Font.BOLD, 25);
        g2D.setFont(f);
        //Graphics2D g2D = (Graphics2D)this.getGraphics();
        g2D.setColor(Color.BLUE);
        //g2D.drawString(S, 550, 400);

        if (Court.isgameOn == true) {
            if (Court.currentplayer == Status.PropertiesOfPlayer1) {
                g2D.drawString("Turn of Player1", 550, 580);
            } else {
                g2D.drawString("Turn of Player2", 550, 580);
            }
        }

        //g2D.drawString(""+Court.chipkilled, 550, 550);
    }

    public void drawFinishText(Graphics2D g2D) {
        String S1 = "Congratulation!!";
        String S2 = ">>Player 1 Won<<";
        String S3 = ">>Player 2 Won<<";
        Font f = new Font("Tahoma", Font.BOLD, 25);
        g2D.setFont(f);
        //Graphics2D g2D = (Graphics2D)this.getGraphics();
        g2D.setColor(Color.BLUE);
        //g2D.drawString(S, 550, 400);


        if (Court.isgameOn == false) {
            g2D.drawString(S1, 550, 450);
            if (Court.player[1] == 0) {
                g2D.drawString(S2, 550, 550);
            } else if (Court.player[0] == 0) {
                g2D.drawString(S3, 550, 550);
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g.drawImage(BGImg[currentBGImg], 0, 0, null);
        drawOpenNodes(g);
        drawGameInfo(g2D);
        drawString(g2D);
        drawPossibleNode(g2D);
        drawFinishText(g2D);
    }

    public void addInPossibleMoves(Node a) {
        int i;
        for (i = 0; i < maymove.length && maymove[i] != null; i++) {
            if (maymove[i] == a) {
                return;
            }
        }
        maymove[i] = a;
    }

    public boolean isPossibleToMove(int current, int previous) {
        for (int i = 0; i < maymove.length && maymove[i] != null; i++) {
            if (maymove[i] == c.nodes[current]) {
                return true;
            }
        }
        return false;
    }

    public void flushPossibleMove() {
        for (int i = 0; i < maymove.length; i++) {
            maymove[i] = null;
        }
    }

    public void resetVars() {
        current = previous = Court.NULL;
    }

    public void shift(int from, int to) {
        System.out.println("In shift" + to + " " + from);


        if (c.inNext(to, from) == false) {
            Node n = c.commonNode(c.nodes[to], c.nodes[from]);

            if (n != null) {

                if (n.status == Status.PropertiesOfPlayer1) {
                    Court.player[0]--;
                    n.status = Status.NoValue;
                    // c.undobuffer.Guti = Status.PropertiesOfPlayer1;
                } else if (n.status == Status.PropertiesOfPlayer2) {
                    Court.player[1]--;
                    n.status = Status.NoValue;
                    //c.undobuffer.Guti = Status.PropertiesOfPlayer2;
                }
                Court.chipkilled = true;
            }
        } else {
            Court.chipkilled = false;
        }



        if (isPossibleToMove(to, from) == true) {
            c.nodes[to].status = c.nodes[from].status;
            c.nodes[from].clickedOn = false;
            c.nodes[from].status = Status.NoValue;
        }

        if (Court.player[0] == 0 || Court.player[1] == 0) {
            Court.isgameOn = false;
        }

    }

    /* public void print(){
    if(current!=Court.NULL && previous!=Court.NULL)
    System.out.println(+c.nodes[current].no +" "+ c.nodes[previous].no);
    else if(current!=Court.NULL && previous==Court.NULL)
    System.out.println(+c.nodes[current].no +" "+ previous);
    else if(current==Court.NULL && previous!=Court.NULL)
    System.out.println(+current +" "+ c.nodes[previous].no);
    else if(current==Court.NULL && previous==Court.NULL)
    System.out.println(+current +" "+ previous);
    }*/
    public void changePlayer() {
        if (Court.currentplayer == Status.PropertiesOfPlayer1) {
            Court.currentplayer = Status.PropertiesOfPlayer2;
        } else {
            Court.currentplayer = Status.PropertiesOfPlayer1;
        }
    }

    public void valid1stMove() {
        System.out.println("Cond2");

        if (Court.currentplayer == c.nodes[current].status) {
            flushPossibleMove();
            maymove = c.getPossibleMoves(current);
            for (int i = 0; i < 8 && maymove[i] != null; i++) {
                System.out.print(maymove[i].no);
            }
            System.out.println("");

            if (maymove[0] == null) {
            } else {
                if (previous != Court.NULL) {
                    c.nodes[previous].clickedOn = false;
                }
                c.nodes[current].clickedOn = true;
                previous = current;
                repaint();
            }
        } else {
            flushPossibleMove();

        }
    }

    public void inputProcess() {
        // drawString();
        if (current != Court.NULL) {
            System.out.println(c.nodes[current].status);

            if (c.nodes[current].status == Status.NoValue && previous == Court.NULL) {
                //System.out.println("Cond1");
                resetVars();
            } else if (c.nodes[current].status != Status.NoValue && previous == Court.NULL) {
                if (Court.tempHolder == c.nodes[current].status || Court.tempHolder == Status.NoValue) {
                    valid1stMove();
                } else if (Court.tempHolder != c.nodes[current].status) {
                    changePlayer();
                    valid1stMove();
                }
            } else if ((previous != Court.NULL && c.nodes[previous].clickedOn == true)
                    && c.nodes[current].status != Status.NoValue) {
                if (previous != current) {
                    valid1stMove();
                } else {
                    resetVars();
                }
            } else if ((previous != Court.NULL && c.nodes[previous].clickedOn == true)
                    && c.nodes[current].status == Status.NoValue) {
                System.out.println("Cond4");


                if (isPossibleToMove(current, previous)) {
                    shift(previous, current);

                    if(Court.AI == true && Court.currentplayer != AI.aiPlayer)
                    {
                        ai.findVulnarable(current);

                    }
                    if (Court.chipkilled == true) {
                        flushPossibleMove();
                        maymove = c.getPossibleKillerMoves(current);
                        if (maymove[0] != null) {
                            Court.tempHolder = c.nodes[current].status;
                        } else {
                            Court.tempHolder = Status.NoValue;
                            changePlayer();
                        }
                    } else {
                        Court.tempHolder = Status.NoValue;
                        changePlayer();
                    }
                }
                resetVars();
            }
        } else {
            System.out.print("Not a Node!!");
            resetVars();
        }
        repaint();



    }

    public void clickCheck(int x, int y) {

        if (Court.currentplayer == Status.PropertiesOfPlayer1
                || (Court.currentplayer == Status.PropertiesOfPlayer2 && c.AI == false)) {
            current = c.checkGutiOn(x, y);
            inputProcess();
        }
    }

    class MouseHandler extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {


            // drawString();
            if (Court.isgameOn == true) {

                x = e.getX();
                y = e.getY();
                clickCheck(x, y);
            }//End of 1st if
        }//End of MouseCLicked

        public void mousePressed(MouseEvent me) {
            System.out.println(me.getX() + " " + me.getY());
        }
    }//End of MouseHandler
}//end of Display canvas

