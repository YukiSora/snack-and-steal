public class Data implements java.io.Serializable {
    String data;

    Data(String data) {
        this.data = data;
    }

    public String toString() {
        return "data: " + data;
    }
}
