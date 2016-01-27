package com.warriors.groups.supershopproductsearch;

/**
 * Created by Mobile App Develop on 22-12-15.
 */
public class ProductModel
{

    private int productId;
    private String productName;
    private String productPrice;
    private String productCategory;
    private String productStatus;
    private String companyName;
    private String productLocation;
//Constructor


    public ProductModel() {
    }

    public ProductModel(int productId, String productName, String productPrice, String productCategory, String productStatus,String companyName,String productLocation)
    {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
        this.companyName = companyName;
        this.productLocation = productLocation;
    }
    //Constructor
    public ProductModel(String productName, String productPrice, String productCategory, String productStatus,String companyName,String productLocation)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
        this.companyName = companyName;
        this.productLocation = productLocation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }



}
