package com.gow.microservices.poc.netflixzuulapigatewayserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    //When to exec the filter - "pre" req, "post" req, "error" req
    @Override
    public String filterType() {
        return "error";
    }

    //Specifies order of exec in case of multiple filters
    @Override
    public int filterOrder() {
        return 0;
    }

    //Decide based on business logic
    @Override
    public boolean shouldFilter() {
        return false;
    }

    //Logic of filtering
    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        log.error("Request: {}, Request URI: {}", request, request.getRequestURI());
        return null;
    }
}
