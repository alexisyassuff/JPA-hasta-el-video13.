package org.example;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Factura")
public class Factura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fecha;
    private int numero;
    private int total;
    @ManyToOne(cascade = CascadeType.PERSIST) //A que cliente pertenece esta factura?
    @JoinColumn(name="fk_cliente")
    private Cliente cliente;
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL) //Que detalle tiene esta factura?
    @Builder.Default
    private Set<DetalleFactura> detalleFactura = new HashSet<>();
}
