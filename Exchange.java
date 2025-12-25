import java.util.concurrent.Exchanger;

// use Exchange if you want to have point to point and synchronous data exchange between two threads

// use Queue if you want to have one to many or many to one asynchronous data exchange between threads

public class Exchange
{
   public static void main(String[] args) {

      Exchanger<Integer> exchanger = new Exchanger<>();

      new Thread(() -> {
         try {
            Integer dataToSend = 10;
            System.out.println("Thread 1 sending: " + dataToSend);
            Integer receivedData = exchanger.exchange(dataToSend);
            System.out.println("Thread 1 received: " + receivedData);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         }
      }).start();

      new Thread(() -> {
         try {
            Integer dataToSend = 20;
            System.out.println("Thread 2 sending: " + dataToSend);
            Integer receivedData = exchanger.exchange(dataToSend);
            System.out.println("Thread 2 received: " + receivedData);
         } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
         }
      }).start();
      
   }
}
