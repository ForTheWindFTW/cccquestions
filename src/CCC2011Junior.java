import java.util.*;

public class CCC2011Junior {
  static void whichAlien() {
    Scanner reader = new Scanner(System.in);
    System.out.println("How many antennas?");
    int antennas = reader.nextInt();
    System.out.println("How many eyes?");
    int eyes = reader.nextInt();

    if(3 <= antennas && eyes <= 4)
      System.out.println("TroyMartian");
    if(antennas <= 6 && 2 <= eyes)
      System.out.println("VladSaturnian");
    if(antennas <= 2 && eyes <= 3)
      System.out.println("GraemeMercurian");
  }

  static void whoHasSeenTheWind() {
    Scanner reader = new Scanner(System.in);
    int humidity = reader.nextInt();
    int maxHours = reader.nextInt();

    for(int t = 1; t <= maxHours; t++) {
      int altitude = -6*t*t*t*t + humidity*t*t*t + 2*t*t + t;
      if(altitude <= 0) {
        System.out.println("The balloon first touches ground at hour:");
        System.out.println(t);
        return;
      }
    }

    System.out.println("The balloon does not touch ground in the given time.");
  }

  static void sumacSequences() {
    Scanner reader = new Scanner(System.in);
    int t1 = reader.nextInt();
    int t2 = reader.nextInt();

    int length = 2;
    for(; t1 - t2 > 0; length++) {
      int temp = t2;
      t2 = t1 - t2;
      t1 = temp;
    }

    System.out.println(length);
  }

  static void boringBusiness() {
    Scanner reader = new Scanner(System.in);
    Map<Integer, Set<Integer>> positions = new HashMap<>();

    addCoordinate(0, -1, positions);

    addBoring(0, -1, 'd', 2, positions);
    addBoring(0, -3, 'r', 3, positions);
    addBoring(3, -3, 'd', 2, positions);
    addBoring(3, -5, 'r', 2, positions);
    addBoring(5, -5, 'u', 2, positions);
    addBoring(5, -3, 'r', 2, positions);
    addBoring(7, -3, 'd', 4, positions);
    addBoring(7, -7, 'l', 8, positions);
    addBoring(-1, -7, 'u', 2, positions);

    int x = -1;
    int y = -5;
    char choice;

    while(true) {
      choice = reader.next().charAt(0);
      if(choice == 'q') break;
      int mag = reader.nextInt();
      boolean isSafe = addBoring(x, y, choice, mag, positions);
      switch(choice) {
        case 'l':
          x -= mag;
          break;
        case 'r':
          x += mag;
          break;
        case 'u':
          y += mag;
          break;
        case 'd':
          y -= mag;
          break;
      }
      System.out.println(x + " " + y + " " + (isSafe ? "safe" : "DANGER"));
      if(!isSafe) break;
    }
//    System.out.println("Finished current method");
  }

  static boolean addBoring(int x0, int y0, char direction, int magnitude, Map<Integer, Set<Integer>> coordinates) {
    switch(direction) {
      case 'l':
        for(int i = 0; i < magnitude; i++) {
          x0--;
          if(!addCoordinate(x0, y0, coordinates)) return false;
        }
        break;
      case 'r':
        for(int i = 0; i < magnitude; i++) {
          x0++;
          if(!addCoordinate(x0, y0, coordinates)) return false;
        }
        break;
      case 'u':
        for(int i = 0; i < magnitude; i++) {
          y0++;
          if(!addCoordinate(x0, y0, coordinates)) return false;
        }
        break;
      case 'd':
        for(int i = 0; i < magnitude; i++) {
          y0--;
          if(!addCoordinate(x0, y0, coordinates)) return false;
        }
        break;
    }
    return true;
  }

  static boolean addCoordinate(int x, int y, Map<Integer, Set<Integer>> coords) {
    if(coords.containsKey(x) && coords.get(x).contains(y)) {
      return false;
    }
    if(coords.containsKey(x)) {
      coords.get(x).add(y);
      return true;
    } else {
      coords.put(x, new HashSet<>());
      coords.get(x).add(y);
      return true;
    }
  }

  static void unfriend() {
    Scanner reader = new Scanner(System.in);
    int people = reader.nextInt();

//    Map<Integer, Set<Integer>> friendList = new HashMap<>();
    int[] friendList = new int[people - 1];

    for(int i = 0; i < friendList.length; i++) {
      friendList[i] = reader.nextInt();
    }

    System.out.println(count(people, friendList));
  }

  static int count(int currentFriend, int[] connections) {
    int answer = 1;
    for(int i = 0; i < connections.length; i++) {
      if(connections[i] == currentFriend) {
        answer *= (1 + count(i + 1, connections));
      }
    }
    return answer;
  }
}
