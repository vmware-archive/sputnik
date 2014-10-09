package com.sputnik.scheduled;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Map;

@Component
public class ProviderUserEmailRepository {

    @Inject
    DataSource dataSource;

    public Map<String, String> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate.query("select userconnection.provideruserid, users.email from users inner join userconnection on users.id = CAST(userconnection.userid AS INT)", new ProviderIdEmailExtractor());
    }
}
