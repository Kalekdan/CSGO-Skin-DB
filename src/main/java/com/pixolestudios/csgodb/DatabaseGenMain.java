package main.java.com.pixolestudios.csgodb;

public class DatabaseGenMain {
    private DatabaseGenMain() {
    }

    public static void main(String[] args) {
        System.out.println(Utils.URLContentstoString("http://csgobackpack.net/api/GetItemsList/v2/?details=true"));
    }


}
