package Objects;

import java.util.ArrayList;

/**
 * <h1>Campus Context</h1>
 * Campus context provides toner and printer information across JavaFX scenes
 * <p>
 * <b>Note:</b> Needs further testing
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/19/18
 */

public class CampusContext {

    /**
     * Static variable single_instance of type CampusContext
     */
    private static CampusContext single_instance = null;

    /**
     * In case we need to return this instance of CampusContext
     */
    private static CampusContext ourInstance = new CampusContext();

    //Data Fields
    public TonerBank tonerBank;
    public PrinterBank printerBank;
    public Campus campus;
    public ArrayList compatibleTonerList;

    /**
     * This method returns this instance of CampusContext
     */
    public static CampusContext getInstance() {
        return ourInstance;
    }

    /**
     * Static constructor to ensure that we only have a single instance of this class at a time
     */
    public static CampusContext CampusContext() {
        if (single_instance == null) {
            single_instance = new CampusContext();
        }

        return single_instance;
    }

    public ArrayList getCompatibleTonerList() {
        return compatibleTonerList;
    }

    public void setCompatibleTonerList(ArrayList compatibleTonerList) {
        this.compatibleTonerList = compatibleTonerList;
    }

    public TonerBank getTonerBank() {
        return tonerBank;
    }

    public void setTonerBank(TonerBank tonerBank) {
        this.tonerBank = tonerBank;
    }

    public PrinterBank getPrinterBank() {
        return printerBank;
    }

    public void setPrinterBank(PrinterBank printerBank) {
        this.printerBank = printerBank;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}
