import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VerProductos {

    public static void main(String[] args) {
        ArrayList<Productos> al = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("./ListaProductos.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                int idproducto = Integer.parseInt(parts[0]);
                String nombreproducto = parts[1];
                float precioporunidad = Float.parseFloat(parts[2]);
      
                Productos producto = new Productos(idproducto, nombreproducto, precioporunidad);
                al.add(producto);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al leer el precio por unidad: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Formato incorrecto en la línea del archivo: " + e.getMessage());
        }

        for (Productos producto : al) {
            System.out.println(producto);
        }
    }
}