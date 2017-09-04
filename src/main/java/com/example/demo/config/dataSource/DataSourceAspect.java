package com.example.demo.config.dataSource;

import com.example.demo.config.annotation.TargetDataSource;
import com.example.demo.config.dds.DynamicDataSourceThreadLocal;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义数据源切面
 * Created by Jet.chen on 2017/9/4.
 */
@Aspect
@Component
@Log4j
public class DataSourceAspect {
    // 切点 (设置在 DAO 层上)
    @Pointcut(value = "execution(* com.example.demo.repository.*.*(..))")
    public void dataSourcePointCut(){}

    // 执行此次 AOP 中的重点（切换数据源）
    @Before(value = "dataSourcePointCut()")
    public void before(JoinPoint joinPoint){
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();

        Class<?>[] clazz = target.getClass().getInterfaces();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            // 判断方法上是否存在用于切换数据源的注解 (我们自定义的注解)
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)){
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                String dataSource = data.value(); // 获得注解上写入的 数据源 名称
                DynamicDataSourceThreadLocal.setDataSource(dataSource);
                log.debug(Thread.currentThread().getName() + " add " + dataSource + " to ThreadLocal");
            } else {
                log.debug("no switch dataSource, use default");
            }
        } catch (NoSuchMethodException e) {
            log.error(Thread.currentThread().getName() + " add data to ThreadLocal error", e);
        }
    }

    // 清空本次使用的 ThreadLocal 节约一定的内存空间
    @After(value = "dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceThreadLocal.removeDataSource();
    }
}
