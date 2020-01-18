import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    int maxTime = reader.nextInt();
    int choreCount = reader.nextInt();
    Map<Integer, Integer> chores = new TreeMap<>();

    for(int i = 0; i < choreCount; i++) {
      int choreTime = reader.nextInt();
      chores.put(choreTime, chores.getOrDefault(choreTime, 0) + 1);
    }

    Iterator iterator = chores.keySet().iterator();
    int choresDone = 0;
    for(int time = 0; time < maxTime;) {
      if(iterator.hasNext()) {
        int key = (int)iterator.next();
        for(int j = 0; j < chores.get(key); j++) {
          if(time + key <= maxTime) {
            time += key;
            choresDone++;
          } else {
            time = maxTime;
            break;
          }
        }
      }
    }

    System.out.println(choresDone);
  }
}