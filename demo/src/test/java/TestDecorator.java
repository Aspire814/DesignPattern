import org.junit.Test;

import com.storm.struct_model.decorator.Decorator;
import com.storm.struct_model.decorator.Target;
import com.storm.struct_model.decorator.AnotherDecorator;

public class TestDecorator {
    @Test
    public void test() {
        Target target = new Target();
        Decorator decorator = new Decorator(target);
        AnotherDecorator anotherDecorator = new AnotherDecorator(target);
        decorator.fun();
        anotherDecorator.fun();
    }
}
