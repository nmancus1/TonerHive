package Tests;

import Objects.TonerBank;
import Objects.TonerBuilder;
import Objects.TonerFileWriter;

import java.io.IOException;

/**
 * <h1>TONER FILE WRITER TEST</h1>
 * This file tests the functionality of the TonerFileWriter, by passing it an
 * <p>
 * <b>Note:</b>
 *
 * @author
 * @since
 */
public class TonerFileWriterTest {

    public static void main(String[] args) {

        //Create tonerbank object
        TonerBank tonerBank = new TonerBank();


        //Build inventories, passing in spreadsheet files
        try {
            tonerBank = TonerBuilder.build("TonerInventory.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File read error!");
        }

        /**
         * Call TonerFileWriter's static write method to write all of the toner objects to
         * the XLSX file.  Let's just use a blank file to practice with (the implementation of
         * TonerFileWriter should create a new file if it doesn't exist), we will call it "tester.xlsx".
         */

        System.out.println("Write to XLSX file successful? : ");

        try {
            System.out.println(TonerFileWriter.write(tonerBank, "tester.xlsx"));
        } catch (IOException e) {
            System.out.println("false");
        }

    }
}
