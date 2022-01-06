package com.whenwhenwhere.cor.servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whenwhenwhere
 * @create 2022-01-06 19:10
 */
public class ServletMain {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "大家好:),<script>,欢迎访问http://whenwhenwhere.com 996";
        Response response = new Response();
        response.str = "response";

        //
        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).add(new SensitiveFilter());
        fc.doFilter(request,response);
        System.out.println(request.str);
        System.out.println(response.str);
    }
}

class Request{
    String str;

    @Override
    public String toString() {
        return "Request{" +
                "str='" + str + '\'' +
                '}';
    }
}

class Response{
    String str;

    @Override
    public String toString() {
        return "Response{" +
                "str='" + str + '\'' +
                '}';
    }
}

interface Filter{
    boolean doFilter(Request request, Response response,FilterChain chain);
}

class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replaceAll("<","[").replaceAll(">","]") + "HTMLFilter()";
        chain.doFilter(request,response);
        response.str += "--HTMLFilter()";
        return true;
    }
}

class SensitiveFilter implements Filter{

    @Override
    public boolean doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replace("996","995") + "SensitiveFilter()";
        chain.doFilter(request,response);
        response.str += "--SensitiveFilter()";
        return true;
    }
}

class FilterChain {

    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }
    public boolean doFilter(Request request,Response response){
        if (index == filters.size()) {
            return false;
        }
        Filter f = filters.get(index);
        index++;

        return f.doFilter(request,response,this);
    }
}