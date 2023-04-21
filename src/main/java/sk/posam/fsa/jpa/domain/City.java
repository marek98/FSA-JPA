package sk.posam.fsa.jpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "city", schema = "public")
@Access(AccessType.FIELD)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city")
    private String name;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update")
    private Date lastUpdate;

    // Relations
    @OneToMany(
            targetEntity = Address.class,
            mappedBy = "city"
    )
    private List<Address> addressesInCity;

    @Override
    public String toString() {
        return "City(" +
                "name = " + name +
                ", country = " + country +
                ")";
    }
}
