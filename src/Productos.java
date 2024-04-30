import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Productos {
    private int idproducto;
    private String nombreproducto;
    private float precioporunidad;

    public Productos(int idproducto, String nombreproducto, float precioporunidad) {
        this.idproducto = idproducto;
        this.nombreproducto = nombreproducto;
        this.precioporunidad = precioporunidad;
    }

    @Override
    public String toString() {
        return "Productos{" + "idproducto=" + idproducto + ", nombreproducto=" + nombreproducto
                + ", precioporunidad=" + precioporunidad + '}';
    }

    public String toCSV() {
        return this.idproducto + ";" + this.nombreproducto + ";" + this.precioporunidad;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public float getPrecioporunidad() {
        return precioporunidad;
    }

    public void setPrecioporunidad(float precioporunidad) {
        this.precioporunidad = precioporunidad;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<Productos> al = Productos.listaProductos(sc);
            
            try (FileWriter fw = new FileWriter("./ListaProductos.csv", true)) {
                for (Productos producto : al) {
                    fw.write(producto.getIdproducto() + ";" +
                            producto.getNombreproducto() + ";" +
                            producto.getPrecioporunidad() + "\n");
                }
            } catch (Exception e) {
                System.out.println("Se ha producido un error al escribir en el archivo.");
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Productos> listaProductos(Scanner sc) {
        ArrayList<Productos> al = new ArrayList<>();
        String input;
        do {
            System.out.print("Id del producto (o 'EXIT' para salir): ");
            input = sc.nextLine();
            if (!input.equalsIgnoreCase("EXIT")) {
                try {
                    int idproducto = Integer.parseInt(input);

                    System.out.print("Nombre del Producto: ");
                    String nombreproducto = sc.nextLine();

                    System.out.print("Precio por unidad: ");
                    float precioporunidad = Float.parseFloat(sc.nextLine());

                    Productos p = new Productos(idproducto, nombreproducto, precioporunidad);
                    al.add(p);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                }
            }
        } while (!input.equalsIgnoreCase("EXIT"));
        return al;
    }
}