import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentCollections
{
   public static void main(String[] args)
   {
      //normally collections like ArrayList are not thread safe
      //this is one way to create a synchronized list
      //disadvantage is that it locks the entire list for every operation(intrinsic locking)

      List<Integer> al=Collections.synchronizedList(new ArrayList<>());

      Thread t1=new Thread(()->{
         for(int i=0;i<1000;i++)
         {
            al.add(i);
         }
      });

      Thread t2=new Thread(()->{
         for(int i=1000;i<2000;i++)
         {
            al.add(i);
         }
      });

      t1.start();
      t2.start();

      try
      {
         t1.join();
         t2.join();
      }
      catch(InterruptedException e)
      {
         System.out.println("Main thread interrupted");
      }


   }
}
