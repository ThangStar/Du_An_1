package MODEL.OOP;

public class Hoadon {
    private  int mahoadon;
    private  int id;
    private  String phantramkhuyenmai;
    private  String tendiachi;
    private  String ngaymua;
    private  int tongtien;
    private  int sotiengiam;
    private  int sotienphaitra;
    private  int soluonghoadon;

    public Hoadon() {
    }

    public int getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(int mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhantramkhuyenmai() {
        return phantramkhuyenmai;
    }

    public void setPhantramkhuyenmai(String phantramkhuyenmai) {
        this.phantramkhuyenmai = phantramkhuyenmai;
    }

    public String getTendiachi() {
        return tendiachi;
    }

    public void setTendiachi(String tendiachi) {
        this.tendiachi = tendiachi;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getSotiengiam() {
        return sotiengiam;
    }

    public void setSotiengiam(int sotiengiam) {
        this.sotiengiam = sotiengiam;
    }

    public int getSotienphaitra() {
        return sotienphaitra;
    }

    public void setSotienphaitra(int sotienphaitra) {
        this.sotienphaitra = sotienphaitra;
    }

    public int getSoluonghoadon() {
        return soluonghoadon;
    }

    public void setSoluonghoadon(int soluonghoadon) {
        this.soluonghoadon = soluonghoadon;
    }

    public Hoadon(int mahoadon, int id, String phantramkhuyenmai, String tendiachi, String ngaymua, int tongtien, int sotiengiam, int sotienphaitra, int soluonghoadon) {
        this.mahoadon = mahoadon;
        this.id = id;
        this.phantramkhuyenmai = phantramkhuyenmai;
        this.tendiachi = tendiachi;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
        this.sotiengiam = sotiengiam;
        this.sotienphaitra = sotienphaitra;
        this.soluonghoadon = soluonghoadon;
    }
}
