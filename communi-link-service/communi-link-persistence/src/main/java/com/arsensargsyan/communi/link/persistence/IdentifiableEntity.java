package com.arsensargsyan.communi.link.persistence;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    protected IdentifiableEntity() {
        super();
    }

    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id())
                .toString();
    }
}