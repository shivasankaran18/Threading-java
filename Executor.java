import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Sum implements Callable<Integer> {
   int no;

   public Sum(int no) {
      this.no = no;
   }

   @Override
   public Integer call() {
      int sum = 0;
      for (int i = 1; i <= no; i++) {
         sum = sum + i;
      }
      return sum;
   }

}

class Demo implements Callable<Void> {
   @Override
   public Void call() throws InterruptedException{
      Thread.sleep(10000);
      return null;
   }

}

public class Executor
{
   public static void main(String[] args) throws ExecutionException, InterruptedException {
      ExecutorService executor = Executors.newFixedThreadPool(3);

      Callable<Integer> worker1 = new Sum(10);
      Callable<Void> worker2 = new Demo();
      Callable<Integer> worker3 = new Sum(20);
      
      Future<Integer> result1 = executor.submit(worker1);
      Future<Void> result2 = executor.submit(worker2);
      Future<Integer> result3 = executor.submit(worker3);
   
      System.out.println("Sum of first 5 natural numbers: " + result1.get());

      //this is a blocking call . The main thread will wait here until the result is available
      System.out.println(result2.get());
      System.out.println("Sum of first 15 natural numbers: " + result3.get());

      executor.shutdown();
   }
}
