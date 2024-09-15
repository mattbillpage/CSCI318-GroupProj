package csci318.group10.cartservice.domain.dto;

public class CartItemDTO {
    private int productId;
    private int quantity;
    private String productName;
    private String productDescription;
    private double productPrice;

    public CartItemDTO() {}


    public CartItemDTO(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItemDTO(int productId, int quantity, String productName, String productDescription, double productPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;

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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
}
