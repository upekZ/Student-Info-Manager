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
@Table(name = "ADMIN_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminLog.findAll", query = "SELECT a FROM AdminLog a")
    , @NamedQuery(name = "AdminLog.findByAdminId", query = "SELECT a FROM AdminLog a WHERE a.adminId = :adminId")
    , @NamedQuery(name = "AdminLog.findByAdminName", query = "SELECT a FROM AdminLog a WHERE a.adminName = :adminName")
    , @NamedQuery(name = "AdminLog.findByAdminPass", query = "SELECT a FROM AdminLog a WHERE a.adminPass = :adminPass")})
public class AdminLog implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ADMIN_ID")
    private Integer adminId;
    @Basic(optional = false)
    @Column(name = "ADMIN_NAME")
    private String adminName;
    @Basic(optional = false)
    @Column(name = "ADMIN_PASS")
    private String adminPass;

    public AdminLog() {
    }

    public AdminLog(Integer adminId) {
        this.adminId = adminId;
    }

    public AdminLog(Integer adminId, String adminName, String adminPass) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPass = adminPass;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        Integer oldAdminId = this.adminId;
        this.adminId = adminId;
        changeSupport.firePropertyChange("adminId", oldAdminId, adminId);
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        String oldAdminName = this.adminName;
        this.adminName = adminName;
        changeSupport.firePropertyChange("adminName", oldAdminName, adminName);
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        String oldAdminPass = this.adminPass;
        this.adminPass = adminPass;
        changeSupport.firePropertyChange("adminPass", oldAdminPass, adminPass);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminId != null ? adminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminLog)) {
            return false;
        }
        AdminLog other = (AdminLog) object;
        if ((this.adminId == null && other.adminId != null) || (this.adminId != null && !this.adminId.equals(other.adminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.AdminLog[ adminId=" + adminId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
