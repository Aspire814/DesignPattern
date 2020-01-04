import org.junit.Test;

import com.storm.common.aspect.ApiMonitorAspect;
import com.storm.common.util.BeanFactory;
import com.storm.service.ISingleDemoSrv;

public class ProxyTest {
    @Test
    public void test2() {
        try {
            BeanFactory.init("com.storm.service", ApiMonitorAspect.class);
            ISingleDemoSrv singleDemoSrv = (ISingleDemoSrv) BeanFactory.getBean("singleDemoSrv");
            singleDemoSrv.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
