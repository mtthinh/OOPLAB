package hust.soict.hedspi.aims.screen;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;

    public AddItemToStoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.add(createSouth(), BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Add Item to Store");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        
        JMenuItem backItem = new JMenuItem("Back to Store");
        backItem.addActionListener(e -> {
            new StoreScreen(store, cart);
            this.dispose();
        });
        menu.add(backItem);
        
        menuBar.add(menu);
        return menuBar;
    }

    protected JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        
        JLabel title = new JLabel("Add Item");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);
        north.add(title);
        
        return north;
    }

    protected abstract JPanel createCenter();

    protected JPanel createSouth() {
        JPanel south = new JPanel();
        
        JButton addBtn = new JButton("Add to Store");
        addBtn.addActionListener(e -> addItemToStore());
        
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            new StoreScreen(store, cart);
            this.dispose();
        });
        
        south.add(addBtn);
        south.add(backBtn);
        
        return south;
    }

    protected abstract void addItemToStore();
}
