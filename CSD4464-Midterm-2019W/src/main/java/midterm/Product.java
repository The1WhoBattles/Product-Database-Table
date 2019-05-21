package midterm;

/**
 * The Product Model
 *
 * @author <ENTER YOUR NAME HERE>
 */
public class Product {

    private int productId;
    private int manufacturerId;
    private String description;
    private String productCode;

    /**
     *
     * @return
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     *
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     *
     * @return
     */
    public int getProductId() {
        return productId;
    }

    /**
     *
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     *
     * @return
     */
    public int getManufacturerId() {
        return manufacturerId;
    }

    /**
     *
     * @param manufacturerId
     */
    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param string
     */
    void setName(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
