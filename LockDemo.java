import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo
{
   private static int max=5;
   private static Queue<Integer> q=new LinkedList<>();

   private static Lock lock=new ReentrantLock();
   private static Condition notFull=lock.newCondition();
   private static Condition notEmpty=lock.newCondition();

   private static  void produce(int item) throws InterruptedException
   {
      lock.lock();
      try
      {
         while(q.size()==max)
         {
            notEmpty.await();
         }

         System.out.println("Produced: "+item);
         q.add(item);
         notFull.signal();
      }
      finally
      {
         lock.unlock();
      }
      
   }
   private static void consume() throws InterruptedException
   {
      lock.lock();
      try
      {
         while(q.isEmpty())
         {
            notFull.await();
         }
         int item=q.poll();
         System.out.println("Consumed: "+item);
         notEmpty.signal();
      }
      finally
      {
         lock.unlock();
      }
   }

   public static void main(String[] args) {
      Thread producer=new Thread(()->{

         for(int i=0;i<10;i++)
         {
            try {
               produce(i);
               Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      });

      Thread consumer=new Thread(()->{
         for(int i=0;i<10;i++)
         {
            try {
               consume();
               Thread.sleep(2000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
      });

      producer.start();
      consumer.start();
   }





}
