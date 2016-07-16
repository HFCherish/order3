package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.AssertionConcern;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Arrays.asList;

public class User extends AssertionConcern implements Record {
    private String id;
    private String name;

    public User(String name) {
        this();
        setName(name);
    }

    private User() {
        this.id = UUID.randomUUID().toString();
    }

    private void setName(String name) {
        if( !name.matches("^[A-Za-z\\d]+$")) {
            throw new IllegalArgumentException("the name must be composed of letters and numbers.");
        }
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("id", getId());
            put("name", getName());
        }};
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }

}
