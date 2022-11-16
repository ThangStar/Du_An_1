package MODEL.OOP;

public class KhuyenMai {
    private String magiamgia;
    private  int phantramgiam;
    private String ngaybatdau;
    private  String ngayketthuc;
    private int sotiengiam;

    public KhuyenMai() {
    }

    public KhuyenMai(String magiamgia, int phantramgiam, String ngaybatdau, String ngayketthuc, int sotiengiam) {
        this.magiamgia = magiamgia;
        this.phantramgiam = phantramgiam;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.sotiengiam = sotiengiam;
    }

    public String getMagiamgia() {
        return magiamgia;
    }

    public void setMagiamgia(String magiamgia) {
        this.magiamgia = magiamgia;
    }

    public int getPhantramgiam() {
        return phantramgiam;
    }

    public void setPhantramgiam(int phantramgiam) {
        this.phantramgiam = phantramgiam;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public int getSotiengiam() {
        return sotiengiam;
    }

    public void setSotiengiam(int sotiengiam) {
        this.sotiengiam = sotiengiam;
    }
}
