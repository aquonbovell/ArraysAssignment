import java.util.Random;
import java.lang.Math;

public class ArrayData {
  private int rows, columns, values[][], rowData[], colData[];

  /**
   * Set rows and columns to their default value of 10 and construct rowData array
   * to have lenght of rows, colData array to have lenght
   * of columns and values array to have height of rows and lenght of columns with
   * defalut entries of 0
   */
  ArrayData() {
    /* checked */
    setRows(10);
    setColumns(10);
    values = new int[rows][columns];
    rowData = new int[rows];
    colData = new int[columns];
    initalizeArray(values);
  }

  /**
   * Set rows and columns to the specified value of nRows and nColumns
   * respectively and construct rowData array to
   * have lenght of rows with default entries of 0, colData array to have lenght
   * of columns with default entries
   * of 0 and values array to have height of rows and lenght of columns with with
   * defalut entries of 0
   * 
   * @param nRows    the number of rows of the values array which must be
   *                 non-negative
   * @param nColumns the number of columns of the values array which must be
   *                 non-negative
   */
  ArrayData(int nRows, int nColumns) {
    /* checked */
    setRows(nRows);
    setColumns(nColumns);
    values = new int[rows][columns];
    rowData = new int[rows];
    colData = new int[columns];
    initalizeArray(values);
  }

  /**
   * Set rows and columns to the specified value of nRows and nColumns
   * respectively and construct rowData array to
   * have lenght of rows with default entries of 0, colData array to have lenght
   * of columns with default entries
   * of 0 and values array to have height of rows and lenght of columns with
   * defalut entries of the value of startingValue
   * 
   * @param nRows         the number of rows of the values array which must be
   *                      non-negative
   * @param nColumns      the number of columns of the values array which must be
   *                      non-negative
   * @param startingValue the starting value of all entries in the values array
   */
  ArrayData(int nRows, int nColumns, int startingValue) {
    /* checked */
    setRows(nRows);
    setColumns(nColumns);
    values = new int[rows][columns];
    rowData = new int[rows];
    colData = new int[columns];
    initalizeArrayWithStartingValue(values, startingValue);
  }

  /**
   * Set the attribute {@code rows} to the specified value of rows
   * 
   * @param rows the number of rows of the values array which must be non-negative
   */
  public void setRows(int rows) {
    /* checked */
    if (rows >= 0) {
      this.rows = rows;
    } else {
      this.rows = 0;
      System.out.println("rows must be positive");
    }
  }

  /**
   * Returns the value of rows
   * 
   * @return the value of rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * Set the attribute {@code columns} to the specified value of columns
   * 
   * @param columns the number of columns of the values array which must be
   *                non-negative
   */
  public void setColumns(int columns) {
    /* checked */
    if (columns >= 0) {
      this.columns = columns;
    } else {
      this.columns = 0;
      System.out.println("columns must be positive");
    }
  }

  /**
   * Returns the value of columns
   * 
   * @return the value of columns
   */
  public int getColumns() {
    return columns;
  }

  /**
   * Randomly selects a position within the intersection of the range of the rows
   * {@code maxRow - minRow} and
   * range of the columns {@code maxColumn - minColumn} in the values array and
   * assign the value of newValue. This
   * process is repeated {@code total} many times.
   * 
   * @param newValue the new value to reassign
   * @param total    the number of times to randomly reassign a position
   * @param minRow   the minimum row to be used for selection
   * @param maxRow   the maximum row to be used for selection
   * @param minCol   the minimum column to be used for selection
   * @param maxCol   the maximum column to be used for selection
   */
  public void generate(int newValue, int total, int minRow, int maxRow, int minCol, int maxCol) {
    /* checked */
    int replaceableRange = (maxRow - minRow) * (maxCol - minCol);
    if (replaceableRange >= total) {
      for (int i = 0; i < total; ++i) {
        int row = getRandomNumberInRange(maxRow, minRow);
        int col = getRandomNumberInRange(maxCol, minCol);
        while (values[row][col] == newValue) {
          row = getRandomNumberInRange(maxRow, minRow);
          col = getRandomNumberInRange(maxCol, minCol);
        }
        values[row][col] = newValue;
      }
    } else {
      System.out.println("Total must be lower or equal to the replaceable range for the values array.");
    }
  }

