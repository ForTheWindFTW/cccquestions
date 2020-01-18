import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

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

  static void chancesOfWinning() {
    Scanner reader = new Scanner(System.in);
    int team = reader.nextInt();
    int gamesPlayed = reader.nextInt();
    int[] points = new int[4];
    int remainingGames = 6 - gamesPlayed;
    int[] team1Game = new int[gamesPlayed];
    int[] team2Game = new int[gamesPlayed];

    for(int i = 0; i < gamesPlayed; i++) {
      int team1 = reader.nextInt();
      int team2 = reader.nextInt();
      int score1 = reader.nextInt();
      int score2 = reader.nextInt();

      // Set played
      team1Game[i] = team1;
      team2Game[i] = team2;

      // Add points to teams
      if(score1 > score2) {
        points[team1 - 1] += 3;
      } else if(score1 < score2) {
        points[team2 - 1] += 3;
      } else {
        points[team1 - 1]++;
        points[team2 - 1]++;
      }
    }

    int[] unplayed = getPossibleGames(team1Game, team2Game);
    System.out.println("Played team points: " + Arrays.toString(points));
    System.out.println("Unplayed possible games: " + unplayed);

    Iterator iterator = unplayed.keySet().iterator();
    playGames(unplayed, points, iterator, 0);
//    for(int i = 1; i < 4; i++) {
//      int size = unplayed.getOrDefault(i, Set.of()).size();
//      for(int j = 0; j < size; j++) {
//
//      }
//    }
    /**
     * LOGIC PART:
     * If @param team is not highest:
     * try to increase it
     *
     * BRUTE FORCE-LOGIC:
     * Possible games:
     * 1-2, 1-3, 1-4
     * 2-3, 2-4
     * 3-4
     *
     * 3 possible outcomes per game
     * 6 - @param gamesPlayed pairs of teams that can be chosen
     */
  }

  static void playGames(Map<Integer, Set<Integer>> possibleMoves, int[] points, Iterator iterator, int winner) {
    if(iterator.hasNext()) {
      /**
       *
       */
//      // Next one
//      int currentTeam1 = (int)iterator.next();
//      playGames(possibleMoves, points, iterator, 0);
//      playGames(possibleMoves, points, iterator, 1);
//      playGames(possibleMoves, points, iterator, 2);
//      System.out.println(currentTeam1);

    } else {
      /**
       *
       */
      // Base case
      // Try win / tie / other win
      // +3, 0 / +1, +1 / 0, +3
//      switch(winner) {
//        case 0: team1 += 3; break;
//        case 1: team1++; team2++; break;
//        case 2: team2 += 3; break;
//      }
      System.out.println("NO MORE");
    }
  }

  static int[] getPossibleGames(int[] team1Game, int[] team2Game) {
//    Map<Integer, Set<Integer>> played = new HashMap<>();
//    for(int i = 0; i < playHistory.length / 2; i++) {
//      played.putIfAbsent(i + 1, )
//    }
//
//    int[] possibleMoves;
//    for(int i = 0; i < playHistory.length / 2; i++) {
//      possibleMoves.
//    }
//    for(int i = 1; i < 4; i++) {
//      for(int j = i + 1; j <= 4; j++) {
//        if(playHistory.containsKey(i)) {
//          // Game played of i team
//          if(playHistory.get(i).contains(j)) {
//            // Don't add current
//          } else {
//            // Add unplayed game
//            possibleMoves.putIfAbsent(i, new HashSet<>());
//            possibleMoves.get(i).add(j);
//          }
//        } else {
//          // No game played of i team
//          possibleMoves.putIfAbsent(i, new HashSet<>());
//          possibleMoves.get(i).add(j);
//        }
//      }
//    }
//    return possibleMoves;
  }
}
