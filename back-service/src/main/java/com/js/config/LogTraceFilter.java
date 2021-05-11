package com.js.config;

import com.js.common.util.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 日志traceId添加
 * Created by xxx on 2020/11/26 12:11
 */
@Slf4j
@WebFilter(filterName = "logTraceFilter", urlPatterns = "/*")
public class LogTraceFilter implements Filter {

    private static final String TRACE_ID = "traceRootId";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        boolean bInsertMDC = insertMDC();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            if (bInsertMDC) {
                MDC.remove(TRACE_ID);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean insertMDC() {
        String traceRootId = String.valueOf(IdUtils.get32Uuid());
        MDC.put(TRACE_ID, traceRootId);
        return true;
    }
}

