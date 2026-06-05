package hust.soict.hedspi.aims.screen;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.*;
import hust.soict.hedspi.aims.media.*;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> new AddBookToStoreScreen(store, cart));
        smUpdateStore.add(addBookItem);
        
        JMenuItem addCdItem = new JMenuItem("Add CD");
        addCdItem.addActionListener(e -> new AddCompactDiscToStoreScreen(store, cart));
        smUpdateStore.add(addCdItem);
        
        JMenuItem addDvdItem = new JMenuItem("Add DVD");
        addDvdItem.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store, cart));
        smUpdateStore.add(addDvdItem);

        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));
        menu.add(new JMenuItem("View cart"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton viewCartBtn = new JButton("View cart");
        viewCartBtn.setPreferredSize(new Dimension(100, 50));
        viewCartBtn.setMaximumSize(new Dimension(100, 50));
        viewCartBtn.addActionListener(e -> {
            CartScreen cartScreen = new CartScreen(cart);
            cartScreen.setSize(1024, 768);
            cartScreen.setLocationRelativeTo(null);
            cartScreen.setDefaultCloseOperation(CartScreen.EXIT_ON_CLOSE);
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(viewCartBtn);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        ArrayList<Media> mediaInStore = store.getItemsInStore();
        for(int i = 0; i < mediaInStore.size() && i < 9; i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
            center.add(cell);
        }

        return center;
    }

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }
}
