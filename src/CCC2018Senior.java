import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CCC2018Senior {
  static void voronoiVillages() {
    Scanner reader = new Scanner(System.in);
    int villages = reader.nextInt();
    Set<Integer> villagePositions = new TreeSet<>();

    for(int i = 0; i < villages; i++)
      villagePositions.add(reader.nextInt());

    Iterator i = villagePositions.iterator();
    int pos1 = (int)i.next();
    int pos2 = (int)i.next();
    int pos3 = (int)i.next();
    double minSize = (pos3 - pos1) / 2.0;
    while(i.hasNext()) {
      pos1 = pos2;
      pos2 = pos3;
      pos3 = (int)i.next();
      minSize = Math.min(minSize, (pos3 - pos1) / 2.0);
    }

    NumberFormat numberFormat = new DecimalFormat(".#");
    System.out.println(numberFormat.format(minSize));
  }

  static void sunflowers() {
    Scanner reader = new Scanner(System.in);
    int size = reader.nextInt();
    int[][] grid = new int[size][size];

    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        grid[i][j] = reader.nextInt();

    while(!isCorrect(grid))
      grid = rotateArrays(grid);

    for(int[] i : grid)
      System.out.println(Arrays.toString(i).replaceAll(",", "").replace("[", "").replaceAll("]", ""));
  }

  static int[][] rotateArrays(int[][] in) {
    int size = in.length;
    int i = size - 1;
    int[][] out = new int[size][size];

    for(int j = 0; j < size; j++)
      for(int k = 0; k < size; k++)
        out[j][k] = in[k][i - j];

    return out;
  }

  static boolean isCorrect(int[][] grid) {
    int size = grid.length;

    for(int i = 1; i < size; i++)
      if(grid[i - 1][0] > grid[i][0] || grid[0][i - 1] > grid[0][i])
        return false;
    return true;
  }

  static Tile[][] factory;
  static boolean[][] detectedTiles;
  static void roboThieves() {
    Scanner reader = new Scanner(System.in);
    String[] dimensions = reader.nextLine().split(" ", 2);
    int height = Integer.parseInt(dimensions[0]);
    int width = Integer.parseInt(dimensions[1]);
    int hStart = -1;
    int wStart = -1;

    factory = new Tile[height][width];
    detectedTiles = new boolean[height][width];

    for(int i = 0; i < height; i++) {
      char[] cells = reader.nextLine().toCharArray();
      for(int j = 0; j < width; j++) {
        if(cells[j] == 'S') {
          factory[i][j] = new Tile(i, j, 'S', 0);
          hStart = i;
          wStart = j;
        } else
          factory[i][j] = new Tile(i, j, cells[j], -1);
      }
    }

    for(int i = 0; i < height; i++)
      for(int j = 0; j < width; j++) {
        if(factory[i][j].type == 'C') {
          int i0 = i;
          int j0 = j;
          // Camera Position
          detectedTiles[i0][j0] = true;
          // Up of Camera
          while(i0 > 0 && factory[i0 - 1][j0].type != 'W') {
            i0--;
            detectedTiles[i0][j0] = factory[i0][j0].type == '.' || factory[i0][j0].type == 'C' || factory[i0][j0].type == 'S';
          }
          i0 = i;
          // Down of Camera
          while(i0 < height - 1 && factory[i0 + 1][j0].type != 'W') {
            i0++;
            detectedTiles[i0][j0] = factory[i0][j0].type == '.' || factory[i0][j0].type == 'C' || factory[i0][j0].type == 'S';
          }
          i0 = i;
          // Left of Camera
          while(j0 > 0 && factory[i0][j0 - 1].type != 'W') {
            j0--;
            detectedTiles[i0][j0] = factory[i0][j0].type == '.' || factory[i0][j0].type == 'C' || factory[i0][j0].type == 'S';
          }
          j0 = j;
          // Right of Camera
          while(j0 < width - 1 && factory[i0][j0 + 1].type != 'W') {
            j0++;
            detectedTiles[i0][j0] = factory[i0][j0].type == '.' || factory[i0][j0].type == 'C' || factory[i0][j0].type == 'S';
          }
        }
      }

    // BFS Start
    Queue<Tile> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.d)));
    queue.add(factory[hStart][wStart]);

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while(!queue.isEmpty()) {
      Tile current = queue.remove();
//      System.out.println("At: " + current);
      if(detectedTiles[current.y][current.x] || factory[current.y][current.x].type == 'W') {
//        System.out.println(" -Skipped (Invalid Position): " + current);
        continue;
      } else {
        for(int[] direction : directions) {
          int dy = direction[0];
          int dx = direction[1];
          Tile destTile = factory[current.y + dy][current.x + dx];
//          System.out.println("  -Checking: " + destTile);
          if(destTile.d != -1 || destTile.type == 'X' || detectedTiles[destTile.y][destTile.x]) {
//            System.out.println("   -Skipped: " + destTile);
            continue;
          } else if(destTile.type == '.') {
            destTile.d = current.d + 1;
            if(destTile.type != 'W') {
              queue.add(destTile);
//              System.out.println("   +Added (.): " + destTile);
            }
          } else {
            destTile.d = current.d + 1;
            // Move along conveyors
            while(destTile.type == 'U' || destTile.type == 'D' || destTile.type == 'L' || destTile.type == 'R') {
              switch(destTile.type) {
                case 'U':
                  factory[destTile.y - 1][destTile.x].d = current.d + 1;
                  destTile = factory[destTile.y - 1][destTile.x];
                  break;
                case 'D':
                  factory[destTile.y + 1][destTile.x].d = current.d + 1;
                  destTile = factory[destTile.y + 1][destTile.x];
                  break;
                case 'L':
                  factory[destTile.y][destTile.x - 1].d = current.d + 1;
                  destTile = factory[destTile.y][destTile.x - 1];
                  break;
                case 'R':
                  factory[destTile.y][destTile.x + 1].d = current.d + 1;
                  destTile = factory[destTile.y][destTile.x + 1];
                  break;
              }
              // RIP Didn't give more points
              if(destTile.type == '.' && detectedTiles[destTile.y][destTile.x]) {
                destTile.d = -1;
                break;
              }
//                System.out.println("    Conveyed to: " + destTile);
            }
            if(destTile.type != 'W') {
              queue.add(destTile);
//                System.out.println("   +Added (Conveyor): " + destTile);
            }
          }
        }
      }
    }

