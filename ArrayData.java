import java.util.Random;

public class ArrayData {
  private int rows, columns, values[][], rowData[], colData[];

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
    initalizeArrayWithStartingValue(values, startingValue);
    initalizeArray(rowData);
    initalizeArray(colData);
  }

  private void initalizeArray(int array[][]) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = 0;
      }
    }
  }

  private void initalizeArray(int array[]) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = 0;
    }
  }

  private void initalizeArrayWithStartingValue(int array[][], int startingValue) {
    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array.length; ++j) {
        array[i][j] = startingValue;
      }
    }
  }

  public void generate(int newValue, int total, int minRow, int maxRow, int minCol, int maxCol) {
    for (int i = 0; i < total; ++i) {
      values[getRandomNumberInRange(maxRow, minRow)][getRandomNumberInRange(maxCol, minCol)] = newValue;
    }
  }

  private int getRandomNumberInRange(int max, int min) {
    Random rand = new Random();
    return (rand.nextInt((max - min) + 1) + min - 1);
  }

  public void print() {
    System.out.println("\n-----------------------------------------");
    for (int i = 0; i < values.length; ++i) {
      System.out.print("| ");
      for (int j = 0; j < columns; j++) {
        System.out.print(values[i][j] + " | ");
      }
      System.out.println("\n-----------------------------------------");
    }
  }

  public void sum() {

    for (int i = 0; i < rowData.length; ++i) {
      int sum = 0;
      for (int index = 0; index < columns; ++index) {
        sum += values[i][index];
      }
      rowData[i] = sum;
    }

    for (int i = 0; i < colData.length; ++i) {
      int sum = 0;
      for (int index = 0; index < columns; ++index) {
        sum += values[index][i];
      }
      colData[i] = sum;
    }
  }

  public void occurrence(int num) {
    for (int i = 0; i < values.length; i++) {
      int rowOccurrences = 0;
      for (int j = 0; j < columns; j++) {
        if (values[i][j] == num || values[i][j] % num != 0)
          ++rowOccurrences;
      }
      System.out.println(rowOccurrences);
    }

    System.out.println("\n\n");
    for (int j = 0; j < columns; ++j) {
      int colOccurrences = 0;
      for (int i = 0; i < values.length; ++i) {
        if (values[i][j] != 0 && values[i][j] % num == 0)
          ++colOccurrences;
      }
      System.out.println(colOccurrences);
    }
  }

  public void product(int min, int max) {
    /* Kenez */
    int prodCol[], prodRow[];
    prodCol = new int[columns];
    prodRow = new int[rows];
    Random rand = new Random();
    int i = 0, j = 0;
    while (i <= rows) {
      while (j <= columns) {
        prodRow[i] *= values[i][j];
      }
      if ((prodRow[i] >= min) && (prodRow[i] <= max)) {
        int rando = rand.nextInt(columns);
        values[i][rando] -= 1;
      } else {
        rowData[i] = prodRow[i];
        i++;
      }
    }
    j = 0;
    i = 0;
    while (j <= columns) {
      while (i <= rows) {
        prodCol[j] *= values[i][j];
      }
      if ((prodCol[j] >= min) && (prodCol[j] <= max)) {
        int rando = rand.nextInt(rows);
        values[rando][j] -= 1;
      } else {
        colData[j] = prodCol[j];
        j++;
      }
    }
  }

  public void flip(int num, int val) {
    /* Kenez */
    Random rand = new Random();
    int i = 0;

    while (i <= num) {
      int nom = rand.nextInt(rows);
      int numb = rand.nextInt(columns);
      values[nom][numb] = val;
      i++;
    }
  }
}
