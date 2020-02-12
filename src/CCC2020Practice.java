import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CCC2020Practice {
  static void whatIsNDaddy() {
    Scanner reader = new Scanner(System.in);
    int n = reader.nextInt();

    int count = 0;
    for(int l = Math.min(n, 5), r = Math.max(n - 5, 0); l >= r; l--, r++, count++) {
      System.out.println("left: " + l + ", right: " + r + ", count: " + count);
    }
    System.out.println(count);
  }

  static void sumGame() {
    Scanner reader = new Scanner(System.in);
    int days = reader.nextInt();
    int[][] score = new int[2][days];

    for(int i = 0; i < days; i++)
      score[0][i] = reader.nextInt();

    for(int i = 0; i < days; i++)
      score[1][i] = reader.nextInt();

    int[] totals = new int[2];
    int count = 0;

    for(int i = 0; i < days; i++) {
      totals[0] += score[0][i];
      totals[1] += score[1][i];
      if(totals[0] == totals[1])
        count = i + 1;
    }
    System.out.println(count);
  }

  static void absolutelyAcidic() {
    Scanner reader = new Scanner(System.in);
    int sensors = reader.nextInt();
    Map<Integer, Integer> acidity = new HashMap<>();

    int lowFreq = 0;
    int lowNum = 0;
    int highFreq = 0;
    int highNum = 0;
    int highCount = 1;
    for(int i = 0; i < sensors; i++) {
      int keyAcid = reader.nextInt();
      acidity.putIfAbsent(keyAcid, 0);
      acidity.replace(keyAcid, acidity.get(keyAcid) + 1);
      int freq = acidity.get(keyAcid);
      if(freq > highFreq) {
        // Possible error for lowFreq?
        highFreq = freq;
        highNum = keyAcid;
        highCount = 1;
      } else if(freq == highFreq) {
        highCount++;
      }
      if(keyAcid != highNum && lowFreq < freq) {
        lowFreq = freq;
        lowNum = keyAcid;
      }
    }

    System.out.println("Low acid: " + lowNum + ", x" + lowFreq + "");
    System.out.println("High acid: " + highNum + ", x" + highFreq + "");
    System.out.println("Num of max freq: " + highCount);

    int acid1 = 0;
    int acid2 = 0;
    int acid3 = 0;
    for(int keyAcid : acidity.keySet()) {
      int count = acidity.get(keyAcid);
      if(highCount > 1) {
        if(count == highFreq) {
          if(acid1 == 0) {
            acid1 = keyAcid;
          } else {
            acid1 = Math.min(acid1, keyAcid);
          }
          acid2 = Math.max(acid2, keyAcid);
        } else
          continue;
      } else {
       if(count == highFreq) {
         acid1 = keyAcid;
       } else if(count == lowFreq) {
         if(acid2 == 0) {
           acid2 = keyAcid;
         } else {
           acid2 = Math.min(acid2, keyAcid);
         }
         acid3 = Math.max(acid3, keyAcid);
       }
      }
    }

    System.out.println(acid1);
    System.out.println(acid2);
    System.out.println(acid3);
    if(acid3 == 0) {
      System.out.println(Math.abs(acid1 - acid2));
    } else {
      System.out.println(Math.max(Math.abs(acid1 - acid2), Math.abs(acid1 - acid3)));
    }
  }

  static int pens;
  static int[][] adjMat;
  static Map<Path, Set<Integer>> connections = new HashMap<>();

/**
4
3 1 2 3 7 4 6
4 1 2 4 5 7 7 2 6
4 4 7 6 5 4 8 9 2
5 3 2 4 7 8 4 7 4 7 7

*/
  static void animalFarm() {
    /**
     * Assumption: weights of shapes that are the same side will be EQUAL
     */

    readRawInput();
//    System.out.println(connections);
    processInput();
    for(int[] i : adjMat)
      System.out.println(Arrays.toString(i));
//    int exclude = minimumSpanningTree(pens - 1);
//    int include = minimumSpanningTree(pens);
//
//    System.out.println(Math.min(exclude, include));
    /**
     * FK NO LOL NOT THIS AGAIN!!
     *
     * Concepts: Input processing into graph, then:
     * Perform 2 MST (minimum spanning tree); one including outside 'node', and one excluding it.
     */
  }

  static void readRawInput() {
    Scanner reader = new Scanner(System.in);
    pens = reader.nextInt();

    for(int pen = 0; pen < pens; pen++) {
      final int edges = reader.nextInt();
      final int[] cornerIDs = new int[edges];
      final int[] weights = new int[edges];
      for(int i = 0; i < edges; i++)
        cornerIDs[i] = reader.nextInt();
      for(int i = 0; i < edges; i++)
        weights[i] = reader.nextInt();

      for(int pt = 0; pt < edges; pt++) {
        // modulo adds the last line from first to last pt
        int lowPt = Math.min(cornerIDs[pt], cornerIDs[(pt + 1) % edges]);
        int highPt = Math.max(cornerIDs[pt], cornerIDs[(pt + 1) % edges]);
        connections.putIfAbsent(new Path(lowPt, highPt, weights[pt]), new HashSet<>());
        connections.get(new Path(lowPt, highPt, weights[pt])).add(pen);
      }
    }

    adjMat = new int[pens + 1][pens + 1];
    /** Possibly Need? */
    for(int[] i : adjMat)
      Arrays.fill(i, Integer.MAX_VALUE);
    /** Possibly Need? */
  }

  /**
   * TODO: FIX THE PROBLEM THAT PROBABLY LIES HERE LOL!
   */
  static void processInput() {
    for(Path path : connections.keySet()) {
//      int start = path.start;
//      int end = path.end;
      int weight = path.weight;
      Set<Integer> shapes = connections.get(path);
      Iterator intIterator = shapes.iterator();
      /** if(shapes.size() == 2 && adjMat[start][end] == -1) { */
      if(shapes.size() > 1) {
        int shape1 = (int)intIterator.next();
        int shape2 = (int)intIterator.next();
        adjMat[shape1][shape2] = weight;
        adjMat[shape2][shape1] = weight;
      } else {
        int shape = (int)intIterator.next();
        adjMat[shape][pens] = Math.min(adjMat[shape][pens], weight);
        adjMat[pens][shape] = Math.min(adjMat[shape][pens], weight);
      }
    }
  }

  static int minimumSpanningTree(int pens) {
    Set<Integer> visited = new HashSet<>();
    int min = 0;

    visited.add(0);
    while(visited.size() <= pens) {
      int minWeight = Integer.MAX_VALUE;
      int minNode = -1;
      for(int node : visited) {
        for(int j = 0; j <= pens; j++) {
//          System.out.println("adjMat[" + node + "][" + j + "] = " + adjMat[node][j] + " and " + minWeight);
          if(adjMat[node][j] < minWeight && !visited.contains(j)) {
            minWeight = adjMat[node][j];
            minNode = j;
          }
        }
      }
      if(minNode != -1) {
        visited.add(minNode);
      } else {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! MST FAILED!!!!! MIN NODE IS -1");
      }
      min += minWeight;
//      System.out.println("Added: " + minWeight);
    }
//    System.out.println("Gave back: " + min);
//    System.out.println(visited);
    return min;
  }
}


class Path {
  int start;
  int end;
  int weight;

  Path(int start, int end, int weight) {
    this.start = start;
    this.end = end;
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o)
      return true;
    if(o == null || this.getClass() != o.getClass())
      return false;

    final Path path = (Path)o;

    if(this.start != path.start)
      return false;
    if(this.end != path.end)
      return false;
    return this.weight == path.weight;
  }

  @Override
  public int hashCode() {
    int result = this.start;
    result = 31 * result + this.end;
    result = 31 * result + this.weight;
    return result;
  }

  @Override
  public String toString() {
    return "Path{" + "start=" + start + ", end=" + end + ", weight=" + weight + '}';
  }
}