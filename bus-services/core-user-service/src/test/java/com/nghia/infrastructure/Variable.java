package com.nghia.infrastructure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Variable {
    private Map<String, Object> storedVariables;

    {
        storedVariables = new LinkedHashMap<>();
    }

    void addVariable(String k, Object v) {
        this.storedVariables.put(k.replaceFirst("\\$", ""), v);
    }

    Object retrieve(String k) {
        return this.storedVariables.get(k.replaceFirst("#", ""));
    }

    public String parseReqFromVariables(String api) {
        if (!api.contains("#")) {
            return api;
        }
        String req = new String(api);
        for (Map.Entry entry : this.storedVariables.entrySet()) {
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
        if (requestBodyRaw == null) {
            return;
        }

        detectVariable(requestBodyRaw);
    }

    private void detectVariable(Object requestBodyRaw) {
        Map<String, Object> reqBodyParser = new HashMap<>();

        if (requestBodyRaw instanceof Map) {
            reqBodyParser = ((Map<String, Object>) requestBodyRaw);

        }

        for (Map.Entry e : reqBodyParser.entrySet()) {
            if (e.getValue() instanceof String) {
                String v = e.getValue().toString();
                if (v.startsWith("$")) {
                    /**
                     * register or re-new variable $var_name
                     */
                    this.storedVariables.put(v.replaceFirst("\\$", ""), v);
                } else if (v.startsWith("#")) {
                    // using this variable.
                    Object valOfVar = this.retrieve(v);
                    e.setValue(valOfVar);
                }
            } else if (e.getValue() instanceof List) {
                // Value la 1 object hoặc có giá trị khác String nên không xử lý.
                for (Object o : ((List) e.getValue())) {
                    detectVariable(o);
                }
            }
        }
    }

    public void add(String k, Object v) {
        this.storedVariables.put(k, v);
    }

    public void clear() {
        this.storedVariables.clear();
    }

    public void printAll() {
        this.storedVariables.forEach((k, v) -> System.out.println(String.format("Variables:\nKey: %s, Value: %s", k, v)));
    }
}
