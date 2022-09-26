import java.util.Arrays;

public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData();
    s.generate(6, 30, 1, 8, 1, 8);

    s.print();

    s.flip(50, 2);

    s.print();

    s.sum();

    s.occurrence(6);

    System.out.println(Arrays.toString(s.getColData()));
    System.out.println(Arrays.toString(s.getRowData()));
  }
}
