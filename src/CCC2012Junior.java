import java.util.Scanner;

public class CCC2012Junior {
  static void speedFinesAreNotFine() {
    Scanner reader = new Scanner(System.in);

    System.out.print("Enter the speed limit: ");
    int limit = reader.nextInt();
    System.out.print("Enter the recorded speed of the car: ");
    int speed = reader.nextInt();

    String message;

    if(speed <+ limit) {
      message = "Congratulations, you are within the speed limit!";
    } else if(speed <= limit + 20) {
      message = "You are speeding and your fine is $100.";
    } else if(speed <= limit + 30) {
      message = "You are speeding and your fine is $270.";
    } else {
      message = "You are speeding and your fine is $500.";
    }

    System.out.println(message);
  }

  static void soundsFishy() {
    Scanner reader = new Scanner(System.in);
    int[] depth = new int[4];

    for(int i = 0; i < depth.length; i++) {
      depth[i] = reader.nextInt();
    }

    String output = "";
    for(int i = 0; i < depth.length - 1; i++) {
      if(depth[i + 1] - depth[i] > 0)
        output += "+";
      else if(depth[i + 1] - depth[i] < 0)
        output += "-";
      else
        output += "0";
    }

    if("---".equals(output))
      System.out.println("Fish Diving");
    else if("+++".equals(output))
      System.out.println("Fish Rising");
    else if("000".equals(output))
      System.out.println("Fish At Constant Depth");
    else
      System.out.println("No Fish");
  }

  static void iconScaling() {
    Scanner reader = new Scanner(System.in);
    int sf = reader.nextInt();

    StringBuilder sb = new StringBuilder();
    sb.append(
      repeat(repeat("*", sf) + repeat("x", sf) + repeat("*", sf) + "\n", sf) +
      repeat(repeat(" ", sf) + repeat("x", sf) + repeat("x", sf) + "\n", sf) +
      repeat(repeat("*", sf) + repeat(" ", sf) + repeat("*", sf) + "\n", sf)
    );

    System.out.println(sb);
  }

  private static String repeat(String letter, int count) {
    StringBuilder string = new StringBuilder();
    for(int i = 0; i < count; i++) {
      string.append(letter);
    }
    return string.toString();
  }

  static void bigBangSecrets() {
    Scanner reader = new Scanner(System.in);
    int shift = reader.nextInt();
    reader.nextLine();
    String encoded = reader.nextLine();

    for(int i = 0; i < encoded.length(); i++) {
      decode(encoded.charAt(i), 3 * (i + 1) + shift);
    }
  }

  static void decode(char letter, int shiftValue) {
    int ascii = (int) letter - shiftValue;
    if(ascii < (int)'A')
      ascii += 26;
    System.out.print((char)ascii);
  }

  static void aCoinGame() {
    Scanner reader = new Scanner(System.in);
  }
}
