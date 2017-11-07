# Code Example with Spring Boot and Spring Data
Code exercise 

To build and run:

Prerequistite: 
Install and place Maven in your path.

To veridfy this, from a command line, run

`mvn -version`

You should see:

```
Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-03T15:39:06-04:00)
Maven home: C:\Users\nomad\Documents\apache-maven-3.5.0\bin\..
Java version: 1.8.0_144, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_144\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

If Maven is not installed, downlaoid and install it from here:
https://maven.apache.org/


To start the Calendar service, from project root:
`mvnw spring-boot:run`

You should see the server start and run some command-line tests:

```
2017-08-15 08:43:39.639  INFO 5160 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-08-15 08:43:40.023  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendars found with findAll():
2017-08-15 08:43:40.023  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : -------------------------------
2017-08-15 08:43:40.065  INFO 5160 --- [           main] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
2017-08-15 08:43:40.431  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendar{id=1, user='CalendarUser{userName='esapp', displayName='Evan Sapp'}', calendarName='Vacation', description='Vacation Calendar', events=[CalendarEvent{id=1, title='Summer Break', eventDate=2017-08-22, eventTime=08:43:39.927, location='Jacksonville Beach', attendees=[], reminder=2017-08-16T08:43:39.922, reminderSent=false}]}
2017-08-15 08:43:40.435  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendar{id=2, user='CalendarUser{userName='demo1', displayName='Demo User One'}', calendarName='Meetings', description='Meetings Calendar', events=[]}
2017-08-15 08:43:40.435  INFO 5160 --- [           main] org.tinroof.CalendarApplication          :
2017-08-15 08:43:40.443  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendar found with findOne(1):
2017-08-15 08:43:40.446  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : --------------------------------
2017-08-15 08:43:40.446  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendar{id=1, user='CalendarUser{userName='esapp', displayName='Evan Sapp'}', calendarName='Vacation', description='Vacation Calendar', events=[CalendarEvent{id=1, title='Summer Break', eventDate=2017-08-22, eventTime=08:43:39.927, location='Jacksonville Beach', attendees=[], reminder=2017-08-16T08:43:39.922, reminderSent=false}]}
2017-08-15 08:43:40.456  INFO 5160 --- [           main] org.tinroof.CalendarApplication          :
2017-08-15 08:43:40.456  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendar found with findByUserName('esapp'):
2017-08-15 08:43:40.457  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : --------------------------------------------
2017-08-15 08:43:40.545  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Calendar{id=1, user='CalendarUser{userName='esapp', displayName='Evan Sapp'}', calendarName='Vacation', description='Vacation Calendar', events=[CalendarEvent{id=1, title='Summer Break', eventDate=2017-08-22, eventTime=08:43:39.927, location='Jacksonville Beach', attendees=[], reminder=2017-08-16T08:43:39.922, reminderSent=false}]}
2017-08-15 08:43:40.547  INFO 5160 --- [           main] org.tinroof.CalendarApplication          :
2017-08-15 08:43:40.550  INFO 5160 --- [           main] org.tinroof.CalendarApplication          : Started CalendarApplication in 27.693 seconds (JVM running for 42.967)
2017-08-15 08:43:50.788  INFO 5160 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2017-08-15 08:43:50.788  INFO 5160 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2017-08-15 08:43:50.856  INFO 5160 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 67 ms
2017-08-15 18:33:30.912  INFO 5160 --- [       Thread-7] ationConfigEmbeddedWebApplicationContext : Closing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@5367436c: startup date [Tue Aug 15 08:43:15 EDT 2017]; parent: org.springframework.context.annotation.AnnotationConfigApplicationContext@3dedde19
2017-08-15 18:33:31.381  INFO 5160 --- [       Thread-7] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 2147483647
2017-08-15 18:33:31.443  INFO 5160 --- [       Thread-7] o.s.c.support.DefaultLifecycleProcessor  : Stopping beans in phase 0
2017-08-15 18:33:31.475  INFO 5160 --- [       Thread-7] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans on shutdown
2017-08-15 18:33:31.475  INFO 5160 --- [       Thread-7] o.s.j.e.a.AnnotationMBeanExporter        : Unregistering JMX-exposed beans
2017-08-15 18:33:31.490  INFO 5160 --- [       Thread-7] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2017-08-15 18:33:31.537  INFO 5160 --- [       Thread-7] org.hibernate.tool.hbm2ddl.SchemaExport  : HHH000227: Running hbm2ddl schema export
2017-08-15 18:33:31.647  INFO 5160 --- [       Thread-7] org.hibernate.tool.hbm2ddl.SchemaExport  : HHH000230: Schema export complete
```



