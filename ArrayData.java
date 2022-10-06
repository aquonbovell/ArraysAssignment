import java.util.Random;
import java.lang.Math;

/*
 * The names of the programmers are:
 * Aquon Bovell, Kenez Horne and Dwayne Archer
 * The ArrayData class contains multiple methods and fields 
 * The basic functionality of the ArrayData class is to manipulate 
 * the two dimensional values Array
 * Also calculations are performed on the values array and the results are stored 
 * in the rowData and colData arrays
 */

public class ArrayData {
  public int rows, columns, values[][], rowData[], colData[];
  private int checkingForCol = 0; // The checkingForCol is used for the calStandardDeviation method to distinguish
                                  // between the rowData and ColData

  /**
   * Set fields rows and columns to their default value of 10 and construct
   * rowData array
   * to have lenght of rows, colData array to have lenght of columns and values
   * array
   * to have height of rows and lenght of columns with defalut entries of 0.
   * There are no parameters and no return type since this is a constructor.
   */
  ArrayData() {
    setRows(10);
    setColumns(10);
    rowData = new int[rows];
    colData = new int[columns];
    values = new int[rows][columns];
    initalizeArray(values);
  }

  /**
   * Set the fields rows and columns to the specified value of nRows and nColumns
   * respectively and construct rowData array to have lenght of rows, colData
   * array
   * to have lenght of columns and values array to have height of rows and lenght
   * of columns with with defalut entries of 0.
   * The parameters are nRows which represents the number of rows of the values
   * array
   * which must be a non-negative integer and nColumns which represents the number
   * of
   * columns of the values array which must be a non-negative integer.
   * There is no return type since this is a general constructor.
   */
  ArrayData(int nRows, int nColumns) {
    /* checked */
    setRows(nRows);
    setColumns(nColumns);
    rowData = new int[rows];
    colData = new int[columns];
    values = new int[rows][columns];
    initalizeArray(values);
  }

  /**
   * Set the fields rows and columns to the specified value of nRows and nColumns
   * respectively and construct rowData array to have lenght of rows, colData
   * array
   * to have lenght of columns and values array to have height of rows and lenght
   * of columns with defalut entries of the specified value of startingValue.
   * The parameters are nRows which represents the number of rows of the values
   * array
   * which must be a non-negative integer, nColumns which represents the number of
   * columns of the values array which must be a non-negative integer and
   * startingValue which represents the starting value of all entries in the
   * values array.
   * There is no return type since this is a general constructor.
   */
  ArrayData(int nRows, int nColumns, int startingValue) {
    /* checked */
    setRows(nRows);
    setColumns(nColumns);
    rowData = new int[rows];
    colData = new int[columns];
    values = new int[rows][columns];
    initalizeArrayWithStartingValue(values, startingValue);
  }

  /**
   * Randomly selects a position within the intersection of the range of the rows
   * (maxRow - minRow) and the range of the columns (maxColumn - minColumn) in the
   * values array and assign the value of newValue to the selected position. This
   * process is repeated total many times.
   * The parameters are newValue which represents the new value to reassign,
   * total which represents the number of times to randomly reassign a position.
   * minRow which represents the minimum row to be used for selection and must be
   * at least 1, maxRow which represents the maximum row to be used for selection
   * and must not exceed the value of the row field, minCol which represents the
   * minimum column to be used for selection and must be at least 1, maxCol which
   * represents the maximum column to be used for selection and must not exceed
   * the value of the column field.
   * There are no values that are returned.
   */
  public void generate(int newValue, int total, int minRow, int maxRow, int minCol, int maxCol) {
    /* checked */
    if (minRow <= 0) {
      System.out.println("The minimum row can not be less than 1");
    } else if (maxRow > rows) {
      System.out.println("The maximum row must be less than or equal to the height of the array");
    } else if (minCol <= 0) {
      System.out.println("The minimum column can not be less 1");
    } else if (maxCol > columns) {
      System.out.println("The maximum column must be less than or equal to the width of the array");
    } else if (((maxRow - minRow) + 1) < 1) {
      System.out.println("The range for the rows must be at least 1");
    } else if (((maxCol - minCol) + 1) < 1) {
      System.out.println("The range for the columns must be at least 1");
    } else {
      int replaceableRange = ((maxRow - minRow) + 1) * ((maxCol - minCol) + 1); // this represents the allowable range
                                                                                // that is used
                                                                                // and to ensure the total does not
                                                                                // exceed this amount
      if (replaceableRange >= total) {
        for (int i = 0; i < total; ++i) {
          int row = getRandomNumberInRange(maxRow, minRow); // a random row index for replacement
          int col = getRandomNumberInRange(maxCol, minCol); // a random column index for replacement
          while (values[row][col] == newValue) {
            row = getRandomNumberInRange(maxRow, minRow);
            col = getRandomNumberInRange(maxCol, minCol);
          }
          values[row][col] = newValue;
        }
      } else {
        System.out
            .println("The specified value for Total must be less than or equal to the range that can be replaced");
      }
    }
  }

