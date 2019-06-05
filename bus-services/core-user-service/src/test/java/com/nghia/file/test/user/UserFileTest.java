package com.nghia.file.test.user;

//import com.nghia.base.config.BaseServiceTest;
import com.nghia.infrastructure.BaseTest;
import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(
//        classes = OtatalkSocialNetworkApplication.class
//)
public class UserFileTest extends BaseTest {

    @Override
    protected void testFile(String fileName) {
        super.testFile("/user/" + fileName);
    }

    @Test
    public void createUser() {
        testFile("1_user_create.json");
        System.out.println(JsonUtils.toJson(super.storedVariables));
    }


    @Test
    public void findUser() {
        super.storedVariables.add("userId", "5aaff86b8a491c19306a7ef9");
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
