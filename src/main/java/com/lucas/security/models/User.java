package com.lucas.security.models;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GenericGenerator(name = "uuid_generator", strategy = "com.lucas.security.uuid.UuidGenerator")
    @GeneratedValue(generator = "uuid_generator")
    private UUID uuid;

    private String name;

    private String email;

}
