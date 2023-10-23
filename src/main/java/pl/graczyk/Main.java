package pl.graczyk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wyrażenie");
        String result = new Onp(scanner.next()).calcOnp();
        System.out.println(result);
    }
}
