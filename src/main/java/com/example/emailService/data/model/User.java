package com.example.emailService.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String password;
    private boolean isEnabled;
    @ElementCollection
    @CollectionTable(name = "link_name", joinColumns = @JoinColumn(name = "link_id"))
    @MapKeyColumn(name = "link_key_and_id")
    @Column(name = "link_key_and_id")
    private Map<String, Long> links = new HashMap<>();
    private long numberOfLinks;


}