  /**
   * This method will change the values of a specified number of grid positions
   * and change their value to a specified amount
   * This method accepts two values num and val
   */
  public void flip(int num, int val) {
    /* checked */
    /* Kenez */
    Random rand = new Random(); // Creating an instance of Random to generate a random number for the rows and
                                // columns
    int i = 0;// Counter variable to control the iterations.

    while (i < num) {
      int randomRowNumber = rand.nextInt(rows);
      int randomColumnNumber = rand.nextInt(columns);
      values[randomRowNumber][randomColumnNumber] = val;
      i++;
    }
  }

  /**
   * Sum each row in the values array and assign the value to rowData which
   * corresponds to the index for the row in values array.
   * Sum each column in the values array and assign the value to colData which
   * corresponds to the index for the column in values array.
   */
  public void sum() {
    /* checked */
    initalizeArray(rowData);
    initalizeArray(colData);
    for (int i = 0; i < rows; ++i) {
      int sum = 0;
      for (int j = 0; j < columns; ++j) {
        sum += values[i][j];
      }
      rowData[i] = sum;
    }

    for (int i = 0; i < rows; ++i) {
      int sum = 0;
      for (int j = 0; j < columns; ++j) {
        sum += values[j][i];
      }
      colData[i] = sum;
    }
  }

  /**
   * Tally the number of times the value of {@code num} or a number that is NOT A
   * multiple of {@code num} appears
   * in each row od the values array and stores the result in rowData at the
   * corresponding index of the row.
   * Tally the number of times the value of {@code num} or a number that is a
   * multiple of {@code num} appears in
   * each column od the values array and stores the result in colData at the
   * corresponding index of the column.
   * 
   * @param num the number to search for
   */
  public void occurrence(int num) {
    /* checked */
    initalizeArray(rowData);
    initalizeArray(colData);
    for (int i = 0; i < rows; ++i) {
      int rowOccurrences = 0;
      for (int j = 0; j < columns; ++j) {
        if (values[i][j] == num || values[i][j] % num != 0)
          ++rowOccurrences;
      }
      rowData[i] = rowOccurrences;
    }
    for (int i = 0; i < rows; ++i) {
      int colOccurrences = 0;
      for (int j = 0; j < columns; ++j) {
        if (values[j][i] % num == 0)
          ++colOccurrences;
      }
      colData[i] = colOccurrences;
    }
  }

  /**
   * The method below stores the calculation of the standard deviations
   * in the rowData for each row.
   * And stores the calculation of the standard deviations in the colData
   * for each column.
   */
  public void standardDeviation() {
    /* Dwayne */
    /* Method is working */
    for (int i = 0; i < rowData.length; i++) {
      rowData[i] = calStandardDeviation()[i];
    }

    for (int i = 0; i < rowData.length; i++) {
      colData[i] = calStandardDeviation()[i];
    }
  }

