import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ventas_Productos_Por_Vendedores {
    private int idproducto;
    private String nombreproducto;
    private int cantidad;
    private float precioporunidad;
    private String tipodocumento;
    private int numerodocumento;
    private String nombrevendedor;
    private String apellidovendedor;

    public Ventas_Productos_Por_Vendedores(int idproducto, String nombreproducto, int cantidad, float precioporunidad, String tipodocumento, int numerodocumento, String nombrevendedor, String apellidovendedor) {
        this.idproducto = idproducto;
        this.nombreproducto = nombreproducto;
        this.cantidad = cantidad;
        this.precioporunidad = precioporunidad;
        this.tipodocumento = tipodocumento;
        this.numerodocumento = numerodocumento;
        this.nombrevendedor = nombrevendedor;
        this.apellidovendedor = apellidovendedor;
    }

    @Override
    public String toString() {
        return "Ventas_Productos_Por_Vendedores{" +
                "idproducto=" + idproducto +
                ", nombreproducto='" + nombreproducto + '\'' +
                ", cantidad=" + cantidad +
                ", precioporunidad=" + precioporunidad +
                ", nombrevendedor='" + nombrevendedor + '\'' +
                ", apellidovendedor='" + apellidovendedor + '\'' +
                '}';
    }

    public String toCSV() {
        return this.idproducto + ";" + this.nombreproducto + ";" + this.cantidad + ";" + this.precioporunidad + ";" + this.nombrevendedor + ";" + this.apellidovendedor;
    }

    public void generarArchivoVentasPorVendedores() {
        String carpetaVendedor = "./Vendedores/";
        String nombreArchivo = carpetaVendedor + this.nombrevendedor + "_" + this.apellidovendedor + ".csv";

        try {
            // Esto verifica si la carpeta de vendedor existe, si no la crea
            File carpeta = new File(carpetaVendedor);
            if (!carpeta.exists()) {
                carpeta.mkdir();
            }

            // Esto escribe en el archivo
            try (FileWriter fw = new FileWriter(nombreArchivo, true)) {
                fw.write("TipoDocumentoVendedor;" + this.tipodocumento + "\n");
                fw.write("NumeroDocumentoVendedor;" + this.numerodocumento + "\n");
                fw.write("\n");
                fw.write("IDProducto;Cantidad Vendida\n");
                fw.write("\n");
                fw.write(this.idproducto + ";" + this.cantidad + "\n");
                System.out.println("Archivo de ventas generado para " + this.nombrevendedor + " " + this.apellidovendedor);
            } catch (IOException e) {
                System.out.println("Se ha producido un error al escribir en el archivo.");
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            System.out.println("No se pudo acceder o crear la carpeta de vendedor.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Ventas_Productos_Por_Vendedores> listaVentasProductosPorVendedores(Scanner sc) {
        ArrayList<Ventas_Productos_Por_Vendedores> al = new ArrayList<>();
        String input;
        do {
            System.out.print("Id del producto (o 'EXIT' para salir): ");
            input = sc.nextLine();
            if (!input.equalsIgnoreCase("EXIT")) {
                try {
                    int idproducto = Integer.parseInt(input);

                    System.out.print("Nombre del Producto: ");
                    String nombreproducto = sc.nextLine();

                    System.out.print("Cantidad vendida: ");
                    int cantidad = Integer.parseInt(sc.nextLine());

                    System.out.print("Precio por unidad: ");
                    float precioporunidad = Float.parseFloat(sc.nextLine());

                    System.out.print("Tipo de Documento del Vendedor: ");
                    String tipodocumento = sc.nextLine();

                    System.out.print("Numero de Documento del Vendedor: ");
                    int numerodocumento = Integer.parseInt(sc.nextLine());

                    System.out.print("Nombre del vendedor: ");
                    String nombrevendedor = sc.nextLine();

                    System.out.print("Apellido del vendedor: ");
                    String apellidovendedor = sc.nextLine();

                    Ventas_Productos_Por_Vendedores venta = new Ventas_Productos_Por_Vendedores(idproducto, nombreproducto, cantidad, precioporunidad, tipodocumento, numerodocumento, nombrevendedor, apellidovendedor);
                    al.add(venta);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                }
            }
        } while (!input.equalsIgnoreCase("EXIT"));
        return al;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<Ventas_Productos_Por_Vendedores> al = listaVentasProductosPorVendedores(sc);

            try (FileWriter fw = new FileWriter("./VentasPorVendedores.csv", true)) {
                for (Ventas_Productos_Por_Vendedores venta : al) {
                    fw.write(venta.toCSV() + "\n");
                    venta.generarArchivoVentasPorVendedores(); // Esto nos genera el archivo individual por vendedor
                }
            } catch (IOException e) {
                System.out.println("Se ha producido un error al escribir en el archivo.");
                e.printStackTrace();
            }
        }
    }
} 