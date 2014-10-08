package com.sputnik.scheduled;

import com.sputnik.strava.StravaService;
import com.sputnik.strava.segmenteffort.SegmentEffort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.strava.api.Strava;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class SponsoredSegmentFinder {

    @Autowired
    ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    DataSource dataSource;

    @Value("${sponsoredSegments}")
    String[] segmentIds;

    @Value("${superUserId}")
    String superUserId;

    @Scheduled(cron = "0 22 * * * *")
//    @Scheduled(fixedRate = 5000)
    public void retrieve() {
        JdbcUsersConnectionRepository jdbcRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        ConnectionRepository repository = jdbcRepository.createConnectionRepository(superUserId);
        Connection<Strava> connection = repository.findPrimaryConnection(Strava.class);

        Strava strava = connection.getApi();

        StravaService stravaService = new StravaService();

        stravaService.setStrava(strava);
        stravaService.setSegmentIds(segmentIds);

        Instant now = Instant.now();
        ZonedDateTime nowZoned = now.atZone(ZoneId.of("America/Denver"));
        ZonedDateTime yesterdayZoned = now.minus(Duration.ofDays(1)).atZone(ZoneId.of("America/Denver"));

        String start_time = yesterdayZoned.toString();
        String end_time = nowZoned.toString();

        System.out.println("*************************************************************");
        List<SegmentEffort> allSegmentEfforts = stravaService.getAllSegmentEfforts(start_time, end_time);

        allSegmentEfforts.forEach(effort -> System.out.println(effort.getAthleteId()));
    }
}
