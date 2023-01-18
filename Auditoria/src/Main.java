import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// log in
		Scanner entrada = new Scanner(System.in);
		String usuario, password, rol = "";
		System.out.println("Intoduce usuario");
		usuario = entrada.nextLine();
		System.out.println("Introudce contraseña");
		password = entrada.nextLine();
		// leo fichero usuarios

		// preguntar por la llave

		// descifrar

		File usuarioruta =new File ("usuario.txt");

		String Usuarioruta=usuarioruta.getAbsolutePath();
		
		
		String ruta = ""+Usuarioruta;
		FileReader fichero;
		try {
			fichero = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fichero);
			String linea = "";
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if (usuario.equals(datos[0])) {
					if (password.equals(datos[1])) {
						rol = datos[2];
						break;
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// cifrar los usuarios

		// preguntas por la llave / contraseña
		// leo la agenda

		Agenda agenda = new Agenda();
		
		File agendaruta =new File ("agenda.txt");

		String Agendaruta=agendaruta.getAbsolutePath();
		

		
		ruta = ""+Agendaruta;

		try {
			fichero = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fichero);
			String linea = "";
			int i = 0;
			String nombre = "", apellidos = "", direccion = "", telefono = "";
			while ((linea = br.readLine()) != null) {
				if (i == 0) {
					nombre = linea;
					i++;
				} else if (i == 1) {
					apellidos = linea;
					i++;
				} else if (i == 2) {
					direccion = linea;
					i++;
				} else if (i == 3) {
					telefono = linea;
					agenda.agenda.add(new Contacto(nombre, apellidos, direccion, telefono));
					i = 0;
				}

				// registrarActividad
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// regitrarError();
		}

		// cifras la agenda

		Administrador admin = null;
		Gestor gestor = null;
		Asistente asistente = null;

		switch (rol) {
		case "admin":
			admin = new Administrador(agenda.agenda);
			System.out.println("el suuario es un admin");
			// escibir en traza admin usuario alfredo log in ok como rol de admin
			break;
		case "gestor":
			gestor = new Gestor(agenda.agenda);
			System.out.println("el suuario es un gestor");
			break;
		case "asistente":
			asistente = new Asistente(agenda.agenda);
			break;
		default:
			System.out.println("USUARIO NO VALIDO");

			break;
		}

		// menu usuario
		System.out.println("1. Add contacto");
		System.out.println("2. modificar contacto");
		System.out.println("3. Listar contacto");
		int opcion = entrada.nextInt();
		// registro todso los nuemros
		String info = "";
		Date fecha;
		switch (opcion) {
		case 1:
			switch (rol) {
			case "admin":
				admin.addContact();
				break;
			case "asistente":
				System.out.println("No tienes permiso para agregar un contacto");
				break;
			case "gestor":
				gestor.addContact();
				break;
			}

			fecha = new Date();
			info = fecha + ";" + usuario + ";" + rol + ";addContact()";
			
			File actividadruta =new File ("actividad.txt");

			String Actividadruta=actividadruta.getAbsolutePath();
			ruta = ""+actividadruta;
			
			registrar(ruta, info);
			// registro el tipo de accion elegida
			break;
		
		case 2:
			System.out.println("CASO 2");
			switch (rol) {
			case "admin":
				admin.agregarUsuario();
				break;
			case "asistente":
				System.out.println("No tienes permiso para agregar un usuario");
				break;
			case "gestor":
				System.out.println("No tienes permiso para agregar un usuario");
				break;
			}
			break;
		
		case 3:
			switch (rol) {
			case "admin":
				admin.listarContacto(agenda.agenda);
				break;
			case "asistente":
				asistente.listarContacto(agenda.agenda);
				break;
			case "gestor":
				gestor.listarContacto(agenda.agenda);
				break;
				
			}
			break;
		case 4	:
			switch (rol) {
			case "admin":
				admin.modificarContact(0);
				break;
			case "asistente":
				System.out.println("No tienes permiso para modificar un usuario");
				break;
			case "gestor":
				gestor.modificarContact(0);
				break;
				
			}
			
		}
		}

	public static void registrarActividad(String info) {
		

		File actividadruta =new File ("actividad.txt");

		String Actividadruta=actividadruta.getAbsolutePath();
		

		
		String ruta = ""+Actividadruta;
	
		FileWriter fichero;
		try {
			fichero = new FileWriter(ruta, true);
			BufferedWriter bw = new BufferedWriter(fichero);
			bw.write(info + "\n");
			bw.close();
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void registrar(String ruta, String info) {

		FileWriter fichero;
		try {
			fichero = new FileWriter(ruta, true);
			BufferedWriter bw = new BufferedWriter(fichero);
			bw.write(info + "\n");
			bw.close();
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}