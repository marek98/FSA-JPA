package sk.posam.fsa.jpa;

import sk.posam.fsa.jpa.domain.Category;
import sk.posam.fsa.jpa.domain.Customer;
import sk.posam.fsa.jpa.domain.Film;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class JpaApplication {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dvdrental-pu-name");
        EntityManager em = factory.createEntityManager();

        // Films
        // Print out info about all films
        printFilmsInfo(em);

        // Get categories for film with film_id
        int filmId = 1000;
        System.out.println(String.format("Categories with film with id = %d", filmId));
        for(Category c : getCategoriesForFilmById(em, filmId)) {
            System.out.println(c);
        }

        // Customers
        // Print out info about all customers
        printCustomersInfo(em);

        // Get country name for customer by customer_id
        int customerId = 333;
        System.out.println(String.format("Country name for customer with id = %d", customerId));
        System.out.println(getCountryNameForCustomerById(em, customerId));

        // Get customers from same city by city_id (city_id = 42, 300, 312, 576 have more than 1 customer)
        int cityId = 312;
        System.out.println(String.format("Customers from city '%s'", getCityNameById(em, cityId)));
        for (Customer customer: getCustomersFromCityById(em, cityId)) {
            System.out.println(customer);
        }

        em.close();
    }

    /**
     *  FILMS
     */
    private static List<Film> getFilms(EntityManager em) {
        Query q = em.createQuery("SELECT f FROM Film f");
        return q.getResultList();
    }

    private static void printFilmsInfo(EntityManager em) {
        for(Film film : getFilms(em)) {
            System.out.println(film);
            film.printCategoryList();
        }
    }

    private static List<Category> getCategoriesForFilmById(EntityManager em, long filmId) {
        Query q = em.createQuery("SELECT c FROM Film f JOIN f.filmCategories c WHERE f.id = :filmId");
        q.setParameter("filmId", filmId);
        return q.getResultList();
    }

    /**
     *  CUSTOMERS
     */
    private static List<Customer> getCustomers(EntityManager em) {
        Query q = em.createQuery("SELECT c FROM Customer c");
        return q.getResultList();
    }

    private static void printCustomersInfo(EntityManager em) {
        for(Customer customer : getCustomers(em)) {
            System.out.println(customer);
        }
    }

    private static String getCountryNameForCustomerById(EntityManager em, long customerId) {
        /*
        Query q = em.createQuery(
                "SELECT country.name FROM Customer customer " +
                        "JOIN customer.address adress " +
                        "JOIN adress.city city " +
                        "JOIN city.country country " +
                        "WHERE customer.id = :customerId");
         */
        Query q = em.createQuery("SELECT c.address.city.country.name FROM Customer c WHERE c.id = :customerId");
        q.setParameter("customerId", customerId);
        return q.getSingleResult().toString();
    }

    /**
     * CITY
     */
    private static String getCityNameById(EntityManager em, long cityId) {
        Query q = em.createQuery("SELECT name FROM City WHERE id = :cityId");
        q.setParameter("cityId", cityId);
        return q.getSingleResult().toString();
    }

    private static List<Customer> getCustomersFromCityById(EntityManager em, long cityId) {
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.address.city.id = :cityId");
        q.setParameter("cityId", cityId);
        return q.getResultList();
    }
}
