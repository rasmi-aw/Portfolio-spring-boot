package com.beastwall.portfoliospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class About {
    private String description;
    private List<Activity> activities;
    private List<Testimonial> testimonials;
}
