/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import sun.net.smtp.SmtpClient;

public class PedidoDAO {
        Pedido pedido1;
         Scanner entrada = new Scanner(System.in);
         private Connection connection;
         private Connection con;
         Cliente cliente = new Cliente();
        
         //Função para selecionar todos os pedidos do cliente;
        public ArrayList<Pedido> getPedidos(Cliente cliente) throws SQLException{
        this.con =  new ConnectionFactory().getConnection();
        boolean vouf = true;
        ArrayList<Pedido> pedidoarray = new ArrayList<>();
        do{
            String sql = "select * from Pedido where cid = "+cliente.getIdCliente();
            PreparedStatement stmt = con.prepareStatement(sql);   
            ResultSet rs = stmt.executeQuery();
            
            try {
                while (rs.next()){
                    String str = null;
                    
                    int pid = rs.getInt("pid");
                    int cid = rs.getInt("cid");
                    int qtd = rs.getInt("qtd");
                                       
                                        
                    pedido1 = new Pedido(pid,cid,qtd);
                    pedidoarray.add(pedido1);
                    System.out.println(str);
                }
                return pedidoarray;
            } catch (Exception e) {
                System.out.println(e);
            }  
            vouf = false;
        }while(vouf);
        return null;
}
      //Função Para deletar pedido do cliente no Banco de Dados
        public boolean delPedido(int idcliente) {
	String sql = "Delete from Pedido where cid = "+cliente.getIdCliente();	
	
	this.con =  new ConnectionFactory().getConnection();
	
	try{
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1,idcliente);
		
		int qtdRowsAffected = stmt.executeUpdate();
		stmt.close();
		if(qtdRowsAffected > 0) {
			System.out.println("Pedido Removido");
			return true;
		}
		System.out.println("Erro ao Remover, Pedido não no encontrado no Banco");
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
        //Função Para Inserir pedido do cliente no Banco de Dados
        	public boolean addPedido(Pedido pedido){
                //enviar cliente para o banco de dados
		String sql = "INSERT INTO Pedido (pid,cid,qtd) VALUES (?,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, pedido.getPid());
			stmt.setInt(2, pedido.getCid());
			stmt.setInt(3, pedido.getQtd());
			
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdRowsAffected > 0) {
				System.out.println("Pedido Adicionado");
				
                                return true;
			}
			System.out.println("Erro ao adicionar Pedido");
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
}
