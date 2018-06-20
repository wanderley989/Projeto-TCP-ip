package ConexaoSocketServidor ;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import pojo.Cliente;
import pojo.Funcoes;

import pojo.Prato;
import pojo.Restaurante;

public class ClienteSocket {
    
    static Scanner input = new Scanner(System.in);
    static Funcoes menus = new Funcoes();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        ArrayList<Prato> pratos = new ArrayList<>();
        
        try ( //Criando uma nova conexao
            Socket socket = new Socket("192.168.1.60", 9999)) {
            //Criando socket para receber e enviar string
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            //Criando socket para receber e enviar objetos
            ObjectOutputStream outputObjeto = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputObjeto = new ObjectInputStream(socket.getInputStream());

            String str = "1";
            byte[] line;
            line = str.getBytes();

            //enviando uma string 1
            output.write(line);

            do{
                //recebendo uma strig de confirmaçao de que ha dados para receber
                input.read(line);
                str = new String(line);

                if (str.trim().equals("1")){
                    //receber objeto restaurantes e add no array
                    Restaurante restaurante = (Restaurante) inputObjeto.readObject();
                    restaurantes.add(restaurante);
                }
                //fecha quando receber 0
            }while(str.trim().equals("1"));

            //passa o array para metodo mostra restaurantes que retorna um Restaurante
            Restaurante restaurante = (Restaurante) menus.menuRestaurante(restaurantes);
            //enviar objeto restaurante
            outputObjeto.writeObject(restaurante);
            //um while para receber pratos e colocar em um array
            do{
                //recebendo uma strig de confirmaçao de que ha dados para receber
                input.read(line);
                str = new String(line);

                if (str.trim().equals("1")){
                    //receber objeto prato e add no array
                    pratos.add((Prato) inputObjeto.readObject());
                }
                //fecha quando receber 0
            }while(str.trim().equals("1"));
            
            //passar array para metodo que retorna um objeto cliente
            Cliente cliente = menus.menuPrato(pratos,restaurante);
            //mandar para o servidor
            outputObjeto.writeObject(cliente);
            input.read(line);
        }
        
        catch (Exception err) {
            System.err.println(err);
        }
    }  
}
