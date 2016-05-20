package pl.pawww.hurt.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Tabela Orders(zamówienia)
 * @param id
 * @param dateEnd data zrealizowania zamówienia
 * @param dateStart data złożenia zamówienia
 * @param idShops
 * @param ordersProdutCollection spis wszystkich zamówionych produktów
 * @
 * @author r
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
    @NamedQuery(name = "Orders.findByRealized", query = "SELECT o FROM Orders o WHERE o.dateEnd IS NOT NULL"),
    @NamedQuery(name = "Orders.findByUnrealized", query = "SELECT o FROM Orders o WHERE o.dateEnd IS NULL")
})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @JoinColumn(name = "ID_SHOPS", referencedColumnName = "ID")
    @ManyToOne
    private Shops idShops;
    @OneToMany(mappedBy = "idOrder")
    private Collection<OrdersProdut> ordersProdutCollection;

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    
    
    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }
    @XmlAttribute
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    //@XmlTransient
    public Shops getIdShops() {
        return idShops;
    }

    public void setIdShops(Shops idShops) {
        this.idShops = idShops;
    }

    //@XmlTransient
    @XmlElementWrapper(name="produkty")
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    public void add(OrdersProdut ordersProdut){
        ordersProdutCollection.add(ordersProdut);
    }
    
    @Override
    public String toString() {
        return "pl.pawww.hurt.jpa.Orders[ id=" + id + " ]";
    }
    
}
