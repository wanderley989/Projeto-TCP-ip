package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectionJDBC.ConnectionFactory;
import pojo.Prato;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import pojo.Cliente;
import pojo.Pedido;
import pojo.Restaurante;
public class ClienteDAO {
    Cliente cliente;
    Scanner entrada = new Scanner(System.in);
    private Connection connection;
    private Connection con;
    PedidoDAO pedidodao = new PedidoDAO();
//função para Cadastrar um cliente e adicionar pedido no Banco de Dados 	
    public boolean addCliente(Cliente cliente){
                //enviar cliente para o banco de dados
		String sql = "INSERT INTO Cliente (nome,rua,bairro,numero,cid,rid,complemento) VALUES (?,?,?,?,default,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		          //System.out.println("Passou do insert");
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getRua());
			stmt.setString(3, cliente.getBairro());
			stmt.setInt(4, cliente.getNumero());
			stmt.setInt(5, cliente.getIdRestaurante());
                        stmt.setString(6, cliente.getComplemento());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdRowsAffected > 0) {
				System.out.println("Cliente Adicionado");
                                int str2 = consultaCLienteCid();
                                //Adiciona o pedido do cliente ao banco de dados
                                for(Pedido pedido:cliente.getPedidos()){
                                    pedido.setCid(str2);
                                    pedidodao.addPedido(pedido);
                                }
                                return true;
			}
			System.out.println("Erro ao adicionar Cliente");
			return false;
		}catch(SQLException e){
                    System.err.println(e.getMessage());
		}finally{
			try{
				this.connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return false;
	}
        
        //Função Para Deletar um cliente do Banco de Dados
        public boolean delCLiente(Cliente cliente) {
	String sql = "Delete from Cliente where cid = ?";	
	
	this.con =  new ConnectionFactory().getConnection();
	
	try{
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1,cliente.getIdCliente());
		
		int qtdRowsAffected = stmt.executeUpdate();
		stmt.close();
		if(qtdRowsAffected > 0) {
			System.out.println("Cliente Removido");
			return true;
		}
		System.out.println("Erro ao Remover, Cliente não no encontrado no Banco");
		return false;
	}catch(SQLException e){
		System.out.println(e); 
	}finally{
		try{
			this.con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return false;
}
        
        //Função para Mostrar todos os clientes que fizeram pedido no restaurante
        public ArrayList<Cliente> getClientes(Restaurante restaurante) throws SQLException{
        this.con =  new ConnectionFactory().getConnection();
        boolean vouf = true;
        ArrayList<Cliente> clientearray = new ArrayList<>();
        do{
            String sql = "select * from Cliente where rid ="+restaurante.getRid();
            PreparedStatement stmt = con.prepareStatement(sql);   
            ResultSet rs = stmt.executeQuery();
            try {
                while (rs.next()){
                    String str = null;
                    String nome = rs.getString("nome");
                    String rua = rs.getString("rua");
                    String bairro = rs.getString("bairro");
                    int numero = rs.getInt("numero");
                    int cid = rs.getInt("cid");
                    int rid = rs.getInt("rid");
                    String com = rs.getString("complemento");
                    
                    cliente = new Cliente(nome,rua,bairro, numero, cid, rid, com);
                    cliente.setPedidos(pedidodao.getPedidos(cliente));
                    clientearray.add(cliente);
                    //System.out.println(str);
                    
                }
                return clientearray;
            } catch (Exception e) {
                System.out.println(e);
            }  
            vouf = false;
        }while(vouf);
        return null;
}
        //Função Incompleta Para Mostrar todos os cliente de determinado restaurante
        public int consultaCLienteCid() {
			String sql = "select c.cid from cliente c where cid>=all(select cid from cliente)";
			this.con =  new ConnectionFactory().getConnection();

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
				do{
					int cid = rs.getInt("cid");
					 
                                        
					int str = (cid);
					System.out.println(str);
					return cid;
				}
				while(rs.next());
				}
				else {
					System.out.println("CID Não Encontrado");
				}
		
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 1;
			
			
		}
}
