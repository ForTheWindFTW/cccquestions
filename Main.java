import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
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
}