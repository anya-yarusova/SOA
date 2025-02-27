package com.anyarusova.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabWorkData {

    @JsonProperty("name")
    @NotBlank()
    @NotNull()
    private String name;

    @JsonProperty("coordinates")
    @NotNull
    private Coordinates coordinates;

    @JsonProperty("minimalPoint")
    @Min(1)
    private float minimalPoint;

    @JsonProperty("difficulty")
    private Difficulty difficulty;

    @JsonProperty("author")
    @NotNull
    private Person author;

    @Data
    public static class Coordinates {
        @JsonProperty("x")
        @Max(592)
        private float x;

        @JsonProperty("y")
        @Max(892)
        private double y;
    }

    @Data
    public static class Person {
        @JsonProperty("name")
        @NotBlank
        @NotNull
        private String name;

        @JsonProperty("birthday")
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private Date birthday;

        @JsonProperty("weight")
        @NotNull
        @Min(1)
        private Double weight;

        @JsonProperty("location")
        @NotNull
        private Location location;
    }

    @Data
    public static class Location {
        @JsonProperty("x")
        private float x;

        @JsonProperty("y")
        @NotNull
        private Integer y;

        @JsonProperty("name")
        @Size(max = 783)
        private String name;
    }

    public enum Difficulty {
        VERY_EASY,
        EASY,
        NORMAL,
        IMPOSSIBLE,
        INSANE;

        @JsonCreator
        public static Difficulty fromString(String key) {
            return key == null ? null : Difficulty.valueOf(key.toUpperCase());
        }
    }
}


