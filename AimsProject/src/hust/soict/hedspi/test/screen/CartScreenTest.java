package hust.soict.hedspi.test.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.screen.CartScreen;

public class CartScreenTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        
        DigitalVideoDisc dvd = new DigitalVideoDisc("Avatar");
        dvd.setId(3);
        dvd.setCategory("Action");
        dvd.setCost(15.0f);

        CompactDisc cd = new CompactDisc("Musician");
        cd.setId(1);
        cd.setTitle("Popular Songs");
        cd.setCategory("Music");
        cd.setCost(10.0f);

        Book book = new Book(2);
        book.setTitle("Programming");
        book.setCategory("Technology");
        book.setCost(25.0f);
        
        cart.addMedia(dvd);
        cart.addMedia(cd);
        cart.addMedia(book);
        
        CartScreen cartScreen = new CartScreen(cart);
        cartScreen.setSize(1024, 768);
        cartScreen.setLocationRelativeTo(null);
        cartScreen.setDefaultCloseOperation(CartScreen.EXIT_ON_CLOSE);
        cartScreen.setVisible(true);
    }
}