package edu.arobs.meetingsapp.Participant;

import org.springframework.stereotype.Component;

@Component
public class ParticipantModelMapper {

    public Participant fromDtoToEntity(ParticipantDTO participantDTO) {


        Participant participant = new Participant();
        participant.setIdMeeting(participantDTO.getIdMeeting());
        participant.setIdUser(participantDTO.getIdUser());
        return participant;
    }

    public ParticipantDTO fromEntityToDTO(Participant participant) {

        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setIdUser(participant.getIdUser());
        participantDTO.setIdMeeting(participant.getIdMeeting());
        return participantDTO;
    }
}
