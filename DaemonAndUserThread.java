public class DaemonAndUserThread
{
   public static void main(String args[]) throws InterruptedException
   {
      int cores=Runtime.getRuntime().availableProcessors();
      System.out.println("Number of available cores : "+cores);
      Thread userThread=new Thread(()->{
         try
         {
            Thread.sleep(5000);
         }
         catch(InterruptedException e)
         {
            System.out.println("User thread interrupted");
         }
         System.out.println("User thread finished execution");
      });

      Thread daemonThread=new Thread(()->{
         try
         {
            while(true)
            {
               System.out.println("Daemon thread is running");
               Thread.sleep(1000);
            }
         }
         catch(InterruptedException e)
         {
            System.out.println("Daemon thread interrupted");
         }
      });

      //set daemonThread as daemon thread.. eg of daemon thread :GC thread.
      daemonThread.setDaemon(true);

      daemonThread.start();

      //default priority of thread is 5
      userThread.start();

      userThread.join();
      System.out.println("Main thread finished execution");
   }
}
