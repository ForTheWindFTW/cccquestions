import java.util.*;

public class Main {

  private static List<Map<Integer, List<Integer>>> visited = new ArrayList<>();
  private static int leastMoves;
  private static int EMPTY_COIN_VALUE;

  public static void main(String[] args) {
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