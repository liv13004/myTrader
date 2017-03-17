/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrader.client;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Braden
 */
public class Purchase implements Serializable{
    private Stock stock;
    private int shares;
    
    public Purchase(){
        
    }

    Purchase(Stock stock, int shares) {
      this.stock = stock;
      this.shares = shares;
    }

    public Stock getStock() {
       return stock;
    }
    
    public void setStock(){
        this.stock = stock;
    }

    public int getShares() {
        return shares;
    }
    
    public void setShares(){
        this.shares = shares;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.stock);
        hash = 23 * hash + this.shares;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (this.shares != other.shares) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "Purchase{" + "stock=" + stock + ", shares=" + shares + '}';
    }
}
