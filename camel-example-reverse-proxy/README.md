# Camel Proxy basic example

## Application

The application uses the Camel servlet to listen for request on path:

```
<HOST>:<PORT>/camel-reverse-proxy-web/reverse/proxy
```

The URL above cames from both the web.xml:

```
<!-- Camel servlet -->
    <servlet>
       <servlet-name>CamelServlet</servlet-name>
       <servlet-class>org.apache.camel.component.servlet.CamelHttpTransportServlet</servlet-class>
       <load-on-startup>1</load-on-startup>
     </servlet>

     <!-- Camel servlet mapping -->
     <servlet-mapping>
       <servlet-name>CamelServlet</servlet-name>
       <url-pattern>/reverse/*</url-pattern>
     </servlet-mapping>
```

and the route:


from("servlet:**proxy**?matchOnUriPrefix=true")



## Pre requisite

* Maven 3.3.x or better
* EAP 6.4.x and Fuse 6.3.0

## EAP + Fuse

Read the official documentation to install Fuse on EAP

## Configuration

config the hostname/site you want to proxy in file:

```
/camel-reverse-proxy/src/main/resources/proxyreverse.properties
```

The property is:

```proxedurl```

## Build

Use maven with provided settings.xml:

```
mvn clean package -s settings.xml
```

## Deploy

on the project root

```
cp target/camel-reverse-proxy-web.war  <PATH-TO>/jboss-eap-6.4.x-fuse-6.x/standalone/deployments/
```

## Start the example

Start EAP + Camel in standalone mode:

```
${JBOSS_HOME}/bin/standalone.sh -c <your-profile>.xml
```



