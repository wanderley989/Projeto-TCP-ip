package pojo;

import java.io.Serializable;

public class Prato implements Serializable {
    private int pid;
    private String pnome;
    private Double preco;
    private int rid;

    public Prato(String pnome, Double preco,int pid, int rid) {
        this.pnome = pnome;
        this.preco = preco;
        this.pid = pid;
        this.rid = rid;
    }
public Prato(){
    }
    public Prato(String nome,double preco,int pid){
        this.pid = pid;
        this.pnome = nome;
        this.preco =preco;
    }
    /*gets*/
    public int getPid(){
        return pid;
    }
    public double getPreco(){
        return preco;
    }
    public String getNome(){
        return pnome;
    }
    /*sets*/
    public void setNome(String nome){
        this.pnome = nome;
    }
    public void setPid(int pid){
        this.pid = pid;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
     public int getRid() {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(int rid) {
        this.rid = rid;
    }
    
    public String toString(){
		String str;
		str = "nome: "+pnome+" Preco: "+preco+" pid: "+pid+" Rid: "+rid;
    	return str;
    	
    }

    /**
     * @return the rid
     */
   
}


