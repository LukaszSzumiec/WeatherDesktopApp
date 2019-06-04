package sample;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

enum GetCurrentWeather {
    INSTANCE;
    WeatherModel getData(Controller controller, String city, String country) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.exchange("http://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&units=metric&appid=6d8ea539bd43d2fc3b3d7b556ee86d34",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);
        if(response.getStatusCode() == HttpStatus.OK){
            String inputData =  response.getBody();
            System.out.println(inputData);
            JsonObject newObject = new Gson().fromJson(inputData, JsonObject.class);
            return createModel(newObject); }
        return null;

    }
    public WeatherModel createModel(JsonObject mainObject){
        JsonArray inputWeather =  mainObject.get("weather").getAsJsonArray();
        JsonObject inputMain =  mainObject.get("main").getAsJsonObject();
        JsonObject inputWind =  mainObject.get("wind").getAsJsonObject();
        JsonObject objectWeather = new Gson().fromJson(inputWeather.get(0).toString(), JsonObject.class);
        return new WeatherModel("Description: " + objectWeather.get("description").getAsString(), "Temperature: " + inputMain.get("temp") + "\u00b0C",
                inputMain.get("pressure") + " hPa", "Humidity: " + inputMain.get("humidity") + " %", "Wind: " + inputWind.get("speed") + " m/s" + ", " + inputWind.get("deg") + "\u00b0",
                objectWeather.get("icon").getAsString());
    }


}
// 6d8ea539bd43d2fc3b3d7b556ee86d34