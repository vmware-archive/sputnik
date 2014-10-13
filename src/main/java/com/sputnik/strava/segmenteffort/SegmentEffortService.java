package com.sputnik.strava.segmenteffort;

import com.sputnik.campaign.SegmentEntity;
import com.sputnik.campaign.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.strava.api.Strava;
import org.springframework.social.strava.api.StravaSegmentEffort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SegmentEffortService {

    private Strava strava;
    private SegmentEffortConverter segmentEffortConverter;
    private SegmentRepository segmentRepository;

    @Autowired
    public void setStrava(final Strava strava) {
        this.strava = strava;
    }

    @Autowired
    public void setSegmentRepository(final SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    @Autowired
    public void setSegmentEffortConverter(SegmentEffortConverter segmentEffortConverter) {
        this.segmentEffortConverter = segmentEffortConverter;
    }

    public List<SegmentEffort> getSegmentEfforts() {
        String currentAthleteId = String.valueOf(strava.athleteOperations().getAthleteProfile().getId());

        List<StravaSegmentEffort> stravaSegmentEfforts = new ArrayList<>();

        for(String id : segmentIds()) {
            stravaSegmentEfforts.addAll(strava.segmentEffortOperations().getSegmentEfforts(id, currentAthleteId));
        }

        return segmentEffortConverter.convertList(stravaSegmentEfforts);
    }

    public SegmentEffort getSegmentEffortById(String id) {
        StravaSegmentEffort segmentEffort = strava.segmentEffortOperations().getSegmentEffortById(id);
        return segmentEffortConverter.convert(segmentEffort);
    }

    public List<SegmentEffort> getAllSegmentEfforts(String startTime, String endTime) {
        List<StravaSegmentEffort> stravaSegmentEfforts = new ArrayList<>();

        for(String id : segmentIds()) {
            stravaSegmentEfforts.addAll(strava.segmentEffortOperations().getAllSegmentEfforts(id, startTime, endTime));
        }

        return segmentEffortConverter.convertList(stravaSegmentEfforts);
    }

    private List<String> segmentIds() {
        List<SegmentEntity> segmentEntities = segmentRepository.findWithCampaign();

        List<String> segmentIds = segmentEntities.stream().map(s -> String.valueOf(s.getRemoteid())).collect(Collectors.toList());
        segmentIds.forEach(System.out::println);

        return segmentIds;
    }
}
