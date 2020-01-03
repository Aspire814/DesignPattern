import com.storm.common.aspect.SimpleAspect;
import com.storm.common.util.BeanFactory;
import com.storm.service.IMetricsSrv;
import org.junit.Test;

public class ScannerTest {
    @Test
    public void test1(){
        try {
            BeanFactory.init("com.storm.service.impl", SimpleAspect.class);
            IMetricsSrv metricsSrv = BeanFactory.getBean("metricsSrv",IMetricsSrv.class);
            metricsSrv.recordTimestamp("/test",1000);
            metricsSrv.recordResponseTime("/test",999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
