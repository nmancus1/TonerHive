package Tests;

import Objects.*;

import java.io.IOException;

public class CampusTest {

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

        Campus campus = new Campus("Wilmington", tonerBank, printerBank);


        //Print campus info
        System.out.println(campus);
    }
}
