import org.junit.Test;

import com.storm.action_model.strategy.StrategyFactory;

public class TestStrategySrv {
    @Test
    public void test() {
        StrategyFactory factory = new StrategyFactory(1);
        factory.executeStrategy();
    }
}