  /**
   * Randomly changes the values of a specified number of grid positions
   * and change their value to a specified value of val.
   * The parameters are num which represents the total num of replacements
   * and val which represents the new value to assign to the randomly selected
   * grid position.
   * There are no values that are returned.
   */
  public void flip(int num, int val) {
    /* checked */
    /* Kenez */
    Random rand = new Random(); // Creating an instance of Random to generate a
                                // random number for the rows and columns
    int i = 0; // Counter variable to control the number of iterations

    while (i < num) {
      int randomRowNumber = rand.nextInt(rows);
      int randomColumnNumber = rand.nextInt(columns);
      values[randomRowNumber][randomColumnNumber] = val;
      i++;
    }
  }

  /**
   * Sums each row in the values array and assign the value to rowData which
   * corresponds to the index for the row in values array.
   * Sum each column in the values array and assign the value to colData which
   * corresponds to the index for the column in values array.
   * No parameters are to be passed in.
   * There are no values that are returned.
   */
  public void sum() {
    /* checked */
    initalizeArray(rowData);
    initalizeArray(colData);
    for (int i = 0; i < rows; ++i) {
      int sum = 0; // Holds the sum for each row
      for (int j = 0; j < columns; ++j) {
        sum += values[i][j];
      }
      rowData[i] = sum;
    }

    for (int i = 0; i < rows; ++i) {
      int sum = 0; // Holds the sum for each column
      for (int j = 0; j < columns; ++j) {
        sum += values[j][i];
      }
      colData[i] = sum;
    }
  }

