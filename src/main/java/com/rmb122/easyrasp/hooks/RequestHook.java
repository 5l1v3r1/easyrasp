package com.rmb122.easyrasp.hooks;

import com.rmb122.easyrasp.annotation.HookHandler;
import com.rmb122.easyrasp.enums.HookType;

public class RequestHook {
    private static ThreadLocal<Object> currRequest = new ThreadLocal<>();

    @HookHandler(hookClass = "org.springframework.web.servlet.FrameworkServlet", hookMethod = "service")
    public static Object[] requestHook(Object self, Object[] params) {
        currRequest.set(params[0]);
        return params;
    }

    @HookHandler(hookClass = "org.springframework.web.servlet.FrameworkServlet", hookMethod = "service", hookType = HookType.AFTER_RUN_FINALLY)
    public static void releaseRequest(Object ignored) {
        currRequest.remove(); // 释放内存
    }

    public static Object getRequest() {
        return currRequest.get();
    }
}
