import java.net.NetPermission;
import java.util.Arrays;

public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData(10,10,6);

    // System.out.println(" " + s.columns + " " + s.rows + ""+ Arrays.toString(s.rowData)+""+Arrays.toString(s.colData)+ "\n"+Arrays.deepToString(s.values));

    s.generate(6, 30, 2, 1, 3, 8);

    // // s.product(256, 9000);
    // // s.flip(8,6);
    // s.print(5,4);
    // // System.out.println(s.checkeredOdd());
  }
}
