package Models;

public class CartItem {
    private int userID;
    private Item item;
    private int count;

    public CartItem(int userID, Item item, int count) {
        this.userID = userID;
        this.item = item;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "userID=" + userID +
                ", item=" + item +
                ", count=" + count +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
