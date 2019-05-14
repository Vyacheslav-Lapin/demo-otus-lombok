package com.example.demootuslombok;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demootuslombok.dao.CatRepository;
import com.example.demootuslombok.model.Cat;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = DemoOtusLombokApplication.class)
@AutoConfigureMockMvc
@WithMockUser(authorities = "ADMIN")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DemoOtusLombokApplicationTests {

  MockMvc mockMvc;

  CatRepository catRepository;

  private static final MediaType HAL_JSON =
      MediaType.parseMediaType("application/hal+json;charset=UTF-8");

  @BeforeEach
  void setUp() {
    Stream.of("Мурзик", "Барсик", "Матроскин")
        .map(Cat::new)
        .forEach(catRepository::save);
  }

  @Test
  @SneakyThrows
  void contextLoads() {
    mockMvc.perform(MockMvcRequestBuilders.get("/cats"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(HAL_JSON))
        .andExpect(mvcResult -> assertEquals("3",
            mvcResult.getResponse().getContentAsString()
                .split("totalElements")[1]
                .split(":")[1].trim()
                .split(",")[0]));

  }
}
