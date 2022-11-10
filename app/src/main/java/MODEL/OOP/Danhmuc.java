package MODEL.OOP;

public class Danhmuc {
    private int madanhmuc;
    private String tendanhmuc;
    private String khuvuc;
    private String img;

    public Danhmuc() {
    }

    public Danhmuc(int madanhmuc, String tendanhmuc, String khuvuc, String img) {
        this.madanhmuc = madanhmuc;
        this.tendanhmuc = tendanhmuc;
        this.khuvuc = khuvuc;
        this.img = img;
    }

    public int getMadanhmuc() {
        return madanhmuc;
    }

    public void setMadanhmuc(int madanhmuc) {
        this.madanhmuc = madanhmuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
