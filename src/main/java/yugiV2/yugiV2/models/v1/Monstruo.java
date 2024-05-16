package yugiV2.yugiV2.models.v1;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name="Monstruo")
public class Monstruo implements Serializable {
    //Atributos
    @Id
    @Column(name="IDCARD")
    private String idCard;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="ATRIBUTO")
    private String atributo;

    @Column(name="NIVEL")
    private Integer nivel;

    @Column(name="ATAQUE")
    private Integer ataque;

    @Column(name="DEFENSA")
    private Integer defensa;

}
