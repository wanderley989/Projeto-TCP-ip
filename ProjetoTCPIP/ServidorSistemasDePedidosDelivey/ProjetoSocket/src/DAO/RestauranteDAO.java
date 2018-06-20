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
public class RestauranteDAO {
    Restaurante restaurante1;
    Scanner entrada = new Scanner(System.in);
    private Connection connection;
    private Connection con;
    ClienteDAO clientedao = new ClienteDAO();
    PedidoDAO pedidodao = new PedidoDAO();
    //Função Para Inserir um Novo Restaurante ao Banco de Dados
    public boolean addRestaurante(Restaurante restaurante){
                //Envia os dados do restaurante ao Banco
		String sql = "INSERT INTO Restaurante(rnome,rua,bairro,numero,tipo,rid,senha,login) VALUES (?,?,?,?,?,?,?,?)";
		this.connection = new ConnectionFactory().getConnection();
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, restaurante.getNome());
			stmt.setString(2, restaurante.getRua());
			stmt.setString(3, restaurante.getBairro());
			stmt.setInt(4, restaurante.getNumero());
			stmt.setString(5, restaurante.getTipo());
			stmt.setInt(6, restaurante.getRid());
			stmt.setString(7, restaurante.getSenha());
                        stmt.setString(8, restaurante.getLogin());
			int qtdRowsAffected = stmt.executeUpdate();
			stmt.close();
			if(qtdRowsAffected > 0) {
				System.out.println("Restaurante Adicionado");
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
        
        
        //Função Para Atualizar dados do restaurante no Banco de Dados
        public boolean atualizaRestaurante(Restaurante restaurante1) {
		String sql = "UPDATE Restaurante SET nome = ?, rua = ?, bairro = ?, numero = ?,tipo = ?,senha = ?,login = ? where rid = ?";
		this.con =  new ConnectionFactory().getConnection();
                try {
                        PreparedStatement stmt = con.prepareStatement(sql);
                        stmt.setString(1,restaurante1.getNome());
                        stmt.setString(2, restaurante1.getRua());
                        stmt.setString(3, restaurante1.getBairro());
                        stmt.setInt(4, restaurante1.getNumero());
                        stmt.setString(5, restaurante1.getTipo());
                        stmt.setString(6,restaurante1.getSenha());
                        stmt.setString(7,restaurante1.getLogin());
                        stmt.setInt(8,restaurante1.getRid());
                        int qtdRowsAffected = stmt.executeUpdate();
                        stmt.close();

                        if(qtdRowsAffected > 0) {
                                System.out.println("Restaurante Atualizado com sucesso");
                                return true;
                        }
                        else {
                                System.out.println("Numero Restaurante Inexistente");
                        }
                }			
                catch (SQLException e) {
                        System.out.println("Numero Restaurante com Erro"+e);
                }
                return false;
}
				
			
       //Função Para Mostrar todos os restaurantes cadastrados no Banco ao cliente 
        public ArrayList<Restaurante> getRestaurantes() throws SQLException{
        this.con =  new ConnectionFactory().getConnection();
        boolean vouf = true;
        ArrayList<Restaurante> restaurantearray = new ArrayList<>();
        do{
            String sql = "select * from restaurante ";
            PreparedStatement stmt = con.prepareStatement(sql);   
            ResultSet rs = stmt.executeQuery();
            try {
                while (rs.next()){
                    
                    String nome = rs.getString("nome");
                    String rua = rs.getString("rua");
                    String bairro = rs.getString("bairro");
                    int numero = rs.getInt("numero");
                    String tipo = rs.getString("tipo");
                    int rid = rs.getInt("rid");
                    String login = rs.getString("Login");
                    String senha = rs.getString("Senha");
                    
                    Restaurante restaurante1 = new Restaurante(nome, rua, bairro, numero,rid,tipo, senha, login);
                    ;
                    restaurantearray.add(restaurante1);
                    
                }
                return restaurantearray;
            } catch (Exception e) {
                System.out.println(e);
            }  
            vouf = false;
        }while(vouf);
        return null;
}
        
        
}

