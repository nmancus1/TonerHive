package Tests; /**
 * This class tests the tonerbuilder and printerbuilder methods, as well as deducting a toner that
 * is low from the inventory, so that it sends an alert email to the admins in the admin file.
 *
 */

import Objects.*;

import java.io.IOException;

public class BuilderTest {

    public static void main(String[] args) {

        //Create builder objects
        TonerBank tonerBank = new TonerBank();
        PrinterBank printerBank = new PrinterBank();

        //Build inventories, passing in spreadsheet files
        try {
            tonerBank = TonerBuilder.build("TonerInventory.xlsx");
            printerBank = PrinterBuilder.build("PrinterInventory.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File read error!");
        }

        //Print inventories
        System.out.println(tonerBank);
        System.out.println(printerBank);

        //Test toner bank
       //System.out.println("Model not in bank: " + tonerBank.getCompatibleTonerArrayList("asdfasf"));
        //System.out.println("Model in bank: " + tonerBank.getCompatibleTonerArrayList("B2360"));

        //Retrieve toner from the bank
        //ArrayList<Toner> compatibleTonerlist = tonerBank.getCompatibleTonerArrayList("B2360");

        Toner foundToner = new Toner();

        //for(Toner t: compatibleTonerlist) {
         //   if(t.getModel().equals("REU321BL")) {
         //       foundToner = t;
         //       break;
          //  }
       // }
        //Deduct one, to set off toner alert
        foundToner.deductOne();

        //Print out some toner objects
        //System.out.println("Model in bank: " + tonerBank.getToner("5130CDN"));
        //System.out.println("Model in bank: " + tonerBank.getToner("B2375DNF"));
        //System.out.println("Model in bank: " + tonerBank.getToner("3115CN"));


    }
}
