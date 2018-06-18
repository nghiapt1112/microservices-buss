package com.nghia.base.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Variable {
    private Map<String, Object> STORED_VARIABLES;

    {
        STORED_VARIABLES = new LinkedHashMap<>();
    }

    void addVariable(String k, Object v) {
        this.STORED_VARIABLES.put(k.replaceFirst("\\$", ""), v);
    }

    Object retrieve(String k) {
        return this.STORED_VARIABLES.get(k.replaceFirst("#", ""));
    }

    public String parseReqFromVariables(String api) {
        if (!api.contains("#")) {
            return api;
        }
        String req = new String(api);
        for (Map.Entry entry : this.STORED_VARIABLES.entrySet()) {
            req = req.replace("#" + entry.getKey(), entry.getValue().toString());
        }
        return req;
    }

    /**
     * // chỉ nhận 1 giá trị duy nhất trong mục input. Do RESTful không nên để nhiều requestBody trong 1 API
     * // Phần implement hiện tại của testCase cũng chỉ support 1 requestBody trong INPUT.
     *
     * @param request
     */
    public void refresh(Request request) {
        Object requestBodyRaw = request.getFirstBodyElement();
        Map<String, Object> reqBodyParser = new HashMap<>();

        if (requestBodyRaw instanceof Map) {
            reqBodyParser = ((Map<String, Object>) requestBodyRaw);

        } else if (requestBodyRaw instanceof List) {
            for (Object el : ((List) requestBodyRaw)) {
                reqBodyParser.putAll(((Map<String, Object>) el));
            }
        }

        for (Map.Entry e : reqBodyParser.entrySet()) {
            if (e.getValue() instanceof String) {
                String v = e.getValue().toString();
                if (v.startsWith("$")) {
                    /**
                     * register or re-new variable $var_name
                     */
                    this.STORED_VARIABLES.put(v.replaceFirst("\\$", ""), v);
                } else if (v.startsWith("#")) {
                    // using this variable.
                    Object valOfVar = this.retrieve(v);
                }
            } else {
                // Value la 1 object hoặc có giá trị khác String nên không xử lý.
            }
        }
    }

    public void add(String k, Object v) {
        this.STORED_VARIABLES.put(k, v);
    }
}
