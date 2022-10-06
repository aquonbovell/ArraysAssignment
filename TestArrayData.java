public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData(10, 10);
    s.generate(6, 30, 1, 5, 3, 8);

    // s.product(256, 9000);
    // s.flip(8,6);
    s.print(5,4);
    // System.out.println(s.checkeredOdd());
  }
}
