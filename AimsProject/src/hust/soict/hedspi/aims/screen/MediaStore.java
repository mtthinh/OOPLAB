package hust.soict.hedspi.aims.screen;

import java.awt.*;
import javax.swing.*;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;
    
    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartBtn = new JButton("Add to cart");
        addToCartBtn.addActionListener(e -> {
            cart.addMedia(media);
            System.out.println("Added to cart: " + media.getTitle());
        });
        container.add(addToCartBtn);
        
        if(media instanceof Playable) {
            JButton playBtn = new JButton("Play");
            playBtn.addActionListener(e -> {
                try {
                    ((Playable) media).play();
                } catch (hust.soict.hedspi.aims.exception.PlayerException ex) {
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                }
            });
            container.add(playBtn);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Media getMedia() {
        return media;
    }

    public Cart getCart() {
        return cart;
    }
}
