/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marko.securityApp.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Marko
 */



//In any business application, auditing simply means tracking and logging
//every change we do in our persisted records, which simply means tracking every insert,
//update, and delete operation and storing it.

@MappedSuperclass // Designates a class whose mapping information is applied to the entities that inherit from it.
@EntityListeners(AuditingEntityListener.class) // automatically populate createdAt and updatedAt values when we persist an entity

 // to prevent specified fields from being serialized or deserialized
 // (i.e. not include in JSON output;
//when allowGetters = true --> its used for read only props, there is a getter, but no matching setter:
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)

// To enable JPA Auditing, we’ll need to add @EnableJpaAuditing annotation to our main class or any other configuration classes. We add it in AuditingConfig class
public abstract class DateAudit implements Serializable{
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    
    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
    
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
