package com.sputnik.scheduled;

import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProviderIdEmailExtractor implements ResultSetExtractor<Map<String, String>> {

    @Override
    public Map<String, String> extractData(ResultSet rs) throws SQLException {
        HashMap<String, String> results = new HashMap<>();

        while(rs.next()){
            results.put(rs.getString(1), rs.getString(2));
        }

        return results;
    }
}
