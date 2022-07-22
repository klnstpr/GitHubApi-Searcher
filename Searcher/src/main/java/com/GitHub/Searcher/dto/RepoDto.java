package com.GitHub.Searcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepoDto {
    private String name;
    private String full_name;
    private String language;
    private String created_at;
    private String updated_at;
    private boolean updatedWithinThreeMonths;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isUpdatedWithinThreeMonths() {
        return updatedWithinThreeMonths;
    }

    public void setUpdatedWithinThreeMonths(boolean updatedWithinThreeMonths) {
        this.updatedWithinThreeMonths = updatedWithinThreeMonths;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

}
