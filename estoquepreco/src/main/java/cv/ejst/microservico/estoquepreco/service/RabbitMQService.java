package cv.ejst.microservico.estoquepreco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class RabbitMQService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void enviaMsg(String nomeFila, Object msg){
        try {
            String msgJson = this.objectMapper.writeValueAsString(msg);
            this.rabbitTemplate.convertAndSend(nomeFila, msgJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
