package org.redinn;

// Class for exceptions in case there is a problem with the connection to the OpenWeatherMap API caused by a specific code (401)

public class ApiKeyException extends Exception{

    public ApiKeyException(String errorMsg){
        super(errorMsg);
    }
}