package note;

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


@Entity
@Table(name = "Note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n") 
    , @NamedQuery(name = "Note.findById", query = "SELECT n FROM Note n WHERE n.id = :id")
    , @NamedQuery(name = "Note.viewNotes", query = "SELECT n FROM Note n WHERE n.email = :email")          
    , @NamedQuery(name = "Note.existsNote", query = "SELECT n FROM Note n WHERE n.id = :id")      
    , @NamedQuery(name = "Note.searchNote", query = "SELECT n FROM Note n WHERE n.id = :id")   
    , @NamedQuery(name = "Note.deleteAllNotesFromUser", query = "SELECT n FROM Note n WHERE n.email = :email")         
    , @NamedQuery(name = "Note.findByContent", query = "SELECT n FROM Note n WHERE n.content = :content")
    , @NamedQuery(name = "Note.deleteNote", query = "DELETE FROM Note n WHERE n.id = :id")
    , @NamedQuery(name = "Note.checkNoteOwner", query = "SELECT n FROM Note n WHERE n.id = :id AND n.email = :email")    
    , @NamedQuery(name = "Note.countNotes", query = "SELECT n FROM Note n WHERE n.email = :email")   
    , @NamedQuery(name = "Note.modifyNote", query = "UPDATE Note n SET n.content = :content WHERE n.id = :id")         
    , @NamedQuery(name = "Note.findByEmail", query = "SELECT n FROM Note n WHERE n.email = :email")})

public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "content")
    private String content;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(Integer id, String content, String email) {
        this.id += id;
        this.content = content;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id = " + id + " Content = " + content + "\n";
    }
    
    
    
}
