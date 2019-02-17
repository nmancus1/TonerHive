package Objects; /**
 * <h1>PrinterBuilder Object</h1>
 * This PrinterBuilder class has a single static method for building PrinterBanks
 * <p>
 * <b>Note:</b>
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/14/18
 */
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class PrinterBuilder {


    /**
     * This method parses a spreadsheet and creates printer objects from it, and
     * loads them into a PrinterBank
     * @param XLSXFilename name + path of spreadsheet
     * @return inventory of printers
     * @throws IOException if file not found
     */
    public static PrinterBank build(String XLSXFilename) throws IOException {

        /**
         * Instantiate and initialize FIS and XLSX file, and set up
         * read from file. More info here:
         * http://www.java67.com/2014/09/how-to-read-write-xlsx-file-in-java-apache-poi-example.html
         */
        File excelFile = new File(XLSXFilename);
        FileInputStream fis = new FileInputStream(excelFile);

        // Create workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate on rows
        Iterator<Row> rowIt = sheet.iterator();

        //New printerbank to return
        PrinterBank printerBank = new PrinterBank();

        //Skip first row, because headers
        Row row = rowIt.next();

        //Keep iterating through rows until there's no data left
        while (rowIt.hasNext()) {
            row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            //Make new String[] for each row of toner info
            String[] printerInfoStringArr = new String[10];

            //Index to keep track of cells
            int index = 0;

            //Loop through the row, reading each cell into tonerInfoStringArray
            while (cellIterator.hasNext()) {

                //Get next cell
                Cell cell = cellIterator.next();

                //Extract data and assign to correct index of printerInfoStringArr
                printerInfoStringArr[index] = cell.toString();
                index++;        //increment index

            }

            //Create new printer
            Printer printer = new Printer(
                    (int) Float.parseFloat(printerInfoStringArr[0]),      //barcode
                    printerInfoStringArr[1],                        //description
                    printerInfoStringArr[2],                        //category name
                    printerInfoStringArr[3],                        //location name
                    printerInfoStringArr[4],                        //serial number
                    printerInfoStringArr[5],                        //manufacturer name
                    printerInfoStringArr[6],                        //division
                    printerInfoStringArr[7],                        //department
                    printerInfoStringArr[8]                         //status
            );

            //Add new printer to printerbank
            printerBank.add(printer);

        }

        //Close workbook and fis
        workbook.close();
        fis.close();

        //fin
        return printerBank;
    }

}
