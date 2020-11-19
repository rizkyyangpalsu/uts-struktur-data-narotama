package com.inibukan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan operasi bilangan: ");
        String arithmetic = scanner.nextLine();

        Calculator calculator = new Calculator();
        System.out.println("Hasil: " + calculator.main(arithmetic));
    }
}
