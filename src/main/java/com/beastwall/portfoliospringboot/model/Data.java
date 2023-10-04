package com.beastwall.portfoliospringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Data {
    private Metadata metadata;
    private Personal personal;
    private About about;
    private Resume resume;
    private List<PortfolioProject> portfolio;
    private List<Blog> blog;
}
