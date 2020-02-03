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

  private static int[] team1;
  private static int[] team2;
  private static int desiredTeam;
  private static int winChance = 0;

  static void chancesOfWinning() {
    Scanner reader = new Scanner(System.in);
    desiredTeam = reader.nextInt();
    int playedCount = reader.nextInt();
    int[] score = new int[4];

    Map<Integer, Set<Integer>> teams = new HashMap<>();

    for(int i = 1; i < 4; i++) {
      teams.putIfAbsent(i, new HashSet<>());
      for(int j = i + 1; j <= 4; j++) {
        teams.get(i).add(j);
      }
    }

    System.out.println(teams);

    for(int i = 0; i < playedCount; i++) {
      // REMOVE TEAMS, INCREASE POINTS
      int team1 = reader.nextInt();
      int team2 = reader.nextInt();
      int score1 = reader.nextInt();
      int score2 = reader.nextInt();

      if(score1 > score2) {
        score[team1 - 1] += 3;
      } else if(score1 < score2) {
        score[team2 - 1] += 3;
      } else {
        score[team1 - 1]++;
        score[team2 - 1]++;
      }

      teams.get(team1).remove(team2);
      if(teams.get(team1).isEmpty()) {
        teams.remove(team1);
      }
    }

    System.out.println("Matches: " + teams);
    System.out.println("Current Score: " + Arrays.toString(score));

    team1 = new int[6 - playedCount];
    team2 = new int[6 - playedCount];
    int i = 0;
    for(Iterator t1 = teams.keySet().iterator(); t1.hasNext();) {
      int firstTeam = (int)t1.next();
      for(Iterator t2 = teams.get(firstTeam).iterator(); t2.hasNext(); i++) {
        int secondTeam = (int)t2.next();
        team1[i] = firstTeam;
        team2[i] = secondTeam;
      }
    }

    System.out.println("Team 1: " + Arrays.toString(team1));
    System.out.println("Team 2: " + Arrays.toString(team2));

    playMatch(0, score);

    System.out.println(winChance);
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

  static void playMatch(int index, int[] currentScore) {
    if(index != team1.length) {
      // Team 1 Wins
      currentScore[team1[index] - 1] += 3;
      playMatch(index + 1, currentScore);
      currentScore[team1[index] - 1] -= 3;

      // Team 2 Wins
      currentScore[team2[index] - 1] += 3;
      playMatch(index + 1, currentScore);
      currentScore[team2[index] - 1] -= 3;

      // Teams Tie
      currentScore[team1[index] - 1]++;
      currentScore[team2[index] - 1]++;
      playMatch(index + 1, currentScore);
      currentScore[team1[index] - 1]--;
      currentScore[team2[index] - 1]--;
    } else {
      for(int i = 1; i <= 4; i++) {
        if(desiredTeam == i) {
          continue;
        }
        if(currentScore[i - 1] >= currentScore[desiredTeam - 1]) {
          break;
        }
        if(i == 4) {
          winChance++;
          System.out.println("INC WIN CHANCE");
        }
      }
      System.out.println(Arrays.toString(currentScore));
    }
  }
}
