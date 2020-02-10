import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.lang.ref.Cleaner;
import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;

public class CCC2019Senior {
  static void flipper() {
    Scanner reader = new Scanner(System.in);
    String input = reader.nextLine();
    int hCount = input.length() - input.replaceAll("H", "").length();
    int vCount = input.length() - input.replaceAll("V", "").length();
    if(hCount % 2 == 0) {
      if(vCount % 2 == 0) {
        System.out.println("1 2");
        System.out.println("3 4");
      } else {
        System.out.println("2 1");
        System.out.println("4 3");
      }
    } else {
      if(vCount % 2 == 0) {
        System.out.println("3 4");
        System.out.println("1 2");
      } else {
        System.out.println("4 3");
        System.out.println("2 1");
      }
    }
  }

  static void prettyAveragePrimes() {
    Scanner reader = new Scanner(System.in);
    int cases = reader.nextInt();

    for(int i = 0; i < cases; i++) {
      int sumOfPrimes = reader.nextInt() * 2;
      for(int num1 = (int)Math.ceil(Math.sqrt(sumOfPrimes));; num1++) {
        int num2 = sumOfPrimes - num1;
        if(isPrime(num1) && isPrime(num2)) {
          System.out.println(num1 + " " + num2);
          break;
        }
      }
    }
  }

  static boolean isPrime(int num) {
    double sqrt = Math.sqrt(num);
    for(int i = 2; i <= sqrt; i++) {
      if(num % i == 0) {
        return false;
      }
    }
    return true;
  }

  static final int NA = 1000000001;

  static void arithmeticSquare() {
    Scanner reader = new Scanner(System.in);
    boolean[][] isSolved = new boolean[3][3];
    List unSolved;
    int[][] output = new int[3][3];

    for(int row = 0; row < 3; row++) {
      for(int col = 0; col < 3; col++) {
        String in = reader.next();
        isSolved[row][col] = !in.equals("X");
        output[row][col] = isSolved[row][col] ? Integer.parseInt(in) : NA;
      }
    }

    unSolved = findUnsolved(isSolved);

    while(unSolved.contains(1)) {
      int i = unSolved.indexOf(1);
      if(i < 3) {
        // Horizontal
        if(output[i][0] == NA) {
          output[i][0] = output[i][1] + output[i][1] - output[i][2];
          isSolved[i][0] = true;
        } else if(output[i][1] == NA) {
          output[i][1] = output[i][0] + (output[i][2] - output[i][0]) / 2;
          isSolved[i][1] = true;
        } else if(output[i][2] == NA) {
          output[i][2] = output[i][1] + output[i][1] - output[i][0];
          isSolved[i][2] = true;
        }
      } else {
        // Vertical
        if(output[0][i] == NA) {
          output[0][i] = output[1][i] + output[1][i] - output[2][i];
          isSolved[0][i] = true;
        } else if(output[1][i] == NA) {
          output[1][i] = output[0][i] + (output[2][i] - output[0][i]) / 2;
          isSolved[1][i] = true;
        } else if(output[2][i] == NA) {
          output[2][i] = output[1][i] + output[1][i] - output[0][i];
          isSolved[2][i] = true;
        }
      }
//      System.out.println(unSolved);
      unSolved = findUnsolved(isSolved);
    }

/** SOLVE: Test Case
1 2 3
X 3 X
X 4 X

 1 2 3
 2 3 4
 3 4 5

 1 2 3
 1 3 5
 1 4 7
 ...
 */

//    System.out.println("Final State: " + Arrays.deepToString(isSolved));
//    System.out.println("Final Square: " + Arrays.deepToString(output));
//    System.out.println("Final Unsolved: " + unSolved);
    for(int row = 0; row < 3; row++) {
      for(int col = 0; col < 3; col++) {
        System.out.print(output[row][col]);
        if(col != 2)
          System.out.print(" ");
      }
      System.out.println();
    }
  }

  static List<Integer> findUnsolved(boolean[][] isSolved) {
    List<Integer> unSolved = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

    for(int row = 0; row < 3; row++)
      for(int col = 0; col < 3; col++)
        if(!isSolved[row][col])
          unSolved.set(row, unSolved.get(row) + 1);
    for(int col = 0; col < 3; col++)
      for(int row = 0; row < 3; row++)
        if(!isSolved[row][col])
          unSolved.set(3 + col, unSolved.get(col) + 1);

    return unSolved;
  }

  static boolean isValid(int[][] ints) {
    int x = 0;
    int y = 0;
    if(
        ints[y][0] - ints[y][1] == ints[y][1] - ints[y++][2] &&
        ints[y][0] - ints[y][1] == ints[y][1] - ints[y++][2] &&
        ints[y][0] - ints[y][1] == ints[y][1] - ints[y++][2] &&
        ints[0][x] - ints[1][x] == ints[1][x] - ints[2][x++] &&
        ints[0][x] - ints[1][x] == ints[1][x] - ints[2][x++] &&
        ints[0][x] - ints[1][x] == ints[1][x] - ints[2][x++]
    )
      return true;
    return false;
  }

