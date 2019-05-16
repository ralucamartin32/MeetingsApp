package edu.arobs.meetingsapp.TimeSetter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;

@MappedSuperclass
@Setter
@Getter
public class TimeSetter {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    protected Timestamp creationDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    protected Timestamp lastUpdate;


}
