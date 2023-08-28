package cv.ejst.microservico.estoquepreco.connections;

import cv.ejst.microservico.librabbitmq.helpers.Constants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;



@Component
public class RabbitMQConnection {
    private AmqpAdmin amqpAdmin;
    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }
    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(Constants.EXCHANGE_NAME);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adicionaFilas(){
        Queue filaEstoque =  this.fila(Constants.QUEUE_ESTOQUE);
        Queue filaPreco =  this.fila(Constants.QUEUE_PRECO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troca);

        // criando as filas (queue)
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }
}
