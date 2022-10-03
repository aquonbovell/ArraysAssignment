public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData(10, 10);
    // s.generate(6, 30, 1, 8, 1, 8);

    // s.product(256, 9000);
    s.flip(8,6);
    s.print();
    // System.out.println(s.checkeredOdd());
  }
}
