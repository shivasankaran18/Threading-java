import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo
{
   public static void main(String[] args) {

      ExecutorService executor=Executors.newCachedThreadPool();

      Demo demo=new Demo();

      for(int i=0;i<10;i++)
      {
         executor.execute(()->{
            demo.accessResource();
         });
      }

      executor.shutdown();
      
   }
}

class Demo
{
   private Semaphore semaphore=new Semaphore(3);

   public void accessResource()
   {
      try
      {
         semaphore.acquire();
         System.out.println(Thread.currentThread().getName()+" acquired the semaphore.");
         Thread.sleep(3000);
      }
      catch(InterruptedException e)
      {
         System.out.println(e.getMessage());
      }
      finally
      {
         System.out.println(Thread.currentThread().getName()+" releasing the semaphore.");
         semaphore.release();
      }
   }

}
