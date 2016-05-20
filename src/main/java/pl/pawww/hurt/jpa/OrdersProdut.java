/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.jpa;

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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Tabela OrderdsProducts
 * Pośredniczy pomiędzy Orders, a Products
 * Orders<==>Products
 * Orders1==>OrdersProdut<==1Products
 * @param id
 * @param idOrder
 * @param idProduct
 * @param liczbaSztuk liczba sztuk produktu, które zażądało zamówienie
 * @author r
 */
@Entity
@Table(name = "ORDERS_PRODUT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersProdut.findAll", query = "SELECT o FROM OrdersProdut o"),
    @NamedQuery(name = "OrdersProdut.findById", query = "SELECT o FROM OrdersProdut o WHERE o.id = :id")})
public class OrdersProdut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID")
    @ManyToOne
    private Orders idOrder;
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID")
    @ManyToOne
    private Products idProduct;
    @Column(name = "LICZBA_SZTUK")
    private Integer liczbaSztuk;

    public Integer getLiczbaSztuk() {
        return liczbaSztuk;
    }
    
    public void setLiczbaSztuk(Integer liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }
    
    
    public OrdersProdut() {
    }

    public OrdersProdut(Integer id) {
        this.id = id;
    }
    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @XmlTransient
    public Orders getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Orders idOrder) {
        this.idOrder = idOrder;
    }

    public Products getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Products idProduct) {
        this.idProduct = idProduct;
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
        if (!(object instanceof OrdersProdut)) {
            return false;
        }
        OrdersProdut other = (OrdersProdut) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurt.jpa.OrdersProdut[ id=" + id + " ]";
    }
    
}
