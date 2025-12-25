//Internally: On add/remove/set: New array is created Old array remains unchanged Reference is swapped atomically So readers keep seeing the old snapshot safely.


//Reads:Lock-free , no synchronization needed..hence they Never throw ConcurrentModificationException

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class COWA
{
   public static void main(String[] args) {

      List<Integer> list = new CopyOnWriteArrayList<>();
      for(int i=0;i<10;i++)
         list.add(0);
      Runnable writer=()->{
         while(true)
            list.set((int)(Math.random()*10), (int)(Math.random()*100));
      };

      Thread w1=new Thread(writer);
      Thread w2=new Thread(writer);
      Thread w3=new Thread(writer);
      

      Thread r=new Thread(()->{
         while (true)
         {
         try
         {
            Thread.sleep(1000);
            System.out.println(list);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
         }
         
      });

      w1.start();
      w2.start();
      w3.start(); 
      r.start();

   }
}
