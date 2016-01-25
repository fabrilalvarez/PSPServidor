package servidorcalculador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author fabrilalvarez
 */
public class ServidorCalculador {

    static int numRecibido1, numRecibido2, operacion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Creando socket servidor");

            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");

            InetSocketAddress addr = new InetSocketAddress("LocalHost", 5555);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");

            Socket newSocket = serverSocket.accept();

            System.out.println("Conexion recibida");

            InputStream is = newSocket.getInputStream();
            OutputStream os = newSocket.getOutputStream();

            byte[] mensaje = new byte[25];
            is.read(mensaje);

            Comprobar(mensaje);
            calcular(operacion, numRecibido1, numRecibido2);

            System.out.println("Cerrando el nuevo socket");

            newSocket.close();

            System.out.println("Cerrando el socket servidor");

            serverSocket.close();

            System.out.println("Terminado");

        } catch (IOException e) {
        }
    }

    public static void Comprobar(byte[] b) {
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0) {
                numRecibido1 = Integer.parseInt(new String(b));
            } else if (b[i] == 1) {
                operacion = Integer.parseInt(new String(b));
            } else if (b[i] == 2) {
                numRecibido2 = Integer.parseInt(new String(b));
            }
        }
    }

    public static void calcular(int recibido, int dato1, int dato2) {
        int op = 0;
        double n1, n2, s, m, d, r;
        do {
            op = recibido;
            switch (op) {
                case 1:
                    n1 = dato1;
                    n2 = dato2;
                    s = n1 + n2;
                    System.out.println("Resultado: " + s);
                    break;
                case 2:
                    n1 = dato1;
                    n2 = dato2;
                    r = n1 - n2;
                    System.out.println("Resultado: " + r);
                    break;
                case 3:
                    n1 = dato1;
                    n2 = dato2;
                    m = n1 * n2;
                    System.out.println("Resultado: " + m);
                    break;
                case 4:
                    n1 = dato1;
                    n2 = dato2;
                    d = n1 / n2;
                    System.out.println("Resultado: " + d);
                    break;
            }
        } while (op != 5);
    }

}
