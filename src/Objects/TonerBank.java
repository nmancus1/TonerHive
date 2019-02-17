package Objects;

import Interfaces.ArrayListInventoryInterface;

import java.util.ArrayList;

public class TonerBank implements ArrayListInventoryInterface {

    private static ArrayList<Toner> tonerArrayList = new ArrayList<>();

    public static ArrayList<Toner> getTonerList() {
        return tonerArrayList;
    }

    public boolean remove(Toner toner) {
        return tonerArrayList.remove(toner);
    }

    public boolean add(Object obj) {
        return tonerArrayList.add((Toner) obj);
    }

    public boolean remove(Object obj) {
        return false;
    }

    public Integer getValue() {
        return null;
    }

    public Integer getSize() {
        return tonerArrayList.size();
    }

    /***
     * This method returns a list of all toners for a printer!
     * @param printer the printer to find
     * @return
     */
    public ArrayList <Toner> getCompatibleTonerArrayList(Printer printer) {

        ArrayList compatibleToners = new ArrayList();

        for (Toner t : tonerArrayList) {

            if (t.getCompatiblePrinters().contains(printer.getDescription())) {
                compatibleToners.add(t);
            }
        }

        return compatibleToners;
    }

    /**
     * Returns single toner object
     * @param model
     * @return
     */
    public Toner getToner(String model) {

        for(Toner t: tonerArrayList) {
            if (t.getModel().equals(model)) {
                return t;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "TonerBank{" +
                "tonerArrayList=" + tonerArrayList +
                '}';
    }
}
