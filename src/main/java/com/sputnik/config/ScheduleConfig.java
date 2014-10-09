package com.sputnik.config;

import com.sendgrid.SendGrid;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.inject.Inject;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Inject
    Environment env;

    @Bean
    SendGrid sendGrid() {
        String vcap_services = env.getProperty("VCAP_SERVICES");

        JSONObject credentials = new JSONObject(vcap_services)
                .getJSONArray("sendgrid")
                .getJSONObject(0)
                .getJSONObject("credentials");

        String username = credentials.getString("username");
        String password = credentials.getString("password");

        return new SendGrid(username, password);
    }
}
