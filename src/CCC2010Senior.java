import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CCC2010Senior {
  static void computerPurchase() {
    java.util.Scanner reader = new java.util.Scanner(System.in);
    int computers = Integer.parseInt(reader.nextLine());
    int performance1 = 0;
    int performance2 = 0;
    String id1 = "";
    String id2 = "";

    for(int i = 0; i < computers; i++) {
      String[] computer = reader.nextLine().split(" ", 4);
      String id = computer[0];
      int ram = Integer.parseInt(computer[1]);
      int cpu = Integer.parseInt(computer[2]);
      int space = Integer.parseInt(computer[3]);
      int performance = performance(ram, cpu, space);

//      System.out.println(performance);
//      System.out.println("Id: " + id + ", RAM: " + ram + "GB, CPU: " + cpu + "MHz, SPACE: " + space + "GB, SCORE: " + performance);
      if(performance > performance2 || (performance == performance2 && id2.compareTo(id) < 0)) {
        performance2 = performance;
        id2 = id;
      }
      if(performance2 > performance1 || (performance1 == performance2 && id2.compareTo(id1) < 0)) {
        int tempPerformance = performance1;
        String tempId = id1;
        performance1 = performance2;
        id1 = id2;
        performance2 = tempPerformance;
        id2 = tempId;
      }
      System.out.println(id1);
      System.out.println(id2);
    }

    System.out.println(id1);
    System.out.println(id2);
  }

  static int performance(int ram, int cpu, int space) {
    return 2 * ram + 3 * cpu + space;
  }

  static void huffmanEncoding() {
    java.util.Scanner reader = new java.util.Scanner(System.in);
    int codeCount = Integer.parseInt(reader.nextLine());
    char[] letter = new char[codeCount];
    String[] codes = new String[codeCount];
    int maxLength = 0;

    for(int i = 0; i < codeCount; i++) {
      String[] words = reader.nextLine().split(" ", 2);
      letter[i] = words[0].charAt(0);
      codes[i] = words[1];
      maxLength = Math.max(maxLength, codes[i].length());
    }

    String sequence = reader.nextLine();
    String decoded = "";
    for(int i1 = 0, len = 1; i1 < sequence.length(); len++) {
      String substr = sequence.substring(i1, i1 + len);

      for(int i = 0; i < codes.length; i++) {
        if(substr.matches(codes[i])) {
          decoded += letter[i];
          i1 += len;
          len = 0;
        }
      }

    }

    System.out.println(decoded);
  }

  static void firehose() {
    java.util.Scanner reader = new java.util.Scanner(System.in);
    int houses = reader.nextInt();

    java.util.Set<Integer> addresses = new java.util.TreeSet<>();
    for(int i = 0; i < houses; i++)
      addresses.add(reader.nextInt());

    int hydrantCount = reader.nextInt();
    int minHoseLength = Integer.MAX_VALUE;

    for(int i = 0; i <= 1000000 / (2 * hydrantCount); i++) {
      int hoses = 1;
      java.util.Iterator iterator = addresses.iterator();
      int pos = (int)iterator.next() + i;
      while(iterator.hasNext()) {
        int house = (int)iterator.next();
        if(house > pos + i) {
          pos = house + i;
          hoses++;
        }
      }
      if(hoses <= hydrantCount) {
        minHoseLength = Math.min(minHoseLength, i);
      }
    }

    System.out.println(minHoseLength);
  }

  static void animalFarm() {
    java.util.Scanner reader = new java.util.Scanner(System.in);
    // Number of pens
    int penCount = reader.nextInt();
    System.out.println("PenCount: " + penCount);

    // Weight of sides (+ 1 is for outside 'pen')
    int[][] penWeights = new int[penCount + 1][];

    // Points connected (+ 1 is for outside 'pen')
    List<Integer>[] penPoints = new ArrayList[penCount + 1];

    // Pen connections
    List<Integer>[] penAdjList = new ArrayList[penCount + 1];

    // Reads points and weights to each pen
    for(int i = 0; i < penCount; i++) {
      penPoints[i] = new ArrayList<>();
      penAdjList[i] = new ArrayList<>();

      // Current pen side/point count
      int sides = reader.nextInt();

      penWeights[i] = new int[sides];

      // Point input
      int point1;
      int point2 = reader.nextInt();
      penPoints[i].add(point2);
      for(int j = 1; j < sides; j++) {
        point1 = point2;
        point2 = reader.nextInt();
        penPoints[i].add(point2);

        /** ADD BASED ON CONNECTIONS */
        int connectedShape = indexOfContains(penPoints, point1, point2);
        if(connectedShape > -1) {
          System.out.println(i + " connects to: " + connectedShape);
          penAdjList[i].add(connectedShape);
          int i1 = penPoints[connectedShape].indexOf(point1);
          int i2 = penPoints[connectedShape].indexOf(point2);
          int iMin = Math.min(i1, i2);
          int iMax = Math.max(i1, i2);
          if(iMin != 0 && iMax != 1) {
            penAdjList[connectedShape].set(iMin, i);
          } else {
            penAdjList[connectedShape].set(0, i);
          }
        } else {
          /** Connect to outside */
          penAdjList[i].add(penCount + 10);
        }
        /** ADD BASED ON CONNECTIONS */
      }

      // Weight input
      for(int j = 0; j < sides; j++) {
        penWeights[i][j] = reader.nextInt();
      }
    }

    System.out.println("Done");
    System.out.println(Arrays.deepToString(penWeights));
    System.out.println(Arrays.toString(penPoints));
    System.out.println(Arrays.toString(penAdjList));

    /**
     * TEST CODE:
4
3 1 2 3 7 4 6
4 1 2 4 5 7 7 2 6
4 4 7 6 5 4 8 9 2
5 3 2 4 7 8 4 7 4 7 7

     */
  }

  static int indexOfContains(List<Integer>[] lists, int num1, int num2) {
//    System.out.println("Check: " + Arrays.toString(lists));
    int containCount = 0;
    int iMin = Integer.MAX_VALUE;
    for(int i = 0; i < lists.length; i++) {
//      System.out.println(" Current: " + i);
      if(lists[i] != null && lists[i].containsAll(Arrays.asList(num1, num2))) {
//        System.out.println("  Success: " + i);
        containCount++;
        if(containCount == 2) {
//          System.out.println("   Returned: " + iMin);
          return iMin;
        }
        iMin = Math.min(iMin, i);
      }
    }
//    System.out.println("  Fail: -1");
    return -1;
  }

  static void nutrientTree() {

  }
}
