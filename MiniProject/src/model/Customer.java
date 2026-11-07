package model;
// Purpose: Customer details with a defensive deep-clone operation for its address.
import java.io.Serializable;
public class Customer implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private static long customerCounter = 101;
    private final String customerId;
    private String name, email, mobile;
    private Address address;
    public Customer(String name, String email, String mobile) {
        this.customerId = generateCustomerId(); this.name = name; this.email = email; this.mobile = mobile;
    }
    private static String generateCustomerId() { return "CUST" + customerCounter++; }
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    @Override public Customer clone() {
        try { Customer copy = (Customer) super.clone(); if (address != null) copy.address = address.clone(); return copy; }
        catch (CloneNotSupportedException e) { throw new AssertionError(e); }
    }
    public static class Address implements Cloneable, Serializable {
        private static final long serialVersionUID = 1L;
        private String line, city, pincode;
        public Address(String line, String city, String pincode) { this.line=line; this.city=city; this.pincode=pincode; }
        public String getLine(){return line;} public String getCity(){return city;} public String getPincode(){return pincode;}
        @Override public Address clone() { try { return (Address) super.clone(); } catch (CloneNotSupportedException e) { throw new AssertionError(e); } }
    }
}
