package org.redinn;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.ZonedDateTime;
import java.util.Calendar;

public class ForecastAPI {
    // Class used for predictions made using the API method
    // API USED: OpenWeatherMap ==> More info: https://api.openweathermap.org/

    private static String locationName; // String used to store the name of the city found by searching with the API

    // Print daily forecast (24 hours) taken from the API
    public static void PrintDailyForecasts(String locationName) throws UnirestException, ApiKeyException, InterruptedException {

        JsonObject forecast = getPrevisioni(locationName);

        System.out.println("Here is the forecast for the location " + locationName);

        int hour = ZonedDateTime.now().getHour();  // Current time taken from the system
        String hourString;     // String to contain the time digit (needed to add the "0" in front of a time if there is only one digit)

        // 24 hour stamp
        // The index (i) is also used to get data from the API json
        for(int i=0; i<24; i++){
            Thread.sleep(100);

            // If the hour consists of a single digit, then we add a zero in front of it.
            if (hour % 10 == hour){
                hourString = "0" + String.valueOf(hour);
            }
            else{
                hourString = String.valueOf(hour);
            }

            System.out.println(ColorText.PURPLE + "Hour " + hourString + " | " + ColorText.YELLOW + "Estimated temperature: " + forecast.get("hourly").getAsJsonArray().get(i).getAsJsonObject().get("temp").toString() + "°");
            System.out.println(ColorText.CYAN + "Wind speed: " + forecast.get("hourly").getAsJsonArray().get(i).getAsJsonObject().get("wind_speed").toString() + "m/s " + ColorText.BLUE + "<> " + "Humidity': " + forecast.get("hourly").getAsJsonArray().get(i).getAsJsonObject().get("humidity").toString() + "%" + ColorText.RESET);
            System.out.println("Weather condition - " + forecast.get("hourly").getAsJsonArray().get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").toString());
            System.out.println("---------------------------------------------");

            hour++;
            if(hour == 24){
                hour = 0;
            }

        }


    }

    // Print daily forecast (7 days) taken from the API
    public static void PrintWeeklyForecasts(String locationName) throws ApiKeyException, UnirestException, InterruptedException {

        JsonObject forecast = getPrevisioni(locationName);
        String[] daysWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.println("Here is the forecast for the location " + locationName);

        // Print 7-day forecast
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String day; // Variable used to hold the name of the day of the week

        for(int i=0; i<7; i++){
            Thread.sleep(100);

            day = daysWeek[currentDay-1];
            if(i == 0){
                day += " (Today)";
            }

            System.out.println(ColorText.PURPLE + day + ": " + ColorText.YELLOW + "Average temperature: " + forecast.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("temp").getAsJsonObject().get("day").toString() + "°");
            System.out.println(ColorText.RED + "Max temp: " + forecast.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("temp").getAsJsonObject().get("max").toString() + "°" + ColorText.BLUE + " <> Min temp: " + forecast.get("daily").getAsJsonArray().get(0).getAsJsonObject().get("temp").getAsJsonObject().get("min").toString() + "°" + ColorText.CYAN + " | " + "Wind speed: " + forecast.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("wind_speed").toString() + "m/s" + ColorText.RESET);
            System.out.println("Weather condition: " + forecast.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").toString() + " | Humidity': " + forecast.get("daily").getAsJsonArray().get(i).getAsJsonObject().get("humidity").toString() + "%");
            System.out.println("---------------------------------------------");

            // Resetting the day if the week has ended (i.e. we have arrived at Sunday)
            currentDay++;
            if(currentDay == 8) {
                currentDay = 1;
            }

        }

    }

    // Method used to get forecasts from OpenWeather API
    private static JsonObject getPrevisioni(String nomeLocalita) throws UnirestException, ApiKeyException {

        // To get the city from the API, we first need to extract the coordinates from the search, and then contact the API again with the stored coordinates

        HttpResponse<JsonNode> response = Unirest.get("https://api.openweathermap.org/data/2.5/find?q=" + nomeLocalita + "&appid=5796abbde9106b7da4febfae8c44c232&units=metric").asJson();

        if(response.getStatus() == 401){
            throw new ApiKeyException("Error. API Key probably incorrect/invalid.");
        }

        // Convert the received http response into Json to then get all the necessary information
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        JsonObject jsonObject = je.getAsJsonObject();

        String lon, lat;
        lat = jsonObject.get("list").getAsJsonArray().get(0).getAsJsonObject().get("coord").getAsJsonObject().get("lat").toString();
        lon = jsonObject.get("list").getAsJsonArray().get(0).getAsJsonObject().get("coord").getAsJsonObject().get("lon").toString();

        locationName = jsonObject.get("list").getAsJsonArray().get(0).getAsJsonObject().get("name").toString();

        // Variable containing the predictions obtained by passing the latitude and longitude (coordinates)
        HttpResponse<JsonNode> finalResponse = Unirest.get("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&units=metric&appid=5796abbde9106b7da4febfae8c44c232").asJson();

        if(finalResponse.getStatus() == 401){
            throw new ApiKeyException("Error. API Key probably incorrect/invalid.");
        }

        je = jp.parse(finalResponse.getBody().toString());

        return je.getAsJsonObject();
    }

    // Method used to determine if the location you are looking for exists
    public static boolean locationExisting(String locationName) throws ApiKeyException, UnirestException{

        HttpResponse<JsonNode> response = Unirest.get("https://api.openweathermap.org/data/2.5/find?q=" + locationName + "&appid=5796abbde9106b7da4febfae8c44c232&units=metric").asJson();

        if(response.getStatus() == 401){
            throw new ApiKeyException("Error. API Key probably incorrect/invalid.");
        }

        // Convert the received http response into Json to then get all the necessary information
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody().toString());
        JsonObject jsonObject = je.getAsJsonObject();

        // Count corresponds in the json to the quantity of locations found with the search string entered as input
        if(jsonObject.get("count").toString().equalsIgnoreCase("0")){
            return false;
        }

        return true;
    }
}