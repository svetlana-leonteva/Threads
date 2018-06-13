package CheeseShop;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Cheese {

	static Object lock1 = new Object(); 

	    @SuppressWarnings("unused")
		private static class Buyer implements Runnable, Comparable<Buyer> {
			
			 private String name;
			 
			 public Buyer(String name){
			         this.name = name;
			    }
			   public String getName(){
				   return this.name;
				   }
			     
			    public int compareTo(Buyer another){
			      return this.name.compareTo(another.getName());
			    }
			
	      		public Buyer() {
				
			}
	      		enum Name {
	      			Brave, Humble;
	      		}
 
				public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					   // Buyer queue = null;
					 //   System.out.println(getRandomBoolean());  
						synchronized (lock1) {
						System.out.println(Thread.currentThread().getName() + " sends the request getCheese for the Cashier " + name );
						try {
							lock1.wait();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						int compareTo = new Random().nextInt();
						System.out.println(Thread.currentThread().getName() + " gets the Cheese from the Cashier");
					}
				}
			}
				private char[] getRandomBoolean() {
				
					return null;
				}
				private void add(Buyer buyer) {
								
				}
								
		}

		static class Cashier implements Runnable {
			
			//@SuppressWarnings("unchecked")
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread().getName() + " receives a request getCheese from the Buyer");
					try {
						//BlockingQueue<Buyer> queue = (BlockingQueue<Buyer>) new Buyer ();
						//System.out.println(queue.poll());
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " executes the request ");
					synchronized (lock1) {
						lock1.notify();
					}
				}
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public static void main(String[] args) throws InterruptedException {

				Thread t1 = new Thread(new Buyer());
				Thread t2 = new Thread(new Cashier());

				t1.start();
				t2.start();

				t1.join();
				t2.join();
				
				BlockingQueue<Buyer> queue = new PriorityBlockingQueue();
				    queue.add (new Buyer ("Brave"));
				    queue.add (new Buyer ("Humble"));
					@SuppressWarnings("unused")
					Buyer B = queue.poll();
					 
					@SuppressWarnings("unused")
					Random random = new Random();
					while (queue != null) {
					    System.out.println(queue);
					    B = queue.poll();
			}
		}  
	}
}