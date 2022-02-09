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
@Table(name = "PAYMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p")
    , @NamedQuery(name = "Payments.findByPaidId", query = "SELECT p FROM Payments p WHERE p.paidId = :paidId")
    , @NamedQuery(name = "Payments.findByName", query = "SELECT p FROM Payments p WHERE p.name = :name")
    , @NamedQuery(name = "Payments.findByStatus", query = "SELECT p FROM Payments p WHERE p.status = :status")
    , @NamedQuery(name = "Payments.findByYearLabel", query = "SELECT p FROM Payments p WHERE p.yearLabel = :yearLabel")
    , @NamedQuery(name = "Payments.findByStudentNumber", query = "SELECT p FROM Payments p WHERE p.studentNumber = :studentNumber")})
public class Payments implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAID_ID")
    private Integer paidId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATUS")
    private Boolean status;
    @Column(name = "YEAR_LABEL")
    private String yearLabel;
    @Column(name = "STUDENT_NUMBER")
    private Integer studentNumber;

    public Payments() {
    }

    public Payments(Integer paidId) {
        this.paidId = paidId;
    }

    public Integer getPaidId() {
        return paidId;
    }

    public void setPaidId(Integer paidId) {
        Integer oldPaidId = this.paidId;
        this.paidId = paidId;
        changeSupport.firePropertyChange("paidId", oldPaidId, paidId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        Boolean oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    public String getYearLabel() {
        return yearLabel;
    }

    public void setYearLabel(String yearLabel) {
        String oldYearLabel = this.yearLabel;
        this.yearLabel = yearLabel;
        changeSupport.firePropertyChange("yearLabel", oldYearLabel, yearLabel);
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        Integer oldStudentNumber = this.studentNumber;
        this.studentNumber = studentNumber;
        changeSupport.firePropertyChange("studentNumber", oldStudentNumber, studentNumber);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paidId != null ? paidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.paidId == null && other.paidId != null) || (this.paidId != null && !this.paidId.equals(other.paidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.Payments[ paidId=" + paidId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
