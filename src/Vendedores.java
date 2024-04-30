import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Vendedores {
	private String tipodocumento;
	private int numerodocumento;
	private String nombrevendedor;
	private String apellidovendedor;

	public Vendedores(String tipodocumento, int numerodocumento, String nombrevendedor, String apellidovendedor) {
		this.tipodocumento = tipodocumento;
		this.numerodocumento = numerodocumento;
		this.nombrevendedor = nombrevendedor;
		this.apellidovendedor = apellidovendedor;
	}

	@Override
	public String toString() {
		return "Persona{" + "tipodocumento=" + tipodocumento + ", numerodocumento=" + numerodocumento
				+ ", nombrevendedor=" + nombrevendedor + ", apellidovendedor=" + apellidovendedor + '}';
	}

	public String toCSV() {
		return this.tipodocumento + ";" + this.numerodocumento + ";" + this.nombrevendedor + ";"
				+ this.apellidovendedor;
	}

	public String getTipoDocumento() {
		return tipodocumento;
	}

	public void setTipoDocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public int getNumeroDocumento() {
		return numerodocumento;
	}

	public void setNumeroDocumento(int numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getNombreVendedor() {
		return nombrevendedor;
	}

	public void setNombreVendedor(String nombrevendedor) {
		this.nombrevendedor = nombrevendedor;
	}

	public String getApellidoVendedor() {
		return apellidovendedor;
	}

	public void setApellidoVendedor(String apellidovendedor) {
		this.apellidovendedor = apellidovendedor;
	}

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Vendedores> al = listaVendedores(sc);
        
        try (FileWriter fw = new FileWriter("./ListaVendedores.csv", true)) {
            for (Vendedores vendedor : al) {
                fw.write(vendedor.getTipoDocumento() + ";" +
                		vendedor.getNumeroDocumento() + ";" +
                		vendedor.getNombreVendedor() + ";" +
                		vendedor.getApellidoVendedor() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Vendedores> listaVendedores(Scanner sc) {
        ArrayList<Vendedores> al = new ArrayList<>();
        String tipodocumento;
        int numerodocumento;
        String nombrevendedor;
        String apellidovendedor;
        Vendedores p;
        do {
            System.out.print("Digite tipo de documento (o 'EXIT' para salir): ");
            tipodocumento = sc.nextLine();
            if (!tipodocumento.equalsIgnoreCase("EXIT")) {
                System.out.print("Numero de Documento: ");
                numerodocumento = Integer.parseInt(sc.nextLine());
                System.out.print("Nombres del vendedor: ");
                nombrevendedor = sc.nextLine();
                System.out.print("Apellidos del vendedor: ");
                apellidovendedor = sc.nextLine();
                p = new Vendedores(tipodocumento, numerodocumento, nombrevendedor, apellidovendedor);
                al.add(p);
            }
        } while (!tipodocumento.equalsIgnoreCase("EXIT"));
        return al;
    }
}


