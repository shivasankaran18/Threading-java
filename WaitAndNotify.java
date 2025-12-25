public class WaitAndNotify
{
   private static final Object lock=new Object();

   public static void main (String args[]) throws InterruptedException
   {
      Thread t1=new Thread(()->{
         one();
      });
      Thread t2=new Thread(()->{
         two();
      });
      t1.start();
      t2.start();
      t1.join();
      t2.join();
      
     
   }

   static void one()
   {
      synchronized(lock)
      {
         System.out.println("Thread one is waiting for notification");
         try
         {
            Thread.sleep(1000);
            lock.wait();
         }
         catch(InterruptedException e)
         {
            System.out.println("Thread one interrupted");
         }
         System.out.println("Thread one got notification and resumed execution");
      }
   }
   static void two()
   {
      synchronized(lock)
      {
         System.out.println("Thread two is sending notification");
         lock.notify();
         System.out.println("Thread two sent notification");
      }
   }
}
