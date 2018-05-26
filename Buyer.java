package ru.spb.telematics.parprog;

public class Buyer {
	public Buyer(String string) {
		
	}

	public static void main(String[] args) {
		Buyer brave = new Buyer("brave");
		Buyer humble = new Buyer("humble");
		brave.setPriority(Thread.MAX_PRIORITY);
		brave.run();
	    humble.run();
}

	private void setPriority(int maxPriority) {
		
	}

	private void run() {
			
	}
}