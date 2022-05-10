package cs544.exercise13_1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import java.util.Date;

@Aspect
public class LoggingAdvice {

    @After("execution(public void cs544.exercise13_1.EmailSender.sendEmail(..)) && args(email,message)")
    public  void log(JoinPoint joinPoint,String email,String message){
        //question a
        System.out.println(new Date().toString()+" "+"method=" +joinPoint.getSignature().getName() +" "+"address="+email);
        //question b
        System.out.println("message="+message);
        //question c
        System.out.println("outgoing mail server="+((EmailSender)joinPoint.getTarget()).outgoingMailServer);

    }
    //question d
    @Around("execution(* CustomerDAO.save(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totaltime = sw.getLastTaskTimeMillis();
        // print the time to the console
        System.out.println("Time to execute save ="+totaltime+" "+"ms");
        return retVal;
    }
}
