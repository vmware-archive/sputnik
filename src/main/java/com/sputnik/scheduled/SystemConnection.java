package com.sputnik.scheduled;


import com.sputnik.strava.StravaService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${superUserId}")
    String superUserId;

    @Value("${sponsoredSegments}")
    String[] segmentIds;

    public StravaService getStravaService() {
        JdbcUsersConnectionRepository jdbcRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        ConnectionRepository repository = jdbcRepository.createConnectionRepository(superUserId);
        Connection<Strava> connection = repository.findPrimaryConnection(Strava.class);

        Strava strava = connection.getApi();

        StravaService stravaService = new StravaService();

        stravaService.setStrava(strava);
        stravaService.setSegmentIds(segmentIds);

        return stravaService;
    }
}
