import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VisorVentasPorVendedor {

    public static void main(String[] args) {
        mostrarVendedores();
    }

    public static void mostrarVendedores() {
        File carpetaVendedores = new File("./Vendedores/");
        File[] archivosVendedores = carpetaVendedores.listFiles();

        if (archivosVendedores != null) {
            for (File archivo : archivosVendedores) {
                if (archivo.isFile()) {
                    System.out.println("Información del vendedor en el archivo: " + archivo.getName());
                    mostrarContenidoArchivo(archivo);
                    System.out.println("---------------------------------------------");
                }
            }
        } else {
            System.out.println("No hay archivos de vendedores en la carpeta.");
        }
    }

    public static void mostrarContenidoArchivo(File archivo) {
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}