package com.andrey.deadmessages.infrastructure.adapter.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrey.deadmessages.infrastructure.adapter.out.persistence.entity.ErrorAuditEntity;

public interface ErrorAuditJpaRepository
        extends JpaRepository<ErrorAuditEntity, UUID> {
}