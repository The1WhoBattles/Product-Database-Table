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
 * The Manufacturer Controller Class
 *
 * @author <ENTER YOUR NAME HERE>
 */
@Named
@ApplicationScoped
public class ManufacturerController {

    /**
     *
     */
    private List<Manufacturer> manufacturers = new ArrayList<>();
    /**
     *
     */
    private Manufacturer thisManufacturer = new Manufacturer();

    /**
     *
     * @return
     */
    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    /**
     *
     * @param manufacturers
     */
    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    /**
     *
     * @return
     */
    public Manufacturer getThisManufacturer() {
        return thisManufacturer;
    }

    /**
     *
     * @param thisManufacturer
     */
    public void setThisManufacturer(Manufacturer thisManufacturer) {
        this.thisManufacturer = thisManufacturer;
    }

    /**
     *
     */
    public ManufacturerController() {
        getManufacturerFromDB();
    }

    /**
     *
     */
    private void getManufacturerFromDB() {
        try {
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Manufacturer");
            manufacturers.clear();
            while (rs.next()) {
                Manufacturer m = new Manufacturer();
                m.setManufacturerId(rs.getInt("Manufacturer_Id"));
                m.setName(rs.getString("Name"));
                m.setRepName(rs.getString("Rep"));
                m.setPhoneNumber(rs.getString("Phone"));
                manufacturers.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Manufacturer (Manufacturer_Id, Name, Rep, Phone) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, thisManufacturer.getManufacturerId());
            pstmt.setString(2, thisManufacturer.getName());
            pstmt.setString(3, thisManufacturer.getRepName());
            pstmt.setString(4, thisManufacturer.getPhoneNumber());
            pstmt.executeUpdate();
            getManufacturerFromDB();
            thisManufacturer = new Manufacturer();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     *
     * @param id
     * @return
     */
    public Manufacturer getByID(int id) {
        for (Manufacturer m : manufacturers) {
            if (m.getManufacturerId() == id) {
                return m;
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
        thisManufacturer = getByID(id);
        return "addManufacturer";
    }

    /**
     *
     * @param id
     * @return
     */
    public String removeByID(int id) {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "DELETE FROM Manufacturer WHERE Manufacturer_Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            getManufacturerFromDB();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
