import org.junit.Test;

import com.storm.decorator.AnotherDecorator;
import com.storm.decorator.Decorator;
import com.storm.decorator.Target;

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
