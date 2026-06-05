package hust.soict.hedspi.aims;

import java.util.Scanner;
import java.util.Collections;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

public class Aims {
    private static Store store = new Store();
    private static Cart itemsOrdered = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");

        do {
            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number: 0-1-2-3");
                continue;
            }
            switch (option) {
                case 1:
                    storeMenu();
                    break;
                case 2:
                    updateStoreMenu();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number: 0-1-2-3");
            }
        } while (true);
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");

        do {
            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number: 0-1-2-3-4");
                continue;
            }
            switch (option) {
                case 1:
                    mediaDetailsMenu();
                    break;
                case 2:
                    displayStoreItems();
                    System.out.println("Please enter the title of the media you want to add to cart: ");
                    String title = scanner.nextLine();
                    addMediaToCart(title);
                    break;
                case 3:
                    displayStoreItems();
                    System.out.println("Please enter the title of the media you want to play: ");
                    String playTitle = scanner.nextLine();
                    playMediaFromStore(playTitle);
                    break;
                case 4:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Going back...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number: 0-1-2-3-4");
            }
        } while (true);
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void cartMenu() {
        do {
            itemsOrdered.print();
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");

            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number: 0-1-2-3-4-5");
                continue;
            }
            
            switch (option) {
                case 1:
                    filterMediaInCart();
                    break;
                case 2:
                    sortMediaInCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    placeOrder();
                    return;
                case 0:
                    System.out.println("Going back...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number: 0-1-2-3-4-5");
            }
        } while (true);
    }

    public static void updateStoreMenu() {
        do {
            System.out.println("Update Store Menu: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media to store");
            System.out.println("2. Remove a media from store");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");
            
            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number: 0-1-2");
                continue;
            }
            
            switch (option) {
                case 1:
                    addMediaToStore();
                    break;
                case 2:
                    removeMediaFromStore();
                    break;
                case 0:
                    System.out.println("Going back...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number: 0-1-2");
            }
        } while (true);
    }

    public static void addMediaToStore() {
        System.out.println("This feature is not fully implemented yet.");
    }

    public static void removeMediaFromStore() {
        if (store.itemsInStore.isEmpty()) {
            System.out.println("Store is empty!");
            return;
        }
        displayStoreItems();
        System.out.println("Please enter the title of the media you want to remove: ");
        String title = scanner.nextLine();
        
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        
        Media foundMedia = null;
        for (Media media : store.itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                foundMedia = media;
                break;
            }
        }
        
        if (foundMedia == null) {
            System.out.println("Media with title '" + title + "' not found in store!");
            return;
        }
        
        store.removeMedia(foundMedia);
        System.out.println("Media removed from store: " + foundMedia.getTitle());
    }

    public static void playMediaFromStore(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        
        Media foundMedia = null;
        for (Media media : store.itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                foundMedia = media;
                break;
            }
        }
        
        if (foundMedia == null) {
            System.out.println("Media with title '" + title + "' not found in store!");
            return;
        }
        
        if (foundMedia instanceof Playable) {
            try {
                ((Playable) foundMedia).play();
            } catch (hust.soict.hedspi.aims.exception.PlayerException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("This media cannot be played!");
        }
    }

    public static void playMediaFromCart() {
        if (itemsOrdered.itemsOrdered.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        
        System.out.println("Please enter the title of the media you want to play: ");
        String title = scanner.nextLine();
        
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        
        Media foundMedia = null;
        for (Media media : itemsOrdered.itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                foundMedia = media;
                break;
            }
        }
        
        if (foundMedia == null) {
            System.out.println("Media with title '" + title + "' not found in cart!");
            return;
        }
        
        if (foundMedia instanceof Playable) {
            try {
                ((Playable) foundMedia).play();
            } catch (hust.soict.hedspi.aims.exception.PlayerException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("This media cannot be played!");
        }
    }

    public static void removeMediaFromCart() {
        if (itemsOrdered.itemsOrdered.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        
        System.out.println("Please enter the title of the media you want to remove: ");
        String title = scanner.nextLine();
        
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        
        Media foundMedia = null;
        for (Media media : itemsOrdered.itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                foundMedia = media;
                break;
            }
        }
        
        if (foundMedia == null) {
            System.out.println("Media with title '" + title + "' not found in cart!");
            return;
        }
        
        itemsOrdered.removeMedia(foundMedia);
        System.out.println("Number of DVDs in cart: " + itemsOrdered.itemsOrdered.size());
    }

    public static void filterMediaInCart() {
        System.out.println("Filter Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number: 0-1-2");
            return;
        }
        
        switch (option) {
            case 1:
                System.out.println("Please enter the ID you want to search for: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    itemsOrdered.searchById(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format!");
                }
                break;
            case 2:
                System.out.println("Please enter the title you want to search for: ");
                String title = scanner.nextLine();
                itemsOrdered.searchByTitle(title);
                break;
            case 0:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid option. Please choose a number: 0-1-2");
        }
    }

    public static void sortMediaInCart() {
        System.out.println("Sort Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Sort by Title then Cost");
        System.out.println("2. Sort by Cost then Title");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
        
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number: 0-1-2");
            return;
        }
        
        switch (option) {
            case 1:
                Collections.sort(itemsOrdered.itemsOrdered, Media.COMPARE_BY_TITLE_COST);
                System.out.println("Cart sorted by Title then Cost!");
                break;
            case 2:
                Collections.sort(itemsOrdered.itemsOrdered, Media.COMPARE_BY_COST_TITLE);
                System.out.println("Cart sorted by Cost then Title!");
                break;
            case 0:
                System.out.println("Going back...");
                break;
            default:
                System.out.println("Invalid option. Please choose a number: 0-1-2");
        }
    }

    public static void placeOrder() {
        if (itemsOrdered.itemsOrdered.isEmpty()) {
            System.out.println("Cart is empty! Cannot place order.");
            return;
        }
        
        System.out.println("Order created successfully!");
        System.out.println("Total cost: " + itemsOrdered.totalCost() + "$");
        itemsOrdered.itemsOrdered.clear();
        System.out.println("Cart has been emptied.");
    }

    public static void displayStoreItems() {
        if (store.itemsInStore.isEmpty()) {
            System.out.println("Store is empty!");
            return;
        }
        System.out.println("*****STORE ITEMS*****");
        for (int i = 0; i < store.itemsInStore.size(); i++) {
            Media media = store.itemsInStore.get(i);
            System.out.println((i + 1) + ". Title: " + media.getTitle() + " | Cost: " + media.getCost() + "$");
        }
        System.out.println("*********************");
    }

    public static void addMediaToCart(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        
        Media foundMedia = null;
        for (Media media : store.itemsInStore) {
            if (media.getTitle().equalsIgnoreCase(title.trim())) {
                foundMedia = media;
                break;
            }
        }
        
        if (foundMedia == null) {
            System.out.println("Media with title '" + title + "' not found in store!");
            return;
        }
        
        itemsOrdered.addMedia(foundMedia);
        System.out.println("Number of DVDs in cart: " + itemsOrdered.itemsOrdered.size());
    }

    public static void main(String[] args) {
        showMenu();
    }
}