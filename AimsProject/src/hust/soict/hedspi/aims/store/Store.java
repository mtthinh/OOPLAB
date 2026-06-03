package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;

public class Store {
    public ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public Store() {

    }

    public void addMedia(Media media) {
        itemsInStore.add(media);
    }

    public void removeMedia(Media media) {
        itemsInStore.remove(media);
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}
