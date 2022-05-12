package com.example.todoapi.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
public class BaseEntity {
    @CreationTimestamp
    private Timestamp created_date;
    @UpdateTimestamp
    private Timestamp updated_date;
}
