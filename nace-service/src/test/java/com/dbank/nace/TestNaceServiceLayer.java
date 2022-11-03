package com.dbank.nace;

import com.dbank.nace.entity.NaceData;
import com.dbank.nace.exceptions.NaceNotFoundException;
import com.dbank.nace.repository.NaceRepository;
import com.dbank.nace.service.NaceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestNaceServiceLayer {

    @InjectMocks
    private NaceService service;

    @Mock
    private NaceRepository repository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOneNaceData() {
        // Arrange
        final var naceDataToSave = NaceData.builder()
                .orderId(10L)
                .level(1)
                .description("Fisheries")
                .item_includes("All types of fishes")
                .item_excludes("Molluscs and crustaceans")
                .build();
        when(repository.save(any(NaceData.class))).thenReturn(naceDataToSave);

        // Act
        final var actual = service.saveNaceData(new NaceData());

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(naceDataToSave);
        verify(repository, times(1)).save(any(NaceData.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testNaceDataNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NaceNotFoundException.class, () -> service.getNaceData(randomLong()));
        verify(repository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testDeleteOneNaceData() {
        // Arrange
        doNothing().when(repository).deleteById(anyLong());

        // Act & Assert
        service.deleteNaceData(randomLong());
        verify(repository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    private Long randomLong() {
        return new Random().longs(1, 10).findFirst().getAsLong();
    }
}