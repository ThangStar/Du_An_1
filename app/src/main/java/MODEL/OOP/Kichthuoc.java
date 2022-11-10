package MODEL.OOP;

public class Kichthuoc {
    int makichthuoc;
    int masanpham;
    String tenkichthuoc;

    public Kichthuoc() {
    }

    public Kichthuoc(int makichthuoc, int masanpham, String tenkichthuoc) {
        this.makichthuoc = makichthuoc;
        this.masanpham = masanpham;
        this.tenkichthuoc = tenkichthuoc;
    }

    public int getMakichthuoc() {
        return makichthuoc;
    }

    public void setMakichthuoc(int makichthuoc) {
        this.makichthuoc = makichthuoc;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTenkichthuoc() {
        return tenkichthuoc;
    }

    public void setTenkichthuoc(String tenkichthuoc) {
        this.tenkichthuoc = tenkichthuoc;
    }
}