  /**
   * Counts the number of times the specified value of num or a number that IS
   * NOT A multiple of num appears in each row of the values array and stores
   * the result in rowData at the corresponding index of each row. Also, counts
   * the number of times the value of num or a number that IS a multiple of num
   * appears in each column of the values array and stores the result in colData
   * at the
   * corresponding index of each column.
   * The parameters are num which represents the number to search for.
   * There are no values that are returned.
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
    for (int i = 0; i < rowData.length; i++) {
      rowData[i] = calStandardDeviation()[i];
    }

    for (int i = 0; i < colData.length; i++) {
      checkingForCol = 1;
      colData[i] = calStandardDeviation()[i];
    }
  }

  /**
   * Sums the values of the odd and even positions in the values array separately
   * then returns the result of dividing the sum of the odd positions by the sum
   * of the even positions only if the sum of the even positons is not zero.
   * There are no parameters to be passed in.
   * The value returned is the quotient of the sum of the odd positions divided
   * by the sum of the even positions.
   */
  public double checkeredOdd() {
    /* checked */
    
    int oddTotal = 0, evenTotal = 0;  // Declaring and initalizing all the variables for a grid
                                      // location that is even or odd
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

  /**
   * Displays all the elements in the ith row along with the ith element in
   * rowData separated by two pipes ("||") and finally all the elements in
   * the colData on a separate line.
   * There are no parameters to be passed in.
   * There are no values that are returned.
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
      System.out.println("");
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
   * Displays rows number of rows in values array starting a the first row
   * along with the element in rowData that corresponds to the row in the
   * values array separated by two pipes ("||") and columns number of columns
   * in the values array and then finally columns number of elements in the
   * colData starting at the first column.
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
    int j = 0;
    while (j < columns) {

      System.out.print(colData[j] + " | ");
      j++;
    }
    System.out.println("");
  }

  /**
   * Calculates the product of each row and column separately and decrements the
   * value of a random member in each row or column by 1 until the product of 
   * each row or column is less than the specified value of min and then stores the
   * result in rowData and colData respectively.
   */
  public void product(int min, int max) {
    /* Kenez */
    /* checked */
    int productCol[], productRow[]; // Arrays to store the product of the columns and rows repesectively
    productCol = new int[columns];  // Declaring columns' product array.
    productRow = new int[rows];     // Declaring rows' product array.
    Random rand = new Random();     // Creating and instance of a random operator.
    int i = 0, j = 0;               // Counter variables to traverse the grid.
    initalizeArrayWithStartingValue(productRow, 1);
    initalizeArrayWithStartingValue(productCol, 1);
    while (i < rows) {
      while (j < columns) {
        productRow[i] *= values[i][j];
        j++;
      }
      if ((productRow[i] >= min) && (productRow[i] <= max)) {
        int randomNumber = rand.nextInt(columns);
        values[i][randomNumber] -= 1;
        productRow[i] = 1;
      } else {
        rowData[i] = productRow[i];
        i++;
      }
      j = 0;
    }
    j = 0;
    i = 0;
    while (j < columns) {
      while (i < rows) {
        productCol[j] *= values[i][j];
        i++;
      }
      if ((productCol[j] >= min) && (productCol[j] <= max)) {
        int randomNumber = rand.nextInt(rows);
        values[randomNumber][j] -= 1;
        productCol[j] = 1;
      } else {
        colData[j] = productCol[j];
        j++;
      }
      i = 0;
    }
  }

  /**
   * Initializes all the elements of a two dimensional array of type double to zero
   * The parameter is a two dimensional array of type double.
   * There are no values that are returned.
   */
  private void initalizeArray(double array[][]) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = 0;
      }
    }
  }

  /**
   * Initializes all the elements of a two dimensional array of type int to zero
   * The parameter is a two dimensional array of type int.
   * There are no values that are returned.
   */
  private void initalizeArray(int array[][]) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = 0;
      }
    }
  }

  /**
   * Initializes all the elements of a one dimensional array of type double to zero
   * The parameter is a one dimensional array of type double.
   * There are no values that are returned.
   */
  private void initalizeArray(double array[]) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = 0;
    }
  }

  /**
   * Initializes all the elements of a one dimensional array of type int to zero
   * The parameter is a one dimensional array of type int.
   * There are no values that are returned.
   */
  private void initalizeArray(int array[]) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = 0;
    }
  }

  /**
   * The initalizeArrayWithStartingValue(int array[], int startingValue) method
   * initializes the elements of the one dimensional array to the starting value
   * The two parameters are an integer one dimensional array and
   * a starting value which is an integer
   */
  private void initalizeArrayWithStartingValue(int array[], int startingValue) {
    for (int i = 0; i < array.length; ++i) {
      array[i] = startingValue;
    }
  }

  /**
   * The initalizeArrayWithStartingValue(int array[][], int startingValue) method
   * initializes the elements of the two dimensional array to the starting value
   * The two parameters are an integer two dimensional array and
   * a starting value which is an integer
   */
  private void initalizeArrayWithStartingValue(int array[][], int startingValue) {
    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < columns; ++j) {
        array[i][j] = startingValue;
      }
    }
  }

  /**
   * The getRandomNumberInRange(int max, int min) method
   * basically gets a random number between the maximum and minimum numbers
   * Then it returns that random number
   * The two parameters for the method is a maximum number and a minimum number
   */
  private int getRandomNumberInRange(int max, int min) {
    Random rand = new Random();
    return (rand.nextInt((max - min + 1)) + min - 1);
  }

  /**
   * The sumSqDeviation() method,
   * firstly, the method subtracts the average (using the averageRow method) from
   * each value in the
   * values array and stores it in the deviation array.
   * Secondly, the deviations are squared and the result is store in SqDeviations.
   * Thirdly, the squared deviations are summed and stored in sumSqDeviation.
   * Then the sumSqDeviation array is returned
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
        deviations[row][col] = values[row][col] - averageRow(row);
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
   * The sumSqDeviationCol() method,
   * firstly, the method subtracts the average (using the averageCol method) from
   * each value in the
   * values array and stores it in the deviation array.
   * Secondly, the deviations are squared and the result is store in SqDeviations.
   * Thirdly, the squared deviations are summed and stored in sumSqDeviationCol.
   * Then the sumSqDeviationCol array is returned
   */

  private double[] sumSqDeviationCol() {

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
        deviations[col][row] = values[col][row] - averageCol(row);
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sqDeviations[col][row] = deviations[col][row] * deviations[col][row];
      }
    }

    for (int row = 0; row < rowData.length; row++) {
      for (int col = 0; col < colData.length; col++) {
        sumSqDeviations[row] += sqDeviations[col][row];
      }
    }
    return sumSqDeviations;
  }

  /**
   * The calStandardDeviation() method
   * Checks to see which sumSqDeviation to use using the checkingForCol field
   * A placeHolder stores the variance as a double
   * Then the placeHolder is rooted using the Math.sqrt() method
   * Afterwards the placeHolder is converted into a integer and stored in the
   * field standardDevia
   * Then the standardDevia is returned which is the final value of the standard
   * deviation
   */
  private int[] calStandardDeviation() {
    int sampleSize = rowData.length; // The sampleSize is used as the population value for the standard deviation
    double[] placeHolder = new double[rowData.length]; // The placeHolder's purpose is to store the value of the
                                                       // standard deviation temporarily as a double
    int[] standardDevia = new int[rowData.length]; // The standardDevia's purpose is to store the final value of the
                                                   // standard deviation as an integer

    initalizeArray(placeHolder);
    initalizeArray(standardDevia);

    if (checkingForCol == 0) {
      for (int i = 0; i < rowData.length; i++) {
        placeHolder[i] = sumSqDeviation()[i] / sampleSize;
      }
    } else if (checkingForCol == 1) {
      for (int i = 0; i < rowData.length; i++) {
        placeHolder[i] = sumSqDeviationCol()[i] / sampleSize;
      }
    }

    for (int i = 0; i < rowData.length; i++) {
      placeHolder[i] = Math.sqrt(placeHolder[i]);
    }

    for (int i = 0; i < rowData.length; i++) {
      standardDevia[i] = (int) placeHolder[i];
    }

    return standardDevia;
  }

  /**
   * The purpose of the averageRow(int r) method
   * is to find the avergage of each row in the values array
   * for the standard deviation
   * r is the parameter of the method and r is the row index for the values array
   * The value returned from the method is avgRow which is the average for the
   * specific row index
   */
  private double averageRow(int r) {
    /* Dwanye */
    double sumRow = 0; // sumRow stores the sum of all of the numbers in the specific row in the values
                       // array
    double avgRow = 0; // avgRow stores the average for the specific row in the values array
    double sample = 0; // sample is the sample size of the row which is ten

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
    sample = rowData.length;
    avgRow = sumRow / sample;
    return avgRow;
  }

  /**
   * The purpose of the averageCol(int c) method
   * is to find the average of each column in the values array
   * for the standard deviation
   * c is the parameter of the method and c is the column index for the values
   * array
   * The value returned is avgCol which is the average for the specific column
   * index
   */
  private double averageCol(int c) {
    /* Dwanye */
    double sumCol = 0; // sumCol stores the sum of all of the numbers in the specific column in the
                       // values array
    double avgCol = 0; // avgCol stores the average for the specific column in the values array
    double sample = 0; // sample is the sample size of the column which is ten

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
    sample = colData.length;
    avgCol = sumCol / sample;
    return avgCol;
  }

  /**
   * Set the field rows to the specified value of nRows if the value of
   * nRows is non-negative, otherwise it will default to the value of 10.
   * The parameter is nRows which represents the number of
   * rows of the values array which be non-negative integer.
   * There are no values that are returned.
   */
  public void setRows(int nRows) {
    /* checked */
    if (nRows >= 0) {
      rows = nRows;
    } else {
      rows = 10;
      System.out.println("The number of rows must be greater or equal to zero.");
    }
  }

  /**
   * No parameters are to be passed in.
   * Returns the value of the rows field
   */
  public int getRows() {
    return rows;
  }

  /**
   * Set the field columns to the specified value of nColumns if the value of
   * nColumns is non-negative, otherwise it will default to the value of 10.
   * The parameter is nColumns which represents the number of
   * rows of the values array which be non-negative integer.
   * There are no values that are returned.
   */
  public void setColumns(int nColumns) {
    /* checked */
    if (nColumns >= 0) {
      columns = nColumns;
    } else {
      columns = 10;
      System.out.println("The number of columns must be greater or equal to zero.");
    }
  }

  /**
   * No parameters are to be passed in.
   * Returns the value of columns field
   */
  public int getColumns() {
    return columns;
  }
}