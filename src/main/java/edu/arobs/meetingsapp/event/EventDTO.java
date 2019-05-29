package edu.arobs.meetingsapp.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.arobs.meetingsapp.Feedback.Feedback;
import edu.arobs.meetingsapp.user.UserDTO;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventDTO {

    private Integer id; ///
    private String name;
    private Type type;
    private String lang;///language
    private Integer usersId; ////
    private String duration;
    private Difficulty difficulty;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time time;
    private List<Integer> attendanceIds = new ArrayList<>();////
    private Integer maxPeople;////persons
    private List<Integer> waitingListIds = new ArrayList<>();/////
    private Long timestamp;///

    private List<Feedback> feedback = new ArrayList<Feedback>();
    private UserDTO users;
    private String description;

}
