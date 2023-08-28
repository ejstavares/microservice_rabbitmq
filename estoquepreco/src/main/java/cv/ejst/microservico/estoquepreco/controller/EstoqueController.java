package cv.ejst.microservico.estoquepreco.controller;

import cv.ejst.microservico.librabbitmq.dto.EstoqueDto;
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
@RequestMapping(value = "estoque")
public class EstoqueController {
    @Autowired
    private RabbitMQService rabbitMQService;
    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDto estoqueDto){
        System.out.println(estoqueDto.toString());

        for (int i = 0; i < 5; i++) {
            EstoqueDto _e = new EstoqueDto();
            _e.codigoProduto = i+" - "+estoqueDto.codigoProduto;
            _e.quantidade = estoqueDto.quantidade;
            System.out.println(_e.toString());
            
        this.rabbitMQService.enviaMsg(Constants.QUEUE_ESTOQUE, _e);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
