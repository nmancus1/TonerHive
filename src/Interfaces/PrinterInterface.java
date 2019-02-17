package Interfaces;

/**
 * <h1>PrinterInterface<h1>
 * An interface that describes the operations of the Printer objects class
 * <p>
 * <b>Note:</b> NOTES HERE
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */

public interface PrinterInterface {
    //Accessors and mutators
    /**
     *This method returns the barcode of a printer object
     * @return printer barcode
     */
    Integer getBarCode();
    /**
     * This method replaces a barcode from a specified printer
     * object with the specified new element
     * @param barCode
     */
    void setBarCode(Integer barCode);
    /**
     *This method returns the description of a printer object
     * @return printer description
     */
    String getDescription();
    /**
     * This method replaces a description from a specified printer
     * object with the specified new element
     * @param description
     */
    void setDescription(String description);
    /**
     *This method returns the category name of a printer object
     * @return printer categoryName
     */
    String getCategoryName();
    /**
     * This method replaces the category name from a specified printer
     * object with the specified new element
     * @param  categoryName
     */
    void setCategoryName(String categoryName);
    /**
     *This method returns the location name of a printer object
     * @return printer locationName
     */
    String getLocationName();
    /**
     * This method replaces the location of a specified printer object
     * with the specified new element
     * @param  locationName
     */
    void setLocationName(String locationName);
    /**
     *This method returns the serial number of a printer object
     * @return printer serialNumber
     */
    String getSerialNumber();
    /**
     * This method replaces the serial number of a specified printer object
     * with the specified new element
     * @param  serialNumber
     */
    void setSerialNumber(String serialNumber);
    /**
     *This method returns the manufacture name of a printer object
     * @return printer manufacturerName
     */
    String getManufacturerName();
    /**
     * This method replaces the manufacturer name of a specified
     * printer object with the specified new element
     * @param  manufacturerName
     */
    void setManufacturerName(String manufacturerName);
    /**
     *This method returns the division of a printer object
     * @return printer division
     */
    String getDivision();
    /**
     * This method replaces the division of a specified printer object
     * with the specified new element
     * @param  division
     */
    void setDivision(String division);
    /**
     *This method returns the department of a printer object
     * @return printer department
     */
    String getDepartment();
    /**
     * This method replaces the department of a specified printer object
     * with the specified new element
     * @param  department
     */
    void setDepartment(String department);
    /**
     *This method returns the status of a printer object
     * @return printer status
     */
    String getStatus();
    /**
     * This method replaces the status of a specified printer object
     * with the specified new element
     * @param status
     */
    void setStatus(String status);

    //TODO: could improve this toString method
    @Override
    String toString();
}
