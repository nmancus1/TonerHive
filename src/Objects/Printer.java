package Objects;

import Interfaces.PrinterInterface;

/**
 * <h1>Printer Object</h1>
 * Printer object represents a printer's properties.
 * <p>
 * <b>Note:</b>As of version 0.1, there is no error checking in the constructor
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */

public class Printer implements PrinterInterface {

    //Data Fields
    private Integer barCode;
    private String description;
    private String categoryName;
    private String locationName;
    private String serialNumber;
    private String manufacturerName;
    private String division;
    private String department;
    private String status;

    //Default constructor
    public Printer() {

    }

    //Constructor
    public Printer(Integer barCode, String description, String categoryName,
                   String locationName, String serialNumber, String manufacturerName,
                   String division, String department, String status) {


        //TODO: ADD ERROR CHECKING, YOU CAN USE SET METHODS BELOW, AND PUT THE ERROR CHECKING LOGIC IN THERE TO...
        //TODO:    KEEP THIS CONSTRUCTOR CLEANER

        this.barCode = barCode;
        this.description = description;
        this.categoryName = categoryName;
        this.locationName = locationName;
        this.serialNumber = serialNumber;
        this.manufacturerName = manufacturerName;
        this.division = division;
        this.department = department;
        this.status = status;
    }

    //Accessors and mutators
    public Integer getBarCode() {
        return barCode;
    }

    public void setBarCode(Integer barCode) {
        this.barCode = barCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //TODO: could improve this toString method
    @Override
    public String toString() {
        return "Printer{" +
                "barCode=" + barCode +
                ", description='" + description + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", division='" + division + '\'' +
                ", department='" + department + '\'' +
                ", status='" + status + '\'' +
                "}\n";
    }
}
