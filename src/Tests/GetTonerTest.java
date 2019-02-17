package Tests;

import Objects.PrinterBank;
import Objects.PrinterBuilder;
import Objects.TonerBank;
import Objects.TonerBuilder;

import java.io.IOException;

public class GetTonerTest {
    public static void main (String[] args) {

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


        System.out.println(tonerBank.getToner("5130A"));
    }




}
