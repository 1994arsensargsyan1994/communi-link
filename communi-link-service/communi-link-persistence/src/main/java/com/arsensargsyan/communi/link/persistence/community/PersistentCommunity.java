package com.arsensargsyan.communi.link.persistence.community;

import com.arsensargsyan.communi.link.common.CommunityType;
import com.arsensargsyan.communi.link.persistence.AuditableEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@SequenceGenerator(name = "default_gen", sequenceName = "community_seq")
@Table(
        name = "community",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_community_name_type", columnNames = {"name", "type"})
        }
)
public class PersistentCommunity extends AuditableEntity {

    @Column(name = "name", nullable = false, updatable = false, length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false, length = 32)
    private CommunityType type;

    @Column(name = "max_count", nullable = false)
    private Integer maxCount;

    @Version
    @Column(name = "current_count", nullable = false)
    private Integer currentCount;

    @Column(name = "available", insertable = false, updatable = false, nullable = false)
    private boolean available;

    /* Will be used by reflection */
    protected PersistentCommunity() {
        super();
    }

    public PersistentCommunity(final String name, final CommunityType type, final Integer maxCount) {
        this.name = name;
        this.type = type;
        this.maxCount = maxCount;
    }

    public String name() {
        return name;
    }


    public CommunityType type() {
        return type;
    }

    public Integer maxCount() {
        return maxCount;
    }

    public Integer currentCount() {
        return currentCount;
    }

    public boolean available() {
        return available;
    }

    public boolean busy() {
        return currentCount >= maxCount;
    }

    public PersistentCommunity incrementCurrentCount() {
        this.currentCount = currentCount + 1;
        return this;
    }

    public PersistentCommunity maxCount(final Integer maxCount) {
        this.maxCount = maxCount;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name())
                .append("type", type())
                .append("maxCount", maxCount())
                .append("currentCount", currentCount())
                .append("available", available())
                .toString();
    }
}
