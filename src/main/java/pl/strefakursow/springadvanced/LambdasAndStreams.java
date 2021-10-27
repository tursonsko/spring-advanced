package pl.strefakursow.springadvanced;

import java.util.Arrays;
import java.util.List;

public class LambdasAndStreams {

    public static void main(String[] args) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Informacja z osobnego wątku");
//            }
//        });
        //to samo co wyzej - ozna tak zrobic bo Tunnable jest interfejsem funkcyjnym (jedna matoda + adnotacja @FunctionalInterface)
//        Thread thread = new Thread(() -> System.out.println("Informacja z osobnego wątku"));
//
//        thread.start();
//        System.out.println("Informacja z metody main");

        List<Integer> numbers = Arrays.asList(52,56,32,76,11,75,86,237,23,53);

        //wybrac elementy parzyste, pomoyc je przez 2, zsmowac uwyswilic wynik

        int result = 0;

        for (int i : numbers) {
            if(i % 2 == 0) {
                result += i * 2;
            }
        }
        System.out.println(result);

        Integer sum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }
}
