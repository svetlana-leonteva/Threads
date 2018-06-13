package ru.spbstu.tele.l3;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;


public class CheeseShop {
	static Object lock1 = new Object(); 
	
	private static PriorityBlockingQueue<Buyer> queue = new PriorityBlockingQueue<>();
	
	static class Buyer implements Comparable<Buyer> {
		private boolean isHumble;

		public Buyer(boolean isHumble) {
			super();
			this.isHumble = isHumble;
		}

		@Override
		public int compareTo(Buyer o) {
			if (o.isHumble && isHumble) {
				return 0;
			} else if (isHumble && !o.isHumble){
				return -1;
			} else if (!isHumble && o.isHumble){
				return 1;
			}
			return 0;
		}
	}

	
	private static class BuyerGenerator implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
		     Random random = new Random();
		   Buyer o =  new Buyer(random.nextBoolean());
		       queue.add(o);
		  		System.out.println(Thread.currentThread().getName() + " created new Buyer: " + o.isHumble);
		  		try {
					Thread.sleep(random.nextInt(200));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
				
			
		private static class Cashier implements Runnable {
 
		@Override
		public void run() {
			
			//while
			// take new buyer
			//log output
			// wait a bit (while service)
			while (!Thread.currentThread().isInterrupted()) {
				Buyer o = null;
				try {
					o = queue.take();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + " Cashier processes next Buyer: " + o.isHumble);
				try {
					Thread.sleep(new Random ().nextInt(200));
				} catch (InterruptedException e) {
					e.printStackTrace();
				 }
			   }
			}
		}
		
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new BuyerGenerator());
		Thread t2 = new Thread(new Cashier());

		t1.start();
		t2.start();

		t1.join();
		t2.join();

	   }
	 
   }

