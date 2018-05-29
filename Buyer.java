package ShopCheese;

public class Buyer {
	private static final String queue = null;

	public Buyer(String string) {
		
	}

	public static void main(String[] args) {
		Buyer brave = new Buyer(queue);
		Buyer humble = new Buyer(queue);
		brave.setPriority(Thread.MAX_PRIORITY);
		brave.run();
	    humble.run();
}

	private void setPriority(int maxPriority) {
		
	}

	private void run() {
			
	}
}
