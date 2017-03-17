/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrader;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Braden
 */
@Entity
@Table(name = "stocks", catalog = "daytraderdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stocks.findAll", query = "SELECT s FROM Stocks s")
    , @NamedQuery(name = "Stocks.findById", query = "SELECT s FROM Stocks s WHERE s.id = :id")
    , @NamedQuery(name = "Stocks.findByCurrentPrice", query = "SELECT s FROM Stocks s WHERE s.currentPrice = :currentPrice")
    , @NamedQuery(name = "Stocks.findBySymbol", query = "SELECT s FROM Stocks s WHERE s.symbol = :symbol")})
public class StocksDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "current_price")
    private double currentPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "symbol")
    private String symbol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockId")
    private List<PurchasesDB> purchasesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockId")
    private List<SalesDB> salesList;

    public StocksDB() {
    }

    public StocksDB(Integer id) {
        this.id = id;
    }

    public StocksDB(Integer id, double currentPrice, String symbol) {
        this.id = id;
        this.currentPrice = currentPrice;
        this.symbol = symbol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @XmlTransient
    public List<PurchasesDB> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<PurchasesDB> purchasesList) {
        this.purchasesList = purchasesList;
    }

    @XmlTransient
    public List<SalesDB> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<SalesDB> salesList) {
        this.salesList = salesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StocksDB)) {
            return false;
        }
        StocksDB other = (StocksDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mytrader.Stocks[ id=" + id + " ]";
    }
    
}
