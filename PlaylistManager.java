import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PlaylistManager {
    private HashMap<String, Playlist> playlists; // Map to store playlists

    // Constructor
    public PlaylistManager() {
        this.playlists = new HashMap<>();
    }

    // Method to create a new playlist
    public void createPlaylist(String name) {
        if (!playlists.containsKey(name)) {
            playlists.put(name, new Playlist(name));
            System.out.println("Playlist '" + name + "' created successfully.");
        } else {
            System.out.println("Playlist with name '" + name + "' already exists.");
        }
    }

    // Method to get a playlist by name from the playlist manager
    public Playlist getPlaylist(String playlistName) {
        return playlists.get(playlistName);
    }

    // Method to add a song to a playlist
    public void addSongToPlaylist(String playlistName, Song song) {
        Playlist playlist = playlists.get(playlistName);
        playlist.addSong(song);
        System.out.println("Song '" + song.getTitle() + "' by '" + song.getArtist() + "' added to playlist '" + playlistName + "'.");
    }

    // Method to remove a song from a playlist
    public void removeSongFromPlaylist(String playlistName, String songTitle) {
        Playlist playlist = playlists.get(playlistName);

        // Find the song in the playlist
        Song songToRemove = playlist.findSongByTitle(songTitle);
        if (songToRemove != null) {
            // Remove the song from the playlist
            playlist.removeSong(songToRemove);
            System.out.println("Song \"" + songTitle + "\" removed from playlist \"" + playlistName + "\".");
        } else {
            System.out.println("Song \"" + songTitle + "\" not found in playlist \"" + playlistName + "\".");
        }
    }

    // Method to display all playlists
    public void displayPlaylists() {
        System.out.println("Available Playlists:");
        for (String playlistName : playlists.keySet()) {
            System.out.println("- " + playlistName);
        }
    }

    // Method to check if a playlist with the given name exists
    public boolean containsPlaylist(String playlistName) {
        return playlists.containsKey(playlistName);
    }

// Method to merge two playlists into a new playlist with specific requirements
public void mergePlaylistsRandom(String playlist1Name, String playlist2Name, String newPlaylistName) {
    // Check if both input playlists exist
    if (!containsPlaylist(playlist1Name) || !containsPlaylist(playlist2Name)) {
        System.out.println("One or both of the input playlists do not exist.");
        return;
    }

    // Check if the new playlist name already exists
    if (containsPlaylist(newPlaylistName)) {
        System.out.println("A playlist with the name '" + newPlaylistName + "' already exists.");
        return;
    }

    // Get the input playlists
    Playlist playlist1 = getPlaylist(playlist1Name);
    Playlist playlist2 = getPlaylist(playlist2Name);

    int totalSongs = playlist1.getAllSongs().size() + playlist2.getAllSongs().size();
    Random random = new Random();

    // Generate random N (size of new playlist)
    int N = random.nextInt(totalSongs - 1) + 2; // Ensures N is at least 2 and less than totalSongs

    // Determine random number of songs from each playlist
    int songsFromPlaylist1 = random.nextInt(N - 1) + 1; // At least 1, at most N-1
    int songsFromPlaylist2 = N - songsFromPlaylist1; // Ensures sum is N
    // Create the new playlist
    createPlaylist(newPlaylistName);
    Playlist newPlaylist = getPlaylist(newPlaylistName);

    // Add random songs from playlist1
    addRandomSongs(playlist1, newPlaylist, songsFromPlaylist1);

    // Add random songs from playlist2
    addRandomSongs(playlist2, newPlaylist, songsFromPlaylist2);

    System.out.println("New playlist '" + newPlaylistName + "' created with " + N + " songs (" 
                       + songsFromPlaylist1 + " from '" + playlist1Name + "' and " 
                       + songsFromPlaylist2 + " from '" + playlist2Name + "').");
}

// Helper method to add random songs from one playlist to another
private void addRandomSongs(Playlist sourcePlaylist, Playlist targetPlaylist, int numSongs) {
    List<Song> allSongs = new ArrayList<>(sourcePlaylist.getAllSongs());
    Collections.shuffle(allSongs);
    for (int i = 0; i < Math.min(numSongs, allSongs.size()); i++) {
        targetPlaylist.addSong(allSongs.get(i));
    }
}
}