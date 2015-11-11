
package beans;

/**
 *
 * @author Dimi
 */
public class Korisnik 
{
    int id;
    String user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    String pass;
    String ime;
    String prezime;
    int godRodj;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getGodRodj() {
        return godRodj;
    }

    public void setGodRodj(int godRodj) {
        this.godRodj = godRodj;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
    boolean student;
    boolean blokiran;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
