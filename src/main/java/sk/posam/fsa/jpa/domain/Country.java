package sk.posam.fsa.jpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "country", schema = "public")
@Access(AccessType.FIELD)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "country")
    private String name;

    @Column(name = "last_update")
    private Date lastUpdate;

    // Relations
    @OneToMany(
            targetEntity = City.class,
            mappedBy = "country"
    )
    private List<Address> citiesInCountry;


    @Override
    public String toString() {
        return "Country(" +
                "name = " + name +
                ")";
    }
}
