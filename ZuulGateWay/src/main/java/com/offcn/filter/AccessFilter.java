package com.offcn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //指定过滤器的调用时机
        // 1、pre 请求被路由转发前调用
        //2、routing 路由请求被调用
        //3、error 出现错误
        //4、post 在路由请求 error请求被执行时调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤器被调用顺序 数字越小 优先级越高
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //是否启用该过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //鉴权
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //从当前请求对象，获取调用方传递过来的token
        String token = request.getParameter("token");
        //判断token是否为空
        if (token == null) {
            //静止路由转发
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
        }
        return null;
    }
}
