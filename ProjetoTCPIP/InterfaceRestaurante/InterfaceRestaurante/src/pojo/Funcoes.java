package pojo;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcoes {
    Scanner input = new Scanner(System.in);
    
    public Funcoes(){}
    
   public Restaurante loginrestaurante(ArrayList<Restaurante> restaurantes){
        do{
            System.out.println("Digite login");
            String login = input.nextLine();
            System.out.println("Digite senha");
            String senha = input.nextLine();

            for(Restaurante restaurante: restaurantes){
                if(login.equals(restaurante.getLogin())){
                    if(senha.equals(restaurante.getSenha())){
                        return restaurante;
                    }
                }

            }

        }while(true);  
    }
    public Restaurante atualizarDados(Restaurante restaurante){
        boolean end = true;
        do{
            System.out.println(
                    "| 1 | para mudar nome\n"
                  + "| 2 | para mudar tipo\n"
                  + "| 3 | para mudar endereço\n"
                   +"| 4 | para mudar login\n"
                  + "| 5 | para mudar senha\n"
                  + "| 6 | terminar");
           String op = input.nextLine();
           
           switch(Integer.parseInt(op)){
               case 1:
                   System.out.print("Digite o nome:");
                   String nome = input.nextLine();
                   restaurante.setNome(nome);
                   break;
               case 2:
                   System.out.print("Digite o tipo:");
                   String tipo = input.nextLine();
                   restaurante.setTipo(tipo);
                   break;
               case 3:
                   System.out.print("Digite o Bairro:");
                   String bairro  = input.nextLine();
                   System.out.print("Digite o Rua:");
                   String rua  = input.nextLine();
                   System.out.print("Digite o numero:");
                   String numero  = input.nextLine();
                   System.out.print("Digite o complemento:");
                   restaurante.setBairro(bairro);
                   restaurante.setRua(rua);
                   restaurante.setNumero(Integer.parseInt(numero));
                   break;
               case 4:
                   System.out.println("Digite o novo login:");
                   String login  = input.nextLine();
                   restaurante.setLogin(login);
                   break;
               case 5:
                   System.out.println("Digite a nova senha:");
                   String  senha = input.nextLine();
                   restaurante.setSenha(senha);
                   break;
               case 6:
                   end = false;
                   break;
           }
        }while(end);
        return restaurante;    
    }
    
    public Prato addPrato(Restaurante restaurante){
        
        System.out.print("Digite o nome do novo prato: ");
        String nome = input.nextLine();
        System.out.print("Digite o preço do novo prato: ");
        String preco = input.nextLine();
        System.out.print("Digite o numero do novo prato: ");
        String numero = input.nextLine();
        
        Prato prato = new Prato(nome, Double.parseDouble(preco),Integer.parseInt(numero),restaurante.getRid());
        
        return prato;
    }
    
    public void mostraPedidos(ArrayList<Cliente> clientes,ArrayList<Prato> pratos1){
        for(Cliente cliente1 : clientes){
            System.out.println("#################################################################################################################################\n"
                               +"##Nº Pedido:"+cliente1.getIdCliente()+" # Nome:"+cliente1.getNome()+" # Bairro:"+cliente1.getBairro()+" # Rua:"
                               +cliente1.getRua()+" # Numero:"+cliente1.getNumero()+" # Complemento:"
                                               +cliente1.getComplemento());
            for(Pedido pedido: cliente1.getPedidos()){
                for(Prato prato1: pratos1){
                    if(pedido.getPid() == prato1.getPid()){
                        System.out.println("## "+prato1.getPid()+" # "+prato1.getNome()+" # "+pedido.getQtd());
                        break;
                    }
                }
                
            }
            System.out.println("#################################################################################################################################");
        }
    }
    
    public Cliente delPedido(ArrayList<Cliente> clientes){
        System.out.println("#######################################################\n"
                           +"Digite o numero do pedido que ja foi atendido");
        do{
            int id_cliente = input.nextInt();
            for(Cliente cliente : clientes){
                if(cliente.getIdCliente()==id_cliente){
                    return cliente;
                }
            }
            System.out.println("Digite um numero de pedido valido");
        }while(true);
    }
    public Prato delPrato(ArrayList<Prato> pratos){
        System.out.print("Digite o numero do prato: ");
        do{
            int id_prato = input.nextInt();
            for(Prato prato : pratos){
                if(prato.getPid()==id_prato){
                    return prato;
                }
            }
            System.out.println("Digite um numero de prato valido");
        }while(true);
    }
}
