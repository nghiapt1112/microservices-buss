package com.nghia.tut.domain.tenant;

import com.nghia.tut.infrastructure.utils.AbstractEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tenant")
public class Tenant extends AbstractEntity {

    private String tenantName;
    private String tenantDesc;
}
