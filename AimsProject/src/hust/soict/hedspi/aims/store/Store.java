package hust.soict.hedspi.aims.store;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class Store {
    public DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[100];

    public void addDVD(DigitalVideoDisc disc) {
        for (int i = 0; i < itemsInStore.length; i++) {
            if (itemsInStore[i] == null) {
                itemsInStore[i] = disc;
                break;
            }
        }
    }

    public void removeDVD(DigitalVideoDisc disc) {
        for (int i = 0; i < itemsInStore.length; i++) {
            if (itemsInStore[i] == disc) {
                itemsInStore[i] = null;
                break;
            }
        }
    }
}
