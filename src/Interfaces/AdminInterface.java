package Interfaces; /**
 * <h1>AdminInterface<h1>
 * An interface that describes the operations of the Admin objects class
 * <p></p>
 * <b>Note:</b> NOTES HERE
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
import java.io.Serializable;

public interface AdminInterface extends Serializable {
    /**
     * This method returns the name of an admin object
     *
     * @return admin name
     */
    String getName();

    /**
     * This method sets an admins name, mainly used for testing
     * @param name admin name
     */
    void setName(String name);

    /**
     * This method returns the email address of an admin object
     * @return email address
     */
    String getEmail();

    /**
     * This method sets the email address of an admin object, mainly used for testing
     * @param email set email address
     */
    void setEmail(String email);

    /**
     * This method returns the password field of an admin object
     * @return plain-text password
     */
    String getPassword();

    /**
     * This method sets the password field of an admin object
     * @param password sets plain text password
     */
    void setPassword(String password);

    /**
     * This method returns the ID number of an admin object
     * @return id number of admin
     */
    Integer getIdNumber();

    /**
     * This method sets the ID number of an admin object, mainly used for testing
     * @param idNumber set new ID number for admin
     */
    void setIdNumber(Integer idNumber);
}
