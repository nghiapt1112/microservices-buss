package com.nghia.base.config;

import com.nghia.base.test.TestCase;
import com.nghia.base.test.Variable;
import com.nghia.libraries.commons.mss.utils.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Objects;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ApplicationTest.class, MongoConfig.class, EmailConfig.class})
@WebFluxTest
@WebAppConfiguration
public abstract class BaseServiceTest {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceTest.class);
    protected Variable STORED_VARIABLES;
    @Autowired
    private WebTestClient webTestClient;

    @Before
    public void init() {
        this.STORED_VARIABLES = new Variable();
    }


    protected void testFile(String fileName) {
        try (InputStream stream = new ClassPathResource(fileName).getInputStream()) {
            this.execFile(stream, 0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void execFile(InputStream stream, int sec) throws IOException, InterruptedException {
        String fileContent = IOUtils.toString(stream, Charset.defaultCharset());
        ArrayList<TestCase> testCases = JsonUtils.toCollection(fileContent, ArrayList.class, TestCase.class);

        for (TestCase testCase : testCases) {
            Thread.sleep(1000 * sec);
            String tcInfo = this.STORED_VARIABLES.parseReqFromVariables(testCase.getDesc());
            LOGGER.info("{} is Executing.", tcInfo);
            long start = System.currentTimeMillis();
            testCase
                    .injectDependencies(webTestClient, STORED_VARIABLES)
                    .execTest();
            LOGGER.info("Testcase {} DONE in {} (s)", tcInfo, (System.currentTimeMillis() - start) / 1000.0);
        }
    }

    protected BaseServiceTest testFolder(String folderPath) {
        this.testFolder(folderPath, 0);
        return this;
    }

    /**
     * @param folderPath
     * @param sec        :       sleep between each testCases.
     */
    protected BaseServiceTest testFolder(String folderPath, int sec) {
        try {
            File[] files = new File(BaseServiceTest.class.getResource(folderPath).getFile()).listFiles();
            if (Objects.isNull(files)) {
                LOGGER.error("Folder is empty");
                return this;
            }
            for (File file : files) {
                this.execFile(new FileInputStream(file), sec);
            }
            return this;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void withNewVariableStore() {
        this.STORED_VARIABLES = null;
        this.STORED_VARIABLES = new Variable();
    }

    @After
    public void tearDown() {
        STORED_VARIABLES = null;
    }


}