  static void tourism() {
    Scanner reader = new Scanner(System.in);
    int attractions = reader.nextInt();
    int dailyMax = reader.nextInt();
    int[] scores = new int[attractions];

    for(int i = 0; i < attractions; i++) {
      scores[i] = reader.nextInt();
    }

    int days = (int)Math.ceil((double)attractions / dailyMax);


  }

/**
4 2
3
1 2
4 2 1
6 1 4 2

*/
  static void triangleTheDataStructure() {
    Scanner reader = new Scanner(System.in);
    int size = reader.nextInt();
    int subSize = reader.nextInt();

    /** C
     * Check values while taking input
     * C */
    /** B */
    Map<Integer, Set<Triangle>> values = new TreeMap<>((o1, o2) -> o1 > o2 ? -1 : o1.equals(o2) ? 0 : 1);

    for(int i = 0; i < size; i++) {
      for(int j = 0; j < i + 1; j++) {
        int value = reader.nextInt();
        if(!values.containsKey(value))
          values.put(value, new HashSet<>(Arrays.asList(new Triangle(i, j))));
        else
          values.get(value).add(new Triangle(i, j));
      }
    }

//    for(int topY = 0; topY <= size - subSize; topY++) {
//      for(int topX = 0; topX <= topY; topX++) {
//        // TODO: Check triangle max based on top...
//        for(int i : values.keySet()) {
//
//        }
//      }
//    }
    int[][] maxValues = new int[size - subSize + 1][];
    for(int i = 0; i < maxValues.length; i++) {
      maxValues[i] = new int[i + 1];
    }

    int count = 0;
    int total = (size - subSize + 1) * (size - subSize + 2) / 2;
    for(int i : values.keySet()) {
//      System.out.println("Value: " + i);
      Set<Triangle> triangleSet = values.get(i);
//      System.out.println(" Points: " + triangleSet);
      for(Triangle triangle: triangleSet) {
//        System.out.println("  Visiting: " + triangle);
        // Add value for all points above, in maxValues
        for(int y = triangle.y; y > triangle.y - subSize; y--) {
          for(int x = triangle.x; x >= triangle.x + y - triangle.y; x--) {
            try {
//              System.out.println("x: " + x + ", y:" + y);
              if(maxValues[y][x] < i) {
//                System.out.println("WTF");
                maxValues[y][x] = i;
//                System.out.println(i);
//                System.out.println(1);
                count++;
              }
//              System.out.println("NEXT");
//              System.out.println(Arrays.deepToString(maxValues));
            } catch(IndexOutOfBoundsException ignored) {}
          }
        }
      }
      if(count == total) {
//        System.out.println("Finished maxes");
        break;
      }
    }
//    System.out.println("Done");

    int max = 0;
    for(int[] i : maxValues)
      for(int j : i)
        max += j;
    System.out.println(max);

    /**
     * 0          0
     * 1         0 1
     * 2        0 1 2
     * 3       0 1 2 3
     * 4      0 1 2 3 4
     * 5     0 1 2 3 4 5
     * 6    0 1*2*3 4 5 6
     * 7   0 1 2 3 4 5 6 7
     * 8  0 1 2 3 4 5 6 7 8
     * 9 0 1 2 3 4 5 6 7 8 9
     */
    /** B */





    /** A
    int[][] values = new int[size][];

    for(int i = 0; i < size; i++) {
      values[i] = new int[i + 1];
      for(int j = 0; j < i + 1; j++) {
        values[i][j] = reader.nextInt();
      }
    }

    int total = 0;
    int max = 0;
    for(int topY = 0; topY <= size - subSize; topY++) {
      for(int topX = 0; topX <= topY; topX++) {
//        System.out.println("Starting at [" + topY + "][" + topX + "]");
        for(int relY = 0; relY < subSize; relY++) {
          for(int relX = 0; relX <= relY; relX++) {
//            System.out.println((topY + relY) + " and " + (topX + relX));
            max = Math.max(max, values[topY + relY][topX + relX]);
          }
        }
//        System.out.println("Max: " + max);
        total += max;
        max = 0;
      }
    }

//    System.out.println("Traingle:");
//    for(int[] i : values)
//      System.out.println(Arrays.toString(i));
    System.out.println(total);
     A */
  }
}

class Triangle {
  int y;
  int x;
  public Triangle(int y, int x) {
    this.y = y;
    this.x = x;
  }

  @Override
  public String toString() {
    return "[" + this.y + ", " + this.x + "]";
  }

  @Override
  public boolean equals(Object o) {
    if(this == o)
      return true;
    if(o == null || this.getClass() != o.getClass())
      return false;

    final Triangle triangle = (Triangle)o;

    if(this.y != triangle.y)
      return false;
    return this.x == triangle.x;
  }

  @Override
  public int hashCode() {
    int result = this.y;
    result = 31 * result + this.x;
    return result;
  }
}