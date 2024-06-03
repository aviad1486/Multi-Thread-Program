package Asn2;


public class OP {
	// op = 1: add song to the head of the player.
	// op = 2: remove song to the head of the player.
	private int _op;
	private FutureImpl _future;
	
	public OP(int op) {
		_op = op;
		_future = new FutureImpl();
	}
	
	public int getOP() {
		return _op;
	}
	public FutureImpl getFuture() {
		return _future;
	}
}
