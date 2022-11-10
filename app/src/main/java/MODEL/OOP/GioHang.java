package MODEL.OOP;

public class GioHang {
    int magiohang;
    int id;
    int masanpham;
    int mamausac;
    int makichthuoc;
    int soluong;

    public GioHang() {
    }

    public GioHang(int magiohang, int id, int masanpham, int mamausac, int makichthuoc, int soluong) {
        this.magiohang = magiohang;
        this.id = id;
        this.masanpham = masanpham;
        this.mamausac = mamausac;
        this.makichthuoc = makichthuoc;
        this.soluong = soluong;
    }

    public int getMagiohang() {
        return magiohang;
    }

    public void setMagiohang(int magiohang) {
        this.magiohang = magiohang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getMamausac() {
        return mamausac;
    }

    public void setMamausac(int mamausac) {
        this.mamausac = mamausac;
    }

    public int getMakichthuoc() {
        return makichthuoc;
    }

    public void setMakichthuoc(int makichthuoc) {
        this.makichthuoc = makichthuoc;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
