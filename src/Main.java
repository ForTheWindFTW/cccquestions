public class Main {
  public static void main(String[] args) {
    java.util.Scanner reader = new java.util.Scanner(System.in);
    int codeCount = Integer.parseInt(reader.nextLine());
    char[] letter = new char[codeCount];
    String[] codes = new String[codeCount];
    int maxLength = 0;

    for(int i = 0; i < codeCount; i++) {
      String[] words = reader.nextLine().split(" ", 2);
      letter[i] = words[0].charAt(0);
      codes[i] = words[1];
      maxLength = Math.max(maxLength, codes[i].length());
    }

    String sequence = reader.nextLine();
    String decoded = "";
    for(int i1 = 0, i2 = i1 + maxLength; i2 <= sequence.length(); i2--) {
      for(int i = 0; i < codes.length; i++) {
        System.out.println(i1 + " to " + i2);
        if(i2 > sequence.length()) {
          i2 = sequence.length();
        }
        if(sequence.substring(i1, i2).matches(codes[i])) {
          System.out.println("MATCH");
          decoded += letter[i];
          System.out.println(sequence.substring(i1, i2));
          i1 = i2;
          i2 += maxLength;
          break;
        }
      }
    }

    System.out.println(decoded);
  }
}