/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvit;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author umash
 */
@Entity
@Table(name = "STU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stu.findAll", query = "SELECT s FROM Stu s")
    , @NamedQuery(name = "Stu.findByUsn", query = "SELECT s FROM Stu s WHERE s.usn = :usn")
    , @NamedQuery(name = "Stu.findByName", query = "SELECT s FROM Stu s WHERE s.name = :name")
    , @NamedQuery(name = "Stu.findByBranch", query = "SELECT s FROM Stu s WHERE s.branch = :branch")})
public class Stu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USN")
    private String usn;
    @Size(max = 20)
    @Column(name = "NAME")
    private String name;
    @Size(max = 3)
    @Column(name = "BRANCH")
    private String branch;

    public Stu() {
    }

    public Stu(String usn) {
        this.usn = usn;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usn != null ? usn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stu)) {
            return false;
        }
        Stu other = (Stu) object;
        if ((this.usn == null && other.usn != null) || (this.usn != null && !this.usn.equals(other.usn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mvit.Stu[ usn=" + usn + " ]";
    }
    
}
