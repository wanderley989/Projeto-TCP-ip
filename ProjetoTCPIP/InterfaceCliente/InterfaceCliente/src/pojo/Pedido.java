
package pojo;

/**
 *
 * @author helter
 */
import java.io.Serializable;


public class Pedido implements Serializable{
    
    private int cid;
    private int pid;
    private int qtd;
    
    public Pedido(int pid,int qtd){
        this.pid = pid;
        this.qtd = qtd;
    }
    public Pedido(int pid,int cid,int qtd){
        this.pid = pid;
        this.cid = cid;
        this.qtd = qtd;
    }

    /**
     * @return the cid
     */
    public int getCid() {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(int cid) {
        this.cid = cid;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
 
    @Override
    public String toString(){
        String str;
        str ="IDcliente:"+cid+" IDprato:"+pid+" Qtd:"+qtd;
        return str;
    }
}
