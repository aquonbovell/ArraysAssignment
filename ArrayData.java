import java.util.Random;

public class ArrayData {
  int rows, columns, values[][], rowData[], colData[];

  /*
   * Constructor
   */
  ArrayData() {
    rows = columns = 10;
    values = new int[rows][columns];
    rowData = new int[rows];
    colData = new int[columns];
    initalizeArray(values);
    initalizeArray(rowData);
    initalizeArray(colData);
  }

  /*
   * Augmented Constructor
   */
  ArrayData(int nRows, int nColumns) {
    rows = nRows;
    columns = nColumns;
    values = new int[rows][columns];
    rowData = new int[rows];
    colData = new int[columns];
    initalizeArray(values);
    initalizeArray(rowData);
    initalizeArray(colData);
  }
  
  /*
   * Augmented Constructor
   */
  ArrayData(int nRows, int nColumns, int startingValue) {
    rows = nRows;
    columns = nColumns;
    values = new int[rows][columns];
    rowData = new int[rows];
    colData = new int[columns];
    initalizeArray(values, startingValue);
    initalizeArray(rowData);
    initalizeArray(colData);
  }

  private void initalizeArray(int array[][]) {
    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array.length; ++j) {
        array[i][j] = 0;
      }
    }
  }

  private void initalizeArray(int array[][], int startingValue) {
    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array.length; ++j) {
        array[i][j] = startingValue;
      }
    }
  }

  private void initalizeArray(int array[]) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = 0;
    }
  }

  public void generate(int newValue, int total, int minRow, int maxRow, int minCol, int maxCol) {
    for (int i = 0; i < total; ++i) {
      values[getRandomNumberInRange(maxRow, minRow)][getRandomNumberInRange(maxCol, minCol)] = newValue;
    }
  }

  private int getRandomNumberInRange(int max, int min) {
    Random rand = new Random();
    return (rand.nextInt((max - min) +1) + min -1);
  }

  public void print() {
    System.out.println("\n-----------------------------------------");
    for (int i = 0; i < values.length; ++i) {
      System.out.print("| ");
      for (int j = 0; j < columns; j++) {
        System.out.print(values[i][j]+ " | ");
      }
      System.out.println("\n-----------------------------------------");
    }    
  }
}
