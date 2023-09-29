package com.codedifferently.kahootcloneapi.domain.common;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class TimeStampBaseEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        created = date;
        updated = date;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
