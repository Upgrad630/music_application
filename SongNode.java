/* A SongNode class with song and SongNode fields and a contructor with getters for song and next and setter for next */

public class SongNode {
    private Song song;
    private SongNode next;

    public SongNode(Song song) {
        this.song = song;
        this.next = null;
    }

    public Song getSong() {
        return song;
    }

    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }
}