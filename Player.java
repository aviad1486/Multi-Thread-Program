package Asn2;

import java.util.concurrent.atomic.AtomicReference;

//the methods of this object *must not* be synchronized.
//do it lock-free...
//this class must be implementes as a *thread-safe singleton*.
public class Player {
	private static Player instance = new Player();
	private AtomicReference<Link> head = new AtomicReference<Link>(null);
	
	private Player() {}
	
	public static Player getInstance() {
		return instance;
	}
	
	public void add(Song song) {
		// code...
		Link oldVal;
		Link newVal;
		
		do {
			oldVal = head.get();
			newVal = new Link(song);
			newVal.setNext(oldVal);
		} while(!head.compareAndSet(oldVal, newVal));
	}
	
	public void remove() {
		// code...					
		// case 1 if head = null but considering when there'll be something to remove later
		// if the output 2 2 2 i can't wait, but for 2 1 1 2 i need to.

		// case 2 if head != null
			Link oldVal;
			Link newVal;	
			do {
				oldVal = head.get();				
				if (oldVal == null) {
					newVal = null;
					oldVal = new Link(null);
		        }
				else {
					newVal = oldVal.getNext();
				}
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
			} while (!head.compareAndSet(oldVal, newVal));
	}
	
	// you can assume that no other thread interferes with this method.
	// this method must iterate over all songs and count them on the fly,
	// it must not return the value of some counter.
	public int size() {
		// code...
		int count = 0;
		Link p = head.get();
		while (p != null) {
			count++;
			p = p.getNext();
		}
		return count;
	}
}