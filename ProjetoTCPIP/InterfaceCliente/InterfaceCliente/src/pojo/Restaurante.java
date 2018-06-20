package pojo;

import java.io.Serializable;

public class Restaurante implements Serializable {
    private String rnome, rua, bairro,tipo,senha,login;
    private int numero,rid;
    
    public Restaurante(){
    }
    
    public Restaurante(String nome,String rua,String bairro,int numero,String tipo,String senha,String login){
        this.bairro = bairro;
        this.login = login;
        this.senha = senha;
        this.rnome = nome;
        this.rua = rua;
        this.tipo = tipo;
        this.numero = numero;
    }
    public Restaurante(String nome,String rua,String bairro,int numero,int rid,String tipo,String senha,String login){
        this.bairro = bairro;
        this.login = login;
        this.senha = senha;
        this.rnome = nome;
        this.rua = rua;
        this.rid = rid;
        this.tipo = tipo;
        this.numero = numero;
    }

    public Restaurante(String nome,String rua,String bairro,int numero,String tipo,int rid){
        this.bairro = bairro;
        this.rid = rid;
        this.rnome = nome;
        this.rua = rua;
        this.tipo = tipo;
        this.numero = numero;
    }

    
    /*gets*/
    public String getNome(){
        return rnome;
    }
    public String getLogin(){
        return login;
    }
    public String getSenha(){
        return senha;
    }
    public String getRua(){
        return rua;
    }
    public String getBairro(){
        return bairro;
    }
    public String getTipo(){
        return tipo;
    }
     public int getNumero(){
        return numero;
    }
      public int getRid(){
        return rid;
    }
    /*sets*/
    public void setNome(String nome){
        this.rnome = nome;
    }
    public void setRua(String rua){
        this.rua = rua;
    }
     public void setLogin(String login){
        this.login = login;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setBairro(String bairro){
        this.bairro = bairro;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
     public void setNumero(int num){
        this.numero = num;
    }
      public void setRid(int rid){
        this.rid = rid;
    }
      /*funcs*/
    public String toString(){
    String str;
    str = "Nome:"+rnome+" Rua:"+rua+" Bairro:"+bairro+" Numero;"+numero+" Tipo:"+tipo+" Rid:"+rid;
    return str;
    }
}

