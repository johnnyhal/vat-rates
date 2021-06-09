package com.example.vatRates.service;

import com.example.vatRates.model.CountryJson;
import com.example.vatRates.model.RatesJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VatRateServiceImpl implements VatRateService {

    Logger logger = LoggerFactory.getLogger(VatRateServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${vatrates.url}")
    private String url;

    public List<CountryJson> getCountriesSortedByVatRate() {
        try {
            RatesJson rates = restTemplate.getForObject(url, RatesJson.class);
            if (rates != null) {
                Map<String, CountryJson> countries = rates.getCountryMap();
                if (countries != null) {
                    return countries.values().stream().filter(country -> country.getIsoDuplicateOf() == null)
                            .sorted(Comparator.comparing(CountryJson::getStandardRate)).collect(Collectors.toList());
                }
            }
            return Collections.emptyList();
        } catch (Exception e) {
            logger.error("Exception was thrown while getting VAT rates: ", e);
            return Collections.emptyList();
        }
    }
}
