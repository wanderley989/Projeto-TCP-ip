package ConexaoSocketServidor;

import DAO.RestauranteDAO;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import pojo.*;
import DAO.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServidor extends Thread {

static PratoDAO pratodao = new PratoDAO();
static ClienteDAO clientedao = new ClienteDAO();
static RestauranteDAO restaurantedao = new RestauranteDAO();
    static  int option;
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
      
           try{
                ServerSocket  socketServer = new ServerSocket(9999);
                String str;
                byte[] line = new byte[100];
                while (true) {
                   try{//tentando criar uma conexao
			//Cria um SocketServer com porta 9999
			while(true){
                            /* Cria um objeto Socket, mas passando informações características de um servidor,
                             *no qual somente abre uma porta e aguarda a conexão de um cliente 
                             */
                            //cria uma thread que envia a conexao
                            Thread t = new SocketServidor(socketServer.accept());
                            //inicia a thread t
                            t.start();
			}
                    }catch(IOException e){
                            System.out.println("IOException "+e);
                    }
                }
           } catch(Exception e){
               System.out.println("Erro"+e);
           } 
    }    
                static private Socket socket;
        	public SocketServidor(Socket s){//recebe o valor do socket enviado na thread
		socket = s;
            }
            public void run(){
                try{
			    //Criando socket para receber e enviar string
                    String str;
                    byte[] line = new byte [100];
                    InputStream input;
                    
                    input = socket.getInputStream();
    
                    OutputStream output = socket.getOutputStream();

                    //Criando socket para receber e enviar objetos
                    ObjectOutputStream outputObjeto = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inputObjeto = new ObjectInputStream(socket.getInputStream());
                    //Restaurante r = new Restaurante("bar do doca", "bebebe", "merda", 2, "hh", 6);
                    //outputObjeto.writeObject(r);
                    //recebendo uma string 1
                    input.read(line);

                    //processando info
                    str = new String(line);
                    //System.out.println("Confirmando "+str.trim() );
                    int option = Integer.parseInt(str.trim());
                    //System.out.println(option);

                    switch(option){
                        //Switch para cadastrar clientes e pedidos
                        //teste da string recebida

                        case 1: 
                            System.out.println("Cliente Acabou de se Conectar");
                            //System.out.println("Enviando Restaurantes");
                            for(Restaurante restaurante1: restaurantedao.getRestaurantes()){
                                str = "1";
                                line = str.getBytes();
                                //System.out.println("Enviando 1");
                                output.write(line);//2
                                //enviando restaurante
                                outputObjeto.writeObject(restaurante1);//3
                            }
                            str = "0";
                            line = str.getBytes();
                            output.write(line);

                            //receber id de retalrante
                            Restaurante r = (Restaurante) inputObjeto.readObject();//4
                            
                            //for enviando todos os pratos do restaurante
                            for(Prato prato: pratodao.getPratos(r)){
                                str = "1";
                                //System.out.println("to aqui");
                                line = str.getBytes();
                                output.write(line);
                                //enviando restaurante
                                outputObjeto.writeObject(prato);
                            }
                            str = "0";
                            line = str.getBytes();
                            output.write(line);
                            //recebendo objeto pedido e inserindo no banco  
                            Cliente c = (Cliente)inputObjeto.readObject();
                            //System.out.println(c.getNome());
                            str = "9";
                            line = str.getBytes();
                            output.write(line);
                            clientedao.addCliente(c);


                            break;

                        case 2:                           
                            //Switch para Administrador de restauranteds
                            //Função para receber todos os restaurantes
                            for(Restaurante restaurante1: restaurantedao.getRestaurantes()){
                                str = "1";
                                line = str.getBytes();
                                //System.out.println("Enviando 1");
                                output.write(line);//2
                                //enviando restaurante
                                outputObjeto.writeObject(restaurante1);//3
                            }
                            str = "0";
                            line = str.getBytes();
                            output.write(line);
                            //Armazenando restaurante escolhido pelo Administrador
                            Restaurante r1 = (Restaurante) inputObjeto.readObject();//
                            //System.out.println(r1.toString());


                            //Opções para o case
                            boolean end3 = true;
                            do{
                                System.out.println("Um Administrador Acabou de se Conectar");
                                //System.out.println("Crud do restaurante");
                                 //Recebendo opção do restaurante
                                input.read(line);
                                //processando info
                                str = new String(line);
                                //System.out.println("Confirmando "+str.trim() );

                                int option2 = Integer.parseInt(str.trim());
                                System.out.println(option2);

                                switch(option2){

                                    case 1:
                                        //Menu dos pedidos
                                        boolean end = true;
                                        do{

                                            input.read(line);
                                            //processando info
                                            str = new String(line);
                                            int option3 = Integer.parseInt(str.trim());
                                            switch(option3){
                                                case 1:
                                                    //System.out.println("Enviando pedido ao cliente");
                                                    for(Cliente cliente: clientedao.getClientes(r1) ){
                                                        str = "1";
                                                        //System.out.println("to aqui");
                                                        line = str.getBytes();
                                                        output.write(line);
                                                        //enviando restaurante
                                                        outputObjeto.writeObject(cliente);
                                                    }
                                                    str = "0";
                                                    line = str.getBytes();
                                                    //System.out.println("nao pegou objeto");
                                                    output.write(line);
                                                    for(Prato prato: pratodao.getPratos(r1)){
                                                        str = "1";
                                                        //System.out.println("to aqui");
                                                        line = str.getBytes();
                                                        output.write(line);
                                                        //enviando restaurante
                                                        outputObjeto.writeObject(prato);
                                                    }
                                                    str = "0";
                                                    line = str.getBytes();
                                                    //System.out.println("nao pegou objeto");
                                                    output.write(line);
                                                    break;
                                                case 2:
                                                    //System.out.println("Case cliente pedidos");
                                                    for(Cliente cliente: clientedao.getClientes(r1) ){
                                                        str = "1";
                                                        //System.out.println("to aqui");
                                                        line = str.getBytes();
                                                        output.write(line);
                                                        //enviando restaurante
                                                        outputObjeto.writeObject(cliente);
                                                    }
                                                    str = "0";
                                                    line = str.getBytes();
                                                    //System.out.println("nao pegou objeto");
                                                    output.write(line);  
                                                    clientedao.delCLiente((Cliente) inputObjeto.readObject());
                                                    break;
                                                case 3:
                                                    end = false;
                                                    break;
                                            }
                                        }while(end);
                                        break;

                                    case 2:
                                        restaurantedao.atualizaRestaurante( (Restaurante) inputObjeto.readObject());
                                        break;   

                                    case 3:
                                        //Menu prato
                                        boolean end1 = true;
                                        do{
                                            input.read(line);
                                            //processando info
                                            str = new String(line);
                                            int option4 = Integer.parseInt(str.trim());
                                            switch(option4){
                                                case 1:
                                                    pratodao.addPrato((Prato) inputObjeto.readObject());
                                                    break;
                                                case 2:
                                                    Restaurante r3 = (Restaurante) inputObjeto.readObject();//4
                                                    //System.out.println(r3.getNome()+"deu certo");
                                                    //for enviando todos os pratos do restaurante/
                                                    for(Prato prato: pratodao.getPratos(r3)){
                                                        str = "1";
                                                        //System.out.println("to aqui");
                                                        line = str.getBytes();
                                                        output.write(line);
                                                        //enviando restaurante
                                                        outputObjeto.writeObject(prato);
                                                    }
                                                    str = "0";
                                                    line = str.getBytes();
                                                    //System.out.println("nao pegou objeto");
                                                    output.write(line);
                                                    pratodao.delPrato((Prato) inputObjeto.readObject(),r1);
                                                    break;
                                                case 3:
                                                    end1 = false;
                                                    break;
                                            }

                                        }
                                        while(end1);
                                        break;
                                    case 4:
                                        end3 = false;
                                        break;
                                }
                            }
                            while(end3);
                            break;
                    }  
                    //fechando socket  
                    //socket.close();
                    } catch (Exception ex) {
        Logger.getLogger(SocketServidor.class.getName()).log(Level.SEVERE, null, ex);
    }
                } 
}





