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
@Table(name = "MODULES", catalog = "", schema = "OPERATOR")
@NamedQueries({
    @NamedQuery(name = "Modules.findAll", query = "SELECT m FROM Modules m")
    , @NamedQuery(name = "Modules.findByModuleId", query = "SELECT m FROM Modules m WHERE m.moduleId = :moduleId")
    , @NamedQuery(name = "Modules.findByModuleName", query = "SELECT m FROM Modules m WHERE m.moduleName = :moduleName")})
public class Modules implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MODULE_ID")
    private String moduleId;
    @Basic(optional = false)
    @Column(name = "MODULE_NAME")
    private String moduleName;

    public Modules() {
    }

    public Modules(String moduleId) {
        this.moduleId = moduleId;
    }

    public Modules(String moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        String oldModuleId = this.moduleId;
        this.moduleId = moduleId;
        changeSupport.firePropertyChange("moduleId", oldModuleId, moduleId);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        String oldModuleName = this.moduleName;
        this.moduleName = moduleName;
        changeSupport.firePropertyChange("moduleName", oldModuleName, moduleName);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduleId != null ? moduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modules)) {
            return false;
        }
        Modules other = (Modules) object;
        if ((this.moduleId == null && other.moduleId != null) || (this.moduleId != null && !this.moduleId.equals(other.moduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return moduleName;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
    
    
}
