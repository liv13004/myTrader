/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytrader;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Braden
 */
@Entity
@Table(name = "purchases", catalog = "daytraderdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchases.findAll", query = "SELECT p FROM Purchases p")
    , @NamedQuery(name = "Purchases.findById", query = "SELECT p FROM Purchases p WHERE p.id = :id")
    , @NamedQuery(name = "Purchases.findByPrice", query = "SELECT p FROM Purchases p WHERE p.price = :price")
    , @NamedQuery(name = "Purchases.findByNumberShares", query = "SELECT p FROM Purchases p WHERE p.numberShares = :numberShares")})
public class PurchasesDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_shares")
    private int numberShares;
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StocksDB stockId;

    public PurchasesDB() {
    }

    public PurchasesDB(Integer id) {
        this.id = id;
    }

    public PurchasesDB(Integer id, double price, int numberShares) {
        this.id = id;
        this.price = price;
        this.numberShares = numberShares;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberShares() {
        return numberShares;
    }

    public void setNumberShares(int numberShares) {
        this.numberShares = numberShares;
    }

    public StocksDB getStockId() {
        return stockId;
    }

    public void setStockId(StocksDB stockId) {
        this.stockId = stockId;
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
        if (!(object instanceof PurchasesDB)) {
            return false;
        }
        PurchasesDB other = (PurchasesDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mytrader.Purchases[ id=" + id + " ]";
    }
    
}
