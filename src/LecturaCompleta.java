
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Martin_txs
 */
public class LecturaCompleta {

    public static void leer() {
        try {
            XSSFWorkbook workbook;
            try (FileInputStream file = new FileInputStream(new File("C:\\Users\\WIN 8\\Documents\\NetBeansProjects\\LeerExcel\\Origen.xlsx"))) {
                workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(3);
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    //For each row, iterate through each columns
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {

                        Cell cell = cellIterator.next();

                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t\t");
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t\t");
                                break;
                            case Cell.CELL_TYPE_STRING:
                                System.out.print(cell.getStringCellValue() + "\t\t");
                                break;
                        }
                    }
                    System.out.println("");
                }
            }
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(new File("C:\\Users\\WIN 8\\Documents\\NetBeansProjects\\LeerExcel\\Origen.xlsx"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LecturaCompleta.class.getName()).log(Level.SEVERE, null, ex);
            }
            workbook.write(out);
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(LecturaCompleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
