package com.heritage.mkheritage.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HeritageSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String historic;
    @Column(name = "\"natural\"")
    private String natural;
    private String tourism;
    private String address;
    private double lat;
    private double lon;
}
