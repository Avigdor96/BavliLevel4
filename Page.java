package Level4;

public class Page {
    private String name;
    private String path;
    private boolean ifMishna;


    public Page(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isIfMishna() {
        return ifMishna;
    }

    public void setIfMishna(boolean ifMishna) {
        this.ifMishna = ifMishna;
    }
}
