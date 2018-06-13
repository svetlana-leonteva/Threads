package CheeseShop;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Cheese {

	static Object lock1 = new Object(); 
	public static Buyer B; 

	    @SuppressWarnings("unused")
		private static class Buyer implements Runnable, Comparable<Buyer> {
			
			 //private static final Random Brave = null;
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
					 Random random = Brave;
					boolean Bayer = random.nextBoolean ();
					   System.out.println(getRandomBoolean());  
						synchronized (lock1) {
						System.out.println(Thread.currentThread().getName() + " sends the request getCheese for the Cashier " + B );
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
				public static void take(Buyer b) {
									
				}
								
		}

		static class Cashier implements Runnable {
			
			//@SuppressWarnings("unchecked")
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					Buyer.take(B);
					System.out.println(Thread.currentThread().getName() + " receives a request getCheese from the Buyer" + B);
					try {
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
			public static boolean main(String[] args) throws InterruptedException {

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
					 
					Random random = new Random();
					return random.nextBoolean();
					
		}  
	}
}