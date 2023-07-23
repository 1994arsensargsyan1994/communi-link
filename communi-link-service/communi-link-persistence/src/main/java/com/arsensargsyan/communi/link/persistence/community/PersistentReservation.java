package com.arsensargsyan.communi.link.persistence.community;

import java.time.LocalDateTime;

import com.arsensargsyan.communi.link.persistence.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;


@Entity
@SequenceGenerator(name = "default_gen", sequenceName = "reservation_seq")
@Table(
        name = "reservation",
        indexes = {
                @Index(name = "IDX_reservation_community_id", columnList = "community_id"),
                @Index(name = "IDX_reservation_resident_id", columnList = "resident_id")
        }
)
public class PersistentReservation extends AuditableEntity {

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDateTime start;

    @Column(name = "end_date")
    private LocalDateTime end;

    @Column(name = "slot_info", length = 1000)
    private String slotInfo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "community_id", referencedColumnName = "id",
            nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "FK_reservation_community_id")
    )
    private PersistentCommunity community;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "resident_id",
            nullable = false, updatable = false,
            foreignKey = @ForeignKey(name = "FK_reservation_resident_id")
    )
    private PersistentResident resident;

    protected PersistentReservation() {
        super();
    }

    public PersistentReservation(
            final LocalDateTime start,
            final LocalDateTime end,
            final PersistentCommunity community,
            final PersistentResident resident
    ) {
        this.start = start;
        this.end = end;
        this.community = community;
        this.resident = resident;
    }

    public LocalDateTime start() {
        return start;
    }

    public LocalDateTime end() {
        return end;
    }

    public PersistentCommunity community() {
        return community;
    }

    public String slotInfo() {
        return slotInfo;
    }

    public PersistentResident resident() {
        return resident;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("start", start())
                .append("end", end())
                .append("resident", resident())
                .append("community", community())
                .append("slotInfo", slotInfo())
                .toString();
    }
}
