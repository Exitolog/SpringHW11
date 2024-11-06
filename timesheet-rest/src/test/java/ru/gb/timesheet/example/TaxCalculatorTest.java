package ru.gb.timesheet.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {


    @Mock
    TaxResolver mock;


    @Test
    void testGetPriceWithTax() {

        //TaxResolver mokkk = Mockito.mock(TaxResolver.class);
        //when(mock.getCurrentTax()).thenReturn(0.2);
//        TaxResolver mock = mock(TaxResolver.class);
//        when(mock.getCurrentTax()).thenReturn(0.2);

        doReturn(0.2).when(mock).getCurrentTax();

        TaxCalculator taxCalculator = new TaxCalculator(mock);
        Assertions.assertEquals(120.0 ,taxCalculator.getPriceWithTax(100.0), 0.00009);

        verify(mock).getCurrentTax();

    }

}