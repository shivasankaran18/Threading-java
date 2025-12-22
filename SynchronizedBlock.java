public class SynchronizedBlock
{
   private static final Object lock1=new Object();
   private static final Object lock2=new Object();

   private static  int cnt1=0;
   private static int cnt2=0;
   public static void main (String args[]) throws InterruptedException
   {


      Thread t1=new Thread(()->{
         increment1();
      });

      Thread t2=new Thread(()->{
         increment2();
      });
      t1.start();
      t2.start();

      t1.join();
      t2.join();
   }

   static void increment1()
   {
      synchronized(lock1)
      {
         //critical section
         cnt1++;
      }
   }
   static void increment2()
   {
      synchronized(lock2)
      {
         //critical section
         cnt2++;
      }
   }

   //don't use synchronized methods because it locks the entire object,

}
