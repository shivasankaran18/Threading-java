import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Shared
{

   synchronized void waitingCall()
   {
      System.out.println("Waiting call started "+Thread.currentThread().getName());
      try
      {
         wait();
      }
      catch(InterruptedException e)
      {
         System.out.println("Waiting call interrupted");
      }
      System.out.println("Notified"+Thread.currentThread().getName());
   }

   synchronized void notifyingCall()
   {
      System.out.println("Notifying call started "+Thread.currentThread().getName());
      notify();
      System.out.println("Notifying call ended "+Thread.currentThread().getName());
   }

}



public class WaitAndNotify
{
   public static void main(String args[]) throws InterruptedException
   {
      Shared shared=new Shared();

      //thread pool. thread pool is used for creating multiple reusable threads.
      ExecutorService executor=Executors.newFixedThreadPool(3);

      Runnable r1=()->{
         shared.waitingCall();
      };

      Runnable r2=()->{
         try
         {
            Thread.sleep(2000);
         }
         catch(InterruptedException e)
         {
            System.out.println("Thread interrupted");
         }
         shared.waitingCall();
      };
      Runnable r3=()->{
         try
         {
            Thread.sleep(4000);
         }
         catch(InterruptedException e)
         {
            System.out.println("Thread interrupted");
         }
         shared.notifyingCall();
      };

      executor.submit(r1);
      executor.submit(r2);
      executor.submit(r3);
      executor.submit(r3);


      //move threads to the terminated state
      executor.shutdown();




   }
      
}
