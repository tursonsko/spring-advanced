package pl.strefakursow.springadvanced.component;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component	
public class CustomTaskScheduler {

//	@Scheduled(fixedRate=5000)
//	@Scheduled(fixedDelay=5000)
//	sekunda, minuta, godzina, dzień miesiaca, miesiac, dzien tygodnia
//	@Scheduled(cron = "${cron.expression}")
	public void doSomeStuff() {
		System.out.println("some operation, time: " + LocalTime.now());
	}

}