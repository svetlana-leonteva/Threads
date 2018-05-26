//Покупатели. Сырный отдел в супермаркете непрерывно собирает голодных покупателями.
//2 типа покупателей: храбрые покупатели и смиренные покупатели
//Запрос на обслуживание обозначен действием “getCheese”
//Завершение обслуживания обозначено действием “Cheese”
//Всегда есть доступный сыр, и постоянное количество двух храбрых покупателей и двух смиренных
//Каждый покупатель должен быть создан в виде отдельной нити, которая входит в очередь, обслуживается, и прекращает работу.
package ShopCheese;

public class ToGetCheese {

	static Object lock1 = new Object();
	
	public static void main(String[] args) throws InterruptedException {

		Thread brave = new Thread(new BraveBuyer());
		Thread humble = new Thread(new HumbleBuyer());
		//brave.setPriority(Thread.MAX_PRIORITY);
		//// Применение join() для ожидания завершения потоков.
		
		brave.start();
		try {
            brave.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

	    humble.start();
	    try {
            brave.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    
	  //даем всем потокам возможность закончить выполнение перед тем, как программа (главный поток) закончит свое выполнение
	    try {
	       brave.join();
	       humble.join();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Все купили сыр, завершаем программу.");
    }
	       
    static class BraveBuyer implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				synchronized (lock1) {
					System.out.println("BraveBuyer is " + Thread.currentThread().getName() + "  хочет купить сыр");
					try {
						System.out.println("BraveBuyer is " + Thread.currentThread().getName() + "  проходит без очереди к кассе");
						lock1.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("BraveBuyer is " + Thread.currentThread().getName() + "  получает сыр");
				}
			}
		}

	}

	static class HumbleBuyer implements Runnable {

		@Override
		public void run() {
				while (!Thread.currentThread().isInterrupted()) {
				System.out.println("HumbleBuyer is " + Thread.currentThread().getName() + "  хочет купить сыр");
				try {
					System.out.println("HumbleBuyer is " + Thread.currentThread().getName() + "  ждет, пока наглый покупатель купит сыр");
					System.out.println("HumbleBuyer is " + Thread.currentThread().getName() + "  получает сыр ");
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				    synchronized (lock1) {
					lock1.notify();
				}
			}
		}

	}

	

}
