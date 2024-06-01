package ru.netology.dataaccessobject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MyRepository {
    public String script = read("scriptRequest.sql");

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MyRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    public List<String> getProductName(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
                namedParameterJdbcTemplate.getJdbcTemplate().getDataSource());
        List<String> results = template.queryForList(script, parameters, String.class);
        return results;
    }

    public static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}