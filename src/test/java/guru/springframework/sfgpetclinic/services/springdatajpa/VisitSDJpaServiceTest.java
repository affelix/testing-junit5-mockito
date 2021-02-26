package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepositoryMock;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        Visit visit = new Visit();

        Set<Visit> visits = new HashSet<>();
        visits.add(visit);

        when(visitRepositoryMock.findAll()).thenReturn(visits);

        Set<Visit> foundVisits = service.findAll();

        verify(visitRepositoryMock).findAll();

        assertThat(foundVisits).hasSize(1);
    }

    @DisplayName("Test Find By ID")
    @Test
    void findById() {
        Visit visit = new Visit();

        when(visitRepositoryMock.findById(anyLong())).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(2L);

        verify(visitRepositoryMock).findById(anyLong());

        assertThat(foundVisit).isNotNull();
    }

    @DisplayName("Test Save")
    @Test
    void save() {
        Visit visit = new Visit();

        when(visitRepositoryMock.save(any(Visit.class))).thenReturn(visit);

        Visit savedVisit = service.save(new Visit());

        verify(visitRepositoryMock).save(any(Visit.class));

        assertThat(savedVisit).isNotNull();
    }

    /**
     * Here it is not needed the when() method because delete method returns void.
     */
    @DisplayName("Test Delete")
    @Test
    void delete() {
        Visit visit = new Visit();

        service.delete(visit);

        verify(visitRepositoryMock).delete(any(Visit.class));
    }

    @Test
        @DisplayName("Test Delete By ID")
    void deleteById() {
        service.deleteById(1L);

        verify(visitRepositoryMock).deleteById(anyLong());
    }
}