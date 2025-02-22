package api.queryHelper;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import utility.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBQueryHelper {

    /**
     * Retrieves the latest natid from the WORKING_CLASS_HEROES table.
     * @return the latest natid, or null if not found.
     *  Retrieves a record from the WORKING_CLASS_HEROES table by natid.
     *      @param natId The natid to search for.
     *      @return The natid if found, otherwise null.
     */
    public static String retrieveDBRecordFromNatid(String natId) {
        String query = "select natid from working_class_heroes where natid ='" + natId+"'";
        String natid = null;

        try (Connection conn = DBUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                natid = rs.getString("natid");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving natid from the database.");
            e.printStackTrace();
        }

        return natid;
    }

    /**
     * Retrieves a record from the WORKING_CLASS_HEROES table by Voucher Name.
     * @param voucherName The voucher name to search for.
     * @return The natid if found, otherwise null.
     */
    public static String retrieveDBRecordFromVoucherName(String voucherName) {
        String query = " select * from vouchers where name ='" + voucherName+"'";
        String natid = null;

        try (Connection conn = DBUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                voucherName = rs.getString("name");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving natid from the database.");
            e.printStackTrace();
        }

        return voucherName;
    }

    /**
     * Delete record from WORKING_CLASS_HEROES table by natid.
     * @param natId The natid to search for.
     */

    public static void deleteRecordFromWorkingClassHeroes(String natId) {
        String query = "delete from working_class_heroes where natid ='" + natId + "'";
        try (Connection conn = DBUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting record from the database.");
            e.printStackTrace();
        }
    }


    /**
     * Verify record is deleted from WORKING_CLASS_HEROES table by natid.
     * @param natId The natid to search for.
     */

    public static boolean verifyRecordDeletedFromWorkingClassHeroes(String natId) {
        String query = "select * from working_class_heroes where natid ='" + natId + "'";
        boolean isDeleted = false;
        try (Connection conn = DBUtility.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (!rs.next()) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.err.println("Error verifying record deletion from the database.");
            e.printStackTrace();
        }
        return isDeleted;
    }

    /**
     * Verify record is not created as request failed
     */

public static boolean verifyRecordNotCreated(String natId) {
    String query = "select natid from working_class_heroes where natid ='" + natId + "'";
    boolean isCreated = false;
    try (Connection conn = DBUtility.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
            isCreated = true;
        }
    } catch (SQLException e) {
        System.err.println("Error verifying record deletion from the database.");
        e.printStackTrace();
    }
    return isCreated;
}





}
