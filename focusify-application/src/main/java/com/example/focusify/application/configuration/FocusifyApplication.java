package com.example.focusify.application.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/api")
@OpenAPIDefinition(
    info =
        @Info(
            title = "Focusify API",
            description = "This API allows to manage todo-lists based on the ivy-lee principles",
            version = "1.0",
            contact = @Contact(name = "cloudbug", email = "cloudbug2020@gmail.com")),
    servers = {@Server(url = "http://localhost:8081")},
    externalDocs =
        @ExternalDocumentation(
            url = "https://github.com/cloudbug2020/Focusify",
            description = "Github repository of this project"),
    tags = {
      @Tag(name = "api", description = "Public that can be used by anybody"),
      @Tag(name = "todos", description = "application user managing todos")
    })
public class FocusifyApplication extends Application {}
