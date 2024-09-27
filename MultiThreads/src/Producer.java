import java.util.Queue;

public class Producer extends Thread {
    private final int seconds;

    private final int maxSizeBuffer;

    private Queue<Integer> buffer;

    Producer(Queue<Integer> buffer, int seconds, int maxSizeBuffer){
        this.buffer = buffer;
        this.seconds = seconds;
        this.maxSizeBuffer = maxSizeBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                sleep(1000/seconds);
                synchronized (buffer){
                    if(buffer.size()!=maxSizeBuffer){
                        int r = (int) (Math.random()*100);
                        buffer.add(r);
                        System.out.println("Производитель добавил число: " + r + "\n");
                        buffer.notify();
                    } else{
                        System.out.println("Производитель ждет");
                        buffer.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
