import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VisorTodosVendedores {

    public static void main(String[] args) {
        mostrarContenidoVentasPorVendedores();
       
    }

    public static void mostrarContenidoVentasPorVendedores() {
        File archivoVentasPorVendedor = new File("./VentasPorVendedores.csv");

        try (Scanner scanner = new Scanner(archivoVentasPorVendedor)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar el archivo VentasPorVendedores.csv");
        }
    }
}

    