import com.storm.common.aspect.SimpleAspect;
import com.storm.common.util.BeanFactory;
import com.storm.service.impl.SingleDemoSrv;
import org.junit.Test;

public class ScannerTest {
    @Test
    public void test1(){
        try {
            BeanFactory.init("com.storm.service.impl", SimpleAspect.class);
            SingleDemoSrv singleDemoSrv = BeanFactory.getBean("singleDemoSrv", SingleDemoSrv.class);
            singleDemoSrv.doSomething();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
