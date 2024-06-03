package Asn2;

public class Link {
	private Link _next;
	private Song _song; // warning - never used
	
	public Link(Song song) {
		_song = song;
		_next = null;
	}
	public Song getSong() {
		return _song;
	}
	public Link getNext() {
		return _next;
	}
	public void setNext(Link p) {
		_next = p;
	}
}
