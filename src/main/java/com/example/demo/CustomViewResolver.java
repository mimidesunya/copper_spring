package com.example.demo;

import java.net.URI;
import java.util.Locale;

import org.springframework.web.servlet.View;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import jakarta.servlet.http.HttpServletResponse;
import jp.cssj.cti2.CTIDriverManager;
import jp.cssj.cti2.CTISession;
import jp.cssj.cti2.helpers.jakarta.CTIHttpServletResponseWrapper;
import jp.cssj.cti2.helpers.jakarta.ServletHelper;

public class CustomViewResolver extends ThymeleafViewResolver {

    @Override
    protected View loadView(final String viewName, final Locale locale) throws Exception {
        final View view = super.loadView(viewName, locale);
        return (model, request, response) -> {
            final String uri = request.getRequestURI();

            // 拡張子がPDFでなければ通常のViewを使用
            if (!uri.endsWith(".pdf")) {
                view.render(model, request, response);
                return;
            }

            // 拡張子がPDFの場合は変換する
            final CTISession session = CTIDriverManager.getSession(URI.create("ctip://cti.li/"), "user", "kappa");
            ServletHelper.setServletResponse(session, response);
            try(final CTIHttpServletResponseWrapper responseWrapper = new CTIHttpServletResponseWrapper((HttpServletResponse) response, session, URI.create(uri))) {
                view.render(model, request, responseWrapper);
            }
        };
    }

}
