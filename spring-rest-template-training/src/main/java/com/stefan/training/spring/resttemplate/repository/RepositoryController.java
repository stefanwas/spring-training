package com.stefan.training.spring.resttemplate.repository;

import allegro.homework.github.GithubClient;
import allegro.homework.github.GithubException;
import allegro.homework.github.GithubRepoDetails;
import allegro.homework.shared.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class RepositoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryController.class);

    @Autowired
    private GithubClient githubClient;

    public RepositoryController(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    @RequestMapping(value = "/repository/{owner}/{name}", method = RequestMethod.GET)
    public Repository getRepositoryDetails(@PathVariable String owner, @PathVariable String name) {
        LOGGER.debug("GET /repository/{}/{}", owner, name);

        GithubRepoDetails githubRepo = githubClient.getRepositoryDetails(owner, name);
        Repository repository = convert(githubRepo);

        return repository;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Message> handleException(WebRequest request, Exception exception) {
        // This method handles all exceptions for ResponseController.

        // Every exception should be logged with all possible details.
        LOGGER.error("Request failure: {}", request.getDescription(false), exception);

        // Here we can decide how do we map exceptions to response codes and how many details we pass to the client.
        if (exception instanceof GithubException) {
            GithubException githubException = (GithubException) exception;
            return new ResponseEntity<>(new Message(exception.getMessage()), githubException.getHttpStatus());
        }

        if (exception instanceof ResourceAccessException) {
            return new ResponseEntity<>(new Message("Failed to access repository data."), HttpStatus.NOT_FOUND);
        }

        //... other cases if we need any more specific response codes and detailed messages

        // In case something really unexpected happens - reply with code 500.
        return new ResponseEntity<>(new Message(exception.getMessage()), INTERNAL_SERVER_ERROR);
    }

    private Repository convert(GithubRepoDetails source) {

        Repository result = new Repository();
        result.setFullName(source.getFullName());
        result.setDescription(source.getDescription());
        result.setCloneUrl(source.getCloneUrl());
        result.setStars(source.getStargazersCount());
        result.setCreatedAt(source.getCreatedAt());

        return result;
    }

}
