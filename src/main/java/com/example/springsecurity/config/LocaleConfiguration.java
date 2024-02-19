package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleConfiguration extends AcceptHeaderLocaleResolver {
    private static final List<Locale> LOCALES = Arrays.asList(new Locale("vi"), new Locale("en"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-language");
        if (language == null || language.equals("vi")) {
            return new Locale("vi");
        }
        return Locale.lookup(Locale.LanguageRange.parse(language), LOCALES);
    }
}
