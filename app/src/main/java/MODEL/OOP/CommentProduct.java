package MODEL.OOP;

public class CommentProduct {
    private  String ten;
    private  String phone;
    private  String noidungbinhluan;
    private int saodanhgia;
    private String ngaydang;

    public CommentProduct() {
    }

    public CommentProduct(String ten, String phone, String noidungbinhluan, int saodanhgia, String ngaydang) {
        this.ten = ten;
        this.phone = phone;
        this.noidungbinhluan = noidungbinhluan;
        this.saodanhgia = saodanhgia;
        this.ngaydang = ngaydang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNoidungbinhluan() {
        return noidungbinhluan;
    }

    public void setNoidungbinhluan(String noidungbinhluan) {
        this.noidungbinhluan = noidungbinhluan;
    }

    public int getSaodanhgia() {
        return saodanhgia;
    }

    public void setSaodanhgia(int saodanhgia) {
        this.saodanhgia = saodanhgia;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }
}
