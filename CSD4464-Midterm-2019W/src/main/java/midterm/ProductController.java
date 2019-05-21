package midterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * The Product Controller Class
 *
 * @author <ENTER YOUR NAME HERE>
 */
@Named
@ApplicationScoped
public class ProductController {

    /**
     *
     */
    private List<Product> products = new ArrayList<>();

    /**
     *
     * @param products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    /**
     *
     */
    private Product thisProduct = new Product();

    /**
     *
     */
    public ProductController() {
        getProductFromDB();
    }

    /**
     *
     * @return
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Retrieve the Product Model used in Forms
     *
     * @return the Product Model used in Forms
     */
    public Product getThisProduct() {
        return thisProduct;
    }

    /**
     *
     * @param thisProduct
     */
    public void setThisProduct(Product thisProduct) {
        this.thisProduct = thisProduct;
    }

    /**
     * Basic Constructor for Products - Retrieves from DB
     */
    private void getProductFromDB() {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            products.clear();
            while (rs.next()) {
                Product p = new Product();

                p.setProductId(rs.getInt("Product_Id"));
                p.setManufacturerId(rs.getInt("Manufacturer_Id"));
                p.setDescription(rs.getString("Description"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieve the full list of Products
     *
     * @return the List of Products
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Product (Product_Id, Manufacturer_Id, Description, Product_Code) VALUES (?, ?, ?, 'SW')";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thisProduct.getProductId());
            pstmt.setInt(2, thisProduct.getManufacturerId());
            pstmt.setString(3, thisProduct.getDescription());
            pstmt.executeUpdate();
            getProductFromDB();
            thisProduct = new Product();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Product getByID(int id) {
        for (Product p : products) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public String editByID(int id) {
        thisProduct = getByID(id);
        getProductFromDB();
        return "addProduct";
    }

    /**
     *
     * @param id
     * @return
     */
    public String removeByID(int id) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "DELETE FROM Product WHERE Product_Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            getProductFromDB();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
