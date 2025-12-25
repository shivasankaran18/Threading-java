import java.util.concurrent.CountDownLatch;

public class Latch
{
   public static void main(String[] args)
   {
      CountDownLatch latch=new CountDownLatch(3);

      Thread chef1=new Thread(new Chef("Chef A","Salad",latch));
      Thread chef2=new Thread(new Chef("Chef B","Soup",latch));
      Thread chef3=new Thread(new Chef("Chef C","Steak",latch));

      chef1.start();
      chef2.start();
      chef3.start();

      try
      {
         latch.await();
      }
      catch(InterruptedException e)
      {
         System.out.println(e.getMessage());
      }

      System.out.println("Done");
   }
   

}


class Chef implements Runnable
{
   private String name;
   private String dish;
   private final CountDownLatch latch;

   public Chef(String name, String dish, CountDownLatch latch)
   {
      this.name=name;
      this.dish=dish;
      this.latch=latch;
   }

   @Override
   public void run()
   {
      System.out.println(name+" is preparing "+dish);
      try
      {
         Thread.sleep((long)(Math.random()*5000));
      }
      catch(InterruptedException e)
      {
         System.out.println(name+" was interrupted while preparing "+dish);
      }
      System.out.println(name+" has finished preparing "+dish);
      latch.countDown();
   }
}
