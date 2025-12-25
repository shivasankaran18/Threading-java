import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo
{

   private static final ReadWriteLock lock=new ReentrantReadWriteLock();
   private static int count=0;

   public static void main(String[] args) {

      Runnable reader=()->{
         lock.readLock().lock();
         try
         {
            System.out.println("Read Count: "+count);
            Thread.sleep(5000);
         }
         catch(InterruptedException e)
         {
            System.out.println(e.getMessage());
         }
         finally
         {
            lock.readLock().unlock();
         }
      };

      Thread r1=new Thread(reader);
      Thread r2=new Thread(reader);

      Thread writer=new Thread(()->{
         lock.writeLock().lock();
         try
         {
            count++;
            System.out.println("Wrote Count: "+count);
            Thread.sleep(5000);
         }
         catch(InterruptedException e)
         {
            System.out.println(e.getMessage());
         }
         finally
         {
            lock.writeLock().unlock();
         }
      });

      r1.start();
      r2.start();
      writer.start();
      
   }

}
