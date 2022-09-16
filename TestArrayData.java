import java.util.Arrays;

public class TestArrayData {
  
  public static void main(String[] args) {
    ArrayData s = new ArrayData();
    System.out.println(Arrays.deepToString(s.values));
    System.out.println("\n"+Arrays.toString(s.rowData));
    System.out.println(Arrays.toString(s.colData));
  }
}
