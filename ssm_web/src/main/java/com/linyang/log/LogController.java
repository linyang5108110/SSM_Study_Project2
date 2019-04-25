package com.linyang.log;

import com.linyang.domian.SysLog;
import com.linyang.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * **
 *  *
 *  * 配置aop，记录日志
 *  *
 *  *  配置切面类
 *  *       切面 = 切入点() + 增强(记录日志)
 *  *
 *  *   切入点：对连接点的定义
 *  *   连接点：在spring中所有的方法都是连接点(控制器中所有的方法)
 *  *  增强类型
 *  *      前置增强
 *  *      后置增强
 *  *      异常增强
 *  *      最终增强
 *  *
 *  *      环绕增强(前四种增强)：
 *  *   @Component: 创建对象
 *  *   @Aspect; 标记该类为切面类
 *  * @author 黑马程序员
 *  * @Company http://www.ithiema.com
 *  * @Version 1.0
 *  *
 */
@Component
@Aspect//加上这个注解表示这个类使用了面向切面编程
public class LogController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    LogService logService;

    /**
     * 配置切入点
     * @Pointcut加上这个注解表示这个方法就是一个切入点
     * execution：里面写增强的方法
     * * com.linyang.controller.*.*(..)  第一个*表示任何返回值   .*.*表示任意类，下任意方法  （..)表示任意参数
     *
     */

      @Pointcut("execution(* com.linyang.controller.*.*(..))")
      public void adc(){}

    /**
     * 定义一个前置增强
     */
    @Before("adc()")
    public void  before(JoinPoint joinPoint)
    {   //创建一个日志对象
        SysLog sysLog = new SysLog();
        System.out.println("ok");
        //获得访问这用户名
        //获得安装框架上下文
        SecurityContext securityContext = SecurityContextHolder.getContext();
        //获得认证对象,实际就是user
        Authentication authentication = securityContext.getAuthentication();
        //获得user
        User user = (User) authentication.getPrincipal();
        //放入log
        sysLog.setUsername(user.getUsername());
        //获得访问时间放入log
        Date date = new Date();
        sysLog.setVisitTime(date);
        //获得ip地址
        String remoteAddr = request.getRemoteAddr();
        sysLog.setIp(remoteAddr);
        //使用连接点对象获得访问的类名字和方法名字
        Object object = joinPoint.getTarget();
        //获取类名
        String className = object.getClass().getName();
        //获取方法名字
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        sysLog.setMethod(className +"."+ methodName);//com.itheima.controller.UserController.findAll

        logService.save(sysLog);
    }

}
