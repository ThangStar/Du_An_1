package MODEL.OOP;

public class Mausac {
    int mamausac;
    int masanpham;
    String tenmausac;
    int soluong;
    int gia;
    public Mausac() {
    }

    public Mausac(int mamausac, int masanpham, String tenmausac, int soluong, int gia) {
        this.mamausac = mamausac;
        this.masanpham = masanpham;
        this.tenmausac = tenmausac;
        this.soluong = soluong;
        this.gia = gia;
    }

    public int getMamausac() {
        return mamausac;
    }

    public void setMamausac(int mamausac) {
        this.mamausac = mamausac;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTenmausac() {
        return tenmausac;
    }

    public void setTenmausac(String tenmausac) {
        this.tenmausac = tenmausac;
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
        return tenmausac;
    }
}
