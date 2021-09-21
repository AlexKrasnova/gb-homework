package ru.alexkrasnova.spring.lesson2.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import ru.alexkrasnova.spring.lesson2.dto.CustomerDTO;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.repository.CustomerRepository;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/v1/customers";

    @Autowired
    private CustomerRepository customerRepository;


    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    public void findByIdSuccess() {
        Customer savesCustomer = new Customer();
        savesCustomer.setName("Васёк");
        customerRepository.save(savesCustomer);
        ResponseEntity<CustomerDTO> actual = restTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/" + savesCustomer.getId(), CustomerDTO.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isNotNull();
        Assertions.assertThat(actual.getBody().getName()).isEqualTo("Васёк");
    }

    @Test
    public void findByIdFail() {
        ResponseEntity<CustomerDTO> actual = restTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/1", CustomerDTO.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void saveSuccess() throws JSONException, JsonProcessingException, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final String NAME = "Клава";
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("name", NAME);

        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity("http://localhost:" + port + BASE_URL, request, String.class);

        Assertions.assertThat(responseEntityStr.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String path = responseEntityStr.getHeaders().getLocation().getPath();
        String[] strings = path.split("/");
        Long id = Long.parseLong(strings[strings.length - 1]);
        Customer customer = customerRepository.findById(id).orElseThrow();

        Assertions.assertThat(customer.getName()).isEqualTo(NAME);
    }
}
