public class CountThread extends Thread {

    private long tts;

    CountThread(long tts){
        this.tts = tts;
    }

    public void setTts(long tts){
        this.tts = tts;
    }

    @Override
    public void run(){
        for (int i = 0; i <= 100; i++) {
            if(i % 10 == 0){
                System.out.println(i+"\n");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
