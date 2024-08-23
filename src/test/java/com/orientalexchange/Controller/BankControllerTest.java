package com.orientalexchange.Controller;

import com.orientalexchange.model.BankRequest;
import com.orientalexchange.model.BankTO;
import com.orientalexchange.model.BankUpdateRequest;
import com.orientalexchange.service.BankServiceV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public BankServiceV2 bankService;

    @Test
    public void findAll_whenBankDetailsExist_thenReturnBankDetails() throws Exception {
        List<BankTO> bankTOS = new ArrayList<>();
        BankTO bank = new BankTO();
        bank.setId("345");
        bank.setBankCode("22");
        bank.setBankName("Test");
        bank.setBankAddress("Bangalore");
        bankTOS.add(bank);

        when(bankService.findAll()).thenReturn(bankTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/nosql/api/v2/banks")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void findById_whenBankDetailsExist_thenReturnBankDetails() throws Exception {
        BankTO bank = new BankTO();
        bank.setId("2");
        bank.setBankCode("456");
        bank.setBankName("Test");
        bank.setBankAddress("Bangalore");

        when(bankService.findById(anyString())).thenReturn(bank);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/nosql/api/v2/banks/2")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    public void findByBankName_whenBankDetailsExist_thenReturnBankDetails() throws Exception {
        BankTO bank3 = new BankTO();
        bank3.setId("2");
        bank3.setBankCode("456");
        bank3.setBankName("Test");
        bank3.setBankAddress("Bangalore");
        when(bankService.findByBankName(anyString())).thenReturn(bank3);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/nosql/api/v2/banks/name?name=Test")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());

    }
    @Test
    public void saveBank_whenBankDetailsExist_thenReturnBankDetails() throws Exception {
        BankTO bank4 = new BankTO();
        bank4.setId("2");
        bank4.setBankCode("456");
        bank4.setBankName("Test");
        bank4.setBankAddress("Bangalore");
        when(bankService.save(any())).thenReturn(bank4);

        BankRequest bankRequest = new BankRequest();
        bankRequest.setId("678");
        bankRequest.setBankCode("564");
        bankRequest.setBankName("SBI");
        bankRequest.setBankAddress("Bangalore");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(bankRequest);

        RequestBuilder requestBuilders = MockMvcRequestBuilders.post("/nosql/api/v2/banks")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilders).andExpect(status().isOk());
    }
    @Test
    public void updateBank_whenBankDetailsExist_thenReturnBankDetails() throws Exception {
        BankTO bank4 = new BankTO();
        bank4.setId("2");
        bank4.setBankCode("456");
        bank4.setBankName("Test");
        bank4.setBankAddress("Bangalore");
        when(bankService.update(any())).thenReturn(bank4);

        BankUpdateRequest updateRequest = new BankUpdateRequest();
        updateRequest.setId("678");
        updateRequest.setBankCode("564");
        updateRequest.setBankName("SBIN");
        updateRequest.setBankAddress("Bangalore");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(updateRequest);

        RequestBuilder requestBuilders = MockMvcRequestBuilders.post("/nosql/api/v2/banks")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilders).andExpect(status().isOk());
    }

    @Test
    public void delete_whenBankId_thenDeleteTheBank() throws Exception {
        when(bankService.delete(anyString())).thenReturn("Bank details has been deleted");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/nosql/api/v2/banks?id=2")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
}



