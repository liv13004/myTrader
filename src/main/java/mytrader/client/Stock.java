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
public class Stock implements Serializable {

    private String symbol;       
    private Double openingValue; 
    private Double lastValue;    
    private Double changeValue;  
    private Double changePercent;
    
    public Stock(Stock stock){
        this.symbol = stock.symbol;
        this.openingValue = stock.openingValue;
        this.lastValue = stock.lastValue;
        this.changeValue = stock.changeValue;
        this.changePercent = stock.changePercent;
    }
    
    

    public String getSymbol() {
     return symbol;
    }
    
    public void setSymbol(String symbol){
        this.symbol=symbol;
    }
    
    public Double getOpeningValue(){
        return openingValue;
    }
    
    public void setOpeningValue(){
        this.openingValue = openingValue;
    }
    
    public Double getLastValue() {
       return lastValue;
    }
    
    public void setLastValue(){
        this.lastValue = lastValue;
    }
    
    public Double getChangeValue(){
        return changeValue;
    }
    
    public void setChangeValue(Double changeValue){
            this.changeValue = changeValue;
    }
    
    public Double getChangePercent(){
        return changePercent;
    }
            
    public void setChangePercent(Double changePercent){
        this.changePercent = changePercent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.symbol);
        hash = 23 * hash + Objects.hashCode(this.openingValue);
        hash = 23 * hash + Objects.hashCode(this.lastValue);
        hash = 23 * hash + Objects.hashCode(this.changeValue);
        hash = 23 * hash + Objects.hashCode(this.changePercent);
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
        final Stock other = (Stock) obj;
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.openingValue, other.openingValue)) {
            return false;
        }
        if (!Objects.equals(this.lastValue, other.lastValue)) {
            return false;
        }
        if (!Objects.equals(this.changeValue, other.changeValue)) {
            return false;
        }
        if (!Objects.equals(this.changePercent, other.changePercent)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Stock{" + "symbol=" + symbol + ", openingValue=" + openingValue + ", lastValue=" + lastValue + ", changeValue=" + changeValue + ", changePercent=" + changePercent + '}';
    } 
    
}

