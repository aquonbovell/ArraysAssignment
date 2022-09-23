public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData();
    s.generate(6, 99, 1, 9, 1, 8);
    s.print();

    s.sum();
    s.product(5, 9);
    // System.out.println(Arrays.toString(s.rowData));
    // System.out.println(Arrays.toString(s.colData));
    
    s.occurrence(4);
  }
}
