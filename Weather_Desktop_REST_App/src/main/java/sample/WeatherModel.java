package sample;


public class WeatherModel {
    private String description;
    private String temperature;
    private String pressure;
    private String humidity;
    private String wind;
    private String icon;

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getIcon() {
        return icon;
    }

    WeatherModel(String description, String temperature, String pressure, String humidity, String wind, String icon) {
        this.description = description;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.icon = icon;
    }


}