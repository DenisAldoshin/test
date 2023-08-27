package test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class tes551 {
    public static void main(String[] args) {
        Random random = new Random();
        Integer[] arrayNumbers = new Integer[1_000_0000];
        Character[] arrayChar = new Character[1_000_0000];
        for (int i = 0; i < arrayNumbers.length; i++) {

            arrayChar[i] = (char) random.nextInt(0, 100_000);
            arrayNumbers[i] = random.nextInt(0, 100_000);
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int sum1 = 0;
                long starTime1 = System.nanoTime();
                for (int i = 0; i < arrayNumbers.length; i++) {
                    if (arrayChar[i].hashCode() == arrayNumbers[i].hashCode() & Integer.valueOf(arrayChar[i]).equals(arrayNumbers[i])) {
                        sum1++;
                    }
                }
                System.out.println("Количество совпадений = " + sum1 + "\n == и hashcode: " + ((System.nanoTime() - starTime1) / 1_000));
            }
        });
        executorService.shutdown();
        executorService1.execute(new Runnable() {
            @Override
            public void run() {
                int sum2 = 0;
                long starTime2 = System.nanoTime();
                for (int i = 0; i < arrayNumbers.length; i++) {
                    if (Integer.valueOf(arrayChar[i]).equals(arrayNumbers[i])) {
                        sum2++;
                    }
                }
                System.out.println("Количество совпадений  = " + sum2 + "\n Только equals: " + ((System.nanoTime() - starTime2) / 1_000));
            }
        });
        executorService1.shutdown();
    }
}



