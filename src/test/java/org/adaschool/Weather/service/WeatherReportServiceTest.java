package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    void setUp() {
        weatherReportService.setRestTemplate(restTemplate);
    }

    @Test
    public void testGetWeatherReport() {
        WeatherApiResponse mockResponse = new WeatherApiResponse();
        WeatherApiResponse.Main mockMain = new WeatherApiResponse.Main();
        mockMain.setTemperature(25.0);
        mockMain.setHumidity(70.0);
        mockResponse.setMain(mockMain);

        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(WeatherApiResponse.class)))
                .thenReturn(mockResponse);
        WeatherReport result = weatherReportService.getWeatherReport(40.7128, -74.0060);

        assertEquals(25.0, result.getTemperature(), "La temperatura esperada no coincide");
        assertEquals(70.0, result.getHumidity(), "La humedad esperada no coincide");
    }
}

