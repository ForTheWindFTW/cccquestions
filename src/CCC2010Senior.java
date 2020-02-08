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
    for(int i1 = 0, i2 = i1 + maxLength; i2 < sequence.length(); i2--) {
      for(int i = 0; i < codes.length; i++) {
        if(sequence.substring(i1, Math.min(i2, sequence.length())).matches(codes[i])) {
          decoded += letter[i];
          i1 = i2;
          i2 += maxLength;
        }
      }
    }

    System.out.println(decoded);
  }

  static void firehose() {

  }

  static void animalFarm() {

  }

  static void nutrientTree() {

  }
}
