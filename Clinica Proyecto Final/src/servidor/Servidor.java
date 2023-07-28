package servidor;

import java.io.*;
import java.net.*;

public class Servidor extends Thread {
	private static int connectionCounter = 1;
	private static boolean running = true;

	public static void main(String args[]) {
		ServerSocket sfd = null;
		try {
			sfd = new ServerSocket(8007);
			System.out.println("Servidor listo y esperando conexiones...");
		} catch (IOException ioe) {
			System.out.println("Comunicación rechazada." + ioe);
			System.exit(1);
		}

		// Agregar un ShutdownHook para cerrar el servidor de forma adecuada
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			running = false;
			try {
				sfd.close();
				System.out.println("Servidor cerrado.");
			} catch (IOException e) {
				System.out.println("Error al cerrar el servidor: " + e.getMessage());
			}
		}));

		while (running) {
			try {
				Socket nsfd = sfd.accept();
				System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());
				new Thread(() -> {
					handleClient(nsfd);
				}).start();
			} catch (IOException ioe) {
				System.out.println("Error: " + ioe);
			}
		}
	}

	private static void handleClient(Socket nsfd) {
		try (DataInputStream flujoLectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()))) {
			String linea = flujoLectura.readUTF();

			if (!linea.equals("")) {
				String fileName = "C:\\Users\\DELL\\Documents\\Plantillas personalizadas de Office\\Clinica_"
						+ connectionCounter + ".dat";
				connectionCounter++;

				// Guardar el texto recibido en un archivo único para esta conexión
				try (FileWriter fileWriter = new FileWriter(fileName, true);
						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
					printWriter.println(linea);
				} catch (IOException e) {
					System.out.println("Error al guardar el archivo: " + e.getMessage());
				}

				System.out.println("Texto recibido y guardado en el archivo: " + fileName);
			}
		} catch (IOException ioe) {
			System.out.println("Error al leer el mensaje del cliente: " + ioe);
		} finally {
			try {
				nsfd.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar la conexión: " + e.getMessage());
			}
		}
	}
}
