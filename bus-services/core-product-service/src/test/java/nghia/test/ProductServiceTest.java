package nghia.test;

import com.nghia.tut.mss.domain.product.repository.ProductRepository;
import nghia.test.base.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ProductServiceTest extends BaseServiceTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void checkTest() {
        String name = "product_name_1";
        List out = productRepository.findByName(name);

        System.out.println(out);
    }
}
