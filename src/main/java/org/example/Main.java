
package org.example;
import org.example.Cliente;
/*/ <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/> /*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");

        EntityManager em = emf.createEntityManager();
        System.out.println("en marcha Alberto");

        try {
            // Persistir una nueva entidad Person
            em.getTransaction().begin();

            Cliente cliente = Cliente.builder()
                    .dni(45143369)
                    .nombre("Alexis").
                    apellido("Yassuff").
                    build();

            em.persist(cliente);
            Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("Ramon")
                    .numero(12).
                    build();
            em.persist(domicilio);

            cliente.setDomicilio(domicilio);

            Factura fact1 = Factura.builder()
                    .fecha("02/08/2024")
                    .numero(123)
                    .total(2000).
                    build();

            em.persist(fact1);

            Categoria categoria = Categoria.builder()
                    .denominacion("Deportes")
                    .build();
            em.persist(categoria);

            Articulo articulo = Articulo.builder()
                    .cantidad(5)
                    .denominacion("Pelota")
                    .precio(200)
                    .build();
            articulo.getCategorias().add(categoria);  // Agregar la categoría al artículo
            em.persist(articulo);

            DetalleFactura detalleFactura = DetalleFactura.builder()
                    .subtotal(2300)
                    .cantidad(3)
                    .articulo(articulo)
                    .factura(fact1)
                    .build();
            em.persist(detalleFactura);
            fact1.setCliente(cliente);

            em.getTransaction().commit();

        }catch (Exception e){

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Salí por el catch");}

        // Cerrar el EntityManager y el EntityManagerFactory
        em.close();
        emf.close();
    }
}


/*
Manejo del Ciclo de Estados en JPA
El ciclo de estados en JPA (Java Persistence API) define los diferentes estados que puede tener una entidad en relación con el contexto de persistencia (EntityManager). Comprender y manejar correctamente estos estados es crucial para trabajar eficazmente con JPA. Los estados del ciclo de vida de una entidad en JPA son:

New (Nuevo):

Una entidad está en estado "New" cuando ha sido creada pero aún no ha sido persistida en la base de datos.
Managed (Gestionado):

Una entidad está en estado "Managed" cuando está asociada con un contexto de persistencia (EntityManager) y cualquier cambio en la entidad se reflejará automáticamente en la base de datos.
Detached (Desconectado):

Una entidad está en estado "Detached" cuando ya no está asociada con un contexto de persistencia. Los cambios en la entidad no se reflejarán automáticamente en la base de datos.
Removed (Eliminado):

Una entidad está en estado "Removed" cuando ha sido marcada para su eliminación en la base de datos.
*/


