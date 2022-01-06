package com.whenwhenwhere.cor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whenwhenwhere
 * @create 2022-01-06 17:57
 */
public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:),<script>,欢迎访问whenwhenwhere.com 大家都是996 ");

        // 处理Msg

//        new HTMLFilter().doFilter(msg);
//        new SensitiveFilter().doFilter(msg);

//        List<Filter> filters = new ArrayList<>();
//        filters.add(new HTMLFilter());
//        filters.add(new SensitiveFilter());

//        for (Filter f : filters) {
//            f.doFilter(msg);
//        }

        FilterChain fc = new FilterChain();
        fc.add(new HTMLFilter()).
                add(new SensitiveFilter());
//        fc.add(new SensitiveFilter());
//        fc.doFilter(msg);

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).
                add(new URLFilter());

        fc.add(fc2);
        fc.doFilter(msg);
        
        System.out.println(msg);
    }
}

class Msg{
    String name;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter{
    Boolean doFilter(Msg m);
}

class HTMLFilter implements Filter{

    @Override
    public Boolean doFilter(Msg m) {
        String s = m.getMsg();
        s = s.replace("<","[");
        s = s.replace(">","]");
        m.setMsg(s);
        return true;
    }
}

class SensitiveFilter implements Filter{

    @Override
    public Boolean doFilter(Msg m) {
        String s = m.getMsg();
        s = s.replaceAll("996","995");
        m.setMsg(s);
        return true;
    }
}

class FaceFilter implements Filter{

    @Override
    public Boolean doFilter(Msg m) {
        String s = m.getMsg();
        s = s.replace(":)","^-^");
        m.setMsg(s);
        return false;
    }
}

class URLFilter implements Filter{

    @Override
    public Boolean doFilter(Msg m) {
        String s = m.getMsg();
        s = s.replace("whenwhenwhere.com","https://www.whenwhenwhere.com");
        m.setMsg(s);
        return true;
    }
}

class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

//    public void add(Filter f){
//        filters.add(f);
//    }
    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }
    @Override
    public Boolean doFilter(Msg m){
        for (Filter f : filters) {
            if (!f.doFilter(m)) {
                return false;
            }
        }
        return true;
    }
}