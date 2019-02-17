package Objects; /**
 * <h1>TONER FILE WRITER</h1>
 *  This object writes toner objects to a XLSX file.
 * <p>
 * <b>Note:</b> Need to tidy up for Java docs
 *
 * @author Nick Mancuso
 * @version 0.1
 * @since 11/18/18
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TonerFileWriter {

    public static boolean write(TonerBank tonerBank, String XLSXFilename) throws IOException {


        String[] columns = {"Printer Model", "Toner Color", "Brand", "Toner Model", "Minimum Stock", "Current Stock", "isOrdered?", "Typical Amount For Reorder"};

        ArrayList<Toner> tonerList = TonerBank.getTonerList(); // get the ArrayList from TonerBank

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("XLSXFilename");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells
        int rowNum = 1;

        //Initialize some variables for creating compatible printer string here

        List<String> compatiblePrinters;

        for (Toner tone : tonerList) {
            Row row = sheet.createRow(rowNum++);

            //Logic here for writing compatible printers, need to loop through elements
            /*************************************************************************/
            String compatiblePrinterString = "";
            int i;

            //Initialize list
            compatiblePrinters = tone.getCompatiblePrinters();

            //Loop through all but last element, writing a "/" in front of each
            for (i = 0; i < compatiblePrinters.size() - 1; i++) {
                compatiblePrinterString += compatiblePrinters.get(i) + "/";
            }

            //Write last element with no trailing "/"
            compatiblePrinterString += compatiblePrinters.get(i);
            /*************************************************************************/


            row.createCell(0).setCellValue(compatiblePrinterString);
            row.createCell(1).setCellValue(tone.getColor());
            row.createCell(2).setCellValue(tone.getBrand());
            row.createCell(3).setCellValue(tone.getModel());
            row.createCell(4).setCellValue(tone.getMinimumStockAmount());
            row.createCell(5).setCellValue(tone.getCurrentStock());
            row.createCell(6).setCellValue(String.valueOf(tone.getIsOrdered()));
            row.createCell(7).setCellValue(tone.getAverageAmountOrdered());

        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(XLSXFilename); // name of the output file. Right now its Toners because I dont want to overwrite the original file until I fix the printer models
        workbook.write(fileOut);
        fileOut.close();
        return true;
    }


}