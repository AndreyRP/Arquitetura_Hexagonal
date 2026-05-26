package com.andrey.deadmessages.application.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.andrey.deadmessages.core.domain.bo.DlqSeverityBO;
import com.andrey.deadmessages.core.model.ErrorAudit;
import com.andrey.deadmessages.core.model.Severity;
import com.andrey.deadmessages.core.model.Status;
import com.andrey.deadmessages.infrastructure.adapter.in.message.dlq.dto.DlqMessageDTO;
import com.andrey.deadmessages.infrastructure.adapter.out.persistence.adapter.ErrorAuditRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DlqAuditService {

    private final ErrorAuditRepositoryPort repository;

    private final ObjectMapper objectMapper;

    public void process(String payload){

        try{

            DlqMessageDTO message =
                    objectMapper.readValue(payload, DlqMessageDTO.class);

            DlqSeverityBO bo = new DlqSeverityBO();

            Severity severity =
                    bo.calculate(message.getOrderItems());

            ErrorAudit audit =
                    ErrorAudit.builder()
                            .errorId(UUID.randomUUID())
                            .queueName("ANDREY PINHEIRO")
                            .payload(payload)
                            .timestamp(Instant.now())
                            .status(Status.PENDING_ANALYSIS)
                            .severity(severity)
                            .build();

            repository.save(audit);

        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}