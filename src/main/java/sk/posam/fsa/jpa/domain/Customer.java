package sk.posam.fsa.jpa.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer", schema = "public")
@Access(AccessType.FIELD)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "activebool")
    private boolean activebool;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update")
    private Date lastUpdate;

    @Column(name = "active")
    private int active;


    @Override
    public String toString() {
        return "Customer(" + id +
                ", " + firstName + " " + lastName +
                ", email = " + email +
                ", create_date = " + createDate +
                ", activebool = " + activebool +
                ", active = " + active +
                ")\n\t" +
                address;
    }
}
