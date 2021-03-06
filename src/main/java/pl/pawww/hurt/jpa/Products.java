/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Tabela Products(produkty)
 *
 * @param id
 * @param cena
 * @param liczbaSztuk całkowita ilość produktu
 * @param kategoria oczywistym jest, że powinno być idKategoria, a kategorie
 * powinny być w oddzielnej tabeli
 * @param nazwa
 * @param ordersProdutCollection id do którego zamówienia przedmiot ma iść
 * @author r
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id"),
    @NamedQuery(name = "Products.findByCena", query = "SELECT p FROM Products p WHERE p.cena = :cena"),
    @NamedQuery(name = "Products.findByKategoria", query = "SELECT p FROM Products p WHERE p.kategoria = :kategoria"),
    @NamedQuery(name = "Products.findByLiczbaSztuk", query = "SELECT p FROM Products p WHERE p.liczbaSztuk = :liczbaSztuk"),
    @NamedQuery(name = "Products.findByNazwa", query = "SELECT p FROM Products p WHERE p.nazwa LIKE :nazwa")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    private BigDecimal cena;
    @Size(max = 255)
    private String kategoria;
    @Column(name = "LICZBA_SZTUK")
    private Integer liczbaSztuk;
    @Size(max = 255)
    private String nazwa;
    @OneToMany(mappedBy = "idProduct")
    private Collection<OrdersProdut> ordersProdutCollection;

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }
    
    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public BigDecimal getCena() {
        return cena;
    }
    @XmlElement
    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    @XmlAttribute
    public String getKategoria() {
        return kategoria;
    }
    @XmlAttribute
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    @XmlTransient
    public Integer getLiczbaSztuk() {
        return liczbaSztuk;
    }
    
    public void setLiczbaSztuk(Integer liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }
    @XmlElement
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Collection<OrdersProdut> getOrdersProdutCollection() {
        return ordersProdutCollection;
    }

    public void setOrdersProdutCollection(Collection<OrdersProdut> ordersProdutCollection) {
        this.ordersProdutCollection = ordersProdutCollection;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurt.jpa.Products[ id=" + id + " ]";
    }

}
