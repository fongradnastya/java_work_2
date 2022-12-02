import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

/**
 * Класс Main содержит основной функционал консольного приложения
 */
public class Main {
    static List<Movie> movieArray;

    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Содержит основной цикл, является точкой входа в программу
     * @param args - аргументы, переданные программе при запуске
     */
    public static void main(String[] args) {
        boolean end = false;
        String[] array = {
                "Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"
        };
        Movie.setAvailableGenres(array);
        while (! end) {
            movieArray = createMovieList();
            printMenu();
            int command = ConsoleInput.getCommand();
            switch (command) {
                case 1 -> addMovie();
                case 2 -> changeMovieInformation();
                case 3 -> {
                    countStatistic();
                    Stream<Movie> stream = movieArray.stream();
                    stream.forEach(out::println);
                }
                case 4 -> {
                    sortMovies();
                    printMovies(movieArray);
                }
                case 5 -> deleteMovie();
                case 6 -> groupByGenre();
                case 7 -> filterByRating();
                case 8 -> deleteDuplicates();
                case 9 -> countStatistic();
                case 10 -> countTotalBoxOffice();
                case 11 -> findTheHighestRated();
                case 12 -> {
                    end = true;
                    ConsoleInput.close();
                }
                default -> out.println("Wrong command");
            }
        }
    }

    /**
     * Создаёт список фильмов с начальным набором значений
     * @return созданный список фильмов
     */
    public static ArrayList<Movie> createMovieList(){
        ArrayList<Movie> newMovies = new ArrayList<>();
        newMovies.add(new Movie("Titanic", 4.6, 100000000, "Drama"));
        newMovies.add(new Movie("Polar Express", 3.1, 200000, "Fantasy"));
        newMovies.add(new Movie("Black Swan", 4.2, 15000000, "Mystery"));
        newMovies.add(new Movie("House of Peculiar Children", 4.5, 1700000,
                "Mystery"));
        newMovies.add(new Movie("Once Upon a Time in Paris", 3.2, 100000,
                "Comedy"));
        return newMovies;
    }

    /**
     * Добавляет созданный пользователем фильм в общий список
     */
    public static void addMovie(){
        out.println("1 - to add an empty movie");
        out.println("2 - to add a movie with parameters");
        int command = ConsoleInput.getCommand();
        switch (command){
            case 1 -> {
                Movie emptyMovie = new Movie();
                movieArray.add(emptyMovie);
                out.println("Successfully added new movie");
            }
            case 2 -> {
                String name = ConsoleInput.inputFilmName();
                double userRating = ConsoleInput.inputUserRating();
                long boxOffice = ConsoleInput.inputBoxOffice();
                String genre = ConsoleInput.inputMovieGenre();
                Movie movie = new Movie(name, userRating, boxOffice, genre);
                movieArray.add(movie);
                out.println("Successfully added new movie");
            }
            default -> out.println("Wrong command, movie wasn't added");
        }
    }

    /**
     * Удаляет все дубликаты фильмов в списке
     */
    public static void deleteDuplicates(){
        Stream<Movie> stream = movieArray.stream().distinct();
        int oldLength = movieArray.size();
        movieArray = stream.collect(Collectors.toCollection(ArrayList::new));
        long deletedLength = oldLength - movieArray.size();
        if (deletedLength > 0){
            out.printf("%d duplicates was successfully deleted\n", deletedLength);
        }
        else{
            out.println("Duplicates were not found");
        }
    }

    /**
     * Фильтрует фильмы по заданному значению пользовательского рейтинга
     */
    public static void filterByRating(){
        double rating = ConsoleInput.inputUserRating();
        Stream<Movie> movies = movieArray.stream().filter(movie -> movie.getAverageUserRating() >= rating);
        List<Movie> newList = movies.collect(Collectors.toCollection(ArrayList::new));
        printMovies(newList);
    }

    /**
     * Выводит сгруппированный по жанрам список фильмов
     */
    public static void groupByGenre(){
        Map<String, List<Movie>> movieToGenre = movieArray.stream().collect(Collectors.groupingBy(Movie::getFilmGenre));
        Map<String, Long> movieToGenreCounts = movieArray.stream().collect(
                Collectors.groupingBy(Movie::getFilmGenre, counting()));
        for(String key : movieToGenre.keySet()){
            out.println("Genre: " + key + ", count: " + movieToGenreCounts.get(key));
            out.println();
            for(Movie movie : movieToGenre.get(key)){
                printMovie(movie);
            }
        }
    }

    /**
     * Вычисляет суммарный кассовый сбор всех фильмов
     */
    public static void countTotalBoxOffice(){
        long startValue = 0;
        long total = movieArray.stream().reduce(startValue, (value, movie) -> value + movie.getBoxOffice(), Long::sum);
        out.println("Total cinema box office = " + total + "$");
    }

    /**
     * Выводит в консоль фильм с наибольшим пользовательским рейтингом
     */
    public static void findTheHighestRated(){
        Optional<Movie> largest = movieArray.stream().max(Movie::compareTo);
        largest.ifPresent(movie -> System.out.println("Top user rating: " + movie));
    }

    /**
     * Выводит статистические данные о числе фильмов и об их общем рейтинге
     */
    public static void countStatistic(){
        DoubleSummaryStatistics iStats = movieArray.stream().mapToDouble(Movie::countCommonRating).summaryStatistics();
        out.println(iStats.getCount() + " films");
        out.println("average movie rating = " + iStats.getAverage());
        out.println("max movie rating = " + iStats.getMax());
        out.println("min movie rating = "+ iStats.getMin());
    }

