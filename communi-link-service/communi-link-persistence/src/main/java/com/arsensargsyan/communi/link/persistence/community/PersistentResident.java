package com.arsensargsyan.communi.link.persistence.community;

import com.arsensargsyan.communi.link.persistence.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.apache.commons.lang3.builder.ToStringBuilder;

@SequenceGenerator(name = "default_gen", sequenceName = "resident_seq")
@Table(
        name = "resident",
        uniqueConstraints = {
                @UniqueConstraint(name = "UQ_resident_username_community_id", columnNames = {"username", "community_id"})
        }
)
@Entity
public class PersistentResident extends AuditableEntity {

    @Column(name = "community_id", nullable = false, updatable = false)
    private Long communityId;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "identifier", nullable = false, length = 32)
    private String identifier;

    @Column(name = "mail", nullable = false, length = 32)
    private String mail;

    protected PersistentResident() {
        super();
    }

    public PersistentResident(
            final Long communityId,
            final String username,
            final String identifier,
            final String mail
    ) {
        this.communityId = communityId;
        this.identifier = identifier;
        this.username = username;
        this.mail = mail;
    }

    public String username() {
        return username;
    }

    public String identifier() {
        return identifier;
    }

    public String mail() {
        return mail;
    }

    public Long communityId() {
        return communityId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("communityId", communityId())
                .append("identifier", identifier())
                .append("username", username())
                .append("mail", mail())
                .toString();
    }
}
