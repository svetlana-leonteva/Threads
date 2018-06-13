//Покупатели. Сырный отдел в супермаркете непрерывно собирает голодных покупателями.
//2 типа покупателей: храбрые покупатели и смиренные покупатели
//Запрос на обслуживание обозначен действием “getCheese”
//Завершение обслуживания обозначено действием “Cheese”
//Всегда есть доступный сыр и постоянное количество покупателей
//Каждый покупатель должен быть создан в виде отдельной нити, которая входит в очередь, обслуживается, и прекращает работу.

package CheeseShop;

import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@SuppressWarnings("unused")
public class Cheese {

	static Object lock1 = new Object();
	public static Buyer B; 
	
//Comparable содержит один единственный метод int compareTo(E item), который сравнивает текущий объект с объектом, переданным 
//в качестве параметра. Если этот метод возвращает отрицательное число, то текущий объект будет располагаться перед тем, который 
//передается через параметр. Если метод вернет положительное число, то, наоборот, после второго объекта. 
//Если метод возвратит ноль, значит, оба объекта равны.
	
	    private static class Buyer implements Runnable, Comparable<Buyer> {
	    	 public int compare(Buyer Brave, Buyer Humble){
	    	      return Brave.getName().compareTo(Humble.getName());
	    	    }
	    	
			 private String name;
			 public Buyer(String B){
			         this.name = B;
			    }
			   public String getName(){
				   return name;
				   }
			     
			    public int compareTo1(Buyer B){
			     
			        return name.compareTo(B.getName());
			    }
			
	      		public Buyer() {
				
			}
	      		
	      		BlockingQueue<Buyer> bqueue;
	      		public Buyer(BlockingQueue<Buyer> bqueue) {
	      			this.bqueue = bqueue;
	      		}

				public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					B =new Buyer();
					  Random randomno = new Random();
				      // get next next boolean value 
				      boolean value = randomno.nextBoolean();
				     Buyer.add(B);
					//bqueue.offer(new Buyer());
					//bqueue.offer(new Buyer("Humble"));	    				    	
						synchronized (lock1) {
								
						System.out.println(Thread.currentThread().getName() + " sends the request getCheese" + B);
						try {
							lock1.wait();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " gets the Cheese ");
					}
				}
			}
				private static void add(Buyer b) {
									
				}
				@Override
				public int compareTo(Buyer o) {
					
					return 0;
				}
				
				public static void take(Buyer b) {
				}

		}

		static class Cashier implements Runnable {
			BlockingQueue<Cashier> bqueue;
			public Cashier(BlockingQueue<Cashier> bqueue) {
				this.bqueue = bqueue;
			}
			public Cashier() {
				
			}
			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					Buyer.take(B);
					System.out.println(Thread.currentThread().getName() + " receives a request getCheese from the " + B);
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
			private void take(Buyer b) {
								
			}
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public static void main(String[] args) throws InterruptedException {

				Thread t1 = new Thread(new Buyer());
				Thread t2 = new Thread(new Cashier());

				t1.start();
				t2.start();

				t1.join();
				t2.join();
				
				Queue Buyer = new PriorityBlockingQueue();
			 
					Buyer = (Queue) new Buyer ();
					Buyer.add (new Buyer ("Brave"));
					Buyer.add (new Buyer ("Humble"));
		}  
	}
}