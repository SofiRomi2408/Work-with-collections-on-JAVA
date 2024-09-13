public class Product {
    private String productName;
    private int productCount;
    private double productPrice;

    public Product(String productName, int productCount, double productPrice){
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;

    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName= productName;

    }

    public int getProductCount(){
        return productCount;
    }

    public void setProductCount(int productCount){
        this.productCount=productCount;
    }

    public double getProductPrice(){
        return productPrice;
    }

    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }


    public String toString() {
        return productName + " (" + productCount + " x " + productPrice + ")";
    }

}
