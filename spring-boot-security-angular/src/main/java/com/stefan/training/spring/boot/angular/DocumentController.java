package com.stefan.training.spring.boot.angular;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @RequestMapping("/{id}")
    public Document getDoc(@PathVariable String id) {
        return new Document(id, "DOC CONTENT : " + id);
    }

}
