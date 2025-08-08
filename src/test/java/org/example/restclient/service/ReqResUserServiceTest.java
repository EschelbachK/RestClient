package org.example.restclient.service;

import org.example.restclient.model.RR_User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(RR_UserClient_Service.class)
class ReqResUserClientServiceIntegrationTest {

    @Autowired
    private RR_UserClient_Service service;

    @Autowired
    private MockRestServiceServer server;

    @BeforeEach
    void setUp() {
        server.reset();
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        // language=json
        String mockJson = """
            {
              "data": [
                {
                  "id": 7,
                  "email": "michael.lawson@reqres.in",
                  "first_name": "Michael",
                  "last_name": "Lawson",
                  "avatar": "https://reqres.in/img/faces/7-image.jpg"
                }
              ]
            }
            """;

        server.expect(requestTo("https://reqres.in/api/users?page=2"))
                .andRespond(withSuccess(mockJson, MediaType.APPLICATION_JSON));

        List<RR_User> users = service.getAllUsers();

        assertThat(users).hasSize(1);
        assertThat(users.get(0).email()).isEqualTo("michael.lawson@reqres.in");

        server.verify();
    }
}
