package com.andrey.deadmessages.core.model;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorAudit {

    private UUID errorId;

    private String queueName;

    private String payload;

    private Instant timestamp;

    private Status status;

    private Severity severity;
}