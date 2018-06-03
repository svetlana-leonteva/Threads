//Покупатели. Сырный отдел в супермаркете непрерывно собирает голодных покупателями.
//2 типа покупателей: храбрые покупатели и смиренные покупатели
//Запрос на обслуживание обозначен действием “getCheese”
//Завершение обслуживания обозначено действием “Cheese”
//Всегда есть доступный сыр и постоянное количество покупателей
//Каждый покупатель должен быть создан в виде отдельной нити, которая входит в очередь, обслуживается, и прекращает работу.

package CheeseShop;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Cheese {

	static Object lock1 = new Object(); 
	
	    @SuppressWarnings("unused")
		private static class Buyer implements Runnable, Comparable<Buyer> {

			 private String name;
			 Buyer(String name){
			         
			        this.name = name;
			    }
			    String getName(){return name;}
			     
			    public int compareTo1(Buyer B){
			     
			        return name.compareTo(B.getName());
			    }
			
	      		public Buyer() {
				
			}

				public void run() {
				while (!Thread.currentThread().isInterrupted()) {
						synchronized (lock1) {
						System.out.println(Thread.currentThread().getName() + " sends a request getCheese");
						try {
							lock1.wait();
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + " gets the Cheese ");
					}
				}
			}
				@Override
				public int compareTo(Buyer o) {
					
					return 0;
				}

		}

		static class Cashier implements Runnable {

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread().getName() + " receives a request getCheese from the Buyer");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					int type = new Random().nextInt(10);
					System.out.println(Thread.currentThread().getName() + " executes the request " + type);
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
				
				Queue Buyer = new LinkedList();
				for (int i = 0;  i < 10; i++){
					Buyer = (Queue) new Buyer ();
					Buyer.add ("Brave");
					Buyer.add ("Humble");

					
			}

		}  
	}
}
