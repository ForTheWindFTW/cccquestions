import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
  static int pens;
  static int[][] adjMat;
  static Map<Path, Set<Integer>> connections = new HashMap<>();

  public static void main(String[] args) {
    animalFarm();
  }

  static void animalFarm() {
    readRawInput();
    processInput();
    int exclude = minimumSpanningTree(pens - 1);
    int include = minimumSpanningTree(pens);

    System.out.println(Math.min(exclude, include));
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

  static void processInput() {
    for(Path path : connections.keySet()) {
//      int start = path.start;
//      int end = path.end;
      int weight = path.weight;
      Set<Integer> shapes = connections.get(path);
      Iterator intIterator = shapes.iterator();
      /** if(shapes.size() == 2 && adjMat[start][end] == -1) { */
      if(shapes.size() == 2) {
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
          if(adjMat[node][j] < minWeight && !visited.contains(j)) {
            minWeight = adjMat[node][j];
            minNode = j;
          }
        }
      }
      visited.add(minNode);
      min += minWeight;
    }
    return min;
  }
}

//class Path {
//  int start;
//  int end;
//  int weight;
//
//  Path(int start, int end, int weight) {
//    this.start = start;
//    this.end = end;
//    this.weight = weight;
//  }
//
//  @Override
//  public boolean equals(Object o) {
//    if(this == o)
//      return true;
//    if(o == null || this.getClass() != o.getClass())
//      return false;
//
//    final Path path = (Path)o;
//
//    if(this.start != path.start)
//      return false;
//    if(this.end != path.end)
//      return false;
//    return this.weight == path.weight;
//  }
//
//  @Override
//  public int hashCode() {
//    int result = this.start;
//    result = 31 * result + this.end;
//    result = 31 * result + this.weight;
//    return result;
//  }
//
//  @Override
//  public String toString() {
//    return "Path{" + "start=" + start + ", end=" + end + ", weight=" + weight + '}';
//  }
//}