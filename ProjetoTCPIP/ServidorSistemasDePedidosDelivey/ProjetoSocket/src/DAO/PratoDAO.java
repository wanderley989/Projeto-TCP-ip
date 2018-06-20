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
import pojo.Restaurante;
public class PratoDAO {
    Prato prato1;
    Scanner entrada = new Scanner(System.in);    
    private Connection connection;
    private Connection con;
//Função Para Adicionar um prato no Banco de Dados
	public boolean addPrato(Prato prato){
		String sql = "INSERT INTO Prato(nome,preco,pid,rid) VALUES (?,?,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, prato.getNome());
			stmt.setDouble(2, prato.getPreco());
			stmt.setInt(3,prato.getPid());
			stmt.setInt(4,prato.getRid());
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdRowsAffected > 0) {
				System.out.println("Prato Adicionado");
				return true;
			}
			System.out.println("Erro ao adicionar");
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
        
        //Função para Mostrar todos os pratos
        public String consultaPrato(int idPrato) {
			String sql = "SELECT * FROM Prato where pid = ?";
			this.con =  new ConnectionFactory().getConnection();

			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1,idPrato);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
				do{
					String str = null;
					String nome = rs.getString("nome");
					Double preco = rs.getDouble("preco");
					int pid = rs.getInt("pid");
					
					str = ("Nome:"+nome+" preço: " +preco+" Pid: "+pid);
					System.out.println(str);
					return str;
				}
				while(rs.next());
				}
				else {
					System.out.println("Prato Não Encontrado");
				}
		
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
			
			
		}
        //Função Para Deletar um prato de um restaurante específico
        public boolean delPrato(Prato prato,Restaurante restaurante) {
	String sql = "Delete from Prato where pid = ? and rid = ?";	
	
	this.con =  new ConnectionFactory().getConnection();
	
	try{
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1,prato.getPid());
		stmt.setInt(2,restaurante.getRid());
		int qtdRowsAffected = stmt.executeUpdate();
		stmt.close();
		if(qtdRowsAffected > 0) {
			System.out.println("Prato Removido");
			return true;
		}
		System.out.println("Erro ao Remover, Prato não no encontrado no Banco");
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
        
        //Função Para Mostrar Todos os pratos de um restaurante
        public ArrayList<Prato> getPratos(Restaurante restaurante) throws SQLException{
        this.con =  new ConnectionFactory().getConnection();
        boolean vouf = true;
        ArrayList<Prato> pratoarray = new ArrayList<>();
        do{
            String sql = "select * from Prato where rid = "+restaurante.getRid();
            PreparedStatement stmt = con.prepareStatement(sql);   
            ResultSet rs = stmt.executeQuery();
            try {
                while (rs.next()){
                    String str = null;
                    String nome = rs.getString("nome");
                    Double preco = rs.getDouble("preco");
                    int pid = rs.getInt("pid");
                    int rid = rs.getInt("rid");
                     prato1 = new Prato(nome,preco,pid,rid);
                    pratoarray.add(prato1);
           
                }
                return pratoarray;
            } catch (Exception e) {
                System.out.println(e);
            }  
            vouf = false;
        }while(vouf);
        return null;
}
}
