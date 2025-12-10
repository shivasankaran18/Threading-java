import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Thread implements Callable<Integer> {
   int no;

   public Thread(int no) {
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

public class Thread1
{
   public static void main(String[] args) throws ExecutionException, InterruptedException {
      ExecutorService executor = Executors.newFixedThreadPool(3);

      Callable<Integer> worker1 = new Thread(50000000);
      Callable<Integer> worker2 = new Thread(10000000);
      Callable<Integer> worker3 = new Thread(15000000);

      Future<Integer> result1 = executor.submit(worker1);
      Future<Integer> result2 = executor.submit(worker2);
      Future<Integer> result3 = executor.submit(worker3);

      System.out.println("Sum of first 5 natural numbers: " + result1.get());
      System.out.println("Sum of first 10 natural numbers: " + result2.get());
      System.out.println("Sum of first 15 natural numbers: " + result3.get());

      executor.shutdown();
   }
}
