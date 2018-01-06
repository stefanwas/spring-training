package com.stefan.training.spring.resttemplate.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stefan.training.spring.resttemplate.shared.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class GithubClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubClient.class);
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000; //ms

    // Normalnie, takie parametry jak ten URL powinny trafić do konfiguracji aplikacji,
    // Zostawiam go tu, żeby nie bawić się w fabryki, dodatkową Springową konfigurację, itp. (czyli dla prostoyy)
    private String githubUrl = "https://api.github.com/repos/{owner}/{repo}";
    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public GithubClient() {
        mapper = new ObjectMapper();
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                Message body = mapper.readValue(response.getBody(), Message.class);

                LOGGER.error("Github responded with an error. Code: {}, Message: {}", statusCode, body.getMessage());

                throw new GithubException(statusCode, body.getMessage());
            }
        });
        setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
    }

    public GithubRepoDetails getRepositoryDetails(String owner, String name) {
        return restTemplate.getForObject(githubUrl, GithubRepoDetails.class, owner, name);
    }

    public void setConnectionTimeout(int connectionTimeout) {
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(connectionTimeout);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(connectionTimeout);
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }
}
