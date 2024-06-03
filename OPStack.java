package Asn2;

import java.util.ArrayList;
import java.util.List;

// design the class as a *thread-safe singleton*.
// the methods of this object can be synchronized.
public class OPStack implements Stack<OP> {

	// you are allowed to use this data structure.
	// you must not use any built-in thread-safe data structure.
	private List<OP> ops = new ArrayList<OP>();
	private static OPStack instance = null;
	
	
	private OPStack() {}
	
	public static synchronized OPStack getInstance() {
		if (instance == null) {
			instance = new OPStack();	
		}
		return instance;
	}
	
	@Override
	public synchronized FutureImpl push(OP e) {
		// TODO Auto-generated method stub
		// each (distinct) OP inserted into the OPStack has a corresponding Future object.		
		ops.add(e);
		notifyAll();
		return e.getFuture();
	}

	@Override
	public synchronized OP pop() throws InterruptedException {
		// TODO Auto-generated method stub
		// if the OPStack is empty, the calling thread is *blocked*.
				while (ops.size() == 0) {
					wait();
				}
				OP res = ops.get(ops.size()-1);
				ops.remove(ops.size()-1);
				notifyAll();
				return res;	
	}
	
	@Override
	public synchronized boolean isEmpty() {
		return (ops.size() == 0);
	}

	@Override
	public synchronized void resolve(OP e, int val) {
		// TODO Auto-generated method stub
		// resolve the corresponding OP object with the answer @val
	}
	
	
}
