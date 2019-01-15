package nghia.test.base;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.nghia.tut.mss.controller",
        "com.nghia.tut.mss.domain",
        "nghia.test"
})
public class ApplicationTest {
}
