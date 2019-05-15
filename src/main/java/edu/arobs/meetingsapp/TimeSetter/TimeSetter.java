package edu.arobs.meetingsapp.TimeSetter;


import java.sql.Timestamp;
import java.time.Instant;

public class TimeSetter {

    private Timestamp creationDate;
    private Timestamp lastUpdateDate;

    public TimeSetter() {
    }

    public TimeSetter(Timestamp creationDate, Timestamp lastUpdateDate) {
        this.creationDate = Timestamp.from(Instant.now());
        this.lastUpdateDate = lastUpdateDate;
    }
}
