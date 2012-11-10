/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SixteenChips;

/**
 *
 * @author Mamun
 */
public class Queue
{
    public QueueNode head;

    public Queue()
    {
        this.head = new QueueNode(-10000);
    }

    public Queue(int NULL)
    {
        this.head = new QueueNode(NULL);
    }


    public void Enqueue(int n)
    {
        QueueNode qn = new QueueNode(n);

        if(this.head.left == null && this.head.right == null)
        {
            this.head.left = qn;
            qn.right = this.head;
            this.head.right = qn;
            qn.left = this.head;
        }
        else
        {
            qn.left = this.head.left;
            this.head.left.right = qn;
            this.head.left = qn;
            qn.right = this.head;
        }

    }

    public int Dequeue()
    {
        QueueNode qn;
        qn = this.head.right;
        this.head.right = this.head.right.right;
        this.head.right.left = this.head;

        if(qn != this.head)
            return qn.getValue();
        else
            return Court.NULL;
    }


   /* public static void main(String [] args)
    {
        Queue queue = new Queue();
        for(int i=0;i<10;i++)
               queue.Enqueue(i*i);
        for(int i=0;i<5;i++)
            System.out.println(queue.Dequeue());
        for(int i=0;i<10;i++)
               queue.Enqueue(i);
        for(int i=0;i<15;i++)
            System.out.println(queue.Dequeue());

    }*/
}


class QueueNode
{
    private int value;
    public QueueNode left;
    public QueueNode right;

    public QueueNode()
    {
        this.value = 0;
    }

    public QueueNode(int n)
    {
        this.value = n;
    }
    public void addValue(int n)
    {
        this.value = n;
    }

    public int getValue()
    {
        return this.value;

    }
}