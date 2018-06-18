package com.nghia.base.test;

import com.nghia.libraries.commons.mss.utils.JsonUtils;

import java.util.Map;

public abstract class Request {

    protected Map<String, Object> body;

    public Map<String, Object> getBody() {
        return body;
    }

    public Object getFirstBodyElement() {
        String firstKey = this.body.keySet().stream().findFirst().get();
        // xử lý trên object đầu tiên của map. chưa support nhiều object trả về trong 1 request
        return this.body.get(firstKey);
    }


    public <T> T getFirstBodyElement(Class<T> type) {
        String firstKey = this.body.keySet().stream().findFirst().get();
        // xử lý trên object đầu tiên của map. chưa support nhiều object trả về trong 1 request
        return type.cast(this.body.get(firstKey));
    }
}


class Input extends Request {

}

class Output extends Request {
    private int status;

    public int getStatus() {
        return status;
    }

    public Map<String, Object> expectResponse() {
        return body;
    }

    public String bodyToJson() {
        return JsonUtils.toJson(this.body);
    }
}