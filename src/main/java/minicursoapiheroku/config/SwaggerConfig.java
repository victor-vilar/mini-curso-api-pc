package minicursoapiheroku.config;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerConfig {

	//inforamções do contato
	private Contact contato() {
		return new Contact("Nome","http://meusite.com.br","email@email.com");
	}
	
	//informações da api
	private ApiInfoBuilder informacoesAPI() {
		ApiInfoBuilder api = new ApiInfoBuilder();
	
		api.title("Parking API");
		api.description("Api exemplo swagger com Heroku");
		api.version("1.0");
		api.termsOfServiceUrl("Termo de uso: Open");
		api.license("Licença Livre");
		api.licenseUrl("http://www.site.com");
		api.contact(this.contato()).build();
		return api;
	
	}
	
	
	@Bean
	public Docket detalheAPI() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		
		docket.select().apis(RequestHandlerSelectors.basePackage("minicursoapiheroku"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesAPI().build())
		.consumes(new HashSet<String>(Arrays.asList("application.json")))
		.produces(new HashSet<String>(Arrays.asList("application.json")));
	
		return docket;
	}
	
}
