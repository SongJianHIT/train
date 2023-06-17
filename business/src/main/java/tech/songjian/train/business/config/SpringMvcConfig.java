package tech.songjian.train.business.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.songjian.train.common.interceptor.LogInterceptor;
import tech.songjian.train.common.interceptor.MemberInterceptor;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

   @Resource
   LogInterceptor logInterceptor;

   @Resource
   MemberInterceptor memberInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor)
               .addPathPatterns("/**");

      registry.addInterceptor(memberInterceptor)
              .addPathPatterns("/**")
              .excludePathPatterns(
                      "/business/hello"
              );
   }
}
