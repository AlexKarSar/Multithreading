import java.util.Queue;

public class ConsumerThread extends Thread {

    private final int seconds;
    private Queue<Integer> buffer;

    ConsumerThread(Queue<Integer> buffer, int seconds){
        this.buffer = buffer;
        this.seconds = seconds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                sleep(1000/seconds);
                synchronized (buffer){
                    if(buffer.size()!= 0){
                        System.out.println("Потребитель 'съел' число - " + buffer.remove() + '\n');
                        buffer.notify();
                    } else {
                        System.out.println("Потребитель ждет");
                        buffer.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
