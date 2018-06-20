package pojo;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcoes {
    
    Scanner input = new Scanner(System.in);
    
    public Funcoes(){}    
  
    public Restaurante menuRestaurante(ArrayList<Restaurante> restaurantes){
        restaurantes.forEach((restaurante1) -> {
            System.out.println("#######################################################\n"
                    +"## "+restaurante1.getRid()+" # "+restaurante1.getNome() +" # "
                    +     restaurante1.getTipo()+" # " +restaurante1.getBairro());
        });
        do{
        
            int num_restaurante ;
        
            try {
                System.out.println("#######################################################\n"
                        +  "##        Digite um numero de restaurante valido     ##\n"
                        +  "#######################################################");
                
                num_restaurante = Integer.parseInt(input.nextLine());

                for (Restaurante restaurante1: restaurantes){
                    if (restaurante1.getRid() == num_restaurante){
                        return restaurante1;   
                    }
                }
            } catch (NumberFormatException e) {
                //uma falha por ser recursão infinita duas semanas não são suficientes pra fazer um bom trabalho:[
                Restaurante restaurante1 = menuRestaurante(restaurantes);
                return restaurante1;
            }
       
       }while(true);    
    }
    
    
    public Cliente menuPrato(ArrayList<Prato> pratos,Restaurante restaurante){
        Cliente cliente;
        boolean vouf;
        do{ 
            //falta tratamento de erro
            System.out.println("##################################\n"
                      +  "##   Digite o seu nome          ##\n"
                      +  "##################################");
            String nome = input.nextLine();
           
            System.out.println("##################################\n"
                      +  "##   Digite o nome da bairro    ##\n"
                      +  "##################################");
            String bairro = input.nextLine();
            System.out.println("##################################\n"
                      +  "##   Digite o nome da rua       ##\n"
                      +  "##################################");
            String rua = input.nextLine();
            
            System.out.println("##################################\n"
                      +  "##   Digite o complemento       ##\n"
                      +  "##################################");
            String complemento = input.nextLine();
           
            System.out.println("##################################\n"
                      +  "##   Digite o numero da casa    ##\n"
                      +  "##################################");
            String numero = input.nextLine();
          
            
            cliente = new Cliente(nome, rua, bairro, Integer.parseInt(numero), complemento, restaurante.getRid());
            vouf = false;
        }while (vouf);
        
        do{
            pratos.forEach((prato) -> {
                System.out.println( "###################################################################\n"
                        +"## "+prato.getPid()+" # "+prato.getNome()+" # "+prato.getPreco());
            });

            System.out.println("##################################\n"
                            +  "##   Digite o numero do prato   ##\n"
                            +  "##################################");

            try {
                int num_prato = input.nextInt();

                for (Prato prato: pratos){
                    if (prato.getPid()== num_prato){
                        System.out.println("##################################\n"
                                        +  "##     Quantidade do prato      ##\n"
                                        +  "##################################");
                        int qtd_prato = input.nextInt();
                        Pedido pedido = new Pedido(num_prato, qtd_prato);
                        cliente.setPedido(pedido);
                    }
                }
                System.out.println(    "###################################################################\n"
                                    +  "##   Digite 1 para adicionar mais um pedido ou 0 para terminar   ##\n"
                                    +  "###################################################################");
                String adicionar = input.next();
                if(adicionar.equals("0")){
                    double totalcompra = 0;
                    System.out.println("###################################################################\n"
                                     + "##          Recibo da Compra                ");
 
                    for(Pedido pedido: cliente.getPedidos()){
                        for(Prato prato: pratos){
                            if(pedido.getPid() == prato.getPid()){
                                totalcompra+= (pedido.getQtd()*prato.getPreco());
                                System.out.println("## Nome do Prato: "+prato.getNome()+" # Preço: "+prato.getPreco()+" # Quatidade: "+pedido.getQtd());
                            }
                        }
                    }
                    
                    System.out.println("#######################################################\n"
                                    + "## Total da compra: "+totalcompra+"                     \n"
                                    + "#######################################################");
                    return cliente;
                }
            } catch (Exception e) {
                System.err.println("Erro: "+e);

            }
        }while(true);
    }
}
