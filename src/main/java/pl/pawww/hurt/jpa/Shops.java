/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawww.hurt.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Tabel Shops(sklepy)
 *
 * @param id (powienien być NIP, lecz id jest krótsze)
 * @param sklep nazwa sklepu
 * @param adres adres sklepu Powinno być więcej informacji o sklepie lecz w
 * logice bazy nie są one istotne
 * @author r
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shops.findAll", query = "SELECT s FROM Shops s"),
    @NamedQuery(name = "Shops.findById", query = "SELECT s FROM Shops s WHERE s.id = :id"),
    @NamedQuery(name = "Shops.findByAdres", query = "SELECT s FROM Shops s WHERE s.adres = :adres"),
    @NamedQuery(name = "Shops.findBySklep", query = "SELECT s FROM Shops s WHERE s.sklep LIKE :sklep")})
public class Shops implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 255)
    private String adres;
    @Size(max = 255)
    private String sklep;
    @OneToMany(mappedBy = "idShops")
    private Collection<Orders> ordersCollection;

    public Shops() {
    }

    public Shops(Integer id) {
        this.id = id;
    }

    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSklep() {
        return sklep;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setSklep(String sklep) {
        this.sklep = sklep;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
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
        if (!(object instanceof Shops)) {
            return false;
        }
        Shops other = (Shops) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.pawww.hurt.jpa.Shops[ id=" + id + " ]";
    }

}
