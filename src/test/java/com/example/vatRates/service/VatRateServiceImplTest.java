package com.example.vatRates.service;

import com.example.vatRates.model.CountryJson;
import com.example.vatRates.model.RatesJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VatRateServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private VatRateServiceImpl service;

    @Before
    public void before() {
        ReflectionTestUtils.setField(service, "url", "http://5.189.172.205:15500/vat-rates");
    }

    @Test
    public void getSortedCountriesWithoutDuplicates() {
        mockData(false);
        List<CountryJson> countries = service.getCountriesSortedByVatRate();
        assertEquals(5, countries.size());
        assertEquals("Slovakia", countries.get(0).getName());
        assertEquals("United Kingdom", countries.get(1).getName());
        assertEquals("Austria", countries.get(2).getName());
        assertEquals("Poland", countries.get(3).getName());
        assertEquals("Bulgaria", countries.get(4).getName());
    }

    @Test
    public void getSortedCountriesWithFilteredOutDuplicates() {
        mockData(true);
        List<CountryJson> countries = service.getCountriesSortedByVatRate();
        assertEquals(5, countries.size());
        assertEquals("Slovakia", countries.get(0).getName());
        assertEquals("United Kingdom", countries.get(1).getName());
        assertEquals("Austria", countries.get(2).getName());
        assertEquals("Poland", countries.get(3).getName());
        assertEquals("Bulgaria", countries.get(4).getName());
    }


    private void mockData(boolean containsDuplicates) {
        Map<String, CountryJson> map = new HashMap<>();
        map.put("AT", new CountryJson("Austria", 20, null, null, null, "10", "13", "false", "12"));
        map.put("SK", new CountryJson("Slovakia", 14, null, null, null, "10", "13", "false", "12"));
        map.put("PL", new CountryJson("Poland", 25, null, null, null, "10", "13", "false", "12"));
        map.put("BG", new CountryJson("Bulgaria", 78, null, null, null, "10", "13", "false", "12"));
        if (containsDuplicates) {
            map.put("GB", new CountryJson("United Kingdom", 17, null, null, "UK", "10", "13", "false", "12"));
            map.put("UK", new CountryJson("United Kingdom", 17, null, "GB", null, "10", "13", "false", "12"));
        } else {
            map.put("GB", new CountryJson("United Kingdom", 17, null, null, null, "10", "13", "false", "12"));
        }

        RatesJson json = new RatesJson(map);
        when(restTemplate.getForObject(eq("http://5.189.172.205:15500/vat-rates"), eq(RatesJson.class))).thenReturn(json);
    }
}
