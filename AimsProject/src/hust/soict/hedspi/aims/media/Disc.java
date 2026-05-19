package hust.soict.hedspi.aims.media;

public class Disc extends Media {
    private String director;
    private int length;
    
    public Disc() {
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
}
