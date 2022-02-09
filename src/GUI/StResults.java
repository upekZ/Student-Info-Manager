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
@Table(name = "ST_RESULTS", catalog = "", schema = "OPERATOR")
@NamedQueries({
    @NamedQuery(name = "StResults.findAll", query = "SELECT s FROM StResults s")
    , @NamedQuery(name = "StResults.findByEntryNumber", query = "SELECT s FROM StResults s WHERE s.entryNumber = :entryNumber")
    , @NamedQuery(name = "StResults.findByStId", query = "SELECT s FROM StResults s WHERE s.stId = :stId")
    , @NamedQuery(name = "StResults.findByModule", query = "SELECT s FROM StResults s WHERE s.module = :module")
    , @NamedQuery(name = "StResults.findByGrade", query = "SELECT s FROM StResults s WHERE s.grade = :grade")})
public class StResults implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ENTRY_NUMBER")
    private Integer entryNumber;
    @Basic(optional = false)
    @Column(name = "ST_ID")
    private int stId;
    @Basic(optional = false)
    @Column(name = "MODULE")
    private String module;
    @Basic(optional = false)
    @Column(name = "GRADE")
    private String grade;

    public StResults() {
    }

    public StResults(Integer entryNumber) {
        this.entryNumber = entryNumber;
    }

    public StResults(Integer entryNumber, int stId, String module, String grade) {
        this.entryNumber = entryNumber;
        this.stId = stId;
        this.module = module;
        this.grade = grade;
    }

    public Integer getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(Integer entryNumber) {
        Integer oldEntryNumber = this.entryNumber;
        this.entryNumber = entryNumber;
        changeSupport.firePropertyChange("entryNumber", oldEntryNumber, entryNumber);
    }

    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        int oldStId = this.stId;
        this.stId = stId;
        changeSupport.firePropertyChange("stId", oldStId, stId);
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        String oldModule = this.module;
        this.module = module;
        changeSupport.firePropertyChange("module", oldModule, module);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        String oldGrade = this.grade;
        this.grade = grade;
        changeSupport.firePropertyChange("grade", oldGrade, grade);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entryNumber != null ? entryNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StResults)) {
            return false;
        }
        StResults other = (StResults) object;
        if ((this.entryNumber == null && other.entryNumber != null) || (this.entryNumber != null && !this.entryNumber.equals(other.entryNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.StResults[ entryNumber=" + entryNumber + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
