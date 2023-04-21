package sk.posam.fsa.jpa.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "film", schema = "public")
@Access(AccessType.FIELD)
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @Column(name = "title") // netreba
    private String title;

    @Column(name = "description")   // netreba
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "language_id")
    private Long languageId;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private Double rentalRate;

    @Column(name = "length")
    private int length;

    @Column(name = "replacement_cost")
    private Double replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "last_update")
    private Date lastUpdated;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "fulltext")
    private String fulltext;

    // Relations
    @ManyToMany(targetEntity = Category.class)
    @JoinTable(
            name = "film_category",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> filmCategories;


    @Override
    public String toString() {
        return "Film(" +
                "id = " + id +
                ", title = " + title +
                ", rating = " + rating +
                ", length = " + length +
                ")\n" +
                printCategoryList();
    }

    public String printCategoryList() {
        return String.format(
                "\t- Category: %s",
                filmCategories.stream().map(Category::getName).collect(Collectors.joining(", "))
        );
    }
}
