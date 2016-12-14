package com.mycompany.app;

import static kiss.API.*;

public class Clock{
	private double hours;
	private double minutes;
	private double seconds;
	private boolean started;
	private double t0;

	void start(){
		started = true;
		t0 = time();
	}
	void setHours(double _hours){
		hours = _hours;
	}
	double getHours(){
		return started ? (hours + (time()-t0)/3600.0) : hours;
	}
	double getMinutes(){
		double _hours = getHours();
		return (hours - Math.floor(_hours)*60);
	}
	double getSeconds(){
		double _minutes = getMinutes();
		return (seconds - Math.floor(_minutes)*60);
	}

	void testGetTime(){
		Clock clock = new Clock();
		double hours = clock.getHours();
		double minutes = clock.getMinutes();
		double seconds = clock.getSeconds();
	}
	void testGetCorrectTime(){
		Clock clock = new Clock();
		clock.setHours(6.5);
		assert clock.getHours() == 6.50;
		assert clock.getMinutes() == 30.0;
		assert clock.getSeconds() == 0.0;
	}
	void testGetFlowingTime(){
		Clock clock = new Clock();
		clock.setHours(1.00);
		try {
		java.util.concurrent.TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
		  e.printStackTrace();
		}
		double now = clock.getHours();
		double shouldBe = 1.0 + 1.0/3600.0;
		assert abs(now - shouldBe) < 0.1/3600.0;
	}
	void testEquals(){
		Clock clock1 = new Clock();
		Clock clock2 = new Clock();
		clock2 = clock1;
		Clock clock3 = new Clock();
		clock1.setHours(1.00);
		clock3.setHours(1.00);

		assert clock2.getHours() == 1.00;
		assert clock1.equals(clock2) == true;
		assert (clock1 == clock2) == true;
		assert clock1.equals(clock3) == false;
		assert (clock1 == clock3) == false;

	}
	public static void main(String[] args){
		Clock clock = new Clock();
		clock.testGetTime();
	  clock.testGetCorrectTime();
		clock.testGetFlowingTime();
		clock.testEquals();
	}
}
