package hust.soict.hedspi.test.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.store.Store;

public class CartScreenTest {
    public static void main(String[] args) {
        Store store = new Store();
        
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Avatar");
        dvd1.setId(1);
        dvd1.setCategory("Action");
        dvd1.setCost(15.0f);
        store.addMedia(dvd1);
        
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Inception");
        dvd2.setId(2);
        dvd2.setCategory("Sci-Fi");
        dvd2.setCost(20.0f);
        store.addMedia(dvd2);
        
        CompactDisc cd1 = new CompactDisc("Artist1");
        cd1.setId(3);
        cd1.setTitle("Popular Songs");
        cd1.setCategory("Music");
        cd1.setCost(10.0f);
        store.addMedia(cd1);
        
        CompactDisc cd2 = new CompactDisc("Artist2");
        cd2.setId(4);
        cd2.setTitle("Rock Album");
        cd2.setCategory("Music");
        cd2.setCost(12.0f);
        store.addMedia(cd2);
        
        Book book1 = new Book(5);
        book1.setTitle("Programming Java");
        book1.setCategory("Technology");
        book1.setCost(25.0f);
        store.addMedia(book1);
        
        Book book2 = new Book(6);
        book2.setTitle("Database Design");
        book2.setCategory("Technology");
        book2.setCost(30.0f);
        store.addMedia(book2);
        
        Cart cart = new Cart();
        
        new StoreScreen(store, cart);
    }
}