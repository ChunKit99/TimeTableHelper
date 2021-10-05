
package model;

/**
 *
 * @author Liew Chun Kit
 */
public class Section {

    public String id;//section id,example 01A 01B
    private Subject subject;

    public Section(String id, Subject subject) {
        this.id = id;
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
}
