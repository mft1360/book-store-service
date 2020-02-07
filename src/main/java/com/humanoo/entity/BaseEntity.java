package com.humanoo.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Base entity class for providing some fields that are public among all of entities
 *
 * @author R.Fattahi
 */
@MappedSuperclass
@Data
@SuperBuilder
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    public BaseEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateUpdate;

}
