public class TestArrayData {

  public static void main(String[] args) {
    ArrayData s = new ArrayData();

    s.generate(5, 10, 2, 4, 2, 5);

    s.sum();

    s.print();

    s.product(2, 4);

    s.sum();

    s.print();

    s.occurrence(2);

    s.print();

    s.print(4, 2);
  }
}
