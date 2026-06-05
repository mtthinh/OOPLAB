package hust.soict.hedspi.test.screen;

import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.screen.*;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.cart.*;

public class StoreScreenTest {
    public static void main(String[] args) {
        Store s = new Store();
        Cart c = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("DVD1's Title");
        s.addMedia(dvd1);
        
        Book b1 = new Book(1);
        b1.setTitle("Book2's Title");
        s.addMedia(b1);

        CompactDisc cd1 = new CompactDisc("artist1");
        cd1.setTitle("CD3's Title");
        s.addMedia(cd1);

        CompactDisc cd2 = new CompactDisc("artist2");
        cd2.setTitle("CD4's Title");
        s.addMedia(cd2);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("DVD5's Title");
        s.addMedia(dvd2);

        CompactDisc cd3 = new CompactDisc("artist3");
        cd3.setTitle("CD6's Title");
        s.addMedia(cd3);
        
        Book b2 = new Book(2);
        b2.setTitle("Book7's Title");
        s.addMedia(b2);
        
        Book b3 = new Book(3);
        b3.setTitle("Book8's Title");
        s.addMedia(b3);

        CompactDisc cd4 = new CompactDisc("artist4");
        cd4.setTitle("CD9's Title");
        s.addMedia(cd4);
        
        new StoreScreen(s, c);
    }
}
