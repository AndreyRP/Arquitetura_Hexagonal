package com.andrey.deadmessages.infrastructure.adapter.out.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import com.andrey.deadmessages.core.model.Severity;
import com.andrey.deadmessages.core.model.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_error_audit")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorAuditEntity {

    @Id
    private UUID errorId;

    private String queueName;

    private String payload;

    private Instant timestamp;

    private Status status;

    private Severity severity;

}
