import java.util.*;

public class CCC2013Junior {
  static void nextInLine() {
    Scanner reader = new Scanner(System.in);
    int age1 = reader.nextInt();
    int age2 = reader.nextInt();
    int dage = age2 - age1;
    System.out.println(age2 + dage);
  }

  static void rotatingLetters() {
    List validList = Arrays.asList('I', 'O', 'S', 'H', 'Z', 'X', 'N');
    Scanner reader = new Scanner(System.in);
    String letters = reader.nextLine();

    for(int i = 0; i < letters.length(); i++) {
      if(!validList.contains(letters.charAt(i))) {
        System.out.println("NO");
        return;
      }
    }

    System.out.println("YES");
  }

  static void from1987To2013() {
    Scanner reader = new Scanner(System.in);
    int startYear = reader.nextInt();
    List<Character> digits = Arrays.asList();

    int i = startYear;
    do {
      i++;
    } while(!hasDistinctDigits(i));

    System.out.println(i);
  }

  static boolean hasDistinctDigits(int year) {
    List<Character> digits = new ArrayList<>();
    String yr = Integer.toString(year);
    for(int i = 0; i < yr.length(); i++) {
      if(!digits.contains(yr.charAt(i))) {
        digits.add(Character.valueOf(yr.charAt(i)));
      } else {
        return false;
      }
    }
    return true;
  }

  static void timeOnTask() {
    Scanner reader = new Scanner(System.in);
    int minute = reader.nextInt();
    int choreCount = reader.nextInt();
    Map<Integer, Integer> chores = new HashMap<>();

    for(int i = 0; i < choreCount; i++) {
      if(chores.containsKey(i)) {
        chores.put(i, chores.get(i) + 1);
      } else {
        chores.put(i, 1);
      }
    }
  }

  static void chancesOfWinning() {
    Scanner reader = new Scanner(System.in);

  }
}
