import java.util.Arrays;

public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData();
    s.generate(6, 30, 1, 8, 1, 8);

    s.print();

    s.flip(50, 2);

    // double a[] = s.averageArrayRow()?;
    s.standardDeviation();
    // s.print();
// System.out.println(Arrays.toString(a));
    // s.print();

    // s.print();

    // s.sum();
    s.product(0, 0);

    // s.occurrence(6);

    // System.out.println(Arrays.toString(s.getColData()));
    // System.out.println(Arrays.toString(s.getRowData()));
  }
}
