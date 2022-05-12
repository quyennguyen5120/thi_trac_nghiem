package com.example.todoapi.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp created_date;
    @UpdateTimestamp
    private Timestamp updated_date;
}
