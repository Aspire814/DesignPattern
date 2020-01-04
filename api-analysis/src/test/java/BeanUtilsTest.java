import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Test;

import com.storm.common.util.BeanUtils;

public class BeanUtilsTest {
    @Test
    public void test1(){
        Set<Class<?>> typesAnnotatedSet = (Set<Class<?>>) BeanUtils.scanAnnotationByPackageName("com.storm.service.impl", ElementType.TYPE);
        Set<Method> methodsAnnotatedSet = (Set<Method>) BeanUtils.scanAnnotationByPackageName("com.storm.service.impl", ElementType.METHOD);
        System.out.println(typesAnnotatedSet.size());
        System.out.println(methodsAnnotatedSet.size());
    }
}
