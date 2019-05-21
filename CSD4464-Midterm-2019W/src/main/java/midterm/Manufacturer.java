package midterm;

/**
 * The Manufacturer Model Class
 *
 * @author <ENTER YOUR NAME HERE>
 */
public class Manufacturer {

    private int manufacturerId;
    private String name;
    private String phoneNumber;
    private String repName;

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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     */
    public String getRepName() {
        return repName;
    }

    /**
     *
     * @param repName
     */
    public void setRepName(String repName) {
        this.repName = repName;
    }

}
