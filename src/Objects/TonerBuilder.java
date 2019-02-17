package Objects; /**
 * <h1>TITLE HERE</h1>
 * DESCRIPTION HERE
 * <p>
 * <b>Note:</b> NOTES HERE
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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TonerBuilder {


    public static TonerBank build(String XLSXFilename) throws IOException {

        /**
         * Instantiate and initialize FIS and XLSX file, and set up
         * read from file. More info here:
         * http://www.java67.com/2014/09/how-to-read-write-xlsx-file-in-java-apache-poi-example.html
         */
        File excelFile = new File(XLSXFilename);
        FileInputStream fis = new FileInputStream(excelFile);

        //Create an XSSF Workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //Get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate on rows
        Iterator<Row> rowIt = sheet.iterator();

        //New tonerbank to return
        TonerBank tonerBank = new TonerBank();

        //Skip first row, because headers
        Row row = rowIt.next();


        //Keep iterating through rows until there's no data left
        while (rowIt.hasNext()) {
            row = rowIt.next();

            // iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            //Make new String[] for each row of toner info
            String[] tonerInfoStringArr = new String[8];

            //Index to keep track of cells
            int index = 0;

            //Loop through the row, reading each cell into tonerInfoStringArray
            while (cellIterator.hasNext()) {

                //Get next cell
                Cell cell = cellIterator.next();

                //Extract data and assign to correct index of tonerInfoStringArray
                tonerInfoStringArr[index] = cell.toString();
                index++;        //increment index

            }

            //Make list for compatible printers, used in toner constructor
            List<String> compatiblePrinters = Arrays.asList(tonerInfoStringArr[0].split("/"));

            //Create new toner
            Toner toner = new Toner(
                    compatiblePrinters,                         //list of compatible models
                    tonerInfoStringArr[1],                      //color type
                    tonerInfoStringArr[2],                      //brand
                    tonerInfoStringArr[3],                      //toner model
                    (int) Float.parseFloat(tonerInfoStringArr[4]),    //minimum stock amount
                    (int) Float.parseFloat(tonerInfoStringArr[5]),    //current stock amount
                    tonerInfoStringArr[6].charAt(0),            //has it been reordered?
                    (int) Float.parseFloat(tonerInfoStringArr[7])     //average amount ordered
            );

            //Add to tonerBank
            tonerBank.add(toner);

        }

        //Close workbook and fis
        workbook.close();
        fis.close();

        //fin
        return tonerBank;
    }

}