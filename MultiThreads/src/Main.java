import java.util.Scanner;

public class Main {

    private static final Object lock = new Object();
    private static int secondsPassed = 0;

    public static void main(String[] args) {
        int choice = 1;


        while (true) {
            System.out.println("Введите номер задания:");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Задание №1. В цикле выводите знамения от 0 до 100 делящиеся на 10 без остатка. Используйте Thread.sleep(), чтобы делать паузы после каждого вывода в консоль\nВведите значение tts:\n");
                    CountThread t = new CountThread(sc.nextInt());
                    t.start();
                }
                case 2 -> {
                    System.out.println("Задание №2. В цикле выводите знамения от 0 до 100 делящиеся на 10 без остатка. Используйте Thread.sleep(), чтобы делать паузы после каждого вывода в консоль\nВведите значение tts:\n");
                    Thread t = new Thread(new RunnableClass(sc.nextInt()));
                    t.start();
                }
                case 3 -> {
                    System.out.println("Задание №3. Напишите программу, которая каждую секунду отображает на экране время прошедшее от начала сессии");
                    Thread timerThread = new Thread(() -> {
                        try {
                            while (true) {
                                Thread.sleep(1000);  // 1 секунда
                                synchronized (lock) {
                                    secondsPassed++;
                                    System.out.println("Chronometer: " + secondsPassed);
                                    lock.notifyAll();
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                    Thread fiveSecondThread = new Thread(() -> {
                        try {
                            int lastSec = 0;
                            while (true) {
                                synchronized (lock) {

                                    while (secondsPassed % 5 != 0 || lastSec == secondsPassed) {
                                        lock.wait();
                                    }
                                    lastSec = secondsPassed;
                                    System.out.println("Сообщение каждые 5 секунд");
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                    Thread sevenSecondThread = new Thread(() -> {
                        try {
                            int lastSec = 0;
                            while (true) {
                                synchronized (lock) {
                                    while (secondsPassed % 7 != 0 || lastSec == secondsPassed) {
                                        lock.wait();
                                    }
                                    lastSec = secondsPassed;
                                    System.out.println("Сообщение каждые 7 секунд");
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });

                    timerThread.start();
                    fiveSecondThread.start();
                    sevenSecondThread.start();
                }
            }

        }
    }
}


