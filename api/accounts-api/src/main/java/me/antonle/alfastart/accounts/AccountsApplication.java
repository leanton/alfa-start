package me.antonle.alfastart.accounts;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "me.antonle.alfastart.accounts.repository")
@EntityScan(basePackages = "me.antonle.alfastart.common.entity")
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}

	@Bean
	public static AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
		//in here you can provide custom HTTP status code providers etc. eg:
		//exp.setHttpStatusCodeProvider();
		//exp.setErrorResolver();
		return new AutoJsonRpcServiceImplExporter();
	}

}
