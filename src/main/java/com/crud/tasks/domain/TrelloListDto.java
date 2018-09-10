package com.crud.tasks.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@Getter
//@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("closed")
    private boolean isClosed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloListDto)) return false;
        TrelloListDto that = (TrelloListDto) o;
        return isClosed() == that.isClosed() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), isClosed());
    }
}
