import java.util.concurrent.locks.ReentrantLock;

//Reentrant Lock allows the same thread to acquire the lock multiple times.

public class ReentrantLockDemo
{
   private static ReentrantLock lock=new ReentrantLock();
   private static int count=0;
   static void fn1()
   {
      lock.lock();
      try
      {
         count++;
         System.out.println("In fn1" + count);
         fn2();
      }
      finally
      {
         lock.unlock();
      }
   }

   static void fn2()
   {
      lock.lock();
      try
      {
         count--;
         System.out.println("In fn2" + count);
      }
      finally
      {
         lock.unlock();
      }
   }

   public static void main(String[] args) throws InterruptedException{
      
      Thread t=new Thread(()->{

         for(int i=0;i<5;i++)
         {
            fn1();
         }
      });

      t.start();

      t.join();
   }

    
}
