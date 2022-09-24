import java.util.Random;
import java.lang.Math;

public class ArrayData {
  private int rows, columns, values[][], rowData[], colData[];

  /**
   * Default Constructor -
   * Initalizes rows and columns to 10
   * Create rowData array to have lenght of rows and colData array to have lenght
   * of columns
   * Create values array to have height of rows and lenght of columns
   * Initalizes all elements in rowData, colData, values to 0
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

  /**
   * Parameterized Constructor -
   * Initalizes rows to nRows and columns to nColumns
   * Create rowData array to have lenght of rows and colData array to have lenght
   * of columns
   * Create values array to have height of rows and lenght of columns
   * Initalizes all elements in rowData, colData, values to 0
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

  /**
   * Parameterized Constructor -
   * Initalizes rows to nRows and columns to nColumns
   * Create rowData array to have lenght of rows and colData array to have lenght
   * of columns
   * Create values array to have height of rows and lenght of columns
   * Initalizes all elements in rowData, colData to 0
   * Initalizes all values to startingValues
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

  public void generate(int newValue, int total, int minRow, int maxRow, int minCol, int maxCol) {
    for (int i = 0; i < total; ++i) {
      values[getRandomNumberInRange(maxRow, minRow)][getRandomNumberInRange(maxCol, minCol)] = newValue;
    }
  }

  /**
   * Displays all the elements in the ith row along with the ith element in rowData 
   * separated by two pipes("||") and finally all the elements in the colData
   */
  public void print() {
    System.out.println("");
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        if (j == (columns - 1)) {
          System.out.print(values[i][j] + " || ");
        } else {
          System.out.print(values[i][j] + " | ");
        }
      }
      System.out.print(rowData[i]);
      System.out.print("\n");
    }
    for (int i = 0; i < columns; ++i) {
      if (i == (columns - 1)) {
        System.out.print(colData[i]);
      } else {
        System.out.print(colData[i] + " | ");
      }
    }
    System.out.println("");
  }

  public void print(int rows, int columns) {
    /* Dwanye */
    int i = 0;
    while (i < rows) {
      int j = 0;
      System.out.print("| ");
      while (j < columns) {
        System.out.print(values[i][j]);
        if (j < columns - 1) {
          System.out.print(" | ");
        }
        if (j == columns - 1) {
          System.out.print(" || " + rowData[i]);
        }
        j++;
      }
      System.out.println();
      i++;
    }
    System.out.println();
    int j = 0;
    System.out.print("| ");
    while (j < columns) {

      System.out.print(colData[j] + " | ");
      j++;
    }
    System.out.println();
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

  public void standardDeviation() {
    /* Dwanye */
    int sampleSize = 10;
    double[] y = new double[10];
    int[] standardDeviaRow = new int[10];
    double[][] deviationsRow = new double[10][10];
    double[][] sqDeviationsRow = new double[10][10];
    double[] sumSqDeviationsRow = new double[10];

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        deviationsRow[row][col] = 0;
        sqDeviationsRow[row][col] = 0;
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        deviationsRow[row][col] = values[row][col] - averageRow(col);
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sqDeviationsRow[row][col] = deviationsRow[row][col] * deviationsRow[row][col];
      }
    }
    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sumSqDeviationsRow[row] += sqDeviationsRow[row][col];
      }
    }
    for (int i = 0; i < rowData.length; i++) {
      y[i] = sumSqDeviationsRow[i] / sampleSize;
    }
    for (int i = 0; i < rowData.length; i++) {
      y[i] = Math.sqrt(y[i]);
    }
    for (int i = 0; i < rowData.length; i++) {
      standardDeviaRow[i] = (int) y[i];
    }
    for (int i = 0; i < rowData.length; i++) {
      rowData[i] = standardDeviaRow[i];
    }

    // Doing the same method for columns
    double[] z = new double[10];
    int[] standardDeviaCol = new int[10];

    double[][] deviationsCol = new double[10][10];
    double[][] sqDeviationsCol = new double[10][10];
    double[] sumSqDeviationsCol = new double[10];

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        deviationsCol[row][col] = 0;
        sqDeviationsCol[row][col] = 0;
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        deviationsCol[col][row] = values[col][row] - averageCol(col);
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sqDeviationsCol[col][row] = deviationsCol[col][row] * deviationsCol[col][row];
      }
    }
    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sumSqDeviationsCol[row] += sqDeviationsCol[col][row];
      }
    }
    for (int i = 0; i < rowData.length; i++) {
      z[i] = sumSqDeviationsCol[i] / sampleSize;
    }
    for (int i = 0; i < rowData.length; i++) {
      z[i] = Math.sqrt(z[i]);
    }
    for (int i = 0; i < rowData.length; i++) {
      standardDeviaCol[i] = (int) z[i];
    }
    for (int i = 0; i < rowData.length; i++) {
      colData[i] = standardDeviaCol[i];
    }

    // temp for outputing data to the screen.
    for (int row = 0; row < rowData.length; row++) {
      System.out.println(rowData[row]);

    }
    System.out.println();
    for (int row = 0; row < rowData.length; row++) {
      System.out.println(colData[row]);

    }
  }

  public double checkeredOdd() {
    int odd = 0, even = 0, oddT = 0, evenT = 0;
    double quotient = 0.0;
    for (int i = 0; i <= rows; i++) {
      for (int j = 1; j <= columns; j++) {
        if (i % 2 == 0) {
          odd = (2 * j) - 1;
          even = 2 * j;
          oddT += values[i][odd];
          evenT += values[i][even];
        } else {
          odd = 2 * j;
          even = (2 * j) - 1;
          oddT += values[i][odd];
          evenT += values[i][even];
        }
      }
    }
    quotient = oddT / evenT;

    return quotient;
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

  private int getRandomNumberInRange(int max, int min) {
    Random rand = new Random();
    return (rand.nextInt((max - min) + 1) + min - 1);
  }

  private double averageRow(int r) {
    /* Dwanye */
    double sumRow = 0;
    double avgRow = 0;
    double sample = 0;

    int row = 0;
    while (row < rowData.length) {
      if (row == r) {
        for (int col = 0; col < colData.length; col++) {
          sumRow += values[row][col];
        }
        break;
      }
      row++;
    }
    sample = 10;
    avgRow = sumRow / sample;
    return avgRow;
  }

  private double averageCol(int c) {
    /* Dwanye */
    double sumCol = 0;
    double avgCol = 0;
    double sample = 0;

    int row = 0;
    while (row < rowData.length) {
      if (row == c) {
        for (int col = 0; col < colData.length; col++) {
          sumCol += values[col][row];
        }
        break;
      }
      row++;
    }
    sample = 10;
    avgCol = sumCol / sample;
    return avgCol;
  }
}
