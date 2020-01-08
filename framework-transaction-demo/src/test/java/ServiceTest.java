import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.storm.App;
import com.storm.service.ISingleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ServiceTest {

    @Autowired
    ISingleService singleService;

    @Test
    public void test1() {
        singleService.doSomething();
    }
}
