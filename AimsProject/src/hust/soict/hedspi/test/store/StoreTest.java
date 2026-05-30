package hust.soict.hedspi.test.store;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;


public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "John Musker", 90, 18.99f);

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        System.out.println("Store after adding DVDs:");
        for (Media disc : store.itemsInStore) {
            if (disc != null) {
                System.out.println("- " + disc.getTitle());
            }
        }

        store.removeMedia(dvd2);

        System.out.println("\nStore after removing 'Star Wars':");
        for (Media disc : store.itemsInStore) {
            if (disc != null) {
                System.out.println("- " + disc.getTitle());
            }
        }
    }
}
