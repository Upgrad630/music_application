import java.util.ArrayList;
import java.util.List;

public class Album{
    private  String name;
    private  String artist;
    private List<Song> songs;
    private int totalDuration;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<>();
        this.totalDuration = 0;
    }

    public String getName() {
        return name;
    }
    public String getArtist() {
        return artist;
    }
    public List<Song> getSongs() {
        return songs;
    }
    public int getTotalDuration() {
        return totalDuration;
    }
    public void addSong(Song song) {
        songs.add(song);
        totalDuration += song.getDuration();
    }
    public void removeSong(Song song) {
        if (songs.remove(song)) {
            totalDuration -= song.getDuration();
        }
    }
}