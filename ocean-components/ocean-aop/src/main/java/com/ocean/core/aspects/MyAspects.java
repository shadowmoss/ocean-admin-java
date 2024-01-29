package com.ocean.core.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Spring支持两种AOP框架，一种是Spring AOP，也就是Spring 自己实现的AOP，(不支持字段拦截，只支持方法执行拦截)
 * AspectJ 支持方法拦截，也支持字段拦截,也就是更细粒度的AOP实现
 * @AspectJ-风格的AOP,切入点(point cut)签名由一个常规方法定义提供，切入点表达式有@Pointcut主键指出。
 * 作为切入点签名的方法必须返回值为void
 *
 * Spring AOP支持如下的AspectJ pointcut designators(指示器)
 * execution():用于匹配方法执行的join points,主要的切入点指示器。
 * within():限制匹配的joint point在的具体类型内，
 * this():限制匹配的join point的具体类型。代理实现的对象
 * target():限制匹配的join point 的目标对象是给定类型的实例
 * args():限制匹配的join points的参数类型在给定的类型中
 * @target:限制匹配的join points的执行对象的类上有指定的注解。
 * @args:限制匹配的join points,匹配传入的参数的运行时的实际类型上有指定的注解的方法
 * @within:限制匹配的join points的目标对象拥有特定类型的注解，匹配的是这个类型中的所有方法
 * @annotation:限制匹配的join points
 *
 * execution pointcut的指示器格式如下
 * execution(<modifiers-pattern?> ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
 * ret-type-pattern:表示返回值类型，一定得存在,
 * name-pattern:方法名称,可选
 * param-pattern:参数名称,可选
 * @author ltx
 */
@Aspect
public class MyAspects {

    /**
     * 如下是一个简单的切入点表达式
     * 和一个切入点签名方法
     */
    @Pointcut("execution(* transfer(..))")
    public void anyOldTransfer(){

    }
}
