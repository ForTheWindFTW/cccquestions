import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CCC2010Junior {
  static void whatIsNDaddy() {
    int sum = new Scanner(System.in).nextInt();
    int count = 0;

    for(int i = Math.min(5, sum), j = Math.max(0, sum - 5); i >= j; i--, j++) {
      count++;
    }

    System.out.println(count);
  }

  static void upAndDown() {
    Scanner reader = new Scanner(System.in);
    int nikkyF = reader.nextInt();
    int nikkyB = reader.nextInt();
    int byronF = reader.nextInt();
    int byronB = reader.nextInt();
    int totalSteps = reader.nextInt();

    int nikky = 0;
    int byron = 0;

    for(int i = 0; i < totalSteps;) {
      for(int j = 0; j < nikkyF && i < totalSteps; j++, i++) {
        nikky++;
      }
      for(int j = 0; j < nikkyB && i < totalSteps; j++, i++) {
        nikky--;
      }
    }

    for(int i = 0; i < totalSteps;) {
      for(int j = 0; j < byronF && i < totalSteps; j++, i++) {
        byron++;
      }
      for(int j = 0; j < byronB && i < totalSteps; j++, i++) {
        byron--;
      }
    }

    if(nikky > byron) {
      System.out.println("Nikky");
    } else if (nikky < byron) {
      System.out.println("Byron");
    } else {
      System.out.println("Tied");
    }
  }

  static void punchy() {
    Scanner reader = new Scanner(System.in);
    int[] xy = new int[2];

    while(true) {
      String[] strings = reader.nextLine().split(" ");
      int choice = Integer.parseInt(strings[0]);

      if(choice == 7)
        break;

      int num1 = strings[1].equals("A") ? 0 : 1;

      if(choice == 1) {
        xy[num1] = Integer.parseInt(strings[2]);
        continue;
      } else if(choice == 2) {
        System.out.println(xy[num1]);
        continue;
      }

      int num2 = strings[2].equals("A") ? 0 : 1;

      switch(choice) {
        case 3:
          xy[num1] += xy[num2];
          break;
        case 4:
          xy[num1] *= xy[num2];
          break;
        case 5:
          xy[num1] -= xy[num2];
          break;
        case 6:
          xy[num1] /= xy[num2];
          break;
      }
    }
  }

  static void globalWarming() {
    Scanner reader = new Scanner(System.in);
    int tempCount = reader.nextInt();
    int[] temps = new int[tempCount];
    List<Integer> sequence = new ArrayList<>();

    for(int i = 0; i < tempCount; i++) {
      temps[i] = reader.nextInt();
    }

    for(int i = 0; i < tempCount - 1; i++) {
      temps[i] = temps[i + 1] - temps[i];
    }

    sequence.add(temps[0]);
    for(int i = 0; i < tempCount - 1; i++) {
      if(temps[i] == sequence.get(0)) {
        // Check rest
        for(int j = i; j < tempCount - 1; j++) {
          if(temps[j] != sequence.get((j - i) % sequence.size())) {
            break;
          }
          if(j >= tempCount - 2) {
            i = tempCount - 1;
            break;
          }
        }
      } else {
        // Add to sequence if not correct
        sequence.add(temps[i]);
      }
    }

    System.out.println(sequence.size());
  }

  static void knightHop() {
    Scanner reader = new Scanner(System.in);
    int x1 = reader.nextInt() - 1;
    int y1 = reader.nextInt() - 1;
    int x2 = reader.nextInt() - 1;
    int y2 = reader.nextInt() - 1;

    int[][] minMoves = new int[8][8];

    for(int x = 0; x < 8; x++) {
      for(int y = 0; y < 8; y++) {
        minMoves[x][y] = -1;
      }
    }

    int foundMinCount = 0;

    minMoves[x1][y1] = 0;
    foundMinCount++;

    for(int moves = 1; foundMinCount < 63; moves++) {
      for(int x = 0; x < 8; x++) {
        for(int y = 0; y < 8; y++) {
          if(minMoves[x][y] == moves - 1) {
            if(addValueIfOnBoard(x + 1, y + 2, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x + 2, y + 1, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x + 2, y - 1, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x + 1, y - 2, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x - 1, y - 2, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x - 2, y - 1, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x - 2, y + 1, minMoves, moves))
              foundMinCount++;
            if(addValueIfOnBoard(x - 1, y + 2, minMoves, moves))
              foundMinCount++;
          }
        }
      }
    }

    System.out.println(minMoves[x2][y2]);
  }

  static boolean addValueIfOnBoard(int xpos, int ypos, int[][] moves, int value) {
    if(0 <= xpos && xpos < 8 && 0 <= ypos && ypos < 8) {
      if(moves[xpos][ypos] == -1) {
        moves[xpos][ypos] = value;
        return true;
      }
    }
    return false;
  }
}
