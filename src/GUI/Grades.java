/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author weera
 */
@Entity
@Table(name = "GRADES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grades.findAll", query = "SELECT g FROM Grades g")
    , @NamedQuery(name = "Grades.findBySymbol", query = "SELECT g FROM Grades g WHERE g.symbol = :symbol")})
public class Grades implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SYMBOL")
    private String symbol;

    public Grades() {
    }

    public Grades(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        String oldSymbol = this.symbol;
        this.symbol = symbol;
        changeSupport.firePropertyChange("symbol", oldSymbol, symbol);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (symbol != null ? symbol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grades)) {
            return false;
        }
        Grades other = (Grades) object;
        if ((this.symbol == null && other.symbol != null) || (this.symbol != null && !this.symbol.equals(other.symbol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
