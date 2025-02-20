package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "e90f0735b312711f131c5b70be3a7de3";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private RestTemplate restTemplate;

    // Metodo setter para inyectar el RestTemplate
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        if (response == null || response.getMain() == null) {
            throw new RuntimeException("Error obteniendo datos del clima");
        }

        WeatherReport report = new WeatherReport();
        report.setTemperature(response.getMain().getTemperature());
        report.setHumidity(response.getMain().getHumidity());

        return report;
    }

}