package com.pandacreep.onlineshop.util;

import com.pandacreep.onlineshop.model.*;
import com.pandacreep.onlineshop.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

@Configuration
public class PreloadDatabaseWithData {
    @Bean
    CommandLineRunner initDatabase(ItemsRepository itemsRepository,
                                   ProducersRepository producersRepository,
                                   CategoriesRepository categoriesRepository,
                                   UserRepository userRepository,
                                   BasketRepository basketRepository,
                                   OrderRepository orderRepository,
                                   ReviewsRepository reviewsRepository,
                                   ImagesRepository imagesRepository) {
        System.out.println("Initialization of MongoDB with initial data");
        producersRepository.deleteAll();
        categoriesRepository.deleteAll();
        itemsRepository.deleteAll();
        userRepository.deleteAll();
        basketRepository.deleteAll();
        orderRepository.deleteAll();
        reviewsRepository.deleteAll();
        imagesRepository.deleteAll();
        try {
            initProducers(producersRepository).run();
            initCategories(categoriesRepository).run();
            initUsers(userRepository).run();
            List<Producer> producers = new ArrayList<>();
            for (Producer producer : producersRepository.findAll()) {
                producers.add(producer);
            }
            List<Category> categories = new ArrayList<>();
            for (Category category : categoriesRepository.findAll()) {
                categories.add(category);
            }
            initItems(itemsRepository, producers, categories).run();
            initOrders(orderRepository, itemsRepository).run();
            initReviews(reviewsRepository, itemsRepository).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CommandLineRunner initReviews(ReviewsRepository repository,
                                         ItemsRepository itemsRepository) {
        return (args) -> Stream.of(reviews(itemsRepository))
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private CommandLineRunner initOrders(OrderRepository repository,
                                         ItemsRepository itemsRepository) {
        return (args) -> Stream.of(orders(itemsRepository))
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private CommandLineRunner initItems(ItemsRepository repository,
                                        List<Producer> producers,
                                        List<Category> categories) {
        return (args) -> Stream.of(items(producers, categories))
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private CommandLineRunner initProducers(ProducersRepository repository) {
        return (args) -> Stream.of(producers())
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private CommandLineRunner initCategories(CategoriesRepository repository) {
        return (args) -> Stream.of(categories())
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private CommandLineRunner initUsers(UserRepository repository) {
        return (args) -> Stream.of(users())
                .peek(System.out::println)
                .forEach(repository::save);
    }

    private Producer[] producers() {
        return new Producer[]{
                new Producer("Sony", "Japan"),
                new Producer("Samsung", "Korea"),
                new Producer("Dell", "USA"),
                new Producer("Xiaomi", "China"),
                new Producer("JetBrains", "Russia")};
    }

    private Category[] categories() {
        return new Category[]{
                new Category("computer", "notebooks, desktops"),
                new Category("multimedia", "tv-boxes, mp3 players, music centers"),
                new Category("phone", "smartphones, desk phones, feature phones")};
    }

    private Item[] items(List<Producer> producers, List<Category> categories) {
        return new Item[]{
                new Item(200, "TV Sony Bravia",
                        "Screen 110 inch, OLED display",
                        producers.get(0), categories.get(1)),
                new Item(250, "Player Sony walkman",
                        "MP3 player, 32GB, poison color",
                        producers.get(0), categories.get(1)),
                new Item(50, "TV Samsung UHD700",
                        "Screen 210 inch, Super AMOLED display",
                        producers.get(1), categories.get(1)),
                new Item(320, "Notebook Dell 500px",
                        "Display 14 inches, weight 0.6kg",
                        producers.get(2), categories.get(0))
        };
    }

    private User[] users() {
        return new User[]{
                new User("tolik@gmail.com", "Anatoliy Brunov", false),
                new User("anna@gmail.com", "Anna Karenina", false),
                new User("bruno@gmail.com", "Bruno Mars", false)};
    }

    private Order[] orders(ItemsRepository itemsRepository) {
        LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).minusDays(30);
        Item[] items = new Item[(int) itemsRepository.count()];
        int i = 0;
        var allItems = itemsRepository.findAll().iterator();
        while (allItems.hasNext()) {
            items[i++] = allItems.next();
        }

        Order[] orders = {
                getOrder1(ldt, items),
                getOrder2(ldt, items),
                getOrder3(ldt, items),
                getOrder4(ldt, items),
                getOrder5(ldt, items),
                getOrder6(ldt, items)};
        return orders;
    }

    private Order getOrder1(LocalDateTime ldt, Item[] items) {
        Order order = new Order(users()[0].getEmail(),
                ldt.minusDays(5),
                "Ivan Petrov",
                "123",
                "ivan.petrov@gmail.com",
                "Moscow, Req Square",
                "VISA");
        Map<String, Integer> itemsQty = new HashMap<>();
        int orderAmount = 0;
        itemsQty.put(items[0].getId(), 2);
        itemsQty.put(items[1].getId(), 1);
        orderAmount += items[0].getPrice() * 2;
        orderAmount += items[1].getPrice() * 1;
        order.setItemsQty(itemsQty);
        order.setOrderAmount(orderAmount);

        Map<String, Item> itemsItem = new HashMap<>();
        itemsItem.put(items[0].getId(), items[0]);
        itemsItem.put(items[1].getId(), items[1]);
        order.setItems(itemsItem);

        return order;
    }

    private Order getOrder2(LocalDateTime ldt, Item[] items) {
        Order order = new Order(users()[0].getEmail(),
                ldt.minusDays(15),
                "Igor Bakher",
                "645",
                "igor.bakher@gmail.com",
                "Berlin, Gotter strasee, 23",
                "VISA");
        Map<String, Integer> itemsQty = new HashMap<>();
        int orderAmount = 0;
        itemsQty.put(items[2].getId(), 2);
        itemsQty.put(items[1].getId(), 3);
        orderAmount += items[2].getPrice() * 2;
        orderAmount += items[1].getPrice() * 3;
        order.setItemsQty(itemsQty);
        order.setOrderAmount(orderAmount);

        Map<String, Item> itemsItem = new HashMap<>();
        itemsItem.put(items[2].getId(), items[2]);
        itemsItem.put(items[1].getId(), items[1]);
        order.setItems(itemsItem);

        return order;
    }

    private Order getOrder3(LocalDateTime ldt, Item[] items) {
        Order order = new Order(users()[1].getEmail(),
                ldt.minusDays(7),
                "Nona Gvertsiteli",
                "826",
                "nona.gve@gmail.com",
                "Tbilisi, Gauri str, 98",
                "Cash");
        Map<String, Integer> itemsQty = new HashMap<>();
        int orderAmount = 0;
        itemsQty.put(items[2].getId(), 1);
        itemsQty.put(items[3].getId(), 3);
        orderAmount += items[2].getPrice() * 1;
        orderAmount += items[3].getPrice() * 3;
        order.setItemsQty(itemsQty);
        order.setOrderAmount(orderAmount);

        Map<String, Item> itemsItem = new HashMap<>();
        itemsItem.put(items[2].getId(), items[2]);
        itemsItem.put(items[3].getId(), items[3]);
        order.setItems(itemsItem);

        return order;
    }

    private Order getOrder4(LocalDateTime ldt, Item[] items) {
        Order order = new Order(users()[1].getEmail(),
                ldt.minusDays(9),
                "Masha Rostova",
                "909",
                "masha.rostova@gmail.com",
                "Tashkent, Olboni str, 2",
                "Kaspi");
        Map<String, Integer> itemsQty = new HashMap<>();
        int orderAmount = 0;
        itemsQty.put(items[2].getId(), 1);
        itemsQty.put(items[3].getId(), 1);
        orderAmount += items[2].getPrice() * 1;
        orderAmount += items[3].getPrice() * 1;
        order.setItemsQty(itemsQty);
        order.setOrderAmount(orderAmount);

        Map<String, Item> itemsItem = new HashMap<>();
        itemsItem.put(items[2].getId(), items[2]);
        itemsItem.put(items[3].getId(), items[3]);
        order.setItems(itemsItem);

        return order;
    }

    private Order getOrder5(LocalDateTime ldt, Item[] items) {
        Order order = new Order(users()[2].getEmail(),
                ldt.minusDays(2),
                "Alex Stopper",
                "900",
                "alex.stopper@gmail.com",
                "Almaty, Nazarbayev str, 3",
                "Kaspi");
        Map<String, Integer> itemsQty = new HashMap<>();
        int orderAmount = 0;
        itemsQty.put(items[1].getId(), 3);
        itemsQty.put(items[3].getId(), 3);
        orderAmount += items[1].getPrice() * 3;
        orderAmount += items[3].getPrice() * 3;
        order.setItemsQty(itemsQty);
        order.setOrderAmount(orderAmount);

        Map<String, Item> itemsItem = new HashMap<>();
        itemsItem.put(items[1].getId(), items[1]);
        itemsItem.put(items[3].getId(), items[3]);
        order.setItems(itemsItem);

        return order;
    }

    private Order getOrder6(LocalDateTime ldt, Item[] items) {
        Order order = new Order(users()[2].getEmail(),
                ldt.minusDays(4),
                "Aizhan Kurmanbayeva",
                "7712",
                "aizhik@gmail.com",
                "Taraz, Abay str, 23",
                "Cash");
        Map<String, Integer> itemsQty = new HashMap<>();
        int orderAmount = 0;
        itemsQty.put(items[0].getId(), 1);
        itemsQty.put(items[3].getId(), 1);
        orderAmount += items[0].getPrice() * 1;
        orderAmount += items[3].getPrice() * 1;
        order.setItemsQty(itemsQty);
        order.setOrderAmount(orderAmount);

        Map<String, Item> itemsItem = new HashMap<>();
        itemsItem.put(items[0].getId(), items[0]);
        itemsItem.put(items[3].getId(), items[3]);
        order.setItems(itemsItem);

        return order;
    }

    private Review[] reviews(ItemsRepository itemsRepository) {
        Item[] items = new Item[(int) itemsRepository.count()];
        int i = 0;
        var allItems = itemsRepository.findAll().iterator();
        while (allItems.hasNext()) {
            items[i++] = allItems.next();
        }

        Review r1 = new Review("Maradona", "492", "maradonna@gmail.ru",
                5, "Very, very good product!");
        r1.setItem(items[0]);
        Review r2 = new Review("Mary Cary", "423", "mary.cary@gmail.com",
                4, "Nice product! But too expensive");
        r2.setItem(items[0]);
        Review r3 = new Review("Angela", "342", "angela@gmail.com",
                3, "So, so, to ne frankly");
        r3.setItem(items[1]);
        Review r4 = new Review("Mike Bordo", "125", "mike.bordo@gmail.com",
                4, "I am satisfied with that product");
        r4.setItem(items[1]);
        Review r5 = new Review("Alla Norke", "742", "alla.norke@gmail.com",
                1, "Very bad product!(((");
        r5.setItem(items[2]);
        Review r6 = new Review("Petr Chik", "888", "petr.chik@gmail.com",
                5, "I recommend this to buy");
        r6.setItem(items[3]);
        Review[] reviews = {r1, r2, r3, r4, r5, r6};
        return reviews;
    }
}