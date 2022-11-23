package MODEL.OOP;

public class Kichthuoc {
    int makichthuoc;
    String tenkichthuoc;


    public Kichthuoc() {
    }

    public Kichthuoc(int makichthuoc, String tenkichthuoc) {
        this.makichthuoc = makichthuoc;
        this.tenkichthuoc = tenkichthuoc;
    }

    public int getMakichthuoc() {
        return makichthuoc;
    }

    public void setMakichthuoc(int makichthuoc) {
        this.makichthuoc = makichthuoc;
    }

    public String getTenkichthuoc() {
        return tenkichthuoc;
    }

    public void setTenkichthuoc(String tenkichthuoc) {
        this.tenkichthuoc = tenkichthuoc;
    }

    @Override
    public String toString() {
        return  tenkichthuoc;
    }

}
