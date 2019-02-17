package Objects;
/**
 * <h1>Admin Object</h1>
 * Admin object represents the administrative user of a program.
 * <p>
 * <b>Note:</b> There is currently no encryption for the password field
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
import Interfaces.AdminInterface;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements AdminInterface, Serializable {

    //Data fields
    private String name;
    private String email;
    private String password;
    private Integer idNumber;
    private static final long serialVersionUID = 652965443;

    /**
     * Default constructor
     */
    public Admin() {
    }

    public Admin(String name) {
        this.name = name;
    }

    /**
     * Constructs a new admin with all data fields
     * @param name name of admin
     * @param email email address of admin
     * @param password plain-text password of admin
     * @param idNumber id number of admin
     */
    public Admin(String name, String email, String password, Integer idNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idNumber = idNumber;
    }

    //Accessors and mutators

    /**
     *This method returns the name of an admin object
     * @return admin name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets an admins name, mainly used for testing
     * @param name admin name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the email address of an admin object
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the email address of an admin object, mainly used for testing
     * @param email set email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method returns the password field of an admin object
     * @return plain-text password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the password field of an admin object
     * @param password sets plain text password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns the ID number of an admin object
     * @return id number of admin
     */
    public Integer getIdNumber() {
        return idNumber;
    }

    /**
     * This method sets the ID number of an admin object, mainly used for testing
     * @param idNumber set new ID number for admin
     */
    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(name, admin.name);
    }
}

