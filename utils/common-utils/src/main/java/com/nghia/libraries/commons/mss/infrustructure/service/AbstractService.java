package com.nghia.libraries.commons.mss.infrustructure.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractService extends GenericAbstractService {
    protected static final String AUTHORIZATION = "Authorization";
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected RestTemplate restTemplate;
    protected Map<String, Object> shareData = new HashMap<>();
    protected String authorizationKey;
    protected String GATE_WAY_URL = "NULL"; // TODO define default value for String when inject from config-server

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private ResponseService responseService;


    public String getGATE_WAY_URL() {
        return env.getProperty("custom.gateway.url");
    }

    protected URI getServiceURL(String serviceId, String fallbackURI) {
        URI uri;
        try {
            ServiceInstance instance = this.loadBalancerClient.choose(serviceId);
            uri = instance.getUri();
            LOG.debug("Resolved serviceId '{}' to URL '{}'.", serviceId, uri);
        } catch (Exception e) {
            LOG.warn("Cannot get Instance of {}, trying to use defaultURI:", serviceId, fallbackURI);
            LOG.error("Error cause: {}, \nError message: {}", e.getCause(), e.getMessage());
            uri = URI.create(fallbackURI);
        }
        return uri;
    }

    protected <T> T readData(Class<T> clazz, String src) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.reader(clazz).readValue(src);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getForObject(String requestURI) {
        return restTemplate.getForObject(requestURI, String.class);
    }

    protected <T> T getForObject(String requestURI, Class<T> responseType) {
        LOG.warn("Requesting with URI:{}\n", requestURI);
        return restTemplate.exchange(requestURI, HttpMethod.GET, this.getHeaders(null), responseType).getBody();
    }

    protected <T> T getForObject(String requestURI, Map<String, String> headers, Class<T> responseType) {
        LOG.warn("Requesting with URI:{}\n", requestURI);
        return restTemplate.exchange(requestURI, HttpMethod.GET, this.getHeaders(headers), responseType).getBody();
    }

    protected <T> ResponseEntity<T> badGateWay(Class<T> responseType) {
        return this.responseService.createResponse(null, HttpStatus.BAD_GATEWAY);
    }


    private HttpEntity getHeaders(Map<String, String> reqHeaders) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!Objects.isNull(reqHeaders)) {
            reqHeaders.forEach((k, v) -> headers.set(k, v));
        }
        return new HttpEntity<>(headers);
    }
}
