package org.ucvts.comics.model;

import org.ucvts.comics.dao.CustomerDAO;

import java.sql.SQLException;

public class Customer {

    private static long lastCustomerId = 1L; // initial customer ID

    private long customerId;
    private String firstName;
    private String lastName;
    private Long phone;
    private String email;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;

    /**
     * Creates a default instance of the Customer class.
     */

    public Customer() throws SQLException {
        this.customerId = Customer.lastCustomerId++;
        CustomerDAO.insertCustomer(this);// auto-generate ID
    }

    /**
     * Creates an instance of the Customer class.
     *
     * @param firstName     the first name
     * @param lastName      the last name
     * @param phone         the telephone number
     * @param email         the email address
     * @param streetAddress the street address
     * @param city          the city
     * @param state         the state
     * @param postalCode    the postal code
     */

    public Customer(long customerId, String firstName, String lastName, long phone, String email, String streetAddress,
                    String city, String state, String postalCode) throws SQLException {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        CustomerDAO.insertCustomer(this);
    }

    public Customer(String firstName, String lastName, long phone, String email, String streetAddress,
                    String city, String state, String postalCode) throws SQLException {
        this.customerId = Customer.lastCustomerId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        CustomerDAO.insertCustomer(this);
    }

    public Customer(String firstName, String lastName, String email, String streetAddress,
                    String city, String state, String postalCode) throws SQLException {
        this.customerId = Customer.lastCustomerId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        CustomerDAO.insertCustomer(this);
    }

    /**
     * Returns the customer ID.
     *
     * @return customerId
     */

    public long getCustomerId() {
        return customerId;
    }

    /**
     * Returns the customer's first name.
     *
     * @return firstName
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     *
     * @param firstName the new first name
     */

    public void setFirstName(String firstName) throws SQLException {
        this.firstName = firstName;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the customer's last name.
     *
     * @return lastName
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer's last name.
     *
     * @param lastName the new last name
     */

    public void setLastName(String lastName) throws SQLException {
        this.lastName = lastName;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the customer's phone number.
     *
     * @return phone
     */

    public long getPhone() {
        return phone;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phone the new phone number
     */

    public void setPhone(long phone) throws SQLException {
        this.phone = phone;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the customer's email address.
     *
     * @return email
     */

    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer's email address.
     *
     * @param email the new email address
     */

    public void setEmail(String email) throws SQLException {
        this.email = email;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the customer's street address.
     *
     * @return streetAddress
     */

    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the customer's street address.
     *
     * @param streetAddress the new street address
     */

    public void setStreetAddress(String streetAddress) throws SQLException {
        this.streetAddress = streetAddress;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the city in which the cutomer lives.
     *
     * @return city
     */

    public String getCity() {
        return city;
    }

    /**
     * Sets the city in which the customer lives.
     *
     * @param city the new city
     */

    public void setCity(String city) throws SQLException {
        this.city = city;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the state in which the customer lives.
     *
     * @return state
     */

    public String getState() {
        return state;
    }

    /**
     * Sets the state in which the customer lives.
     *
     * @param state the new state
     */

    public void setState(String state) throws SQLException {
        this.state = state;
        CustomerDAO.updateCustomer(this);
    }

    /**
     * Returns the postal code in which the customer lives.
     *
     * @return postalCode
     */

    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code in which the customer lives.
     *
     * @param postalCode the new postalCode
     */

    public void setPostalCode(String postalCode) throws SQLException {
        this.postalCode = postalCode;
        CustomerDAO.updateCustomer(this);
    }

    public void setCustomerId(long customerId) throws SQLException {
        this.customerId = customerId;
        CustomerDAO.updateCustomer(this);
    }
}