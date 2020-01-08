
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.storm.App;
import com.storm.mapper.StormTestMapper;
import com.storm.model.StormTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DaoTest {
    @Autowired
    StormTestMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(DaoTest.class);


    @Test
    public void test1() {
        StormTest stormTest = mapper.selectByPrimaryKey(1);
        logger.info(stormTest.getName());
    }
}
