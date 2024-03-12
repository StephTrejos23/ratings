package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.repository.RatingsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingsServiceTest {

    @Captor
    private ArgumentCaptor<Rating> ratingArgumentCaptor;

    @Mock
    private RatingsRepository ratingsRepository;

    @InjectMocks
    private RatingsService ratingsService;

    @Test
    void test() {
        String movieId = "movieId";
        Rating rating = new Rating(movieId, 5);
        // para un mock ->
        when(ratingsRepository.getRating(movieId)).thenReturn(rating);

        Rating result = ratingsService.getRating(movieId);

        assertEquals(rating, result);

        verify(ratingsRepository, times(1)).getRating(movieId);
        verifyNoMoreInteractions(ratingsRepository);
    }

    // verificar cuando movieId = "movieId" que tire una excepcion
    // verificar que no haya llamados al repositorio
    @Test
    void test1() {
        String movieId = "movieId";

        assertThrows(IllegalArgumentException.class, () -> ratingsService.getRatingValue(movieId),
                "sea mongolo");

//        verify(ratingsRepository, times(0)).getRating(anyString());
//        verify(ratingsRepository, never()).getRating(anyString());
        verifyNoInteractions(ratingsRepository);
    }

    // verificar cuando movieId = "movie-1" que llame al repositorio con "movie-1123"
    // verificar que haya llamado a repositorio con "movie-1123"
    @Test
    void test2() {

//        verify(when(ratingsService.getRatingValue(String.valueOf(movieId=="movie-1"))).thenCallRealMethod());
//        verify(ratingsRepository.getRating(movieId));

        String movieId = "movie-1";
        Rating rating = new Rating(movieId, 5);
        // para un mock ->
//        when(ratingsRepository.getRating(anyString())).thenReturn(rating);
        when(ratingsRepository.getRating("movie-1123")).thenReturn(rating);

        int value = ratingsService.getRatingValue(movieId);

        assertEquals(5, value);

        verify(ratingsRepository, times(1)).getRating("movie-1123");
        verifyNoMoreInteractions(ratingsRepository);
    }

    // verificar cuando movieId != (diferente) a movieId o movie-1
    // verificar que haya llamado a repositorio con el valor que le ha pasado
    @Test
    void test3() {
        String movieId = "loquesea";
        Rating rating = new Rating(movieId, 5);
        // para un mock ->
        when(ratingsRepository.getRating(movieId)).thenReturn(rating);

        int value = ratingsService.getRatingValue(movieId);

        assertEquals(5, value);

        verify(ratingsRepository, times(1)).getRating(movieId);
        verifyNoMoreInteractions(ratingsRepository);



    }

    @Test
    void test4() {

        // 1. llamar al metodo
        // 2. ese metodo nos va a devolver un resultado, guardarlo en una variable
        // 3. usar un assertEquals para comparar la variable creada en el punto 2 con la suma de los valores pasados

        int value = ratingsService.sum(1,2);
        assertEquals(3, value);
    }

    // division
    // cuando ambos valores sean validos
    // comparar el resultado con la division de los valores que se le pasaron al metodo, similar test4
    @Test
    void test5() {
        int value = ratingsService.division(54,2);
        assertEquals(27, value);
    }

    // division
    // cuando el segundo valor es igual a 0
    // va a retornar una excepcion, validar igual que en test 1
    @Test
    void test6() {
        assertThrows(IllegalArgumentException.class, () -> ratingsService.division(1,0),
                "no se puede dividir por 0");
    }

    // saveNewRating
    // validar que el rating no es menor a 1
    // va a retornar una excepcion si rating es 0, validar excepcion igual que test 1
    // validar que no hay llamados a ratingsRepository.save
    @Test
    void test7() {
        String movieId = "movieId";
        Rating rating = new Rating(movieId, 0);

        assertThrows(IllegalArgumentException.class, () -> ratingsService.saveNewRating(rating),
                "rating tiene que ser de 1 a 5");

        verifyNoInteractions(ratingsRepository);
    }

    // saveNewRating
    // validar que el rating no es mayor a 5
    // va a retornar una excepcion si rating es 6 o mayor, validar excepcion igual que test 1
    // validar que no hay llamados a ratingsRepository.save
    @Test
    void test8() {
        String movieId = "movieId";
        Rating rating = new Rating(movieId, 6);

        assertThrows(IllegalArgumentException.class, () -> ratingsService.saveNewRating(rating),
                "rating tiene que ser de 1 a 5");

        verifyNoInteractions(ratingsRepository);
    }

    // saveNewRating
    // validar que hay 1 llamado a ratingsRepository.save
    // validar con argumentcaptor que valid = true -- no hacer esto hasta no completar el resto
    @Test
    void test9() {
        String movieId = "movieId";
        Rating rating = new Rating(movieId, 2);
        ratingsService.saveNewRating(rating);

        verify(ratingsRepository, times(1)).save(ratingArgumentCaptor.capture());

//        ArgumentCaptor<Rating> capturedRating;
//        verify(rating).isValid(capturedRating.capture());
        Rating ratingCaptorValue = ratingArgumentCaptor.getValue();
        assertTrue(ratingCaptorValue.isValid());

    }
}


