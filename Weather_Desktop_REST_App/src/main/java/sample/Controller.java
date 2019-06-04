package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Pane DescPaneCurrentWeather;
    public ProgressIndicator progressingIndicator;
    public TextField cityCurrentWeatherTextField;
    public TextField countryCurrentWeatherTextField;
    public Button getWeatherButton;
    public Label informationLabel;
    public ImageView iconCurrent;
    public ImageView imageView1;
    public Label label1;
    public ImageView imageView2;
    public Label label2;
    public ImageView imageView3;
    public Label label3;
    public ImageView imageView4;
    public Label label4;
    public ImageView imageView5;
    public Label label5;
    public TextField cityForecastWeatherTextField;
    public TextField countryForecastWeatherTextField;
    private Label desc;
    private Label temperature;
    private Label pressure;
    private Label humidity;
    private Label wind;


    private ArrayList<Label> allLabels;
    private ArrayList<ImageView> allIcons;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressingIndicator.setVisible(false);

        allLabels = new ArrayList<>();
        allIcons = new ArrayList<>();
        allIcons.add(imageView1);
        allIcons.add(imageView2);
        allIcons.add(imageView3);
        allIcons.add(imageView4);
        allIcons.add(imageView5);
        allLabels.add(label1);
        allLabels.add(label2);
        allLabels.add(label3);
        allLabels.add(label4);
        allLabels.add(label5);
    }

    @FXML
    public void getCurrentWeather(MouseEvent mouseEvent) {
        progressingIndicator.setVisible(true);
        String city = cityCurrentWeatherTextField.getText();
        String country = countryCurrentWeatherTextField.getText();
        try {
            WeatherModel weatherData = GetCurrentWeather.INSTANCE.getData(this, city, country);


        cityCurrentWeatherTextField.setText("");
        countryCurrentWeatherTextField.setText("");

            assert weatherData != null;
            writeInformation(weatherData, informationLabel, iconCurrent);
        }
    catch (IOException e) {
        e.printStackTrace();}
        progressingIndicator.setVisible(false);
    }

    @FXML
    public void getForecastWeather(MouseEvent mouseEvent) {

        System.out.println(cityForecastWeatherTextField.getText());
        System.out.println(countryForecastWeatherTextField.getText());
            if(!cityForecastWeatherTextField.getText().equals("") && !countryForecastWeatherTextField.getText().equals("")){
                try {
                    String cityName = cityForecastWeatherTextField.getText();
                    String countryCode = countryForecastWeatherTextField.getText();

                    ArrayList<WeatherModel> forecastData = GetForecastWether.INSTANCE.getForecastWeather(cityName, countryCode);

                    cityForecastWeatherTextField.setText("");
                    countryForecastWeatherTextField.setText("");


                    for (int i = 0; i < 40; i++) {
                        if (i % 8 == 0) {
                            writeInformation(forecastData.get(i / 8), allLabels.get(i / 8), allIcons.get(i / 8));
                        }
                    }
                }catch (HttpClientErrorException e) {
                    System.out.println("");
                }
            }
            else
                System.out.println("Please enter data!");
        }
    private void writeInformation(WeatherModel weatherModel, Label information, ImageView icon){
        Image image = new Image("http://openweathermap.org/img/w/" + weatherModel.getIcon() + ".png");
        icon.setImage(image);

        information.setText(weatherModel.getDescription() + "\n" + weatherModel.getTemperature() + "\n" + weatherModel.getPressure() + "\n"
                + weatherModel.getHumidity() + "\n" + weatherModel.getWind());
    }
}
