package com.arsensargsyan.communi.link.api.model.request;

import java.time.LocalDateTime;

import com.arsensargsyan.communi.link.api.model.common.ValidatableRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReservationCreationRequest extends ValidatableRequest {

    @NotBlank
    @JsonProperty("username")
    @Size(min = 1, max = 32)
    private String username;

    @NotBlank
    @JsonProperty("mail")
    @Size(min = 1, max = 32)
    private String mail;

    @NotBlank
    @JsonProperty("identifier")
    @Size(min = 1, max = 32)
    private String identifier;

    @NotNull
    @FutureOrPresent
    @JsonProperty("start")
    private LocalDateTime start = LocalDateTime.now();

    @NotNull
    @FutureOrPresent
    @JsonProperty("end")
    private LocalDateTime end;

    public ReservationCreationRequest() {
        super();
    }

    public String username() {
        return username;
    }

    public void username(final String username) {
        this.username = username;
    }

    public String mail() {
        return mail;
    }

    public void mail(final String mail) {
        this.mail = mail;
    }

    public String identifier() {
        return identifier;
    }

    public void identifier(final String identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime start() {
        return start;
    }

    public void start(final LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime end() {
        return end;
    }

    public void end(final LocalDateTime end) {
        this.end = end;
    }
}