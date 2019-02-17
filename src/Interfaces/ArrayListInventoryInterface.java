package Interfaces;

/**
 * <h1>ArrayListInventoryInterface<h1>
 * An interface that describes the operations of a PrinterBank objects class
 * <p>
 * <b>Note: The implementation of getValue method is basically the same
 *          as getPrinter from the PrinterBank class.
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
public interface ArrayListInventoryInterface {

    //Public and abstract
    /**
     * This method adds and item to the printerbank
     * @param obj printer
     * @return true if add is successful
     */
    boolean add(Object obj);         //add item to inventory
    /**
     * This method removes an item from the printerbank
     * @param obj printer
     * @return true if successful
     */
    boolean remove(Object obj);      //remove item from inventory
    /**
     * This method retrieves from the data structure
     * that holds the printer objects the value that correspond to given
     * search key.
     * @return Either the value that is associated with the search key
     *  or null if no such object exist.
     */
    Integer getValue();
    /**
     * This method returns the number of entries in the printer array list,
     * which reflects the number of printers on a campus
     * @return integer representing the number of printers
     */

    Integer getSize();     //obtain total number of items in arraylist


}
