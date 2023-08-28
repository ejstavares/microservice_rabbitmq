package cv.ejst.microservico.librabbitmq.dto;

import java.io.Serializable;

public class EstoqueDto implements Serializable {
    public String codigoProduto;
    public String quantidade;

    @Override
    public String toString() {
        return "EstoqueDto{" +
                "codigoProduto='" + codigoProduto + '\'' +
                ", quantidade='" + quantidade + '\'' +
                '}';
    }
}
