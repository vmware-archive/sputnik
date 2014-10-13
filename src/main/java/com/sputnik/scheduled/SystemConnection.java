package com.sputnik.scheduled;


import com.sputnik.campaign.SegmentRepository;
import com.sputnik.strava.segmenteffort.SegmentEffortConverter;
import com.sputnik.strava.segmenteffort.SegmentEffortService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.strava.api.Strava;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;

@Component
public class SystemConnection {
    @Inject
    private DataSource dataSource;

    @Inject
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Inject
    Environment env;

    @Inject
    SegmentRepository segmentRepository;

    @Value("${systemUserId}")
    String systemUserId;

    public SegmentEffortService getSegmentEffortService() {
        JdbcUsersConnectionRepository jdbcRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        ConnectionRepository repository = jdbcRepository.createConnectionRepository(env.getProperty("systemUserId"));
        Connection<Strava> connection = repository.findPrimaryConnection(Strava.class);

        Strava strava = connection.getApi();

        SegmentEffortService segmentEffortService = new SegmentEffortService();

        segmentEffortService.setStrava(strava);
        segmentEffortService.setSegmentRepository(segmentRepository);
        segmentEffortService.setSegmentEffortConverter(new SegmentEffortConverter());

        return segmentEffortService;
    }
}
