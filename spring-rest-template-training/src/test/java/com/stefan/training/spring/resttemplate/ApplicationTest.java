package com.stefan.training.spring.resttemplate;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.stefan.training.spring.resttemplate.github.GithubClient;
import com.stefan.training.spring.resttemplate.repository.Repository;
import com.stefan.training.spring.resttemplate.shared.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = DEFINED_PORT)
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private GithubClient githubClient;

    private ClientAndServer mockServer; // zamokowany jest server GitHub'a na porcie 8888

    @Before
    public void startMockServer() throws Exception {
        mockServer = startClientAndServer(8888);
        githubClient.setGithubUrl("http://localhost:8888/repos/{owner}/{repo}");
    }


    @After
    public void stopMockServer() {
        mockServer.stop();
    }

    @Test
    public void shouldRespondWithRepositoryDataForExistingRepo() throws Exception {
        // given
        String existingRepositoryUrl = "http://localhost:" + port + "/repository/owner_x/existing_repo";

        mockServer
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/repos/owner_x/existing_repo"))
                .respond(HttpResponse.response().withStatusCode(200)
                        .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
                        .withBody(getResourceContent("existing-repo.json"))
                );

        // when
        ResponseEntity<Repository> response = new RestTemplate().getForEntity(existingRepositoryUrl, Repository.class);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("owner_x/existing_repo", response.getBody().getFullName());
        // ...
    }

    @Test
    public void shouldRespondWithErrorMessageForNonExistingRepo() throws Exception {
        // given
        String nonExistingRepositoryUrl = "http://localhost:" + port + "/repository/owner_x/non_existing_repo";

        mockServer
                .when(HttpRequest.request()
                        .withMethod("GET")
                        .withPath("/repos/owner_x/non_existing_repo"))
                .respond(HttpResponse.response().withStatusCode(404)
                        .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
                        .withBody(getResourceContent("non-existing-repo.json"))
                );

        // when
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(noActionErrorHandler());
        ResponseEntity<Message> response = restTemplate.getForEntity(nonExistingRepositoryUrl, Message.class);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not Found", response.getBody().getMessage());
    }


    private String getResourceContent(String fileName) throws URISyntaxException, IOException {
        File file = new File(Resources.getResource(fileName).toURI());
        String content = Files.toString(file, Charsets.UTF_8);
        return content;
    }

    private ResponseErrorHandler noActionErrorHandler() {
        return new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                // no action
            }
        };
    }
}