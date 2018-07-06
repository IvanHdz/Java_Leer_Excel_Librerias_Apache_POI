
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Martin_txs
 */
public class LeerExcel {

    private final ArrayList<Equipos> equipos;
    // Se guardan los numeros de las filas donde esta la palabra "Planes" .
    // Donde se encontrarán los nombres de equipos.
    private final ArrayList<Integer> filaPlanes;
    private TreeMap<String, Double> dictionary;
    private int cantidadFilasRecorridas;

    public LeerExcel() {
        this.equipos = new ArrayList<>();
        this.filaPlanes = new ArrayList<>();
        this.cantidadFilasRecorridas = 53;
    }

    public void llenarHoja18() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File("F:\\7.- NetBeans Projects\\2. Intermedios\\10. LeerExcel\\Origen.xlsx")));
            XSSFSheet hoja = workbook.getSheetAt(0);//obtenemos la hoja a leer del Excel
            Iterator<Row> rowIterator = hoja.iterator();

            this.llenarFilaPlanes(rowIterator);
            this.obtenerEquipos(hoja);
            this.relacionEquipoPlan(hoja);

//            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
//                if (!workbook.getSheetName(i).contains("24M")) { //excluimos todos los filaPlanes a 24M
//                    XSSFSheet hoja = workbook.getSheetAt(i);//creamos la hoja
//                    Iterator<Row> rowIteratorEquipos = hoja.iterator();//creamos el iterador para la hoja
//
//                    this.obtenerEquipos(hoja);
//                    this.relacionEquipoPlan(hoja);
//                }
//            }
            System.out.println("Termine!!!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void llenarFilaPlanes(Iterator<Row> rowIterator) {
        while (rowIterator.hasNext()) {
            try {
                Row fila = rowIterator.next();
                Cell celda = fila.getCell(0);
                String contenidoCelda = celda.getStringCellValue();
                if (contenidoCelda.equals("PLANES")) {
                    this.filaPlanes.add(celda.getRowIndex());
                }
            } catch (Exception e) {
            }
        }
    }

    private void obtenerEquipos(XSSFSheet hoja) {
        for (int plan : this.filaPlanes) {
            XSSFRow row = (XSSFRow) hoja.getRow(plan);
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (cell.getColumnIndex() != 0) {
                    Equipos eq = new Equipos();
                    eq.setEquipo(cell.getStringCellValue());
                    eq.setFilaEquipo(plan);
                    eq.setColumnaEquipo(cell.getColumnIndex());

                    this.equipos.add(eq);
                }
            }
        }
    }

    private void relacionEquipoPlan(XSSFSheet hoja) {
        int variableFila = 0;

        for (Equipos t : this.equipos) {
            this.dictionary = new TreeMap<>();

            for (int j = t.getFilaEquipo() + 1; j <= this.cantidadFilasRecorridas; j++) {
                XSSFRow row = (XSSFRow) hoja.getRow(j);
                Iterator<Cell> cellIterator = row.cellIterator();

                String plan = "";
                double precio = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cell.getColumnIndex() == 0) {
                        plan = cell.getStringCellValue();
                    }
                    if (cell.getColumnIndex() == t.getColumnaEquipo()) {
                        precio = cell.getNumericCellValue();
                    }

                    this.dictionary.put(plan, precio);
                }
            }

            t.setPlanPrecio(this.dictionary);

            if (variableFila > 0 && t.getFilaEquipo() != variableFila) {
                this.cantidadFilasRecorridas += this.cantidadFilasRecorridas - 3;
                variableFila = t.getFilaEquipo();
            } else {
                variableFila = t.getFilaEquipo();
            }
        }
    }
}
