package org.redinn;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;

public class DailyForecast extends GeneratedForecast {

    // Class that handles daily forecast management

    public DailyForecast(){
        super();
    }

    // We use this method when we need to generate new daily forecasts
    @Override
    public void regenerateForecasts() {
        minTemp = ForecastGenerator.temperatureGenerator();
        do{
            maxTemp = ForecastGenerator.temperatureGenerator();
        }while (maxTemp < minTemp || maxTemp-25 > minTemp); // We set a limit of 25 degrees maximum for the temperature range

    }

    @Override
    public void PrintForecasts() throws InterruptedException {

        // Regenerating minTemp and maxTemp
        regenerateForecasts();

        int hour = ZonedDateTime.now().getHour();  // Current time taken from the system
        String hourString;     // String to contain the time digit (needed to add the "0" in front of a time if the digit is only one)
        int temp;   // Variable used for generating the temperature of each hour
        int averageTemperature = 0;   // Variable used for average temperature
        DecimalFormat df = new DecimalFormat("#.##");   // Pattern for decimal format, used for the speed of the wind.
        df.setRoundingMode(RoundingMode.CEILING);

        for(int i=0; i<24; i++){
            Thread.sleep(100);

            // If the hour consists of a single digit, then we add a zero in front of it.
            if (hour % 10 == hour){
                hourString = "0" + String.valueOf(hour);
            }
            else{
                hourString = String.valueOf(hour);
            }

            // Temperature generation for each hour, taking into account the total daily maximum and minimum
            do{
                temp = ForecastGenerator.temperatureGenerator();
            }while (temp > maxTemp || temp < minTemp);

            averageTemperature += temp;

            System.out.println(ColorText.PURPLE + "Hour " + hourString + " | " + ColorText.YELLOW + "Estimated temperature: " +  temp + "°");
            System.out.println(ColorText.CYAN + "Wind speed: " + df.format(wind) + "m/s " + ColorText.BLUE + "<> " + "Humidity': " + humidity + "%" + ColorText.RESET);
            System.out.println("Weather condition - " + weatherCondition);
            System.out.println("---------------------------------------------");

            hour++;
            if(hour == 24){
                hour = 0;
            }

            humidity = ForecastGenerator.humidityGenerator();
            wind = ForecastGenerator.windGenerator();
            weatherCondition = ForecastGenerator.conditionGenerator((minTemp+maxTemp)/2);

        }

        System.out.println(ColorText.CYAN + "Average daily temperature: " + averageTemperature/24 + "°" + ColorText.RESET);
    }
}