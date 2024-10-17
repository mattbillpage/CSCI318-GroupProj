package csci318.group10.analyticsservice.shareddomain.events;


public class CartItem {

    private int id;

    private int productId;
    private int quantity;

    public CartItem() {

    }

    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return String.valueOf(productId) + " " + String.valueOf(quantity);
    }



}