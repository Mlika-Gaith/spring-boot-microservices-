<h2 align="center">Spring boot microservices architecture👋</h2>
<p>
This project adopts a multi-modules approach to demonstrate a Spring 
Boot microservices architecture. It provides a comprehensive 
introduction to microservices concepts using Spring Cloud.
</p>


## Prerequisites

Ensure you have the following software installed on your system before proceeding:

* Java Development Kit (JDK) 17 or later
* Maven
* Docker (for containerization, for using kafka, keycloak oauth2)

## Install

* Clone the repository
* Navigate to the project directory.
* Build and package each component with Maven.


## Project Contents

<p> The project mainly covers: </p>

<h4> Discovery service (eureka)</h4>

<p>Eureka is a service discovery tool that enables microservices 
within a system to locate and communicate 
with each other dynamically, providing a streamlined and automated 
way to manage and locate services as they come and go 
in a distributed environment.</p>

<h4> API gateway </h4>

<p>This component is a central entry point for a microservices 
architecture, efficiently directing and managing incoming 
requests from clients to various underlying services.</p>

<h4> Authorization server with Keycloak</h4>

<p>An open-source Identity and Access Management (IAM) 
solution that provides functionalities like authentication, 
single sign-on (SSO), and authorization for web and mobile applications</p>

<h4> Sync inter-service communication between microservices (order service and inventory service) with WebClient</h4>

<p>The inter-service communication uses WebClient a part of the Spring WebFlux 
framework and is used to make HTTP requests to external services or APIs.</p>

<h4> Async inter-service communication between microservices (order service 
and notification service) with Kafka broker</h4>

<p>The inter-service communication uses  Kafka is a distributed event streaming platform that allows 
you to publish and consume events or messages in a scalable, fault-tolerant, and decoupled manner.</p>

<h4> Circuit Breaker using Resilience4j</h4>

<p>Resilience4j is a lightweight fault tolerance library for Java applications, 
particularly useful in microservices architectures, that provides several 
features for improving the resilience and stability of your application. 
One of its key features is the "Circuit Breaker" pattern, 
which helps prevent cascading failures by gracefully handling failing services.</p>

<h4> Distributed tracing with Zipkin and Sleuth</h4>

<p>Spring Cloud Sleuth provides Spring Boot autoconfiguration for distributed tracing.
Zipkin is a distributed tracing system that helps track and analyze the 
flow of requests across various microservices.</p>