package MODEL.OOP;

public class Kichthuoc {
    int makichthuoc;
    int masanpham;
    String tenkichthuoc;
    int soluong;
    int gia;

    public Kichthuoc() {
    }

    public Kichthuoc(int makichthuoc, int masanpham, String tenkichthuoc, int soluong, int gia) {
        this.makichthuoc = makichthuoc;
        this.masanpham = masanpham;
        this.tenkichthuoc = tenkichthuoc;
        this.soluong = soluong;
        this.gia = gia;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return  tenkichthuoc;
    }

}
