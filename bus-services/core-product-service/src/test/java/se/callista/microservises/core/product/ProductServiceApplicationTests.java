package se.callista.microservises.core.product;

import com.nghia.tut.mss.ProductServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductServiceApplication.class)
@WebAppConfiguration
public class ProductServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
