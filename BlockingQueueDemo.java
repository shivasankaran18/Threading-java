import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo
{
   public static void main(String[] args) {

      BlockingQueue<Integer> q=new ArrayBlockingQueue<>(10);

      Thread producer=new Thread(()->{
         for(int i=1;i<=1000;i++)
         {
            try
            {
               q.put(i);
               Thread.sleep(100);

            }
            catch(InterruptedException e)
            {
               System.out.println("Producer interrupted");
            }
         }
       
      });

      Thread consumerOne=new Thread(()->{
         while(true)
         {
            try
            {
               int val=q.take();
               System.out.println("Consumer One consumed: "+val);
               Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
               System.out.println("Consumer One interrupted");
            }
         }
      });

      Thread consumerTwo=new Thread(()->{
         while(true)
         {
            try
            {
               int val=q.take();
               System.out.println("Consumer Two consumed: "+val);
               Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
               System.out.println("Consumer Two interrupted");
            }
         }
      });

      producer.start();
      consumerOne.start();
      consumerTwo.start();

      
   }
}
