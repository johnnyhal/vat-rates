package com.example.vatRates.service;

import com.example.vatRates.model.CountryJson;

import java.util.List;

public interface VatRateService {
    List<CountryJson> getCountriesSortedByVatRate();
}
