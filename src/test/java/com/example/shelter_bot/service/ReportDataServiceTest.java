package com.example.shelter_bot.service;

import com.example.shelter_bot.exceptions.ReportDataNotFoundException;
import com.example.shelter_bot.model.ReportData;
import com.example.shelter_bot.repository.ReportDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ReportDataServiceTest {

    @Mock
    private ReportDataRepository reportDataRepositoryMock;

    @InjectMocks
    private ReportDataService reportDataService;

    /**
     * Test for method <b>findById()</b> in ReportDataService
     * <br>
     * Mockito: when <b>ReportDataRepository::findById</b> method called, returns <b>expected</b> object
     */
    @Test
    public void findByIdTest() {
        ReportData expected = new ReportData();
        expected.setChatId(1L);
        expected.setRation("testRation");
        expected.setDays(1L);
        expected.setHabits("testHabits");
        expected.setCaption("testCaption");

        Mockito.when(reportDataRepositoryMock.findByChatId(any(Long.class))).thenReturn(Optional.of(expected));

        ReportData actual = reportDataService.findById(1L);

        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
        Assertions.assertThat(actual.getRation()).isEqualTo(expected.getRation());
        Assertions.assertThat(actual.getDays()).isEqualTo(expected.getDays());
        Assertions.assertThat(actual.getHabits()).isEqualTo(expected.getHabits());
        Assertions.assertThat(actual.getCaption()).isEqualTo(expected.getCaption());
    }

    /**
     * Test for throwing an exception in method <b>findById()</b> in ReportDataService
     * <br>
     * Mockito: when <b>ReportDataRepository::findById()</b> method called, throws <b>ReportDataNotFoundException</b>
     */
    @Test
    public void findByIdExceptionTest() {
        Mockito.when(reportDataRepositoryMock.findByChatId(any(Long.class))).thenThrow(ReportDataNotFoundException.class);

        org.junit.jupiter.api.Assertions.assertThrows(ReportDataNotFoundException.class, () -> reportDataService.findById(1L));
    }

    /**
     * Test for method <b>findByChatId()</b> in ReportDataService
     * <br>
     * Mockito: when <b>ReportDataRepository::findByChatId()</b> method called, returns <b>expected</b> object
     */
    @Test
    public void findByChatIdTest() {
        ReportData expected = new ReportData();
        expected.setChatId(1L);
        expected.setRation("testRation");
        expected.setDays(1L);
        expected.setHabits("testHabits");
        expected.setCaption("testCaption");

        Mockito.when(reportDataRepositoryMock.findByChatId(any(Long.class))).thenReturn(expected);

        ReportData actual = reportDataService.findByChatId(1L);

        Assertions.assertThat(actual.getChatId()).isEqualTo(expected.getChatId());
        Assertions.assertThat(actual.getRation()).isEqualTo(expected.getRation());
        Assertions.assertThat(actual.getDays()).isEqualTo(expected.getDays());
        Assertions.assertThat(actual.getHabits()).isEqualTo(expected.getHabits());
        Assertions.assertThat(actual.getCaption()).isEqualTo(expected.getCaption());
    }

    /**
     * Test for method <b>findListByChatId()</b> in ReportDataService
     * <br>
     * Mockito: when <b>ReportDataRepository::findListByChatId()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void findListByChatIdTest() {
        Set<ReportData> expected = new HashSet<>();

        ReportData testReport1 = new ReportData();
        testReport1.setChatId(1L);
        testReport1.setRation("testRation1");
        testReport1.setDays(1L);
        testReport1.setHabits("testHabits1");
        testReport1.setCaption("testCaption1");
        expected.add(testReport1);

        ReportData testReport2 = new ReportData();
        testReport1.setChatId(1L);
        testReport1.setRation("testRation2");
        testReport1.setDays(2L);
        testReport1.setHabits("testHabits2");
        testReport1.setCaption("testCaption2");
        expected.add(testReport2);

        ReportData testReport3 = new ReportData();
        testReport1.setChatId(1L);
        testReport1.setRation("testRation3");
        testReport1.setDays(3L);
        testReport1.setHabits("testHabits3");
        testReport1.setCaption("testCaption3");
        expected.add(testReport3);

        Mockito.when(reportDataRepositoryMock.findListByChatId(any(Long.class))).thenReturn(expected);

        Collection<ReportData> actual = reportDataService.findListByChatId(1L);

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /**
     * Test for method <b>getAll()</b> in ReportDataService
     * <br>
     * Mockito: when <b>ReportDataRepository::findAll()</b> method called, returns <b>expected</b> collection
     */
    @Test
    public void findAllTest() {
        Set<ReportData> expected = new HashSet<>();

        ReportData testReport1 = new ReportData();
        testReport1.setChatId(1L);
        testReport1.setRation("testRation1");
        testReport1.setDays(1L);
        testReport1.setHabits("testHabits1");
        testReport1.setCaption("testCaption1");
        expected.add(testReport1);

        ReportData testReport2 = new ReportData();
        testReport1.setChatId(1L);
        testReport1.setRation("testRation2");
        testReport1.setDays(2L);
        testReport1.setHabits("testHabits2");
        testReport1.setCaption("testCaption2");
        expected.add(testReport2);

        ReportData testReport3 = new ReportData();
        testReport1.setChatId(1L);
        testReport1.setRation("testRation3");
        testReport1.setDays(3L);
        testReport1.setHabits("testHabits3");
        testReport1.setCaption("testCaption3");
        expected.add(testReport3);

        Mockito.when(reportDataRepositoryMock.findByChatId(any(Long.class))).thenReturn(expected);

        Collection<ReportData> actual = reportDataService.getAll();

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}

