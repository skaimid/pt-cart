package tech.saltyfish.ptcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PtCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(PtCartApplication.class, args);
    }

//    /**
//     * 支持 vue router的 history 模式
//     */
//    @Bean
//    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
//
//        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableServletWebServerFactory factory) {
//
//                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
//                factory.addErrorPages(error404Page);
//            }
//        };
//    }

}
