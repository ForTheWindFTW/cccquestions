import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    int row = reader.nextInt();
    int col = reader.nextInt();
    int[][] cells = new int[row + 1][col + 1];

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    // Input for room cells
    for (int i = 1; i <= row; i++) {
      String[] nums = new String[0];
      try {
        String a = bufferedReader.readLine();
        nums = a.split(" ");
        System.out.println(a);
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
}