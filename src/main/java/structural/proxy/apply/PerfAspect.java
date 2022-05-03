package structural.proxy.apply;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerfAspect {


    /**
     * 시간을 측정하는 메서드
     * 여기서 ProceedingJoinPoint는 실행되는 메서드
     *
     * @param point
     * @throws Throwable
     */
    @Around("bean(gameService)")
    public void timestamp(ProceedingJoinPoint point) throws Throwable {
        long before = System.currentTimeMillis();
        point.proceed();
        System.out.println("point.getSignature().getName() = " + point.getSignature().getName());
        System.out.println(System.currentTimeMillis() - before);
    }
}
