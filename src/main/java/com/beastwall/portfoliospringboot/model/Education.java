package com.beastwall.portfoliospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Education {
    private String degree;
    private String period;
    private String description;
}
