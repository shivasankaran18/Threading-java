import java.util.concurrent.CyclicBarrier;

//when u want to reuse the functionality of CountDownLatch use CyclicBarrier
public class CyclicBarrierDemo
{
   private static  int players=5;
   private static int stages=3;
   
   private static CyclicBarrier barrier=new CyclicBarrier(players,()->{
      System.out.println("All players have reached the barrier. Proceeding to next stage...");
   });

   public static void main(String[] args) {

      for(int i=0;i<players;i++)
      {

         new Thread(()->{
            for(int j=0;j<stages;j++)
            {
               try
               {
                  System.out.println(Thread.currentThread().getName()+" is playing stage "+(j+1));
                  Thread.sleep((long)(Math.random()*3000));
                  barrier.await();
               }
               catch(Exception e)
               {
                  System.out.println(Thread.currentThread().getName()+" interrupted");
               }
            }
         }).start();
     
      }
      
   }


}
