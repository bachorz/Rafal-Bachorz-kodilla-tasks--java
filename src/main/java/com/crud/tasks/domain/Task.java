package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "tasks")
@Service
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;
}
