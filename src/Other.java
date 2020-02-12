public class Other {
  static void printNextPrime() {
    java.util.Scanner reader = new java.util.Scanner(System.in);
    int input = reader.nextInt();
    System.out.println("The next prime number after " + input + " is: " + nextPrime(input));
  }

  static int nextPrime(int n) {
    int p = n + 1;
    while(!isPrime(p))
      p++;
    return p;
  }

  static boolean isPrime(int p) {
    // condition: numbers <= 1 and even numbers that are not 2
    if(p <= 1 || p != 2 && p % 2 == 0)
      return false;

    // check all factors <= sqrt of number
    for(int i = 3; i <= Math.sqrt(p); i += 2) {
      if(p % i == 0)
        return false;
    }

    return true;
  }
}
