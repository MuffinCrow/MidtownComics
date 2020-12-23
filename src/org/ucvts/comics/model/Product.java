package org.ucvts.comics.model;

import org.ucvts.comics.dao.ProductDAO;

import java.sql.SQLException;

public class Product {

    private static long lastProductId = 1L; // initial product ID

    private long productId;
    private String title;
    private String author;
    private long releaseDate;
    private int issue;
    private double unitPrice;
    private int copies;

    /**
     * Creates a default instance of the Product class.
     */

    public Product() throws SQLException {
        this.productId = Product.lastProductId++; // auto-generate ID
        ProductDAO.insertProduct(this);
    }

    /**
     * Creates an instance of the Product class.
     *
     * @param productId   the product ID
     * @param title       the title
     * @param author      the creator or artist
     * @param releaseDate the date it was released
     * @param issue       the issue number
     * @param unitPrice   the price
     * @param copies      the number of remaining copies
     */

    public Product(long productId, String title, String author, long releaseDate, int issue, double unitPrice,
                   int copies) throws SQLException {
        this.productId = productId;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.issue = issue;
        this.unitPrice = unitPrice;
        this.copies = copies;
        ProductDAO.insertProduct(this);
    }

    /**
     * Creates an instance of the Product class.
     *
     * @param title       the title
     * @param author      the creator or artist
     * @param releaseDate the date it was released
     * @param issue       the issue number
     * @param unitPrice   the price
     * @param copies      the number of remaining copies
     */

    public Product(String title, String author, long releaseDate, int issue, double unitPrice, int copies) throws SQLException {
        this.productId = Product.lastProductId++; // auto-generate ID
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.issue = issue;
        this.unitPrice = unitPrice;
        this.copies = copies;
        ProductDAO.insertProduct(this);
    }

    /**
     * Returns the product ID.
     *
     * @return productId
     */

    public long getProductId() {
        return productId;
    }

    /**
     * Returns the title of the comic book.
     *
     * @return title
     */

    public String getTitle() {
        return title;
    }

    /**
     * Returns the author.
     *
     * @return author
     */

    public String getAuthor() {
        return author;
    }

    /**
     * Returns the date this issue was released.
     *
     * @return releaseDate
     */

    public long getReleaseDate() {
        return releaseDate;
    }

    /**
     * Returns the issue number.
     *
     * @return issue
     */

    public int getIssue() {
        return issue;
    }

    /**
     * Returns the price of one copy.
     *
     * @return unitPrice
     */

    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the price of one copy.
     *
     * @param unitPrice the new price
     */

    public void setUnitPrice(double unitPrice) throws SQLException {
        this.unitPrice = unitPrice;
        ProductDAO.updateProduct(this);
    }

    /**
     * Returns the number of copies available.
     *
     * @return copies
     */

    public int getCopies() {
        return copies;
    }

    /**
     * Sets the number of copies available.
     *
     * @param copies the new number of copies
     */

    public void setCopies(int copies) throws SQLException {
        this.copies = copies;
        ProductDAO.updateProduct(this);
    }

    public void setProductId(long productId) throws SQLException {
        this.productId = productId;
        ProductDAO.updateProduct(this);
    }

    public void setTitle(String title) throws SQLException {
        this.title = title;
        ProductDAO.updateProduct(this);
    }

    public void setAuthor(String author) throws SQLException {
        this.author = author;
        ProductDAO.updateProduct(this);
    }

    public void setReleaseDate(long releaseDate) throws SQLException {
        this.releaseDate = releaseDate;
        ProductDAO.updateProduct(this);
    }

    public void setIssue(int issue) throws SQLException {
        this.issue = issue;
        ProductDAO.updateProduct(this);
    }
}