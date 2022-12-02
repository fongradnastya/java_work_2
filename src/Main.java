import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

/**
 * Класс Main содержит основной функционал консольного приложения
 */
public class Main implements MenuEnum{

    /**
     * Список всех фильмов
     */
    static ArrayList<Movie> movieArray;

    /**
     * Поток вывода данных в консоль
     */
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
        movieArray = createMovieList();
        while (! end) {
            printMenu();
            int command = ConsoleInput.getCommand();
            switch (command) {
                case ADD_MOVIE -> addMovie();
                case CHANGE_MOVIE -> changeMovieInformation();
                case PRINT_MOVIES -> printMovies(movieArray);
                case SORT_MOVIES -> {
                    sortMovies();
                    printMovies(movieArray);
                }
                case DELETE_MOVIE -> deleteMovie();
                case GROUP_MOVIES -> groupByGenre();
                case FILTER_MOVIES -> filterByRating();
                case DELETE_DUPLICATES -> deleteDuplicates();
                case GET_STATISTIC -> countStatistic();
                case TOTAL_BOX_OFFICE -> countTotalBoxOffice();
                case FIND_TOP -> findTheHighestRated();
                case EXIT -> {
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
            case ADD_EMPTY -> {
                Movie emptyMovie = new Movie();
                movieArray.add(emptyMovie);
                out.println("Successfully added new movie");
            }
            case ADD_WITH_PARAMETERS -> {
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
                out.println(movie);
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
        Stream<Movie> stream = movies.stream();
        stream.forEach(out::println);

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
                case CHANGE_NAME -> {
                    String name = ConsoleInput.inputFilmName();
                    movieArray.get(movieNumber - 1).setFilmName(name);
                }
                case CHANGE_GENRE -> {
                    String genre = ConsoleInput.inputMovieGenre();
                    movieArray.get(movieNumber - 1).setFilmGenre(genre);
                }
                case CHANGE_RATING -> {
                    double rating = ConsoleInput.inputUserRating();
                    movieArray.get(movieNumber - 1).setAverageUserRating(rating);
                }
                case CHANGE_BOX_OFFICE -> {
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
        Comparator<Movie> newComparator = null;
        switch (command) {
            case SORT_BY_NAME -> newComparator = Comparator.comparing(Movie::getFilmName);
            case SORT_BY_GENRE -> newComparator = Comparator.comparing(Movie::getFilmGenre);
            case SORT_BY_USER_RATING -> newComparator = Comparator.comparing(Movie::getAverageUserRating);
            case SORT_BY_BOX_OFFICE -> newComparator = Comparator.comparing(Movie::getBoxOffice);
            case SORT_BY_COMMON_RATING -> newComparator = Comparator.comparing(Movie::countCommonRating);
            default -> System.out.println("Wrong command!");
        }
        if (newComparator != null){
            Stream<Movie> sorted = movieArray.stream().sorted(newComparator);
            movieArray = sorted.collect(Collectors.toCollection(ArrayList::new));
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
            printMovies(movieArray);
        }
    }
}