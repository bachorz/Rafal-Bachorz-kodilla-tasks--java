package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class TrelloCardDto {
    private String name;
    private String description;
    private String pos;
    private String listId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloCardDto)) return false;
        TrelloCardDto that = (TrelloCardDto) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getPos(), that.getPos()) && Objects.equals(getListId(), that.getListId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getDescription(), getPos(), getListId());
    }
}