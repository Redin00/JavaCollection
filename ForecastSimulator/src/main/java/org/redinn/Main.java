package org.redinn;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // Weather simulator program, which uses random generation to generate different forecasts either daily and weekly.
        // There's also a real forecast version, which uses the API of OpenWeatherMAP.


        // ASCII title
        String titleText = ColorText.GREEN + "______                           _     _____ _                 _       _             \n" +
                "|  ___|                         | |   /  ___(_)               | |     | |            \n" +
                "| |_ ___  _ __ ___  ___ __ _ ___| |_  \\ `--. _ _ __ ___  _   _| | __ _| |_ ___  _ __ \n" +
                "|  _/ _ \\| '__/ _ \\/ __/ _` / __| __|  `--. \\ | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|\n" +
                "| || (_) | | |  __/ (_| (_| \\__ \\ |_  /\\__/ / | | | | | | |_| | | (_| | || (_) | |   \n" +
                "\\_| \\___/|_|  \\___|\\___\\__,_|___/\\__| \\____/|_|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|   \n" +
                "                                                                                     \n" +
                "                                                                                     " + ColorText.RESET;


        // Sequentially writing the title (semi-animated)
        char[] titleArray = titleText.toCharArray();
        for(char c : titleArray){
            Thread.sleep(1);
            System.out.print(c);
        }
        System.out.println();


        Scanner input = new Scanner(System.in);
        int choice = 0;  // Variable used for menu choices in the program

        boolean mainLoop = true;    // Variable used to perform the main loop of the program, which allows the program mode to be changed
        boolean loop = true;        // Variable used to perform different loops in the code
        boolean inputLoop = true;  // Variable used to perform loops needed for input

        System.out.println(ColorText.GREEN + "Select the version of the program to use (0 to exit the program)" + ColorText.RESET);
        System.out.println(ColorText.YELLOW + "1 - Version with randomly generated predictions" + ColorText.RESET);
        System.out.println(ColorText.YELLOW + "2 - Version with real forecasts, obtained using the OpenWeatherMap API" + ColorText.RESET);

        // Loop for input choice of the program mode you want to run
        while(inputLoop) {

            try {
                System.out.print(ColorText.GREEN + "Enter choice -> " + ColorText.RESET);
                choice = input.nextInt();
                inputLoop = false;
            } catch (InputMismatchException ex) {
                System.out.println(ColorText.RED + "Error. Please enter a number within the choices!" + ColorText.RESET);
                input.nextLine();
            }

            if(!inputLoop && choice != 1 && choice != 2) {
                System.out.println(ColorText.RED + "Error. Please enter a number within the choices!" + ColorText.RESET);
                inputLoop = true;

            }

        }

        // Let's remove the "\n" that the nextInt() function from the Scanner class releases, thus skipping the input of the nextLine() function.
        input.nextLine();



        // Switch used only to print simple texts depending on the choice made
        switch (choice) {
            case 1:
                System.out.println(ColorText.CYAN + "Program selection: random generator..." + ColorText.RESET);
                Thread.sleep(500);
                break;
            case 2:
                System.out.println(ColorText.CYAN + "Program Choice: Real Predictions Obtained with API...." + ColorText.RESET);
                break;
        }

        // Loop for continuous program execution, allowing program mode switching

        // ------------------------------ CONTINUOUS PROGRAM LOOP, INTERRUPTED ONLY IF CHOICE = 0 ------------------------------
        while(mainLoop) {
            // ######## If we have chosen random generation of forecasts #######
            if (choice == 1) {

                double[] tempCoord = new double[2];

                System.out.print("Enter the location for which you want to know the weather forecast -> ");
                String tempLoc = input.nextLine();

                System.out.println("Enter the coordinates of the location now: ");

                inputLoop = true;
                while (inputLoop) {
                    try {
                        System.out.print(ColorText.GREEN + "First coordinate -> " + ColorText.RESET);
                        tempCoord[0] = input.nextDouble();
                        System.out.print(ColorText.GREEN + "Second coordinate -> " + ColorText.RESET);
                        tempCoord[1] = input.nextDouble();
                        inputLoop = false;
                    } catch (InputMismatchException ex) {
                        System.out.println(ColorText.RED + "Mistake! Enter coordinates (double number, e.g. 3,20)\n" + ColorText.RESET);
                        input.nextLine();
                    }
                }

                // Let's remove the "\n" that the nextDouble() function from the Scanner class releases, thus skipping the input of the nextLine() function.
                input.nextLine();

                Location location = new Location(tempLoc, tempCoord);

                System.out.println(ColorText.YELLOW + "Loading program in progress...." + ColorText.RESET + "\n");
                Thread.sleep(200);

                // Loop with selection menu for options that provide interactivity to the program
                loop = true;
                while (loop) {

                    choice = 0;
                    System.out.println(ColorText.PURPLE + "Choose which forecast to display (TO EXIT THE PROGRAM ENTER 0): " + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "1 -- Daily Forecast (24 Hour Forecast)" + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "2 -- Weekly Forecast (7 Days)" + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "3 -- Change location | Coordinates and name" + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "4 -- Program mode change (==> Real forecast)" + ColorText.RESET);

                    inputLoop = true;
                    // Choice control to avoid errors
                    while (inputLoop) {

                        try {
                            System.out.print(ColorText.GREEN + "Enter choice -> " + ColorText.RESET);
                            choice = input.nextInt();
                            inputLoop = false;
                        } catch (InputMismatchException ex) {
                            System.out.println(ColorText.RED + "Error. Please enter a number within the choices!" + ColorText.RESET);
                            input.nextLine();
                        }

                        if (!inputLoop && choice < 0 || choice > 4) {
                            System.out.println(ColorText.RED + "Error. Please enter a number within the choices!" + ColorText.RESET);
                            inputLoop = true;

                        }

                    }

                    // Let's remove the "\n" that the nextInt() function from the Scanner class releases, thus skipping the input of the nextLine() function.
                    input.nextLine();

                    switch (choice) {
                        case 0:
                            System.out.println(ColorText.YELLOW + "Exiting the current program...." + ColorText.RESET);
                            loop = false;
                            mainLoop = false;
                            break;
                        case 1:
                            System.out.println(ColorText.CYAN + "Here is the daily forecast for the location " + location.getName() + ColorText.CYAN + " | Coordinates = " + ColorText.BLUE + location.getCoordinates() + ColorText.RESET);
                            System.out.println(ColorText.CYAN + "===============================================" + ColorText.RESET);
                            DailyForecast dailyForecast = new DailyForecast();
                            dailyForecast.PrintForecasts();


                            System.out.println(ColorText.CYAN + "Press enter to continue...." + ColorText.RESET);
                            input.nextLine();
                            break;

                        case 2:
                            System.out.println(ColorText.CYAN + "Here is the weekly forecast for the location " + ColorText.BLUE + location.getName() + ColorText.CYAN + " | Coordinate = " + location.getCoordinates() + ColorText.RESET);
                            System.out.println(ColorText.CYAN + "===============================================" + ColorText.RESET);
                            WeeklyForecast weeklyForecast = new WeeklyForecast();
                            weeklyForecast.PrintForecasts();


                            System.out.println(ColorText.CYAN + "Press enter to continue...." + ColorText.RESET);
                            input.nextLine();
                            break;
                        case 3:

                            System.out.println(ColorText.PURPLE + "-------- Change of Location --------" + ColorText.RESET);
                            System.out.print(ColorText.BLUE + "Enter new location name: " + ColorText.RESET);

                            tempLoc = input.nextLine();

                            System.out.println(ColorText.BLUE + "Enter coordinates now:" + ColorText.RESET);

                            inputLoop = true;
                            while (inputLoop) {
                                try {
                                    System.out.print(ColorText.GREEN + "First coordinate -> " + ColorText.RESET);
                                    tempCoord[0] = input.nextDouble();
                                    System.out.print(ColorText.GREEN + "Second coordinate -> " + ColorText.RESET);
                                    tempCoord[1] = input.nextDouble();
                                    inputLoop = false;
                                } catch (InputMismatchException ex) {
                                    System.out.println(ColorText.RED + "Mistake! Enter coordinates (double number, e.g. 3,20)" + ColorText.RESET);
                                    input.nextLine();
                                }
                            }

                            // Let's remove the "\n" that the nextInt() and nextDouble() functions from the Scanner class release, causing the input of the nextLine() function to skip.
                            input.nextLine();

                            location.setName(tempLoc);
                            location.setCoordinates(tempCoord);

                            break;
                        case 4:

                            String temp = "";

                            do {
                                System.out.print(ColorText.GREEN + "Are you sure you want to change the program mode to real forecast? (Y/N): " + ColorText.RESET);
                                temp = input.nextLine();

                            } while (!temp.equalsIgnoreCase("Y") && !temp.equalsIgnoreCase("N"));

                            if (temp.equalsIgnoreCase("Y")) {
                                System.out.println(ColorText.YELLOW + "Change mode in real forecasts..." + ColorText.RESET);
                                Thread.sleep(500);

                                // We cancel the loop of the current program mode and change choice so that we can access the other mode
                                loop = false;
                                choice = 2;

                                System.out.println(ColorText.YELLOW + "=============== Mode change done! (Real forecast) ===============" + ColorText.RESET);

                            }
                            else {
                                System.out.println(ColorText.RED + "Mode change cancelled..." + ColorText.RESET);
                                Thread.sleep(500);
                            }

                            break;

                    }
                }
            }
            // If we have chosen real forecasts with use of API
            else if (choice == 2) {

                inputLoop = true;
                String locationName = "";
                // Check via ForecastAPI class method if location exists

                inputLoop = true;
                while (inputLoop) {
                    System.out.print("Enter the location for which you want to know the weather forecast -> ");
                    locationName = input.nextLine();


                    // We change the spaces with (%20) which is used to indicate spaces in the query
                    locationName = locationName.replace(" ", "%20");

                    if (locationName.length() < 3) {
                        System.out.println(ColorText.RED + "Error! Please enter at least 3 characters to find a valid city!" + ColorText.RESET);
                    } else if (!ForecastAPI.locationExisting(locationName)) {
                        System.out.println(ColorText.RED + "Error! The location entered does not exist! Please enter a new one!" + ColorText.RESET);
                    } else {
                        inputLoop = false;
                    }
                }

                System.out.println(ColorText.YELLOW + "Loading program in progress...." + ColorText.RESET + "\n");
                Thread.sleep(200);

                // Loop real forecasts
                loop = true;
                while (loop) {
                    // Input choice prediction

                    choice = 0;
                    System.out.println(ColorText.PURPLE + "Choose which forecast to display (TO EXIT THE PROGRAM ENTER 0): " + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "1 -- Daily Forecast (24 Hour Forecast)" + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "2 -- Weekly Forecast (From the current day to the next previous day to today)" + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "3 -- Change location | Coordinates and name" + ColorText.RESET);
                    System.out.println(ColorText.BLUE + "4 -- Program mode change (==> Random forecast generation)" + ColorText.RESET);

                    inputLoop = true;
                    // Choice control to avoid errors
                    while (inputLoop) {
                        try {
                            System.out.print(ColorText.GREEN + "Enter choice -> " + ColorText.RESET);
                            choice = input.nextInt();
                            inputLoop = false;
                        } catch (InputMismatchException ex) {
                            System.out.println(ColorText.RED + "Error. Please enter a number within the choices!" + ColorText.RESET);
                            input.nextLine();
                        }

                        if (!inputLoop && choice < 0 || choice > 4) {
                            System.out.println(ColorText.RED + "Error. Please enter a number within the choices!" + ColorText.RESET);
                            inputLoop = true;

                        }
                    }

                    // Let's remove the "\n" that the nextInt() function from the Scanner class releases, thus skipping the input of the nextLine() function.
                    input.nextLine();

                    switch (choice) {
                        case 0:
                            System.out.println(ColorText.YELLOW + "Exiting the current program...." + ColorText.RESET);
                            loop = false;
                            mainLoop = false;
                            break;
                        case 1:
                            ForecastAPI.PrintDailyForecasts(locationName);

                            System.out.println(ColorText.CYAN + "Press enter to continue...." + ColorText.RESET);
                            input.nextLine();
                            break;
                        case 2:
                            ForecastAPI.PrintWeeklyForecasts(locationName);

                            System.out.println(ColorText.CYAN + "Press enter to continue...." + ColorText.RESET);
                            input.nextLine();
                            break;
                        case 3:

                            System.out.println(ColorText.PURPLE + "-------- Change of Location --------" + ColorText.RESET);

                            inputLoop = true;
                            while (inputLoop) {
                                System.out.print("Enter the new location -> ");
                                locationName = input.nextLine();

                                // We change the spaces with (%20) which is used to indicate spaces in the url
                                locationName = locationName.replace(" ", "%20");

                                if (locationName.length() < 3) {
                                    System.out.println(ColorText.RED + "Error! Please enter at least 3 characters to find a valid city!" + ColorText.RESET);
                                } else if (!ForecastAPI.locationExisting(locationName)) {
                                    System.out.println(ColorText.RED + "Error! The location entered does not exist! Please enter a new one!" + ColorText.RESET);
                                } else {
                                    inputLoop = false;
                                }
                            }

                            break;
                        case 4:

                            String temp = "";

                            do {
                                System.out.print(ColorText.GREEN + "Are you sure you want to change the program mode to randomly generated predictions? (Y/N): " + ColorText.RESET);
                                temp = input.nextLine();

                            } while (!temp.equalsIgnoreCase("Y") && !temp.equalsIgnoreCase("N"));

                            if (temp.equalsIgnoreCase("Y")) {
                                System.out.println(ColorText.YELLOW + "Change mode to random forecast generation..." + ColorText.RESET);
                                Thread.sleep(500);

                                // We cancel the loop of the current program mode and change choice so that we can access the other mode
                                loop = false;
                                choice = 1;

                                System.out.println(ColorText.YELLOW + "=============== Mode change done! (Randomly generated predictions) ===============" + ColorText.RESET);

                            }
                            else {
                                System.out.println(ColorText.RED + "Mode change cancelled..." + ColorText.RESET);
                                Thread.sleep(500);
                            }

                            break;
                    }

                }
            }
        }

        input.close();

        System.out.println(ColorText.YELLOW + "Thanks for using the program!!" + ColorText.RESET);


    }
}