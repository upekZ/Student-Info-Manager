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
@Table(name = "STUDENT_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentLog.findAll", query = "SELECT s FROM StudentLog s")
    , @NamedQuery(name = "StudentLog.findByStudentId", query = "SELECT s FROM StudentLog s WHERE s.studentId = :studentId")
    , @NamedQuery(name = "StudentLog.findByStudentName", query = "SELECT s FROM StudentLog s WHERE s.studentName = :studentName")
    , @NamedQuery(name = "StudentLog.findByStudentPass", query = "SELECT s FROM StudentLog s WHERE s.studentPass = :studentPass")
    , @NamedQuery(name = "StudentLog.findByImage", query = "SELECT s FROM StudentLog s WHERE s.image = :image")
    , @NamedQuery(name = "StudentLog.findByEnrolledYear", query = "SELECT s FROM StudentLog s WHERE s.enrolledYear = :enrolledYear")})
public class StudentLog implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private Integer studentId;
    @Basic(optional = false)
    @Column(name = "STUDENT_NAME")
    private String studentName;
    @Basic(optional = false)
    @Column(name = "STUDENT_PASS")
    private String studentPass;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "ENROLLED_YEAR")
    private Integer enrolledYear;

    public StudentLog() {
    }

    public StudentLog(Integer studentId) {
        this.studentId = studentId;
    }

    public StudentLog(Integer studentId, String studentName, String studentPass) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPass = studentPass;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        Integer oldStudentId = this.studentId;
        this.studentId = studentId;
        changeSupport.firePropertyChange("studentId", oldStudentId, studentId);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        String oldStudentName = this.studentName;
        this.studentName = studentName;
        changeSupport.firePropertyChange("studentName", oldStudentName, studentName);
    }

    public String getStudentPass() {
        return studentPass;
    }

    public void setStudentPass(String studentPass) {
        String oldStudentPass = this.studentPass;
        this.studentPass = studentPass;
        changeSupport.firePropertyChange("studentPass", oldStudentPass, studentPass);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        String oldImage = this.image;
        this.image = image;
        changeSupport.firePropertyChange("image", oldImage, image);
    }

    public Integer getEnrolledYear() {
        return enrolledYear;
    }

    public void setEnrolledYear(Integer enrolledYear) {
        Integer oldEnrolledYear = this.enrolledYear;
        this.enrolledYear = enrolledYear;
        changeSupport.firePropertyChange("enrolledYear", oldEnrolledYear, enrolledYear);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentLog)) {
            return false;
        }
        StudentLog other = (StudentLog) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GUI.StudentLog[ studentId=" + studentId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
