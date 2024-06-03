package Asn2;



public class FutureImpl implements Future {
	private static int counter = 1;
	private boolean isKnown = false;
	private int order;
	

	public FutureImpl() {}
	
	@Override
	public synchronized int get() {
		// TODO Auto-generated method stub
		while (!isKnown) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return order;
	}
	@Override
	public synchronized void resolve(int val) {
		// TODO Auto-generated method stub
		order = val;
		isKnown = true;
		notifyAll();
	}
	
	public synchronized int getCounter() {
		return counter;
	}
	
	public synchronized static void incrementCounter() {
		counter++;
	}
}
