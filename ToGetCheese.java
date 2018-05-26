//����������. ������ ����� � ������������ ���������� �������� �������� ������������.
//2 ���� �����������: ������� ���������� � ��������� ����������
//������ �� ������������ ��������� ��������� �getCheese�
//���������� ������������ ���������� ��������� �Cheese�
//������ ���� ��������� ���, � ���������� ���������� ���� ������� ����������� � ���� ���������
//������ ���������� ������ ���� ������ � ���� ��������� ����, ������� ������ � �������, �������������, � ���������� ������.
package ShopCheese;

public class ToGetCheese {

	static Object lock1 = new Object();
	
	public static void main(String[] args) throws InterruptedException {

		Thread brave = new Thread(new BraveBuyer());
		Thread humble = new Thread(new HumbleBuyer());
		//brave.setPriority(Thread.MAX_PRIORITY);
		//// ���������� join() ��� �������� ���������� �������.
		
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
	    
	  //���� ���� ������� ����������� ��������� ���������� ����� ���, ��� ��������� (������� �����) �������� ���� ����������
	    try {
	       brave.join();
	       humble.join();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    System.out.println("��� ������ ���, ��������� ���������.");
    }
	       
    static class BraveBuyer implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				synchronized (lock1) {
					System.out.println("BraveBuyer is " + Thread.currentThread().getName() + "  ����� ������ ���");
					try {
						System.out.println("BraveBuyer is " + Thread.currentThread().getName() + "  �������� ��� ������� � �����");
						lock1.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("BraveBuyer is " + Thread.currentThread().getName() + "  �������� ���");
				}
			}
		}

	}

	static class HumbleBuyer implements Runnable {

		@Override
		public void run() {
				while (!Thread.currentThread().isInterrupted()) {
				System.out.println("HumbleBuyer is " + Thread.currentThread().getName() + "  ����� ������ ���");
				try {
					System.out.println("HumbleBuyer is " + Thread.currentThread().getName() + "  ����, ���� ������ ���������� ����� ���");
					System.out.println("HumbleBuyer is " + Thread.currentThread().getName() + "  �������� ��� ");
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