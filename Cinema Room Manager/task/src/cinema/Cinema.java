package cinema;

import java.util.Scanner;

public class Cinema {

    private static int purchaseRowNumber = 0;
    private static int firstHalfRows = 0;
    private static int totalNumSeats = 0;
    private static int numSeatsPurchased = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        String [][] cinema = new String[numRows][numSeats];
        int menuItem;
        boolean furtherAction = true;

        populateCinema(cinema, numRows, numSeats);

        while (furtherAction){

            menuItem = getMenuItem();
            switch (menuItem){
                case 0:
                    furtherAction = false;
                    break;
                case 1:
                    printCinema(cinema);
                    break;
                case 2:
                    purchaseTicket(cinema);
                    System.out.println("Ticket price: $" + getTicketPrice(totalNumSeats, firstHalfRows, purchaseRowNumber));
                    System.out.printf("%n");
                    break;
                case 3:
                    printStats();
            }
        }
        scanner.close();
    }

    private static void printStats() {
        double percentage = (1.0 * numSeatsPurchased/totalNumSeats) * 100.0;
        System.out.printf("Number of purchased tickets: %d%n", numSeatsPurchased);
        System.out.printf("Percentage: %4.2f%% %n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);

        System.out.printf("%n");
    }

    private static void purchaseTicket(String[][] cinema) {
        boolean needPurchaseTicket = true;
        Scanner scanner = new Scanner(System.in);

        while (needPurchaseTicket) {
            System.out.println("Enter a row number:");
            purchaseRowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int purchaseSeatNumber = scanner.nextInt();

            try {
                if ("B".equals(cinema[purchaseRowNumber - 1][purchaseSeatNumber - 1])) {
                    System.out.println("That ticket has already been purchased!");
                    System.out.printf("%n");
                } else {
                    cinema[purchaseRowNumber - 1][purchaseSeatNumber - 1] = "B";
                    numSeatsPurchased += 1;
                    needPurchaseTicket = false;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Wrong input!");
                System.out.printf("%n");
            }
        }
    }

    private static int getMenuItem() {
        Scanner scanner = new Scanner(System.in);
        boolean needMenuItem = true;
        int menuItem = 4;

        while (needMenuItem) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            menuItem =  scanner.nextInt();
            if (menuItem < 4){
                needMenuItem = false;
            } else {
                System.out.println("Invalid entry");
            }
        }
        return menuItem;
    }

    private static int getTotalIncome(String[][] cinema) {
        int totalIncome;
        int remainingRows = cinema.length - firstHalfRows;
        int numSeatsPerRow = cinema[0].length;

        if (totalNumSeats <= 60) {
            totalIncome = 10 * totalNumSeats;
        } else {
            totalIncome = (10 * firstHalfRows * numSeatsPerRow) + (8 * remainingRows * numSeatsPerRow);
        }
        return totalIncome;
    }

    private static int getTicketPrice(int totalNumSeats, int firstHalfRows, int rowNumber) {
        int ticketPrice;
        if (totalNumSeats <= 60){
            ticketPrice = 10;
        } else {
            ticketPrice = rowNumber <= firstHalfRows ? 10 : 8;
        }
        currentIncome += ticketPrice;
        return ticketPrice;
    }

    private static void populateCinema(String[][] cinema, int numRows, int numSeats) {
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeats; seat++) {
                cinema[row][seat] = "S";
            }
        }
        totalNumSeats = cinema.length * cinema[0].length;
        firstHalfRows = cinema[0].length/2;
        totalIncome = getTotalIncome(cinema);
    }

    private static void printCinema(String[][] cinema) {
        System.out.printf("\nCinema: %n");
        for (int j = 0; j < cinema[0].length + 1; j++) {
            System.out.print(j == 0? " ": " " + j);
        }

        System.out.printf("%n");
        for (int row = 0; row < cinema.length; row++) {
            System.out.print(row + 1);
            for (int seat = 0; seat < cinema[row].length; seat++) {
                System.out.print(" " + cinema[row][seat]);
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }
}