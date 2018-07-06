
import java.util.TreeMap;

/**
 *
 * @author TCSCON
 */
public class Equipos {

    private String equipo;
    private int columnaEquipo;
    private int filaEquipo;
    //private ArrayList<String> planes;
    private TreeMap<String, Double> planPrecio;
    
    /**
     * @return the equipo
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    /**
     * @return the planes
     */
//    public ArrayList<String> getPlanes() {
//        return planes;
//    }

    /**
     * @param planes the planes to set
     */
//    public void setPlanes(ArrayList<String> planes) {
//        this.planes = planes;
//    }

    /**
     * @return the columnaEquipo
     */
    public int getColumnaEquipo() {
        return columnaEquipo;
    }

    /**
     * @param columnaEquipo the columnaEquipo to set
     */
    public void setColumnaEquipo(int columnaEquipo) {
        this.columnaEquipo = columnaEquipo;
    }

    /**
     * @return the filaEquipo
     */
    public int getFilaEquipo() {
        return filaEquipo;
    }

    /**
     * @param filaEquipo the filaEquipo to set
     */
    public void setFilaEquipo(int filaEquipo) {
        this.filaEquipo = filaEquipo;
    }

    /**
     * @return the planPrecio
     */
    public TreeMap<String, Double> getPlanPrecio() {
        return planPrecio;
    }

    /**
     * @param planPrecio the planPrecio to set
     */
    public void setPlanPrecio(TreeMap<String, Double> planPrecio) {
        this.planPrecio = planPrecio;
    }
}
