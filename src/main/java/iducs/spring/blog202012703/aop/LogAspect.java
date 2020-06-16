package iducs.spring.blog202012703.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	Logger logger =  LoggerFactory.getLogger(LogAspect.class);
	
	

	@Pointcut("execution(* iducs.spring.blog202012703.service.*ServiceImpl.*(..))")
	public void logginPointcut() { }
	
    @Around("logginPointcut()")
	//@Around("execution(* iducs.spring.blog202012703.service.BlogServiceImpl.*(..))")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable {
	    logger.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
	    Object result = pjp.proceed();
	    logger.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
	    return result;
	}
	
	
}