    /**
     * Выводит в консоль список всех фильмов
     */
    public static void printMovies(List<Movie> movies){
        if(movies.isEmpty()){
            out.println("No movies yet");
        }
        else {
            out.println("                Movies");
            for (int i = 0; i < movies.size(); i++) {
                out.printf("._____________.    Number: %d\n", i + 1);
                printMovie(movies.get(i));
            }
        }
    }

    public static void printMovie(Movie movie){
        out.printf("|^-----------^|    Movie name: %s\n", movie.getFilmName());
        out.printf("||..0.....0..||    Genre: %s\n", movie.getFilmGenre());
        out.printf("||..0.....0..||    User rating: %.1f\n", movie.getAverageUserRating());
        out.printf("|^-----------^|    Box office: %d$\n", movie.getBoxOffice());
        out.printf("|_____________|    Common rating: %.1f\n\n", movie.countCommonRating());
    }

    /**
     * Выводит в консоль основное меню пользователя
     */
    public static void printMenu(){
        out.println("----------------------MENU------------------------");
        out.println("1 - add new movie");
        out.println("2 - edite a movie information");
        out.println("3 - print a movie list");
        out.println("4 - sort movies by a parameter");
        out.println("5 - delete a movie by a number");
        out.println("6 - group movies by genre");
        out.println("7 - filter movies by user rating");
        out.println("8 - delete all duplicates");
        out.println("9 - print movies rating statistic");
        out.println("10 - count total box office");
        out.println("11 - find the most rating movie");
        out.println("12 - exit");
        out.println("--------------------------------------------------");
    }

    /**
     * Меняет одно из свойств фильма на введённое пользователем значение
     */
    public static void changeMovieInformation(){
        out.print("Please enter the number of the movie to be changed: ");
        int movieNumber = ConsoleInput.getPositiveNumber();
        if (movieNumber > movieArray.size()){
            out.println("There is no movie with that number!");
        }
        else {
            printChangeMenu();
            int command;
            command = ConsoleInput.getCommand();
            switch (command) {
                case 1 -> {
                    String name = ConsoleInput.inputFilmName();
                    movieArray.get(movieNumber - 1).setFilmName(name);
                }
                case 2 -> {
                    String genre = ConsoleInput.inputMovieGenre();
                    movieArray.get(movieNumber - 1).setFilmGenre(genre);
                }
                case 3 -> {
                    double rating = ConsoleInput.inputUserRating();
                    movieArray.get(movieNumber - 1).setAverageUserRating(rating);
                }
                case 4 -> {
                    long boxOffice = ConsoleInput.inputBoxOffice();
                    movieArray.get(movieNumber - 1).setBoxOffice(boxOffice);
                }
                default -> System.out.println("Wrong command!");
            }
        }
    }

    /**
     * Выводит в консоль меню изменения объекта
     */
    public static void printChangeMenu() {
        out.println("----------------------CHANGE----------------------");
        out.println("1 - to change a movie name");
        out.println("2 - to change a movie genre");
        out.println("3 - to change a user rating");
        out.println("4 - to change a box office");
        out.println("--------------------------------------------------");
    }

    /**
     * Сортирует фильмы по выбранному пользователем свойству
     */
    public static void sortMovies(){
        printSortMenu();
        int command = ConsoleInput.getCommand();
        switch (command) {
            case 1 -> {
                Comparator<Movie> nameComparator = Comparator.comparing(Movie::getFilmName);
                movieArray.sort(nameComparator);
            }
            case 2 -> {
                Comparator<Movie> genreComparator = Comparator.comparing(Movie::getFilmGenre);
                movieArray.sort(genreComparator);
            }
            case 3 -> {
                Comparator<Movie> userRatingComparator = Comparator.comparing(Movie::getAverageUserRating);
                movieArray.sort(userRatingComparator);
            }
            case 4 -> {
                Comparator<Movie> boxOfficeComparator = Comparator.comparing(Movie::getBoxOffice);
                movieArray.sort(boxOfficeComparator);
            }
            case 5 -> {
                Comparator<Movie> commonRatingComparator = Comparator.comparing(Movie::countCommonRating);
                movieArray.sort(commonRatingComparator);
            }
            default -> System.out.println("Wrong command!");
        }
    }

    /**
     * Выводит в консоль меню сортировки
     */
    public static void printSortMenu() {
        out.println("----------------------SORT-----------------------");
        out.println("1 - to sort by a movie name");
        out.println("2 - to sort by a movie genre");
        out.println("3 - to sort by a user rating");
        out.println("4 - to sort by a box office");
        out.println("5 - to sort by a common rating");
        out.println("--------------------------------------------------");
    }

    /**
     * Удаляет из списка фильм с заданным номером
     */
    public static void deleteMovie(){
        out.print("Please enter the number of the movie to be deleted: ");
        int numberToDelete = ConsoleInput.getPositiveNumber();
        if(numberToDelete > movieArray.size() || numberToDelete <= 0){
            System.out.println("There is no movie with that number!");
        }
        else {
            movieArray.remove(numberToDelete - 1);
            System.out.println("Successfully deleted a movie");
        }
    }
}