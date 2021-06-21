package in.aorder.qr.config;

import in.aorder.qr.constant.PropertyKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Value(PropertyKey.App.CONTROLLER_PACKAGE)
    private String controllerPackage;

    @Value(PropertyKey.App.API_PATH_PREFIX)
    private String pathPrefix;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(pathPrefix, HandlerTypePredicate.forBasePackage(controllerPackage));
    }

}
