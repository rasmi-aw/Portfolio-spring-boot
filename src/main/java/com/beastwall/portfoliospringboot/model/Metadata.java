package com.beastwall.portfoliospringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Metadata {
    private String title;
    private String description;
    @JsonProperty("og:img")
    private String image;
    private String favicon;

    @Override
    public String toString() {
        return "Metadata{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", favicon='" + favicon + '\'' +
                '}';
    }
}
