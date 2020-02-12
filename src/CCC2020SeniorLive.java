import javax.sound.sampled.EnumControl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.IllegalCharsetNameException;
import java.util.*;

public class CCC2020SeniorLive {
  static void surmisingASprintersSpeed() {
    Scanner reader = new Scanner(System.in);
    int observations = reader.nextInt();

    Map<Integer, Integer> positions = new TreeMap<>();

    for (int i = 0; i < observations; i++)
      positions.put(reader.nextInt(), reader.nextInt());

    int t1 = Integer.MAX_VALUE;
    int t2 = Integer.MAX_VALUE;
    double speed = -1;
    for (int t : positions.keySet()) {
      if (t1 == Integer.MAX_VALUE) {
        t1 = t;
        continue;
      } else if (t2 == Integer.MAX_VALUE) {
        t2 = t;
        speed = Math.abs((positions.get(t2) - positions.get(t1)) / (double) (t2 - t1));
        continue;
      }

      t1 = t2;
      t2 = t;
      speed = Math.max(speed, Math.abs((positions.get(t2) - positions.get(t1)) / (double) (t2 - t1)));
//      System.out.println("t1: " + t1 + ", t2: " + t2 + ", v: " + speed);
//      System.out.println("SPEED: " + speed);
    }

    System.out.println(speed);
  }

/**
3
4
3 10 8 14
1 11 12 12
6 2 3 9

*/
  static void escapeRoom() {
    Scanner reader = new Scanner(System.in);
    int row = reader.nextInt();
    int col = reader.nextInt();
    int[][] cells = new int[row + 1][col + 1];

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    // Input for room cells
    for (int i = 1; i <= row; i++) {
      String[] nums = new String[0];
      try {
        nums = bufferedReader.readLine().split(" ");
      } catch (IOException e) {
        e.printStackTrace();
      }
      for (int j = 1; j <= col; j++)
        cells[i][j] = Integer.parseInt(nums[j - 1]);
    }

    // Start at top left room and move through cells
    boolean[][] visited = new boolean[row + 1][col + 1];
    Queue<Map.Entry<Integer, Integer>> positions = new LinkedList<>();
    positions.add(new AbstractMap.SimpleEntry(1, 1));
    visited[1][1] = true;

//    Keep moving until all visitable cells are visited
    while(!positions.isEmpty()) {
      Map.Entry entry = positions.element();
      positions.remove();
      int r = (int)entry.getKey();
      int c = (int)entry.getValue();
      int product = cells[r][c];

      System.out.println("Processing: r" + r + " c" + c + ": " + product);
      for (int factor1 = 1; factor1 <= Math.sqrt(product); factor1++) {
        // Is a valid factor
        System.out.println(" Factor1: " + factor1);
        int factor2 = product / factor1;
        if(factor1 * factor2 != product) {
          // Go to next factors if current factors are not actual factors
          System.out.println("     " + factor1 + " * " + factor2 + " =/= " + product);
          continue;
        }
        System.out.println("  Factor2: " + factor2);

        // Visit factor positions if not visited yet (prevent inf. looping)
        // and if factor2 (higher) is inside room
        if(factor1 <= row && factor2 <= col && !visited[factor1][factor2]) {
          positions.add(new AbstractMap.SimpleEntry(factor1, factor2));
          visited[factor1][factor2] = true;
          System.out.println("   ADDED: " + factor1 + ", " + factor2);
        }
        if(factor2 <= row && factor1 <= col && !visited[factor2][factor1]) {
          positions.add(new AbstractMap.SimpleEntry(factor2, factor1));
          visited[factor2][factor1] = true;
          System.out.println("   ADDED: " + factor2 + ", " + factor1);
        }
      }

      if(visited[row][col]) {
        System.out.println("yes");
        return;
      }
    }
    System.out.println("no");
  }

  static Set<String> unique = new HashSet<>();
  static void searchingForStrings() {
    Scanner reader = new Scanner(System.in);
    String needle = reader.nextLine();
    String haystack = reader.nextLine();

    stringPermute(needle, 0, needle.length() - 1);
    System.out.println(unique);
    int count = 0;
    for(String s : unique) {
      if(haystack.contains(s)) {
        count++;
      }
    }

    System.out.println(count);
  }

  static void stringPermute(String string, int startIndex, int endIndex) {
//    System.out.println("NOW Permuting: " + string + "; start: " + startIndex + ", end: " + endIndex);
    if(startIndex == endIndex) {
//      System.out.println("  " + string);
//      System.out.println(string);
      unique.add(string);
    } else {
      for (int i = endIndex; i > startIndex; i--) {
        // Swap i and endIndex
        char[] chars = string.toCharArray();
        char temp = chars[startIndex];
        chars[startIndex] = chars[i];
        chars[i] = temp;

//        System.out.println(" Recursively A permuting: '" + String.valueOf(chars) + "'");
        stringPermute(String.valueOf(chars), startIndex + 1, endIndex);
      }
      // Swap none
//      System.out.println(" Recursively B permuting: '" + string + "'");
      stringPermute(string, startIndex + 1, endIndex);
    }
  }

  static void d() {
    Scanner reader = new Scanner(System.in);
  }

  static void e() {
    Scanner reader = new Scanner(System.in);
  }
}