//    System.out.println("Done");
    for(int i = 0; i < height; i++)
      for(int j = 0; j < width; j++)
        if(factory[i][j].type == '.')
          System.out.println(factory[i][j].d);
  }

  static void balancedTrees() {
    Scanner reader = new Scanner(System.in);
    int weight = reader.nextInt();
    int balancedTrees = -1;

    System.out.println(balancedTrees);

    /**
     * TODO: Understand wtf this is
     * Perfectly Balanced Tree:
     * - Positive Integer Weight
     * if Weight is 1
     *   single node
     * else
     *   root node with branches to k, and 2 <= k <= Weight, k must be identical and perfectly balanced themselves
     *
     * all K subtrees have same weight, must be max int so sum of weights of k is less or equal to Weight
     */
  }

  static void maximumStrategicSavings() {
    Scanner reader = new Scanner(System.in);
    int planets = reader.nextInt();
    int cities = reader.nextInt();

    int[][] flights;
  }
}

class Tile {
  int y;
  int x;
  char type;
  int d;
  public Tile(int y, int x, char type, int d) {
    this.y = y;
    this.x = x;
    this.type = type;
    this.d = d;
  }

  @Override
  public String toString() {
    return "Tile{" + "y=" + y + ", x=" + x + ", type=" + type + ", d=" + d + '}';
//    return Integer.toString(d);
  }
}