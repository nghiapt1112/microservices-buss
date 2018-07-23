package com.nghia.tut.domain.tenant;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tenant")
public class Tenant extends AbstractEntity {

    private String tenantName;
    private String tenantDesc;
}
