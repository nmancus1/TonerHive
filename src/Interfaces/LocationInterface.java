package Interfaces;

import Objects.PrinterBank;
import Objects.TonerBank;

/**
 * <h1>Location Interface<h1>
 * An interface that describes the operations of the Campus objects class
 *<p>
 * <b>Note:</b> NOTES HERE
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
public interface LocationInterface {

    //Public and abstract
    /**
     * This method returns the campus name
     * @return location name
     */
    String getLocationName();
    /**
     * This method returns the toner inventory
     * @return TonerBank
     */
    TonerBank getTonerBank();
    /**
     * This method returns the printer inventory
     * @return PrinterBank
     */
    PrinterBank getPrinterBank();
    /**
     * This method returns the number of printer on the campus
     * @return number of printers
     */
    Integer getNumberOfPrinters();


}
