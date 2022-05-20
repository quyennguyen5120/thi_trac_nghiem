package com.example.todoapi.entities;

import com.example.todoapi.services.ServiceImpl.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "updated_by")
    private String updated_by;

    @PrePersist
    public void prePersist(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            this.created_by = userDetails.getUsername();
        }
        catch (Exception e){
            this.created_by = "unknowUser";
        }
    }

    @PreUpdate
    public void preUpdate(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            this.updated_by = userDetails.getUsername();
        }
        catch (Exception e){
            this.updated_by = "unknowUser";
        }
    }
}
