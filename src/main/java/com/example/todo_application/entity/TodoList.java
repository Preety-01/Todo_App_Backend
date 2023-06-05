package com.example.todo_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "list")
public class TodoList {

    @Id
    private int list_id;

    @Column(unique = true)
    private String title;

    private String list_description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private User user;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "list")
    private List<Item> items = new ArrayList<>();

}
