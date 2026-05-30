package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import java.util.*;

public class MediaTest {
    public static void main(String[] args) {
        Cart anOrder = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation",18.99f);
        anOrder.addMedia(dvd3);

        System.out.println("Total Cost is: " + anOrder.totalCost());

        anOrder.removeMedia(dvd2);

        System.out.println("Total Cost (after remove dvd2) is: " + anOrder.totalCost());

        List<Media> mediae = new ArrayList<Media>();

        Media cd = new CompactDisc("abc");
        Media dvd = new DigitalVideoDisc("Tom & Jerry");
        Media book = new Book(4);

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        for(Media m: mediae) {
            System.out.println(m.toString());
        }
    }
}
