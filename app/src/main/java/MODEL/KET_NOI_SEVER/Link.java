package MODEL.KET_NOI_SEVER;

public class Link {

    /*
                 //converting image to base64 string
              profile_image.buildDrawingCache();
                Bitmap bmap = profile_image.getDrawingCache();
                String encodedImageData =getEncoded64ImageStringFromBitmap(bmap);


public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    bitmap.compress(CompressFormat.JPEG, 70, stream);
    byte[] byteFormat = stream.toByteArray();
    // get the base 64 string
    String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

    return imgString;
}

    * */

    // địa chỉ mạng
   // static final String IP="192.168.191.162";
  //  static final String IP="192.168.1.18";
    static final String IP="phongabdyt.000webhostapp.com";
    // link lên server
   public static String intsert_user="https://"+IP+"//android/duanmau/insert_user.php";
    public static String update_user="http://"+IP+"//android/duanmau/update_user.php";
    public static String getdata_dangnhap="http://"+IP+"//android/duanmau/gedata_user.php";

    public static String intsert_danhmuc="https://"+IP+"//android/duanmau/insert_danhmuc.php";
    public static String delete_danhmuc="https://"+IP+"//android/duanmau/delete_danhmuc.php";
    public static String update_danhmuc="https://"+IP+"//android/duanmau/update_danhmuc.php";
    public static String getdata_danhmuc="http://"+IP+"//android/duanmau/getdata_danhmuc.php";
    public static String search_danhmuc="https://"+IP+"//android/duanmau/search_danhmuc.php";

    public static String intsert_sanpham="https://"+IP+"//android/duanmau/insert_sanpham.php";
    public static String delete_sanpham="https://"+IP+"//android/duanmau/delete_sanpham.php";
    public static String update_sanpham="https://"+IP+"//android/duanmau/update_sanpham.php";
    public static String update_soluong_sanpham="https://"+IP+"//android/duanmau/update_soluong_sanpham.php";
    public static String getdata_sanpham="https://"+IP+"//android/duanmau/getdata_sanpham.php";
    public static String search_sanpham="https://"+IP+"//android/duanmau/search_sanpham.php";


}
