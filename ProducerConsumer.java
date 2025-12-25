//simple Producer-Consumer problem using wait and notify

import java.util.ArrayList;
import java.util.List;
public class ProducerConsumer
{
   public static void main (String args[]) throws InterruptedException
   {
      Worker worker=new Worker(0,5);
      Thread producerThread=new Thread(()->{
         try
         {
            worker.produce();
         }
         catch(InterruptedException e)
         {
            System.out.println("Producer thread interrupted");
         }
      });

      Thread consumerThread=new Thread(()->{
         try
         {
            worker.consume();
         }
         catch(InterruptedException e)
         {
            System.out.println("Consumer thread interrupted");
         }
      });

      producerThread.start();
      consumerThread.start();

      producerThread.join();
      consumerThread.join();
   }

}


class Worker
{
   List<Integer> container;
   private final int top;
   private final int bottom;
   private int seq;
   private final Object lock;


   public Worker(int bottom, int top)
   {
      this.bottom=bottom;
      this.top=top;
      container=new ArrayList<>();
      seq=bottom;
      lock=new Object();
   }


   void produce() throws InterruptedException
   {
      synchronized(lock)
      {
         while(true)
         {
            if(container.size()==top)
            {
               System.out.println("Producer is waiting, container full");
               lock.wait();
            }
            else
            {
               System.out.println("Producing: "+seq);
               container.add(seq);
               seq++;
               lock.notify();
               Thread.sleep(1000);
            }
         }
         
      }
   }

   void consume() throws InterruptedException
   {
      synchronized(lock)
      {
         while(true)
         {
            if(container.size()==0)
            {
               System.out.println("Consumer is waiting, container empty");
               lock.wait();
            }
            else
            {
               int val=container.remove(0);
               System.out.println("Consuming: "+val);
               lock.notify();
               Thread.sleep(1000);
            }
         }
      }
   }



}
