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
public class Sales implements Serializable{
 
    private Stock stock;
    private int shares;
    
    public Sales(){
        
    }
    public Sales(Stock stock, int shares){
        this.stock = stock;
        this.shares = shares;
    }
    
    public Stock getStock(){
        return stock;
    }
    
    public void setStock(Stock stock){
        this.stock = stock;
    }
    
    public int getShares(){
        return shares;
    }
    
    public void setShares(int shares){
        this.shares = shares;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Sales other = (Sales) obj;
        if (this.shares != other.shares) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return true;
    }
    
    public String toString(){
        
        return "Sale{" + "stock=" + stock + ", shares=" + shares + '}';
    }
    
    }
    
