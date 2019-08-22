package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @NotNull
    private int id;

    @Column(name = "CREATED")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @Column(name = "UPDATED")
    private LocalDateTime updated;

    @Column(name = "VERSION")
    @Version
    private int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
        updated = created;
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
