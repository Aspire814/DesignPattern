import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.storm.App;
import com.storm.common.aspect.RepositoryAspect;
import com.storm.common.util.BeanFactory;
import com.storm.service.ISingleService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TransactionTest {
    @Autowired
    private SqlSessionFactory factory;

    @Test
    public void test1() {
        try {
            BeanFactory.init("com.storm.service", RepositoryAspect.class, factory);
            ISingleService singleService = (ISingleService) BeanFactory.getBeanFromContainer("singleService");
            singleService.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
