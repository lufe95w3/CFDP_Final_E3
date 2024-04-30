import java.io.*;
import java.util.*;

public class ReporteVentasDeVendedores {

    public static void main(String[] args) {
        generarReporteVentasPorVendedoresOrdenado();
        visualizarReporteVentasPorVendedores();
    }

    public static void generarReporteVentasPorVendedoresOrdenado() {
        String archivoEntrada = "./VentasPorVendedores.csv";
        String archivoSalida = "./ReporteVentasPorVendedoresOrdenado.csv";

        Map<String, Double> vendedoresVentas = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(archivoEntrada))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    String nombreCompletoVendedor = datos[4] + " " + datos[5]; // Concatenar nombre y apellido
                    double precioUnitario = Double.parseDouble(datos[2].replace(',', '.'));
                    double cantidadVendida = Double.parseDouble(datos[3].replace(',', '.')); // Convertir a double

                    // Calcular el total de ventas
                    double ventasTotales = precioUnitario * cantidadVendida;

                    // Actualizar el total de ventas del vendedor
                    vendedoresVentas.put(nombreCompletoVendedor, vendedoresVentas.getOrDefault(nombreCompletoVendedor, 0.0) + ventasTotales);
                } else {
                    System.out.println("Error: línea mal formateada en el archivo CSV: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar el archivo VentasPorVendedores.csv");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Error: Error al convertir el valor de ventas: " + e.getMessage());
            return;
        }

        // Convertir el mapa a una lista de entradas para ordenar
        List<Map.Entry<String, Double>> listaVendedoresVentas = new ArrayList<>(vendedoresVentas.entrySet());

        // Ordenar la lista de vendedores por las ventas de mayor a menor
        listaVendedoresVentas.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Escribir los vendedores ordenados en el archivo de salida
        try (FileWriter writer = new FileWriter(archivoSalida)) {
            for (Map.Entry<String, Double> entry : listaVendedoresVentas) {
                String linea = entry.getKey() + ";" + entry.getValue() + "\n";
                writer.write(linea);
            }
            System.out.println("Archivo de reporte de vendedores ordenado creado con éxito: " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error: Error al escribir en el archivo de salida: " + e.getMessage());
        }
    }

    public static void visualizarReporteVentasPorVendedores() {
        // Ruta del archivo de reporte de ventas por vendedores
        String archivoReporte = "./ReporteVentasPorVendedoresOrdenado.csv";

        System.out.println("=== Reporte de ventas por vendedores ===");
        System.out.println("Nombre Vendedor\tDinero Recaudado");

        try (Scanner scanner = new Scanner(new File(archivoReporte))) {
            // Leer y mostrar cada línea del archivo
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar el archivo de reporte de ventas por vendedores");
        }
    }
}