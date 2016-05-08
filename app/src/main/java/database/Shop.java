package database;

/**
 * Created by Kevin on 2015-12-29.
 */
public class Shop {
    private long id;
    private String beacon_id;
    private int beacon_major;
    private int beacon_minor;
    private String path_img;
    private String shop_name;

    public int getBeacon_major() {
        return beacon_major;
    }

    public void setBeacon_major(int beacon_major) {
        this.beacon_major = beacon_major;
    }

    public int getBeacon_minor() {
        return beacon_minor;
    }

    public void setBeacon_minor(int beacon_minor) {
        this.beacon_minor = beacon_minor;
    }




    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }



    public long getId() {
        return id;
    }

    public String getBeacon_id() {
        return beacon_id;
    }

    public String getPath_img() {
        return path_img;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBeacon_id(String beacon_id) {
        this.beacon_id = beacon_id;
    }

    public void setPath_img(String path_img) {
        this.path_img = path_img;
    }

    @Override
    public String toString() {
        return path_img;
    }
}
