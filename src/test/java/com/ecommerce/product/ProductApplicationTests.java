package com.ecommerce.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
                {
                    "name": "iphone 15 pro max",
                    "description": "the best phone ever",
                    "price": 240
                }
                """;

		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.post("/api/product")
				.then()
				.statusCode(201);
//				.body("name", equalTo("iphone 15 pro max"))
//				.body("id", notNullValue())
//				.body("description", equalTo("the best phone ever"));
	}
}
