package com.apiresttask.apiTasks.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//essa anotação diz ao Spring que ela irá escutar erros, para devolver em respostas REST
@RestControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)//retorna o status 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(
            //esse tipo de erro, é para quando você usa o valid, quando não passa na validação, ele rertorna este tipo de excessão
            MethodArgumentNotValidException ex
    ){

        Map<String, String> errors = new HashMap<>();

        //aqui eu pego o campo e o nome do erro, percorro no forEach os erros e pego o nome do campo e os erros, para exibir.
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()
                )
        );
        return errors;
    }
}
