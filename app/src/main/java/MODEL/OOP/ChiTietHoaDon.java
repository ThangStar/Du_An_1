package MODEL.OOP;

public class ChiTietHoaDon {
    private int masanpham;
    private String tensanpham;
    private String img;
    private String tenkichthuoc;
    private String tenmau;
    private int soluong;
    private int giamua;
    private int tongtien;

    public ChiTietHoaDon() {
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenkichthuoc() {
        return tenkichthuoc;
    }

    public void setTenkichthuoc(String tenkichthuoc) {
        this.tenkichthuoc = tenkichthuoc;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiamua() {
        return giamua;
    }

    public void setGiamua(int giamua) {
        this.giamua = giamua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public ChiTietHoaDon(int masanpham, String tensanpham, String img, String tenkichthuoc, String tenmau, int soluong, int giamua, int tongtien) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.img = img;
        this.tenkichthuoc = tenkichthuoc;
        this.tenmau = tenmau;
        this.soluong = soluong;
        this.giamua = giamua;
        this.tongtien = tongtien;
    }
}
