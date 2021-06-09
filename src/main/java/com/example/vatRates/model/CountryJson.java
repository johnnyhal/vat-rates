package com.example.vatRates.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CountryJson {

    @JsonProperty("country")
    private String name;

    @JsonProperty("standard_rate")
    private Integer standardRate;

    @Nullable
    @JsonProperty("_comment")
    private String comment;

    @Nullable
    @JsonProperty("iso_duplicate")
    private String isoDuplicate;

    @Nullable
    @JsonProperty("iso_duplicate_of")
    private String isoDuplicateOf;

    @Nullable
    @JsonProperty("reduced_rate")
    private String reducedRate;

    @Nullable
    @JsonProperty("reduced_rate_alt")
    private String reducedRateAlt;

    @Nullable
    @JsonProperty("super_reduced_rate")
    private String superReducedRate;

    @Nullable
    @JsonProperty("parking_rate")
    private String parkingRate;
}
