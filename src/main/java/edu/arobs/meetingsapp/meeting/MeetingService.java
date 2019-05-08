package edu.arobs.meetingsapp.meeting;

import edu.arobs.meetingsapp.user.User;
import edu.arobs.meetingsapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingModelMapper meetingModelMapper;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, MeetingModelMapper meetingModelMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingModelMapper = meetingModelMapper;
    }

    public Meeting create(Meeting meeting) {
        meeting.setId(null);
        return meetingRepository.save(meeting);
    }

    public Meeting getById(Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Meeting id=%d does not exist", id)));
    }


    public Meeting update(Long id, Meeting meeting) {
        Meeting existingMeeting = getById(id);
        existingMeeting.setDate(meeting.getDate());
        existingMeeting.setTitle(meeting.getTitle());
        existingMeeting.setLocation(meeting.getLocation());

        return meetingRepository.save(existingMeeting);
    }


    public Meeting delete(Long id) {
        Meeting existingMeeting = getById(id);
        meetingRepository.deleteById(id);

        return existingMeeting;
    }
}
