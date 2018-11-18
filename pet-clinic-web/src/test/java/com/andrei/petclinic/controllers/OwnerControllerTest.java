package com.andrei.petclinic.controllers;

import com.andrei.petclinic.model.Owner;
import com.andrei.petclinic.service.jpa.OwnerJpaService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerJpaService service;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;
    List<Owner> ownersList;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().firstName("One").build());
        owners.add(Owner.builder().firstName("Two").build());
        owners.add(Owner.builder().firstName("Three").build());

        ownersList = new ArrayList<>();
        ownersList.add(Owner.builder().firstName("One").build());
        ownersList.add(Owner.builder().firstName("Two").build());
        ownersList.add(Owner.builder().firstName("Three").build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/findOwners"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        Mockito.when(service.findAllBySurnameLike(Mockito.anyString())).thenReturn(ownersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownersList"))
                .andExpect(MockMvcResultMatchers.model().attribute("selections", Matchers.hasSize(3)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        Owner owner = Owner.builder().firstName("One").build();
        owner.setId(1L);

        Mockito.when(service.findAllBySurnameLike(Mockito.anyString())).thenReturn(Arrays.asList(owner));

        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"));
    }

    @Test
    void findOwnerById() throws Exception {
        Owner owner = Owner.builder().firstName("One").build();
        owner.setId(1L);

        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/ownerDetails"))
                .andExpect(MockMvcResultMatchers.model().attribute("owner", Matchers.hasProperty("id", Matchers.is(1L))));
    }

    @Test
    void initSaveOwnerForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyZeroInteractions(service);
    }

    @Test
    void processSaveOwnerForm() throws Exception {
        Owner owner = Owner.builder().firstName("One").build();
        owner.setId(1L);

        Mockito.when(service.save(Mockito.any())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        Owner owner = Owner.builder().firstName("One").build();
        owner.setId(1L);

        Mockito.when(service.findById(Mockito.anyLong())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verifyZeroInteractions(service);

    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        Owner owner = Owner.builder().firstName("One").build();
        owner.setId(1L);

        Mockito.when(service.save(Mockito.any())).thenReturn(owner);

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/owners/1"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));

        Mockito.verify(service).save(ArgumentMatchers.any());
    }
}