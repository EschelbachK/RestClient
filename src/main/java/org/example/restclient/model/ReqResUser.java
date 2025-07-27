package org.example.restclient.model;

public record ReqResUser(
        int id,
        String email,
        String first_name,
        String last_name,
        String avatar
) {}
