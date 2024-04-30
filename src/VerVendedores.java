import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VerVendedores {

    public static void main(String[] args) {
        ArrayList<Vendedores> al = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("./ListaVendedores.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String tipodocumento = parts[0];
                int numerodocumento = Integer.parseInt(parts[1]);
                String nombrevendedor = parts[2];
                String apellidovendedor = parts[3];
                Vendedores p = new Vendedores(tipodocumento, numerodocumento, nombrevendedor, apellidovendedor);
                al.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error al leer el número de documento: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Formato incorrecto en la línea del archivo: " + e.getMessage());
        }

        for (Vendedores vendedor : al) {
            System.out.println(vendedor);
        }
    }
}