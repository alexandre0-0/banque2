package com.cefisi.banquespring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.cefisi")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {

	/**
	 * Les vues sont situées dans <code>/WEB-INF/jsp/</code> et sont suffixées de <code>.jsp</code>.
	 *
	 * @return
	 */
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	/**
	 * Les éléments statiques (css, js, images etc.) sont accessibles depuis le navigateur via /static/ et sont situés dans /WEB-INF/static.
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").
				  addResourceLocations("/WEB-INF/static/");
	}

	/*
   * Configure MessageSource to provide internationalized messages
   *
	 */

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource
				  = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

//  /** Configurer la capture des exceptions */
//  @Bean(name="simpleMappingExceptionResolver")
//  public SimpleMappingExceptionResolver
//                  getSimpleMappingExceptionResolver() {
//    SimpleMappingExceptionResolver resolver =
//                new SimpleMappingExceptionResolver();
//    Properties mappings = new Properties();
//    // Associe l'exception SQLException à la JSP sqlException.jsp
////    mappings.setProperty("SQLException", "sqlException");
////    resolver.setExceptionMappings(mappings);  // None by default
//    resolver.setDefaultErrorView("erreur");    // No default
//    // Nom de l'attribut exception
//    resolver.setExceptionAttribute("exception");     // Default is "exception"
//    resolver.setWarnLogCategory("example.MvcLogger");     // No default
//    return resolver;
//  }
}
