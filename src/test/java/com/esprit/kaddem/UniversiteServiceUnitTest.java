package com.esprit.kaddem;

import com.esprit.kaddem.entities.Universite;
import com.esprit.kaddem.exceptions.OrderNotFoundException;
import com.esprit.kaddem.repositories.UniversiteRepository;
import com.esprit.kaddem.services.IUniversiteService;
import com.esprit.kaddem.services.UniversiteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UniversiteServiceUnitTest {

    @Mock
    UniversiteRepository universiteRepository;

    @InjectMocks
    UniversiteServiceImpl iUniversiteService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testGetUniveristesList() {
        Universite universite1 = new Universite(9, "ben");
        Universite universite2 = new Universite(8, "kevin");
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));
        List<Universite> universiteList = iUniversiteService.retrieveAllUniversites();
        assertEquals(2, universiteList.size());
        assertEquals("ben", universiteList.get(0).getNomUniv());
        assertEquals("kevin", universiteList.get(1).getNomUniv());
    }


    @Test
    public void testGetUniveristeById() {
        Universite universite = new Universite(10, "george");
        when(universiteRepository.findById(10)).thenReturn(Optional.of(universite));
        Universite universiteById = iUniversiteService.retrieveUniversite(10);
        assertNotEquals(null, universiteById);
        assertEquals("george", universiteById.getNomUniv());
    }



    @Test
    public void testGetInvaliduniversiteById() {
        when(universiteRepository.findById(17)).thenThrow(new OrderNotFoundException("Universite Not Found with ID"));
        Exception exception = assertThrows(OrderNotFoundException.class, () -> {
            iUniversiteService.retrieveUniversite(17);
        });
        assertTrue(exception.getMessage().contains("Universite Not Found with ID"));
    }

    @Test
    public void testCreateUniversite() {
        Universite universite = new Universite(12, "john");
        iUniversiteService.addUniversite(universite);
        verify(universiteRepository, times(1)).save(universite);
        ArgumentCaptor<Universite> universiteArgumentCaptor = ArgumentCaptor.forClass(Universite.class);
        verify(universiteRepository).save(universiteArgumentCaptor.capture());
        Universite universiteCreated = universiteArgumentCaptor.getValue();
        assertNotNull(universiteCreated.getIdUniversite());
        assertEquals("john", universiteCreated.getNomUniv());
    }

//    @Test
//    public void testDeleteOrder() {
//        Order order = new Order(13L, "simen", 120.0, 10);
//        when(orderRepository.findById(13L)).thenReturn(Optional.of(order));
//        orderService.deleteOrderById(order.getId());
//        verify(orderRepository, times(1)).deleteById(order.getId());
//        ArgumentCaptor<Long> orderArgumentCaptor = ArgumentCaptor.forClass(Long.class);
//        verify(orderRepository).deleteById(orderArgumentCaptor.capture());
//        Long orderIdDeleted = orderArgumentCaptor.getValue();
//        assertNotNull(orderIdDeleted);
//        assertEquals(13L, orderIdDeleted);
//    }
}
