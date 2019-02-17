package Objects;

import Interfaces.LocationInterface;

/**
 * <h1>Campus Object</h1>
 * Campus object is a data structure that represents a campus or certain location.
 * <p>
 * <b>Note:</b> Each campus can only have one each of a TonerBank(their toner inventory), PrinterBank (printer inventory)
 * and a name
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */

public class Campus implements LocationInterface {

    //Data fields
    private String locationName;
    private TonerBank tonerBank;
    private PrinterBank printerBank;

    /**
     * Default constructor
     */
    public Campus() {
    }

    public Campus(String name) {
        this.locationName = name;
    }

    /**
     * Constructs a new campus object with all data fields
     * @param locationName name of campus
     * @param tonerBank toner inventory
     * @param printerBank printer inventory
     */
    public Campus(String locationName, TonerBank tonerBank, PrinterBank printerBank) {
        this.locationName = locationName;
        this.tonerBank = tonerBank;
        this.printerBank = printerBank;
    }

    /**
     * This method returns the campus name
     * @return location name
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * This method returns the toner inventory
     * @return TonerBank
     */
    public TonerBank getTonerBank() {
        return tonerBank;
    }

    /**
     * This method returns the printer inventory
     * @return PrinterBank
     */
    public PrinterBank getPrinterBank() {
        return printerBank;
    }

    /**
     * This method returns the number of printer on the campus
     * @return number of printers
     */
    public Integer getNumberOfPrinters() {
        return printerBank.getSize();
    }

    /**
     * This method returns the entire inventory of printers, toner, and the campus name
     * @return string representation of campus
     */
    @Override
    public String toString() {
        return "Campus{" +
                "locationName='" + locationName + '\'' +
                ", tonerBank=" + tonerBank +
                ", printerBank=" + printerBank +
                '}';
    }
}
