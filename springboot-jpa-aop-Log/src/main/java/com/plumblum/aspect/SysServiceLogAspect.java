package com.plumblum.aspect;

import com.plumblum.Utils.IPUtils;
import com.plumblum.entity.SysOprlog;
import com.plumblum.service.SysOprlogService;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther: cpb
 * @Date: 2018/8/30 09:12
 * @Description:
 */
@Component
@Aspect
public class SysServiceLogAspect {

    @Resource
    private SysOprlogService sysOprlogService;

    private static String[] types = { "java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float" ,"java.util.HashMap"};
    private static final Logger logger = LoggerFactory.getLogger(SysServiceLogAspect.class);

    // Service层切点
    @Pointcut("@annotation(SysServiceLog)")
    public void serviceAspect() {
    }

    /**
     * 返回通知，拦截方法执行成功后的结果，保存日志
     * @param joinPoint 切点
     * @param result 方法执行结果
     */
    @AfterReturning(value="serviceAspect()",returning="result")
    public void doServiceAfterReturning(JoinPoint joinPoint, Object result) {
        SysOprlog sysOprlog = getSysOprlogInfo(joinPoint);
        sysOprlog.setOprlogDescription(sysOprlog.getOprlogRemark() + "成功!");
        sysOprlog.setOprlogStatus("1");
        saveSysOprlog(sysOprlog);
    }

    /**
     * 异常通知 用于拦截service层记录异常日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getTarget().getClass().getName();// 获取所在实体类
        String methodName = joinPoint.getSignature().getName();// 获取方法名
        logger.error("执行失败!"+className+"->"+methodName+"失败原因：" + e.getMessage());
        SysOprlog sysOprlog = getSysOprlogInfo(joinPoint);
        sysOprlog.setOprlogDescription(sysOprlog.getOprlogRemark() + "失败!");
        sysOprlog.setOprlogStatus("0");
        saveSysOprlog(sysOprlog);
    }

    /**
     * 获取SysOprlog的属性内容
     * @param joinPoint
     * @return
     */
    private SysOprlog getSysOprlogInfo(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        // 读取session中的用户
//        SysUser user = (SysUser) session.getAttribute("user");
        //获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
        String ip = IPUtils.getIp(request);

        String className = joinPoint.getTarget().getClass().getName();// 获取所在实体类
        String methodName = joinPoint.getSignature().getName();// 获取方法名

        SysOprlog sysOprlog = new SysOprlog();
        try {
            String paramContent = getLogParamContent(getFieldsName(this.getClass(), className, methodName), joinPoint);
            //获取注解描述
            SysServiceLog systemServiceLog = getServiceMethodDescription(joinPoint);
            sysOprlog.setOprlogId(UUID.randomUUID().toString().replace("-", ""));
            sysOprlog.setOprlogParam(paramContent);
            sysOprlog.setOprlogRemark(systemServiceLog.oprlogRemark());
            sysOprlog.setOprlogTime(new Date());
            sysOprlog.setOprlogType(systemServiceLog.oprlogType());
            sysOprlog.setOprlogModuleName(systemServiceLog.oprlogModule());
//            sysOprlog.setOprlogLoginIp(ip);
        } catch (Exception e) {
            // 记录本地异常日志
            logger.error("获取失败!失败原因：" + e.getMessage());
        }
        return sysOprlog;
    }

    /**
     * 获取注解中对方法的描述信息，用于service层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private SysServiceLog getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        SysServiceLog systemServiceLog = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    systemServiceLog = method.getAnnotation(SysServiceLog.class);
                    break;
                }
            }
        }
        return systemServiceLog;
    }

    /**
     * 获取调用方法参数的名称
     * @param cls
     * @param clazzName
     * @param methodName
     * @return
     * @throws NotFoundException
     */
    @SuppressWarnings("rawtypes")
    private String[] getFieldsName(Class cls, String clazzName, String methodName) throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++){
            paramNames[i] = attr.variableName(i + pos); //paramNames即参数名
        }
        return paramNames;
    }

    /**
     * 获取日志参数内容。格式：参数名+参数内容
     * @param paramNames
     * @param joinPoint
     * @return
     */
    private String getLogParamContent(String[] paramNames, JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        boolean clazzFlag = true;
        for(int k=0; k<args.length; k++){
            Object arg = args[k];
            sb.append(paramNames[k]);
            // 获取对象类型
            String typeName = arg.getClass().getName();
            for (String t : types) {
                if (t.equals(typeName)) {
                    sb.append("=" + arg+";");
                    clazzFlag = false;
                    break;
                }
            }
            if (clazzFlag) {
                sb.append(getFieldsValue(arg));
            }
        }
        return sb.toString();
    }

    /**
     * 得到参数的值
     * @param obj
     */
    private String getFieldsValue(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String typeName = obj.getClass().getName();
        for (String t : types) {
            if(t.equals(typeName))
                return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                for (String str : types) {
                    if (f.getType().getName().equals(str)){
                        if(f.get(obj) != null) {
                            sb.append(f.getName() + " = " + f.get(obj)+"; ");
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * hessian请求存储操作日志
     * @param sysOprlog
     */
    private void saveSysOprlog(SysOprlog sysOprlog){
        try {
            sysOprlogService.save(sysOprlog);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存操作日志失败!失败原因：" + e.getMessage());
        }
    }
}
