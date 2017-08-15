package org.tinroof;

import com.fasterxml.classmate.TypeResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.tinroof.domain.Calendar;
import org.tinroof.domain.CalendarEvent;
import org.tinroof.domain.CalendarUser;
import org.tinroof.repository.CalendarEventRepository;
import org.tinroof.repository.CalendarRepository;
import org.tinroof.repository.CalendarUserRepository;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.google.common.collect.Lists.*;
import static springfox.documentation.schema.AlternateTypeRules.*;

@SpringBootApplication
@EnableSwagger2
public class CalendarApplication {

	private static final Logger log = LoggerFactory.getLogger(CalendarApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CalendarApplication.class, args);
	}

	@Bean
	public Docket calendarApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/")
				.directModelSubstitute(LocalDate.class,
						String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
								typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder()
								.code(500)
								.message("500 message")
								.responseModel(new ModelRef("Error"))
								.build()))
				.securitySchemes(newArrayList(apiKey()))
				.securityContexts(newArrayList(securityContext()))
				.enableUrlTemplating(true)
				.globalOperationParameters(
						newArrayList(new ParameterBuilder()
								.name("someGlobalParameter")
								.description("Description of someGlobalParameter")
								.modelRef(new ModelRef("string"))
								.parameterType("query")
								.required(true)
								.build()))
				.tags(new Tag("Calendar Service", "All apis relating to Calendars"))
				//.additionalModels(typeResolver.resolve(AdditionalModel.class))
				;
	}

	@Autowired
	private TypeResolver typeResolver;

	private ApiKey apiKey() {
		return new ApiKey("mykey", "api_key", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*"))
				.build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
				= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(
				new SecurityReference("mykey", authorizationScopes));
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(
				"demo",
				"demo",
				"demo-realm",
				"demo-app",
				"apiKey",
				ApiKeyVehicle.HEADER,
				"api_key",
				"," /*scope separator*/);
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration(
				"validatorUrl",// url
				"none",       // docExpansion          => none | list
				"alpha",      // apiSorter             => alpha
				"schema",     // defaultModelRendering => schema
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
				false,        // enableJsonEditor      => true | false
				true,         // showRequestHeaders    => true | false
				60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
	}


	@Bean
	public CommandLineRunner demo(CalendarRepository calendarRepo, CalendarUserRepository userRepo, CalendarEventRepository eventRepo) {
		return (args) -> {

			// Save a few users
			CalendarUser user1 = userRepo.save(new CalendarUser("esapp", "Evan Sapp"));
			CalendarUser user2 = userRepo.save(new CalendarUser("demo1", "Demo User One"));

			// Save a few calendars
			Calendar calendar1 = new Calendar(user1, "Vacation", "Vacation Calendar");
			Calendar calendar2 = new Calendar(user2, "Meetings", "Meetings Calendar");

			calendar1 = calendarRepo.save(calendar1);
			calendar2 = calendarRepo.save(calendar2);

			// Save a few events
			CalendarEvent event = new CalendarEvent();

			LocalDateTime today = LocalDateTime.now();
			LocalDateTime tomorrow = today.plus(1, ChronoUnit.DAYS);
			LocalDateTime nextWeek = today.plus(1, ChronoUnit.WEEKS);

			event.setEventDate(nextWeek.toLocalDate());
			event.setEventTime(LocalTime.now());
			event.setLocation("Jacksonville Beach");
			event.setTitle("Summer Break");
			event.setReminderDateTime(tomorrow);

			event = eventRepo.save(event);

			calendar1.addEvent(event);

			calendarRepo.save(calendar1);

			// fetch all customers
			log.info("Calendars found with findAll():");
			log.info("-------------------------------");
			for (Calendar calendar : calendarRepo.findAll()) {
				log.info(calendar.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Calendar calendar = calendarRepo.findOne(1);
			log.info("Calendar found with findOne(1):");
			log.info("--------------------------------");
			log.info(calendar.toString());
			log.info("");

			// fetch customers by last name
			log.info("Calendar found with findByUserName('esapp'):");
			log.info("--------------------------------------------");
			for (Calendar esapp : calendarRepo.findByUserUserName("esapp")) {
				log.info(esapp.toString());
			}
			log.info("");
		};
	}

}
