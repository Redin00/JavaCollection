package org.redinn;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;

public class WeeklyForecast extends GeneratedForecast {

    // Class for making weekly forecasts, which starts from the same day (today) and makes a 7-day forecast

    protected int avgWeeklyTemperature;

    public WeeklyForecast(){
        super();
    }

    @Override
    public void regenerateForecasts() {
        minTemp = ForecastGenerator.temperatureGenerator();
        do{
            maxTemp = ForecastGenerator.temperatureGenerator();
        }while (maxTemp < minTemp || maxTemp-25 > minTemp); // We set a limit of 25 degrees maximum for the temperature range

        humidity = ForecastGenerator.humidityGenerator();
        wind = ForecastGenerator.windGenerator();
        weatherCondition = ForecastGenerator.conditionGenerator((minTemp+maxTemp)/2);
    }

    @Override
    public void PrintForecasts() throws InterruptedException {
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String day; // Variable used to hold the name of the day of the week

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        for(int i=0; i<7; i++){
            Thread.sleep(100);

            day = daysWeek[currentDay-1];
            if(i == 0){
                day += " (Today)";
            }
            System.out.println(ColorText.PURPLE + day + ": " + ColorText.YELLOW + "Average temperature: " + (minTemp+maxTemp)/2 + "°");
            System.out.println(ColorText.RED + "Max temp: " + maxTemp + "°" + ColorText.BLUE + " <> Min temp: " + minTemp + "°" + ColorText.CYAN + " | " + "Wind speed: " + df.format(wind) + "m/s" + ColorText.RESET);
            System.out.println("Weather condition: " + weatherCondition + " | Humidity': " + humidity + "%");
            System.out.println("---------------------------------------------");

            regenerateForecasts();

            // Resetting the day if the week has ended (i.e. we have arrived at Sunday)
            currentDay++;
            if(currentDay == 8) {
                currentDay = 1;
            }

            // Calculating weekly average temperature
            avgWeeklyTemperature += (minTemp+maxTemp)/2;

        }

        System.out.println(ColorText.CYAN + "Media temperatura settimanale: " + avgWeeklyTemperature/7 + "°" + ColorText.RESET);

    }
}