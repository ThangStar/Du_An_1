package MODEL.OOP;

public class Array_list_thongke {
    int number;
    String title;
    float money;

    public Array_list_thongke(int number, String title, float money) {
        this.number = number;
        this.title = title;
        this.money = money;
    }

    public Array_list_thongke() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
