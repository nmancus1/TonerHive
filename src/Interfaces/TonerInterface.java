package Interfaces;
/**
 * <h1>TonerInterface<h1>
 * This interface defines the methods for Toner objects
 * <p>
 * <b>Note:</b> NOTES HERE
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
import java.util.List;

public interface TonerInterface {

    /**
     * This method returns a list of compatible printers with
     * this model toner
     * @return String List
     */
    List<String> getCompatiblePrinters();
    /**
     * This method allows a new list of compatible printers to be set
     * for this toner
     * @param compatiblePrinters new list of printers
     */
    void setCompatiblePrinters(List<String> compatiblePrinters);
    /**
     * This method deducts one toner of one type from inventory,
     * and checks if the low toner threshold has been reached.
     * If it has, a toneralert is created.
     * @return true if minimum stock is reached
     */
    boolean deductOne();
    /**
     * This method returns the brand name of this toner
     * @return String brand
     */
    String getBrand();
    /**
     * This method sets the brand String for this toner
     * @param brand String containing manufacturer name
     */
    void setBrand(String brand);
    /**
     * This method returns the model number of this toner
     * @return String model
     */
    String getModel();
    /**
     * This method sets the model number of this toner
     * @param model String containing model name
     */
    void setModel(String model);
    /**
     * This method returns the color of the ink in this toner
     * @return String containing color name
     */
    String getColor();
    /**
     * Se the color of this toner cartridge
     * @param color String containing color type
     */
    void setColor(String color);
    /**
     * This method returns the integer representation of the low
     * stock threshold for this toner
     * @return Integer minimum stock
     */
    Integer getMinimumStockAmount();
    /**
     * This method sets the low stock threshold for this toner
     * @param minimumStockAmount Integer for low stock threshold
     */
    void setMinimumStockAmount(Integer minimumStockAmount);
    /**
     * This method returns the current amount of this toner in stock
     * @return Integer current stock
     */
    Integer getCurrentStock();
    /**
     * This method sets the current stock of this toner
     * @param currentStock Integer current stock
     */
    void setCurrentStock(Integer currentStock);
    /**
     * This method returns the flag regarding whether this toner
     * has been reordered yet or not
     * @return char 'y' if reordered, 'n' if not
     * */
    char getIsOrdered();
    /**
     * This method sets the reordered flag for this toner
     * @param isOrdered char 'y' for yes, 'n' for no
     */
    void setIsOrdered(char isOrdered);
    /**
     * This method returns the average amount of this toner that is ordered when low.
     * @return Integer average amount ordered
     */
    Integer getAverageAmountOrdered();
    /**
     * This method sets the average amount ordered
     * @param averageAmountOrdered Integer average amount ordered
     */
    void setAverageAmountOrdered(Integer averageAmountOrdered);

    //toString
    //TODO: need to improve this toString
    @Override
    String toString();
}
