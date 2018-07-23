package com.nghia.file.test.user;

import com.nghia.base.config.BaseServiceTest;
import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.junit.Test;

public class UserFileTest extends BaseServiceTest {

    @Override
    protected void testFile(String fileName) {
        super.testFile("/user/" + fileName);
    }

    @Test
    public void createUser() {
        testFile("1_user_create.json");
        System.out.println(JsonUtils.toJson(super.STORED_VARIABLES));
    }


    @Test
    public void findUser() {
        super.STORED_VARIABLES.add("userId", "5aaff86b8a491c19306a7ef9");
        testFile("2_user_find.json");
    }

    @Test
    public void CRUDUser() {
        testFolder("/user/", 10).withNewVariableStore();
    }

    @Test
    public void createBatch() {
        testFile("3_create_batch.json");
    }
}
