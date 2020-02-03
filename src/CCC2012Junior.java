import javax.crypto.CipherOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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

  private static List<Map<Integer, List<Integer>>> visited = new ArrayList<>();
  private static int leastMoves;
  private static int EMPTY_COIN_VALUE;

  static void aCoinGame() {
    Scanner reader = new Scanner(System.in);
    int coins;

    do {
      leastMoves = -1;
      coins = reader.nextInt();
      EMPTY_COIN_VALUE = coins;
      visited.clear();
      Map<Integer, List<Integer>> pos = new HashMap<>();
      for(int i = 0; i < coins; i++) {
        pos.putIfAbsent(i, new ArrayList<>(Arrays.asList(reader.nextInt())));
      }
      if(coins != 0) {
        playCoinGames(pos);
      }
    } while(coins != 0);
  }

  static void playCoinGames(Map<Integer, List<Integer>> pos) {
    Queue<Map<Integer, List<Integer>>> states = new LinkedList<>();
    Queue<Integer> counts = new LinkedList<>();

    /** Add the start position */
//    visited.add(Map.copyOf(pos));
    visited.add(pos);
    states.add(pos);
    counts.add(0);

    for(int i = 0; i < states.element().size() - 1; i++) {
      if(states.element().get(i).get(0) + 1 != states.element().get(i + 1).get(0)) {
        break;
      }
      if(i == states.element().size() - 2) {
        leastMoves = 0;
      }
    }

    while(!states.isEmpty()) {
//      System.out.println(states.element());
      /** Tries to move all coins left and right, adds to queue */
      for(int i = 0; i < states.element().size(); i++) {
        Map<Integer, List<Integer>> left = moveCoinLeft(i, states.element());
        Map<Integer, List<Integer>> right = moveCoinRight(i, states.element());

        if(left != null && !visited.contains(left)) {
          visited.add(left);
          states.add(left);
          counts.add(counts.element() + 1);
          for(int j = 0; j < left.size() - 1; j++) {
            if(left.get(j).size() != 1 || left.get(j + 1).size() != 1) {
              break;
            }
            if(left.get(j).get(0) + 1 != left.get(j + 1).get(0)) {
              break;
            }
            if(j == left.size() - 2) {
//              System.out.println("LEFT: " + left);
//              System.out.println("PoSSIBLE SOLUTION: " + (counts.element() + 1));
              if(leastMoves == -1) {
                leastMoves = counts.element() + 1;
              } else if(counts.element() + 1 < leastMoves) {
                leastMoves = counts.element() + 1;
              }
            }
          }
        }

        if(right != null && !visited.contains(right)) {
          visited.add(right);
          states.add(right);
          counts.add(counts.element() + 1);
          for(int j = 0; j < right.size() - 1; j++) {
            if(right.get(j).size() != 1 || right.get(j + 1).size() != 1) {
              break;
            }
            if(right.get(j).get(0) + 1 != right.get(j + 1).get(0)) {
              break;
            }
            if(j == right.size() - 2) {
//              System.out.println("RIGHT: " + right);
//              System.out.println("POSSIBLE SOLUTION: " + (counts.element() + 1));
              if(leastMoves == -1) {
                leastMoves = counts.element() + 1;
              } else if(counts.element() + 1 < leastMoves) {
                leastMoves = counts.element() + 1;
              }
            }
          }
        }
      }

      /** Remove the first element */
      states.remove(states.element());
      counts.remove(counts.element());
    }

    if(leastMoves == -1) {
      System.out.println("IMPOSSIBLE");
    } else {
      System.out.println(leastMoves);
    }
//    System.out.println("Shortest Solution: " + leastMoves);
//    System.out.println("Uhh done?");
  }

  static Map<Integer, List<Integer>> moveCoinLeft(int pos1, Map<Integer, List<Integer>> states) {
    Map<Integer, List<Integer>> coins = new HashMap<>();

    for(int i : states.keySet()) {
      coins.put(i, new ArrayList<>(states.get(i)));
    }

    if(pos1 == 0) {
      return null;
    }

    int pos2 = pos1 - 1;
    int coinCount1 = coins.get(pos1).size();

    if(coinCount1 == 0) {
      return null;
    }

    int coinCount2 = coins.get(pos2).size();
    int coin1 = coins.get(pos1).get(coinCount1 - 1);
    int coin2;
    if(coinCount2 == 0) {
      coin2 = EMPTY_COIN_VALUE;
    } else {
      coin2 = coins.get(pos2).get(coinCount2 - 1);
    }

    if(coin1 > coin2) {
      return null;
    }

    coins.get(pos2).add(coin1);
    coins.get(pos1).remove(coinCount1 - 1);

    return coins;
  }

  static Map<Integer, List<Integer>> moveCoinRight(int pos1, Map<Integer, List<Integer>> states) {
    Map<Integer, List<Integer>> coins = new HashMap<>();

    for(int i : states.keySet()) {
      coins.put(i, new ArrayList<>(states.get(i)));
    }

    if(pos1 == coins.size() - 1) {
      return null;
    }

    int pos2 = pos1 + 1;
    int coinCount1 = coins.get(pos1).size();

    if(coinCount1 == 0) {
      return null;
    }

    int coinCount2 = coins.get(pos2).size();
    int coin1 = coins.get(pos1).get(coinCount1 - 1);
    int coin2;
    if(coinCount2 == 0) {
      coin2 = EMPTY_COIN_VALUE;
    } else {
      coin2 = coins.get(pos2).get(coinCount2 - 1);
    }

    if(coin1 > coin2) {
      return null;
    }

    coins.get(pos2).add(coin1);
    coins.get(pos1).remove(coinCount1 - 1);

    return coins;
  }
}
