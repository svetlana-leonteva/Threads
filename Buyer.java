import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


class Buyer implements Runnable{
	private Cashier cashier;
	private int number;
	private ProcessType type;
	
	public Buyer(int number, ProcessType type, Cashier cashier){
		this.number = number;
		this.type = type;
		this.cashier = cashier;
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			synchronized(this.cashier){
				System.out.println((type == ProcessType.Brave ? "Brave" : "Humble") + " thread " + " sends the request getCheese");
				cashier.getCheese(type, number);
			}
			synchronized(this){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println((type == ProcessType.Brave ? "Brave" : "Humble") + " thread " +  " gets the Cheese");
			break;
		}
	}

	public void cheese() {
		this.notify();
	}
}

class Cashier implements Runnable{

	List<Ticket> tickets = null;
	LinkedList<Buyer> buyers = null;
	
	public Cashier(LinkedList<Buyer> buyers){
		this.tickets = Collections.synchronizedList(new LinkedList<Ticket>());
		this.buyers = buyers;
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				synchronized(this){
					wait(15);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(tickets.isEmpty()){
				break;
			}
			Ticket t = null;
			synchronized(tickets){
				for(Ticket i: tickets){
					if(i.getType() == ProcessType.Brave){
						t = i;
						tickets.remove(i);
						break;
				}
				}
				if(t == null){
					t = tickets.get(0);
					tickets.remove(0);
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Buyer b = buyers.get(t.getNumber());
			synchronized (b) {
				b.cheese();
			}
		}
	}

	public synchronized void getCheese(ProcessType type, int number) {
		tickets.add(new Ticket(type, number));
		this.notify();
	}
	
}

enum ProcessType {
	Brave, Humble;
}

class Ticket{
	
	private ProcessType type;
	private int number;
	
	public Ticket(ProcessType type, int number) {
		this.type = type;
		this.number = number;
	}
	
	public ProcessType getType() {
		return type;
	}

	public int getNumber() {
		return number;
	}
 }


