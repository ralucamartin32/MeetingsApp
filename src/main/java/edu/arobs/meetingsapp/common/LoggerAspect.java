package edu.arobs.meetingsapp.common;

import edu.arobs.meetingsapp.proposal.ProposalService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ProposalService.class);
    @Around("execution(public * edu.arobs..*Service..*(..))")
    public Object logServiceMethodsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        long start = System.currentTimeMillis();

        try {
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            Object[] methodArguments = joinPoint.getArgs();

            proceed = joinPoint.proceed();

            long executionTime = System.currentTimeMillis() - start;

            Arrays.stream(parameterNames).forEach(p -> LOGGER.info(p));
            Arrays.stream(methodArguments).forEach(a -> LOGGER.info(a.toString()));
            LOGGER.info(proceed.toString());

            LOGGER.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        } catch (Exception e) {
            LOGGER.error(joinPoint.getSignature() + " threw an exception");
            throw e;
        }

        return proceed;
    }

}
