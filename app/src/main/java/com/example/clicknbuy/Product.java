package com.example.clicknbuy;

public class Product {
   String productName,price,imageUrl;

   public Product(){
   }

    public Product( String productName, String price,String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "imageUrl='" + imageUrl + '\'' +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
