package cv.ejst.microservico.estoquepreco.controller;

import cv.ejst.microservico.librabbitmq.dto.PrecoDto;
import cv.ejst.microservico.librabbitmq.helpers.Constants;
import cv.ejst.microservico.estoquepreco.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {
    @Autowired
    private RabbitMQService rabbitMQService;
    @PutMapping
    private ResponseEntity alteraPreco(@RequestBody PrecoDto precoDto){
        System.out.println(precoDto.toString());

        this.rabbitMQService.enviaMsg(Constants.QUEUE_PRECO, precoDto);

        return new ResponseEntity(HttpStatus.OK);
    }
}
