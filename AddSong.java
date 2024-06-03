package Asn2;


public class AddSong implements Runnable {

	// this class must not have any constructor / members / methods
	// except for the run() method

	@Override
	public void run() {
		// TODO Auto-generated method stub

		/*
		 *  fetch an OP from the OPStack;
		 *  handle it if the opcode is 1,
		 *  push it back to the stack otherwise.
		 */
		while (!Thread.currentThread().isInterrupted()) {			
				try {
					OP operation = OPStack.getInstance().pop();
					if (operation.getOP() == 1) {
						Player.getInstance().add(new Song("song"));						
						int forResolved = operation.getFuture().getCounter();
						operation.getFuture().resolve(forResolved);
						FutureImpl.incrementCounter();						
					}
					else {	
						OPStack.getInstance().push(operation);
					}
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
				}			
		}
	}
}