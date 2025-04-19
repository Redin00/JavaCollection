package org.redinn;
import java.util.Random;

public class ForecastGenerator {

    // Class containing the methods needed to generate predictions randomly and the necessary attributes

    private static final Random random = new Random();  // Used for generating random elements.
    private static final String[] weatherConditions = {"Sunny", "Cloudy", "Partly cloudy", "Drizzle", "Rain", "Foggy", "Storm", "Snowy", "Snowstorm", "Hailstorm"};

    public static int temperatureGenerator(){
        return random.nextInt(71)-25; // Maximum generating temperature: 45 degrees and minimum -25
    }

    public static int humidityGenerator(){
        return random.nextInt(101); // Humidity in percent, so we generate from 0 to 100
    }

    public static double windGenerator(){
        return random.nextDouble()*20;      // Wind speed generation, from 0 to 20 m/s
    }

    // With this method the general predicted state will be generated, which therefore implies a control on the temperature average to avoid senseless forecasts.
    public static String conditionGenerator(int averageTemperature){

        if(averageTemperature > 30){
            return weatherConditions[random.nextInt(3)];
        }
        else if(averageTemperature > 25){
            return weatherConditions[random.nextInt(5)];
        }
        else if(averageTemperature <= 0){
            return weatherConditions[random.nextInt(10)];
        }
        else{
            return weatherConditions[random.nextInt(7)];
        }

    }
}