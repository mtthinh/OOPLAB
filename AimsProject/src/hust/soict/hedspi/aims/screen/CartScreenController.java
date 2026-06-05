package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
    private Cart cart;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblTotal;

    @FXML
    private Button placeOrder;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TextField tfFilter;

    @FXML
    private ToggleGroup filterCategory;

    public CartScreenController(Cart cart) {
        super();
        this.cart = cart; 
    }


    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        
        updateTotalCost();
        cart.getItemsOrdered().addListener(new ListChangeListener<Media>() {
            @Override
            public void onChanged(Change<? extends Media> c) {
                updateTotalCost();
            }
        });

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });

        tblMedia.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Media>() {
            @Override
            public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                if(newValue != null) {
                    updateButtonBar(newValue);
                }
            }
        });
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if(media instanceof Playable) {
            btnPlay.setVisible(true);
        }
        else {
            btnPlay.setVisible(false);
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            showFilteredMedia(tfFilter.getText());
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null && media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (hust.soict.hedspi.aims.exception.PlayerException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    void placeOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("Order placed successfully!");
        System.out.println("Total cost: " + cart.totalCost() + "$");
        cart.getItemsOrdered().clear();
        updateTotalCost();
    }

    private void updateTotalCost() {
        float total = cart.totalCost();
        lblTotal.setText(String.format("%.0f $", total));
    }

    private void showFilteredMedia(String keyword) {
        FilteredList<Media> filteredList = new FilteredList<>(cart.getItemsOrdered());
        
        if(keyword != null && !keyword.isEmpty() && radioBtnFilterId.isSelected()) {
            filteredList.setPredicate(media -> {
                String idString = String.valueOf(media.getId());
                return idString.equals(keyword);
            });
        } else if(keyword != null && !keyword.isEmpty() && radioBtnFilterTitle.isSelected()) {
            filteredList.setPredicate(media -> {
                String title = media.getTitle().toLowerCase();
                return title.contains(keyword.toLowerCase());
            });
        } else {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }
        tblMedia.setItems(filteredList);
    }
}
