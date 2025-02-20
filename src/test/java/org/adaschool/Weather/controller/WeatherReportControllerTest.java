package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class WeatherReportControllerTest {

    @Mock
    private WeatherReportService weatherReportService;

    @InjectMocks
    private WeatherReportController weatherReportController;

    private MockMvc mockMvc;

    private WeatherReport mockReport;

    @BeforeEach
    public void setUp() {
        // Configuración de MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(weatherReportController).build();

        // Crear un WeatherReport simulado
        mockReport = new WeatherReport();
        mockReport.setTemperature(22.5);
        mockReport.setHumidity(60.0);
    }

    @Test
    public void testGetWeatherReport() throws Exception {
        // Simula el comportamiento del servicio
        when(weatherReportService.getWeatherReport(37.8267, -122.4233)).thenReturn(mockReport);

        // Realiza la solicitud GET y verifica la respuesta
        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "37.8267")
                        .param("longitude", "-122.4233"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(22.5))
                .andExpect(jsonPath("$.humidity").value(60.0));
    }
}
