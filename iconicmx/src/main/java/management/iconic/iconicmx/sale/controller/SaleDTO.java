package management.iconic.iconicmx.sale.controller;

public class SaleDTO {
    private long id;
    private String description;
    private double price;
    private int quantity;
    private double amount;
    private double discount;

    public SaleDTO() {
    }

    public SaleDTO(long id, String description, double price, int quantity, double amount, double discount) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
