package Main ;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import pojo.Cliente;
import pojo.Funcoes;
import pojo.Prato;
import pojo.Restaurante;

public class RestauranteSocket {
    
    static Scanner input = new Scanner(System.in);
    static Funcoes funcoes = new Funcoes();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ArrayList<Restaurante> restaurantes = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Prato> pratos = new ArrayList<>();
        
        try ( //Criando uma nova conexao
            Socket socket = new Socket("192.168.1.60",9999)) {
            //Criando socket para receber e enviar string
            InputStream inputsocket = socket.getInputStream();
            OutputStream outputsocket = socket.getOutputStream();
            //Criando socket para receber e enviar objetos
            ObjectOutputStream outputObjeto = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputObjeto = new ObjectInputStream(socket.getInputStream());
            //string para avisar o servidor que um restaurante esta se comunicando
            String str = "2";
            byte[] line;
            line = str.getBytes();
            //enviando a string 2
            outputsocket.write(line);
            //recebendo restaurante para fazer o login
            do{
                //recebendo uma strig de confirmaçao de que ha dados para receber
                inputsocket.read(line);
                str = new String(line);

                if (str.trim().equals("1")){
                    //receber objeto restaurantes e add no array
                    Restaurante restaurantelogin = (Restaurante) inputObjeto.readObject();

                    //adicionando o restaurante ao array
                    restaurantes.add(restaurantelogin);
                }

                //fecha quando receber 0
            }while(str.trim().equals("1"));
            //passa o array para a função login que retorna um Restaurante
            Restaurante restaurante = (Restaurante) funcoes.loginrestaurante(restaurantes);
            //enviando o restaurante para o servidor
            outputObjeto.writeObject(restaurante);
            //while do menu da interface restaurante
            boolean vouf0 = true;
            do {
                //um print com as opçoes
                System.out.println( "| 1 | Pedidos\n"
                        + "| 2 | Atualizar informações do restaurante\n"
                        + "| 3 | Cardápio\n"
                        + "| 4 | Fecha");

                //recebendo a escolha
                String option = input.nextLine();

                //enviando option escolhida para o servidor
                line = option.getBytes();
                outputsocket.write(line);

                //transformando em inteiro
                int option2 = Integer.parseInt(option.trim());

                switch(option2){
                    case 1:
                        boolean vouf = true;
                        do{
                            //um print com as opçoes
                            System.out.println( "| 1 | Mostra Pedidos\n"
                                    + "| 2 | Deletar Pedidos ja atendidos\n"
                                    + "| 3 | Voltar");

                            //recebendo a escolha do teclado
                            option = input.nextLine();

                            //enviando option escolhida para o servidor
                            line = option.getBytes();
                            outputsocket.write(line);

                            option2 = Integer.parseInt(option.trim());

                            switch(option2){
                                case 1:
                                    do{
                                        //recebendo uma strig de confirmaçao de que ha dados para receber
                                        inputsocket.read(line);
                                        str = new String(line);

                                        if (str.trim().equals("1")){
                                            //receber objeto prato e add no array
                                            Cliente c =(Cliente) inputObjeto.readObject();
                                            clientes.add(c);
                                        }

                                        //fecha quando receber 0
                                    }while(str.trim().equals("1"));

                                    //while para receber todos os pratos de restaurante
                                    do{
                                        //recebendo uma strig de confirmaçao de que ha dados para receber
                                        inputsocket.read(line);
                                        str = new String(line);

                                        if (str.trim().equals("1")){
                                            //receber objeto prato e add no array
                                            Prato p =(Prato) inputObjeto.readObject();
                                            pratos.add(p);
                                        }

                                        //fecha quando receber 0
                                    }while(str.trim().equals("1"));
                                    //Chamando função para mostrar os pedidos
                                    funcoes.mostraPedidos(clientes,pratos);
                                    clientes.clear();

                                    break;
                                case 2:
                                    do{
                                        //recebendo uma strig de confirmaçao de que ha dados para receber
                                        inputsocket.read(line);
                                        str = new String(line);

                                        if (str.trim().equals("1")){
                                            //receber objeto prato e add no array
                                            Cliente c =(Cliente) inputObjeto.readObject();
                                            clientes.add(c);
                                        }

                                        //fecha quando receber 0
                                    }while(str.trim().equals("1"));
                                    //Chamando função para deleta os pedidos retorna um cliente
                                    Cliente cliente = funcoes.delPedido(clientes);
                                    outputObjeto.writeObject(cliente);
                                    clientes.clear();

                                    break;
                                case 3:
                                    vouf = false;
                                    break;
                            }
                        }while(vouf);
                        break;
                    case 2:
                        Restaurante restauranteatualizado = funcoes.atualizarDados(restaurante);
                        outputObjeto.writeObject(restauranteatualizado);
                        restaurante = restauranteatualizado;
                        break;
                    case 3:
                        boolean vouf1 = true;
                        do{
                            //um print com as opçoes
                            System.out.println( "| 1 | Adicionar um novo prato\n"
                                    + "| 2 | Deletar um prato\n"
                                    + "| 3 | Voltar");

                            //recebendo a escolha 0
                            option = input.nextLine();

                            //enviando option escolhida para o servidor
                            line = option.getBytes();
                            outputsocket.write(line);

                            option2 = Integer.parseInt(option.trim());

                            switch(option2){
                                case 1:
                                    Prato prato = funcoes.addPrato(restaurante);
                                    outputObjeto.writeObject(prato);
                                    break;
                                case 2:
                                    Prato prat;
                                    outputObjeto.writeObject(restaurante);
                                    //um while para receber pratos e colocar em um array
                                    do{
                                        //recebendo uma strig de confirmaçao de que ha dados para receber
                                        inputsocket.read(line);
                                        str = new String(line);

                                        if (str.trim().equals("1")){
                                            //receber objeto prato e add no array
                                            prat = (Prato) inputObjeto.readObject();
                                            pratos.add(prat);
                                        }
                                        //fecha quando receber 0

                                    }while(str.trim().equals("1"));
                                    //funçao para retorna um prato

                                    outputObjeto.writeObject(funcoes.delPrato(pratos));

                                    break;
                                case 3:
                                    vouf1 = false;
                                    break;
                            }
                        }while(vouf1);

                        break;
                    case 4:
                        //terminando o programa
                        vouf0 = false;
                        break;
                }
            } while (vouf0);
            //fechando conexão
            socket.close();
            
        }catch (IOException | ClassNotFoundException | NumberFormatException err) {
            System.err.println(err);
        }
    }    
}
