import java.util.*;

public class Main {
  public static void main(String[] args) {
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
}