package ru.netolgy.hw_spring_boot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class HwSpringBootApplicationTests {

    private final RestTemplate restTemplate = new RestTemplate();

    @Container
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        System.out.println("Dev container started on port: " + devapp.getMappedPort(8080));
        System.out.println("Prod container started on port: " + prodapp.getMappedPort(8081));
    }

    @Test
    void testDevProfile() {
        int devPort = devapp.getMappedPort(8080);

        String response = restTemplate.getForObject(
                "http://localhost:" + devPort + "/profile",
                String.class
        );

        assertEquals("Current profile is dev", response);
        System.out.println("Dev response: " + response);
    }

    @Test
    void testProdProfile() {
        int prodPort = prodapp.getMappedPort(8081);

        String response = restTemplate.getForObject(
                "http://localhost:" + prodPort + "/profile",
                String.class
        );

        assertEquals("Current profile is production", response);
        System.out.println("Prod response: " + response);
    }
}
