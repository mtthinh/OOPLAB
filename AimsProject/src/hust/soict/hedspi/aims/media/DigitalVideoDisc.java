package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc {
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super.setTitle(title);
        nbDigitalVideoDiscs++;
        super.setId(nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        this(title);
        super.setCategory(category);
        super.setCost(cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        this(title, category, cost);
        super.setDirector(director);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this(title, category, director, cost);
        super.setLength(length);
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + " - " + getCost() + " - " + getId();
    }
}