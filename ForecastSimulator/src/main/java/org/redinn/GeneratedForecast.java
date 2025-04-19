package org.redinn;

public abstract class GeneratedForecast {

    // Abstract class that defines the basic structure of a forecast, i.e. maximum and minimum temperature, humidity, wind speed, and the state (e.g. sunny)

    protected int minTemp;
    protected int maxTemp;
    protected int humidity;
    protected double wind;
    protected String weatherCondition;

    protected static final String[] daysWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public GeneratedForecast(){
        minTemp = ForecastGenerator.temperatureGenerator();
        do{
            maxTemp = ForecastGenerator.temperatureGenerator();
        }while (maxTemp < minTemp || maxTemp-25 > minTemp);     // We set a limit of 25 degrees maximum for the temperature range

        humidity = ForecastGenerator.humidityGenerator();
        wind = ForecastGenerator.windGenerator();
        weatherCondition = ForecastGenerator.conditionGenerator((minTemp+maxTemp)/2);
    }

    public abstract void regenerateForecasts();

    public abstract void PrintForecasts() throws InterruptedException;
}