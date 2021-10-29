package pl.strefakursow.springadvanced;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LambdasAndStreams {
	public static void main(String[] args) {

//		Thread thread = new Thread(() -> System.out.println("Informacja z osobnego wątku"));
//		
//		thread.start();
//		System.out.println("Informacja z metody main");
		
		List<Integer> numbers = Arrays.asList(52,17,44,67,98,60,21,18,57,102);

		
//		int result = 0;
//		
//		for(int i : numbers) {
//			if(i % 2 == 0) {
//				result += i * 2;
//			}
//		}
//
//		System.out.println(
//				numbers.stream()
//				.filter( i -> i % 2 == 0)
//				.mapToInt(i -> i * 2)
//				.sum()
//				);
//		
//		System.out.println(numbers.stream().reduce(0,(subtotal, element) -> subtotal + element));

		Set<Integer> collect = numbers.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

		System.out.println(collect);
	}
}