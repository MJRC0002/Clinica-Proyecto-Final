package servidor;

import java.io.*;
import java.net.*;

public class Servidor extends Thread {
    private static int connectionCounter = 1;
    private static boolean running = true;

    public static void main(String args[]) {
        ServerSocket sfd = null;
        try {
            sfd = new ServerSocket(8016);
            System.out.println("Servidor listo y esperando conexiones...");

            // Declarar sfd fuera del bloque try
            final ServerSocket finalSfd = sfd;

            // Registrar un ShutdownHook para cerrar el servidor de forma adecuada
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    running = false;
                    try {
                        if (finalSfd != null)
                            finalSfd.close();
                        System.out.println("Servidor cerrado.");
                    } catch (IOException e) {
                        System.out.println("Error al cerrar el servidor: " + e.getMessage());
                    }
                }
            }));
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada." + ioe);
            System.exit(1);
        }

        while (running) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexión aceptada de: " + nsfd.getInetAddress());
                new Thread(() -> {
                    handleClient(nsfd);
                }).start();
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe);
            }
        }
    }

    private static void handleClient(Socket nsfd) {
        try (BufferedInputStream bis = new BufferedInputStream(nsfd.getInputStream())) {
            String fileName = "Clinica_" + connectionCounter + ".dat";
            connectionCounter++;

            // Guardar el archivo recibido en el servidor
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                System.out.println("Archivo recibido y guardado: " + fileName);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
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
