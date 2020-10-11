package br.com.brenootsuka.pegcontas.model.response;

import java.time.ZonedDateTime;
import java.util.Set;

public class ExceptionResponse {

    private final ZonedDateTime timestamp;
    private final int status;
    private final String error;
    private final Set<String> messages;

    public ExceptionResponse(
            ZonedDateTime timestamp,
            int status,
            String error,
            Set<String> messages
    ) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messages = messages;
    }

    public ZonedDateTime getTimestamp() { return timestamp; }

    public int getStatus() { return status; }

    public String getError() { return error; }

    public Set<String> getMessages() { return messages; }
}
