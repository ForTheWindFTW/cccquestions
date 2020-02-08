import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
  public static void main(String[] args) {
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
}