package archivoservidor;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ArchivoServidor {

    static int nsocket;
    static Socket socket;
    static ServerSocket serverSocket;

    public static void main(String[] args) {
        try{
            try {
                nsocket = Integer.parseInt(args[args.length - 1]);
            } catch (Exception e) {
                System.out.println("INTRODUZCA SOCKET");
            }

            try {
                serverSocket = new ServerSocket(nsocket);
            } catch (Exception e) {
                System.out.println("NO SE CREO SERVIDOR");
                System.exit(1);
            }

            String entrada = "";

            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
                if ((entrada = lector.readLine()) != null) {
                    System.out.println(entrada);
                    ENVIO(entrada, escritor);
                    serverSocket.close();
                    socket.close();
                } else {
                    System.out.println("ESCRIBA DE NUEVO EL MENSAJE");
                }
            }
        }catch (IOException e) {
            System.out.println("REINTENTAR");
        }
    }

    public static String ENVIO(String directorio, PrintWriter escritor){
        String texto= "";
        File file= new File(directorio);
        if(file.exists()){
            FileReader fr = null;
            BufferedReader br = null;
      try {
         fr = new FileReader (file);
         br = new BufferedReader(fr);
         String linea;
         while((linea=br.readLine())!=null){escritor.println(linea);}
      }catch(Exception e){ e.printStackTrace(); }finally{ try{ if( null != fr ){   fr.close(); } }catch (Exception e2){ e2.printStackTrace(); }}
        }else{
            System.out.println("NO SE ENCONTRO ARCHIVO");
        }
        return texto;
    }
}
