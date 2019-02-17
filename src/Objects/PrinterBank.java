package Objects; /**
 * <h1>PrinterBank Data Structure</h1>
 * This data structure contains printer objects nd allows printers to be added and removed,
 * and also facilitates searching for a specific printer.
 * <p>
 * <b>Note:</b> none
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */

import Interfaces.ArrayListInventoryInterface;

import java.util.ArrayList;

public class PrinterBank implements ArrayListInventoryInterface {

    //This data structure holds the printer objects
    private ArrayList<Printer> printerArrayList = new ArrayList<>();

    /**
     * Default constructor
      */
    public PrinterBank() {
    }

    /**
     * This method adds and item to the printerbank
     * @param obj printer
     * @return true if add is successful
     */
    public boolean add(Object obj) {
        return printerArrayList.add((Printer) obj);
    }

    /**
     * This method removes an item from the printerbank
     * @param obj printer
     * @return true if successful
     */
    public boolean remove(Object obj) {
        return printerArrayList.remove(obj);
    }

    /**
     * This method is a mystery!
     * @return
     */
    public Integer getValue() {
        return null;
    }

    /**
     * This method returns the number of entries in the printer array list,
     * which reflects the number of printers on a campus
     * @return integer representing the number of printers
     */
    public Integer getSize() {
        return printerArrayList.size();
    }

    /**
     * This method finds and returns a specific printer
     * @param serialNumber the serial number of the printer (asset tag)
     * @return the printer, if found
     */
    public Printer getPrinter(String serialNumber) {
        for (Printer p : printerArrayList) {
            if (p.getSerialNumber().equals(serialNumber)) {
                return p;
            }
        }
        return null;
    }


    /**
     * This method creates a String of information about this printer
     * @return string containing printer information
     */
    //toString
    @Override
    public String toString() {
        return "PrinterBank{" +
                "printerArrayList=" + printerArrayList +
                '}';
    }
}
