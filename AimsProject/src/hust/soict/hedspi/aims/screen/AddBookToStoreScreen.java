package hust.soict.hedspi.aims.screen;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfTitle;
    private JTextField tfCategory;
    private JTextField tfAuthor;
    private JTextField tfCost;

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart);
        setTitle("Add Book");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 2, 5, 5));
        center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        center.add(new JLabel("Title:"));
        tfTitle = new JTextField();
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        center.add(tfCategory);

        center.add(new JLabel("Author:"));
        tfAuthor = new JTextField();
        center.add(tfAuthor);

        center.add(new JLabel("Cost:"));
        tfCost = new JTextField();
        center.add(tfCost);

        return center;
    }

    @Override
    protected void addItemToStore() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            String author = tfAuthor.getText();
            float cost = Float.parseFloat(tfCost.getText());

            Book book = new Book((int) (Math.random() * 1000));
            book.setTitle(title);
            book.setCategory(category);
            book.setCost(cost);
            book.addAuthor(author);
            store.addMedia(book);
            
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            
            new StoreScreen(store, cart);
            this.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
