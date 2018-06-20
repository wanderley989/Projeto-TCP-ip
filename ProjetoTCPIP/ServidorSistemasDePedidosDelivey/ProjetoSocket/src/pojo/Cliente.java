package pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
    
    private String nome;
    private String rua;
    private String bairro;
    private int numero,id_cliente,id_restaurante;
    ArrayList<Pedido> arraypedido = new ArrayList<Pedido>();
    private String complemento;
    
    
    public Cliente(){
    }
    
    public Cliente(String nome,String rua,String bairro,int numero, String complemento,int  id_restaurante){
        this.bairro = bairro;
        this.nome = nome;
        this.complemento = complemento;
        this.numero = numero;
        this.rua = rua;
        this.id_restaurante = id_restaurante;
    }
    
    public Cliente(String nome,String rua,String bairro,int numero,int id_cliente,int id_restaurante,String complemento){
        this.nome = nome;
        this.rua = rua;
        this.bairro =bairro;
        this.numero = numero;
        this.id_cliente = id_cliente;
        this.id_restaurante = id_restaurante;
        this.complemento = complemento;
    }
    
    
    /*gets*/
     public ArrayList<Pedido> getPedidos(){
        return arraypedido;
    }
    public  String getComplemento(){
        return complemento;
    }
    public int getIdRestaurante(){
        return id_restaurante;
    }
    public String getNome(){
        return nome;
    }
    public String getBairro(){
        return bairro;
    }
    public String getRua(){
        return rua;
    }
    public int getNumero(){
        return numero;
    }
    public int getIdCliente(){
        return id_cliente;
    }
    /*sets*/
    public  void setComplemento(String complemento){
        complemento = complemento;
    }
    public void setRid(int id_restaurante){
        this.id_restaurante = id_restaurante;
    }
    public void setPedido(Pedido p){
        arraypedido.add(p);
    }
    public void setPedidos(ArrayList<Pedido> ps){
        arraypedido = ps;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    public void setRua(String rua){
        this.rua = rua;
    }
    public void setIdCliente(int Id_cliente){
        this.id_cliente = Id_cliente;
    }
    public void setNumero(int num){
        this.numero = num;
    }
 
    public String toString(){
        String str;
        str = ("Nome: "+nome+" Rua: "+rua+" Bairro: "+bairro+" Numero: "+numero+" Cid: "+id_cliente+" Rid: "+id_restaurante+" Complemento: "+complemento);
        return str;      
    }
}

