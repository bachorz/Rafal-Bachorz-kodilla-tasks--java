package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class TrelloBoard {

    private String id;
    private String name;
    private List<TrelloList> lists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrelloBoard)) return false;
        TrelloBoard that = (TrelloBoard) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getLists(), that.getLists());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getLists());
    }
}
