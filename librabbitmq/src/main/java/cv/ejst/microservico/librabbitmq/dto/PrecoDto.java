package cv.ejst.microservico.librabbitmq.dto;

import java.io.Serializable;

public class PrecoDto implements Serializable {
    public String codigoProduto;
    public double preco;

    @Override
    public String toString() {
        return "PrecoDto{" +
                "codigoProduto='" + codigoProduto + '\'' +
                ", preco=" + preco +
                '}';
    }
}
