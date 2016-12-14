package com.mycompany.app;

public class TimezoneClock extends Clock{
	double timezoneSHift = 0.0;

	void testGetTime(){
		Clock clock = new TimezoneClock();
		double hours = clock.getHours();
		double minutes = clock.getMinutes();
		double seconds = clock.getSeconds();
	}
	void testGetCorrectTime(){
		Clock clock = new TimezoneClock();
		clock.setHours(6.5);
		assert clock.getHours() == 6.50;
		assert clock.getMinutes() == 30.0;
		assert clock.getSeconds() == 0.0;
	}
	void testGetFlowingTime(){
		Clock clock = new TimezoneClock();
		clock.setHours(1.00);
		clock.start();
		try {
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		  e.printStackTrace();
		}
		double now = clock.getHours();
		double shouldBe = 1.0 + 1.0/3600.0;
		assert Math.abs(now - shouldBe) < 0.1/3600.0;
}
}
