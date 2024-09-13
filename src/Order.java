
import java.util.List;

public class Order {
    
    private String orderNumber;
    private String customerName;
    private List<Product> products;
    private String paymentMethod;

    public Order(String orderNumber, String customerName, List<Product> products, String paymentMethod){
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.products = products;
        this.paymentMethod = paymentMethod;
    }

    public String getOrderNumber(){
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber){
        this.orderNumber = orderNumber;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }


    public String toString() {
        return  orderNumber +
                ", " + customerName + 
                ", " + products +
                ", " + paymentMethod;
    }


}
