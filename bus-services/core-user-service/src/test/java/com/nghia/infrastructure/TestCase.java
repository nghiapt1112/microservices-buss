package com.nghia.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TestCase {
    private String desc;
    private String api;
    private Method action;
    private Input input;
    private Output output;

    @JsonIgnore
    private MockMvc mvc;

    @JsonIgnore
    private Variable storedVars;

    public TestCase injectDependencies(MockMvc mvc, Variable storedValues) {
        this.mvc = mvc;
        this.storedVars = storedValues;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void executeTest() {
        try {
            MockHttpServletResponse response = this.parseInput().execRequest().getResponse();
            String content = response.getContentAsString();
//            ResponseEntity httpResponse = (ResponseEntity) this.parseInput()
//                    .execRequest()
//                    .getAsyncResult();
//            if (httpResponse.getBody() instanceof OtatalkAPIResponseEntity) {
//                OtatalkAPIResponseEntity otatalk = (OtatalkAPIResponseEntity) httpResponse.getBody();
//                this.compareStatusCode(otatalk.getStatus(), this.output.getStatus());
//                // expect BODY_RESPONSE
//                this.assertBody(otatalk.getBody(), output.getFirstBodyElement());
//            } else if (httpResponse.getBody() instanceof ErrorDetails) {
//                this.compareStatusCode(((ErrorDetails) httpResponse.getBody()).getStatus(), this.output.getStatus());
//            }
            System.out.println("la la la la");
        } catch (Exception e) { // http url incorrect or something un-catchable happened.
            e.printStackTrace();
            fail(String.format("Failed when execute this API %s\n Message: %s", this.api, e.getMessage()));
        }
    }

    /**
     * Is this test-case comparing value with a variable.
     * use $ to create a variable.
     * use # to use this variable.
     * return true if comparing with variable.
     */
    private boolean detectVariables(String k, Map<String, Object> mapResponseObj, Object value) {

        if (value instanceof String) {
            String v = value.toString();
            if (v.startsWith("$") && mapResponseObj.containsKey(k)) {
                this.storedVars.addVariable(v, mapResponseObj.get(k));
                return true;
            } else if (v.startsWith("#") && mapResponseObj.containsKey(k)) {
                Assert.assertEquals(this.storedVars.retrieve(v), mapResponseObj.get(k));
                return true;
            }
        }
        return false;
    }

    public TestCase parseInput() {
        this.api = this.storedVars.parseReqFromVariables(this.api);
        if (this.action == Method.POST || this.action == Method.PUT) {
            this.storedVars.refresh(this.input);
        }
        if (this.output != null) {
            this.storedVars.refresh(this.output);
        }
        // decode api_request
        // VD: /user/#userId thi phai decode dc #userId la bao nhieu.

        return this;
    }

    private MvcResult execRequest() throws Exception {
        switch (this.action) {
            case GET:
                return this.mvc.perform(get(this.api)).andReturn();
            case POST:
            case PUT:
                return this.mvc.perform(
                        post(this.api)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.toJson(this.input.getFirstBodyElement()))
                ).andReturn();
            case DELETE:
                return this.mvc.perform(delete(this.api).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
            default:
                System.out.println("Khong hieu, khong hieu anh oi");
        }
        return null;
    }

    /**
     * @param responseObject
     * @param expectObject
     */
    private void assertBody(Object responseObject, Object expectObject) {
        if (Objects.isNull(expectObject)) {
            return;
        }
        if (Objects.isNull(responseObject)) {
            fail("Error on comparison: Response and expect: Expect != null but Actual_response is null");
        }

        if (responseObject instanceof List && expectObject instanceof List) {
            compareList(responseObject, expectObject);
        } else {
            compareObject(responseObject, expectObject);
        }

    }

    /**
     * TRUONG HOP OutPut la 1 List
     *
     * @param responseObject
     * @param expectObject
     */
    private void compareList(Object responseObject, Object expectObject) {
        List<String> messages = null;
        List expectObjects = ((List) expectObject);
        List responseObjects = ((List) responseObject);

        Assert.assertEquals("Comparison error on size of List: ", expectObjects.size(), responseObjects.size());

        for (Object currentExpectObj : expectObjects) {
            if (messages == null) {
                messages = new ArrayList<>();
            } else messages.clear();
            boolean expectFlag = false;

            for (Object currentResponseObj : responseObjects) {
                try {
                    compareObject(JsonUtils.toMap(currentResponseObj), JsonUtils.toMap(currentExpectObj));
                    expectFlag = true;
                    break;
                } catch (AssertionError e) {
                    messages.add(e.getMessage());
                    expectFlag = false;
                }
            }
            if (!expectFlag) {
                String msg = messages.stream().collect(Collectors.joining("\n"));
                messages.clear();
                Assert.fail(msg);
            }
        }
    }

    /**
     * Truong hop expect la 1 object binh thuong
     */
    private void compareObject(Object responseObject, Object expectObject) {
        Map<String, Object> mapResponseObj = JsonUtils.toMap(responseObject);
        Map<String, Object> mapExpectObject = (Map<String, Object>) expectObject;

        for (Map.Entry expectEntry : mapExpectObject.entrySet()) {
            String k = expectEntry.getKey().toString();
            Object expectVal = expectEntry.getValue();
            Assert.assertEquals("Expect exist key :" + k + " but not exist.", true, mapResponseObj.containsKey(k));
            if (detectVariables(k, mapResponseObj, expectVal) || !mapResponseObj.containsKey(k)) {
                continue;
            }

            if (expectVal instanceof List) { // Neu la List thi so sanh tung phan tu trong list
                compareList(mapResponseObj.get(k), expectVal);
            } else if (expectVal instanceof Number) { // Neu la Number thi so sanh String cua no vs nhau
                compareNumber(k, mapResponseObj.get(k), expectVal);
            } else {
                Assert.assertEquals(String.format("TestCase: %s.\nComparison error on key: %s \n", this.desc, k), expectEntry.getValue(), mapResponseObj.get(expectEntry.getKey()));
            }

        }
    }

    private void compareNumber(String key, Object responseVal, Object expectVal) {
        Assert.assertEquals(String.format("TestCase: %s.\nComparison error on key: %s \n", this.desc, key), String.valueOf(expectVal), String.valueOf(responseVal));
    }

    private void compareStatusCode(int actualCode, int expectCode) {
        Assert.assertEquals(String.format("TestCase: %s.\nFailed when expect status code.", this.desc), actualCode, expectCode);
    }

    enum Method { GET, POST, PUT, DELETE }
}
