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

/**
 *
 * @author weera
 */
@Entity
@Table(name = "YEARS", catalog = "", schema = "OPERATOR")
@NamedQueries({
    @NamedQuery(name = "Years.findAll", query = "SELECT y FROM Years y")
    , @NamedQuery(name = "Years.findByYearid", query = "SELECT y FROM Years y WHERE y.yearid = :yearid")})
public class Years implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "YEARID")
    private Integer yearid;

    public Years() {
    }

    public Years(Integer yearid) {
        this.yearid = yearid;
    }

    public Integer getYearid() {
        return yearid;
    }

    public void setYearid(Integer yearid) {
        Integer oldYearid = this.yearid;
        this.yearid = yearid;
        changeSupport.firePropertyChange("yearid", oldYearid, yearid);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearid != null ? yearid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Years)) {
            return false;
        }
        Years other = (Years) object;
        if ((this.yearid == null && other.yearid != null) || (this.yearid != null && !this.yearid.equals(other.yearid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(yearid);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
