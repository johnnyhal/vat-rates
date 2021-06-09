package com.example.vatRates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RatesJson {

    @JsonProperty("rates")
    Map<String, CountryJson> countryMap;
}
