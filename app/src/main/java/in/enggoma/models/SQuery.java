package in.enggoma.models;

public class SQuery {

    String stream, semester, subject, type;


    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public void clear() {
        this.stream = "";
        this.semester = "";
        this.subject = "";
        this.type = "";
    }

}
