package com.example.vatRates;

import com.example.vatRates.model.CountryJson;
import com.example.vatRates.service.VatRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class VatRatesApplication implements CommandLineRunner {

    @Autowired
    VatRateService service;

    public static void main(String[] args) {
        SpringApplication.run(VatRatesApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) {
        List<CountryJson> sortedCountries = service.getCountriesSortedByVatRate();

        if (!sortedCountries.isEmpty()) {
            System.out.println("Countries with lowest standard VAT rate:");
            int numberOfElementsToPrintOut = 2;
            for (int i = 0; i < numberOfElementsToPrintOut; i++) {
                CountryJson countryJson = sortedCountries.get(i);
                System.out.println(countryJson.getName() + " - " + countryJson.getStandardRate());
            }

            System.out.println("Countries with highest standard VAT rate:");
            int size = sortedCountries.size();
            for (int i = size - 1; i > size - numberOfElementsToPrintOut - 1; i--) {
                CountryJson countryJson = sortedCountries.get(i);
                System.out.println(countryJson.getName() + " - " + countryJson.getStandardRate());
            }
        } else {
            System.out.println("List of countries is empty.");
        }
    }
}
