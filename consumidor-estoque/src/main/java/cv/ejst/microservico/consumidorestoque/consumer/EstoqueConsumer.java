package cv.ejst.microservico.consumidorestoque.consumer;

import cv.ejst.microservico.librabbitmq.dto.EstoqueDto;
import cv.ejst.microservico.librabbitmq.helpers.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EstoqueConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = Constants.QUEUE_ESTOQUE)
    private void consumidor(String estoqueDto){

        try{
            EstoqueDto estoqueDto1 = this.objectMapper.readValue(estoqueDto, EstoqueDto.class);

            System.out.println("Consumidor::: "+estoqueDto1.toString());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