  /**
   * Displays all the elements in the ith row along with the ith element in
   * rowData
   * separated by two pipes("||") and finally all the elements in the colData
   */
  public void print() {
    /* checked */
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

  /**
   * Displays {@code row} number of rows in values array starting a the first row
   * along with the element in rowData
   * the corresponds to the row in the values array separated by two pipes("||")
   * and then finally
   * {@code row} number of elements in the colData starting at the first column.
   */
  public void print(int rows, int columns) {
    /* Dwanye */
    /* checked */
    System.out.println("");
    int i = 0;
    while (i < rows) {
      int j = 0;
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
    while (j < columns) {

      System.out.print(colData[j] + " | ");
      j++;
    }
    System.out.println();
  }

  /**
   * Calculates the product of each row and column separately and decrements the
   * value of a random member in each row or column
   * by 1 until it is less than the specified value of min and then stores the
   * result in rowData and colData respectively.
   */
  public void product(int min, int max) {
    /* Kenez */
    /* checked */
    int prodCol[], prodRow[];// Arrays to store the product of the columns and rows repesectively
    prodCol = new int[columns];// Declaring columns' product array.
    prodRow = new int[rows];// Declaring rows' product array.
    Random rand = new Random();// Creating and instance of a random operator.
    int i = 0, j = 0;// Counter variables to traverse the grid.
    initalizeArrayWithStartingValue(prodRow, 1);
    initalizeArrayWithStartingValue(prodCol, 1);
    while (i < rows) {
      while (j < columns) {
        prodRow[i] *= values[i][j];
        j++;
      }
      if ((prodRow[i] >= min) && (prodRow[i] <= max)) {
        int randomNumber = rand.nextInt(columns);
        values[i][randomNumber] -= 1;
        prodRow[i] = 1;
      } else {
        rowData[i] = prodRow[i];
        i++;
      }
      j = 0;
    }
    j = 0;
    i = 0;
    while (j < columns) {
      while (i < rows) {
        prodCol[j] *= values[i][j];
        i++;
      }
      if ((prodCol[j] >= min) && (prodCol[j] <= max)) {
        int randomNumber = rand.nextInt(rows);
        values[randomNumber][j] -= 1;
        prodCol[j] = 1;
      } else {
        colData[j] = prodCol[j];
        j++;
      }
      i = 0;
    }
  }

  /**
   * Sums the values of the odd and even positions in the values array separately
   * followed and returns the result of
   * dividing the sum of the odd positions by the sum of the even positions.
   */
  public double checkeredOdd() {
    /* checked */
    // Declaring and initalizing all the variables for a grid location that is
    // even or odd
    int oddTotal = 0, evenTotal = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i % 2 == 0) {
          if (j % 2 == 0) {
            oddTotal += values[i][j];
          } else {
            evenTotal += values[i][j];
          }
        } else {
          if (j % 2 == 1) {
            oddTotal += values[i][j];
          } else {
            evenTotal += values[i][j];
          }
        }
      }
    }
    if (evenTotal == 0) {
      System.out.println("Can not divide by zero.");
      return 0.0;
    } else {
      return ((double) oddTotal / (double) evenTotal);
    }
  }

  private void initalizeArray(double array[][]) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = 0;
      }
    }
  }

  private void initalizeArray(int array[][]) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = 0;
      }
    }
  }

  private void initalizeArray(double array[]) {
    for (int i = 0; i < rows; ++i) {
      array[i] = 0;
    }
  }

  private void initalizeArray(int array[]) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = 0;
    }
  }

  private void initalizeArrayWithStartingValue(int array[], int startingValue) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = startingValue;
    }
  }

  private void initalizeArrayWithStartingValue(int array[][], int startingValue) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = startingValue;
      }
    }
  }

  private int getRandomNumberInRange(int max, int min) {
    Random rand = new Random();
    return (rand.nextInt(max - min));
  }

  /**
   * The sumSqDeviation method,
   * firstly, the method subtracts the average (using the average method) from
   * each value in the
   * values array and stores it in the deviation array.
   * Secondly, the deviations are squared and the result is store in SqDeviations.
   * Thirdly, the squared deviations are summed and stored in sumSqDeviation.
   */
  private double[] sumSqDeviation() {

    double[][] deviations = new double[rowData.length][colData.length]; // Declaring the deviations array and it is used
                                                                        // to store the deviation values
    double[][] sqDeviations = new double[rowData.length][colData.length]; // Declaring the sqDeviations array and it is
                                                                          // used to store the deivations squared
    double[] sumSqDeviations = new double[rowData.length]; // Declaring the sumSqDeviations array and it is used to
                                                           // store the summation of the squared deviations

    initalizeArray(deviations);
    initalizeArray(sqDeviations);
    initalizeArray(sumSqDeviations);

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        deviations[row][col] = values[row][col] - averageRow(col);
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sqDeviations[row][col] = deviations[row][col] * deviations[row][col];
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sumSqDeviations[row] += sqDeviations[row][col];
      }
    }

    return sumSqDeviations;
  }

  /**
   * To be continued
   * 
   */
  private int[] calStandardDeviation() {
    int sampleSize = rowData.length;
    double[] placeHolder = new double[rowData.length];
    int[] standardDeviaRow = new int[rowData.length];

    initalizeArray(placeHolder);
    initalizeArray(standardDeviaRow);

    for (int i = 0; i < rowData.length; i++) {
      placeHolder[i] = sumSqDeviation()[i] / sampleSize;
    }

    for (int i = 0; i < rowData.length; i++) {
      placeHolder[i] = Math.sqrt(placeHolder[i]);
    }

    for (int i = 0; i < rowData.length; i++) {
      standardDeviaRow[i] = (int) placeHolder[i];
    }

    return standardDeviaRow;
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

  public double[] averageArrayRow() {
    /* Dwanye */
    double avgrow[] = new double[rows];
    initalizeArray(avgrow);

    int row = 0;
    for (int i = 0; i < rows; i++) {
      double sumRow = 0;
      for (int j = 0; j < columns; j++) {
        sumRow += values[i][j];
      }
      avgrow[i] = (sumRow / rows);
    }
    return avgrow;
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
  /*
   * // public void standardDeviationn() {
   * // /* Dwanye
   * // int sampleSize = rowData.length;
   * // double[] mu = averageArrayRow();
   * // double[][] deviationsRow = new double[rowData.length][colData.length];
   * // double[] sumDeviationsRow = new double[rowData.length];
   * 
   * // initalizeArray(deviationsRow);
   * // initalizeArray(sumDeviationsRow);
   * 
   * // for (int row = 0; row < rowData.length; row++) {
   * // for (int col = 0; col < colData.length; col++) {
   * // deviationsRow[row][col] = values[row][col] - mu[row];
   * // }
   * // }
   * 
   * // for (int row = 0; row < rowData.length; row++) {
   * // for (int col = 0; col < colData.length; col++) {
   * // deviationsRow[row][col] *= deviationsRow[row][col];
   * // }
   * // }
   * 
   * // for (int row = 0; row < rowData.length; row++) {
   * // double sum =0;
   * // for (int col = 0; col < colData.length; col++) {
   * // sum+=deviationsRow[row][col];
   * // }
   * // sumDeviationsRow[row]=Math.sqrt(sum/10);
   * // }
   * // for (int i = 0; i < rowData.length; i++) {
   * // rowData[i] =(int)sumDeviationsRow[i];
   * // }
   * 
   * // System.out.println(Arrays.toString(rowData));
   * // System.out.println(Arrays.toString(sumDeviationsRow));
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // y[i] = sumSqDeviationsRow[i] / sampleSize;
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // y[i] = Math.sqrt(y[i]);
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // standardDeviaRow[i] = (int) y[i];
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // rowData[i] = standardDeviaRow[i];
   * // // }
   * 
   * // // // Doing the same method for columns
   * // // double[] z = new double[colData.length];
   * // // int[] standardDeviaCol = new int[colData.length];
   * 
   * // // double[][] deviationsCol = new double[colData.length][colData.length];
   * // // double[][] sqDeviationsCol = new
   * double[colData.length][colData.length];
   * // // double[] sumSqDeviationsCol = new double[colData.length];
   * 
   * // // initalizeArray(deviationsCol);
   * // // initalizeArray(sqDeviationsCol);
   * 
   * // // for (int row = 0; row < rowData.length; row++) {
   * // // for (int col = 0; col < colData.length; col++) {
   * // // deviationsCol[col][row] = values[col][row] - averageCol(col);
   * // // }
   * // // }
   * 
   * // // for (int row = 0; row < rowData.length; row++) {
   * // // for (int col = 0; col < colData.length; col++) {
   * // // sqDeviationsCol[col][row] = deviationsCol[col][row] *
   * // // deviationsCol[col][row];
   * // // }
   * // // }
   * 
   * // // for (int row = 0; row < rowData.length; row++) {
   * // // for (int col = 0; col < colData.length; col++) {
   * // // sumSqDeviationsCol[row] += sqDeviationsCol[col][row];
   * // // }
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // z[i] = sumSqDeviationsCol[i] / sampleSize;
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // z[i] = Math.sqrt(z[i]);
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // standardDeviaCol[i] = (int) z[i];
   * // // }
   * 
   * // // for (int i = 0; i < rowData.length; i++) {
   * // // colData[i] = standardDeviaCol[i];
   * // // }
   * 
   * // // // // temp for outputing data to the screen.
   * // // // for (int row = 0; row < rowData.length; row++) {
   * // // // System.out.println(rowData[row]);
   * // // // }
   * 
   * // // // System.out.println();
   * 
   * // // // for (int row = 0; row < rowData.length; row++) {
   * // // // System.out.println(colData[row]);
   * // // // }
   * // }
   */
}