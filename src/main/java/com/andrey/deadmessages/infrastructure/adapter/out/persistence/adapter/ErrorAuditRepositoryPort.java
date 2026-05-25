package com.andrey.deadmessages.infrastructure.adapter.out.persistence.adapter;

import com.andrey.deadmessages.core.model.ErrorAudit;

public interface ErrorAuditRepositoryPort {

    void save(ErrorAudit audit);
}
