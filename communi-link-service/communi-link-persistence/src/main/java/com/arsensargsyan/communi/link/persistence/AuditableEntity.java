package com.arsensargsyan.communi.link.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class AuditableEntity extends IdentifiableEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at", nullable = false)
    private LocalDateTime lastModifiedAt;

    protected AuditableEntity() {
        super();
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime lastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("createdAt", createdAt())
                .append("lastModifiedAt", lastModifiedAt())
                .toString();
    }
}
