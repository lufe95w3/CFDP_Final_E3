import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ReporteProductosVendidos {

    public static void main(String[] args) {
        generarReporteProductosVendidosOrdenado();
        visualizarReporteProductosVendidos();
    }

    public static void generarReporteProductosVendidosOrdenado() {
        String archivoEntrada = "./VentasPorVendedores.csv";
        String archivoSalida = "./ReporteProductosVendidosOrdenado.csv";

        // Mapa para almacenar los productos y sus cantidades vendidas
        Map<String, Double> productos = new HashMap<>();
        // Mapa para almacenar los precios unitarios de los productos
        Map<String, Double> preciosUnitarios = new HashMap<>();

        // Leer datos del archivo y almacenar en los mapas
        try (Scanner scanner = new Scanner(new File(archivoEntrada))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                if (datos.length == 6) {
                    String nombreProducto = datos[1];
                    double cantidadVendida = Double.parseDouble(datos[2].replace(',', '.'));
                    double precioUnitario = Double.parseDouble(datos[3].replace(',', '.'));

                    // Almacenar precio unitario en el mapa de precios unitarios
                    preciosUnitarios.put(nombreProducto, precioUnitario);

                    // Si el producto ya está en el mapa, sumar la cantidad vendida
                    productos.put(nombreProducto, productos.getOrDefault(nombreProducto, 0.0) + cantidadVendida);
                } else {
                    System.out.println("Error: línea mal formateada en el archivo CSV: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el valor: " + e.getMessage());
            return;
        }

        // Crear un TreeMap con un comparador que ordene por cantidad vendida de forma descendente
        TreeMap<String, Double> productosOrdenados = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String producto1, String producto2) {
                double cantidad1 = productos.getOrDefault(producto1, 0.0);
                double cantidad2 = productos.getOrDefault(producto2, 0.0);
                // Orden descendente
                return Double.compare(cantidad2, cantidad1);
            }
        });
        productosOrdenados.putAll(productos);

        // Escribir los productos ordenados en el archivo de salida
        try (FileWriter writer = new FileWriter(archivoSalida)) {
            for (Map.Entry<String, Double> entry : productosOrdenados.entrySet()) {
                String nombreProducto = entry.getKey();
                double cantidadAcumulada = entry.getValue();
                double precioUnitario = preciosUnitarios.get(nombreProducto); // Obtener el precio unitario del mapa de precios
                String linea = nombreProducto + ";" + cantidadAcumulada + ";" + precioUnitario; // Formatear la línea
                writer.write(linea + "\n");
            }
            System.out.println("Archivo de reporte de productos vendidos ordenado por cantidad descendente creado con éxito: " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de salida: " + e.getMessage());
        }
    }

    public static void visualizarReporteProductosVendidos() {
        // Ruta del archivo de reporte de productos vendidos
        String archivoReporte = "./ReporteProductosVendidosOrdenado.csv";

        System.out.println("=== Reporte de productos vendidos ===");
        System.out.println("Producto\tCantidad\tPrecio Unitario");

        try (Scanner scanner = new Scanner(new File(archivoReporte))) {
            // Leer y mostrar cada línea del archivo
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar el archivo de reporte de productos vendidos");
        }
    }
}
