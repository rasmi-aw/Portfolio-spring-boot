package com.beastwall.portfoliospringboot.model;

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
public class Personal {
    private String fullname;
    private String avatar;
    private String job;
    private String birthday;
    private String location;
    private String email;
    private String facebook;
    private String twitter;
    private String instagram;
    private String github;
    private String mapsLink;
}
