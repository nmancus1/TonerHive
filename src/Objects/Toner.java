package Objects; /**
 * <h1>TONER OBJECT</h1>
 * This toner object contains all the data fields in the XLSX file, and implements the tonerinterface
 *
 * <p>
 * <b>Note:</b> deductOne will send a toneralert to admins in the admin file
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */

import Interfaces.TonerInterface;

import java.util.List;

public class Toner implements TonerInterface {

    //Data fields
    private List<String> compatiblePrinters;
    private String brand;
    private String model;
    private String color;
    private Integer minimumStockAmount;
    private Integer currentStock;
    private char isOrdered;
    private Integer averageAmountOrdered;

    /**
     * Default constructor
     */
    public Toner() {
    }


    /**
     * Constructor to be used with all data fields from XLSX file
     *
     * @param compatiblePrinters   List of Strings containing model numbers of compatible printers
     * @param color                color of ink in toner
     * @param brand                manufacturer of toner
     * @param model                model number of toner
     * @param minimumStockAmount   low stock threshold
     * @param currentStock         amount currently in stock
     * @param isOrdered            has it been reordered?
     * @param averageAmountOrdered how much is typically ordered?
     */
    public Toner(List<String> compatiblePrinters, String color,
                 String brand, String model, Integer minimumStockAmount,
                 Integer currentStock, char isOrdered, Integer averageAmountOrdered) {
        this.compatiblePrinters = compatiblePrinters;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.minimumStockAmount = minimumStockAmount;
        this.currentStock = currentStock;
        this.isOrdered = isOrdered;
        this.averageAmountOrdered = averageAmountOrdered;
    }


    /**
     * This method returns a list of compatible printers with this model toner
     *
     * @return String List
     */
    public List<String> getCompatiblePrinters() {
        return compatiblePrinters;
    }

    /**
     * This method allows a new list of compatible printers to be set for this toner
     * @param compatiblePrinters new list of printers
     */
    public void setCompatiblePrinters(List<String> compatiblePrinters) {
        this.compatiblePrinters = compatiblePrinters;
    }


    /**
     * This method deducts one toner of one type from inventory, and checks if the low toner threshold has been
     * reached.  If it has, a toneralert is created.
     * @return true if minimum stock is reached
     */
    public boolean deductOne() {

        //Deduct one of this toner
        this.currentStock--;

        //Check low threshold level
        if ((this.currentStock <= this.minimumStockAmount) && (Character.toUpperCase(this.isOrdered) == 'N')) {

            //If this toner is low, create a new toner alert
            TonerAlert tonerAlert = new TonerAlert(this);

            //Send alert email
            tonerAlert.sendEmail();

            this.isOrdered = 'y';

        }

        //If there's none of these in stock
        return this.currentStock != 0;
    }

    /**
     * This method returns the brand name of this toner
     * @return String brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method sets the brand String for this toner
     * @param brand String containing manufacturer name
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This method returns the model number of this toner
     * @return String model
     */
    public String getModel() {
        return model;
    }

    /**
     * This method sets the model number of this toner
     * @param model String containing model name
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method returns the color of the ink in this toner
     * @return String containing color name
     */
    public String getColor() {
        return color;
    }

    /**
     * Se the color of this toner cartridge
     * @param color String containing color type
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method returns the integer representation of the low stock threshold for this toner
     * @return Integer minimum stock
     */
    public Integer getMinimumStockAmount() {
        return minimumStockAmount;
    }

    /**
     * This method sets the low stock threshold for this toner
     * @param minimumStockAmount Integer for low stock threshold
     */
    public void setMinimumStockAmount(Integer minimumStockAmount) {
        this.minimumStockAmount = minimumStockAmount;
    }

    /**
     * This method returns the current amount of this toner in stock
     * @return Integer current stock
     */
    public Integer getCurrentStock() {
        return currentStock;
    }

    /**
     * This method sets the current stock of this toner
     * @param currentStock Integer current stock
     */
    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    /**
     * This method returns the flag regarding whether this toner has been reordered yet or not
     * @return char 'y' if reordered, 'n' if not
     */
    public char getIsOrdered() {
        return isOrdered;
    }

    /**
     * This method sets the reordered flag for this toner
     * @param isOrdered char 'y' for yes, 'n' for no
     */
    public void setIsOrdered(char isOrdered) {
        this.isOrdered = isOrdered;
    }

    /**
     * This method returns the average amount of this toner that is ordered when low.
     * @return Integer average amount ordered
     */
    public Integer getAverageAmountOrdered() {
        return averageAmountOrdered;
    }

    /**
     * This method sets the average amount ordered
     * @param averageAmountOrdered Integer average amount ordered
     */
    public void setAverageAmountOrdered(Integer averageAmountOrdered) {
        this.averageAmountOrdered = averageAmountOrdered;
    }

    /**
     * Generic toString
     * @return String of information about this toner
     */
    @Override
    public String toString() {
        return "Toner{" +
                "compatiblePrinters=" + compatiblePrinters +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", minimumStockAmount=" + minimumStockAmount +
                ", currentStock=" + currentStock +
                ", isOrdered=" + isOrdered +
                ", averageAmountOrdered=" + averageAmountOrdered +
                "}\n";
    }
}
