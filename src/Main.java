import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс Main содержит основной функционал консольного приложения
 */
public class Main {
    static List<Movie> movieArray = new ArrayList<>();

    private final static PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

    /**
     * Содержит основной цикл, является точкой входа в программу
     * @param args - аргументы, переданные программе при запуске
     */
    public static void main(String[] args) {
        movieArray.add(new Movie("Titanic", 4.6, 100000000, "Drama"));
        movieArray.add(new Movie("Polar Express", 3.1, 200000, "Fantasy"));
        movieArray.add(new Movie("Black Swan", 4.2, 15000000, "Mystery"));
        boolean end = false;
        String[] array = {
                "Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"
        };
        Movie.setAvailableGenres(array);
        while (! end) {
            printMenu();
            int command = ConsoleInput.getCommand();
            switch (command) {
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
                case 3 -> changeMovieInformation();
                case 4 -> {
                    countStatistic();
                    Stream<Movie> stream = movieArray.stream();
                    stream.forEach(out::println);
                }
                case 5 -> {
                    sortMovies();
                    printMovies(movieArray);
                }
                case 6 -> deleteMovie();
                case 7 -> filterByGenre();
                case 8 -> deleteDuplicates();
                case 9 -> {
                    end = true;
                    ConsoleInput.close();
                }
            }
        }
    }

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

    public static void filterByGenre(){
        String genre = ConsoleInput.inputMovieGenre();
        Stream<Movie> movies = movieArray.stream().filter(movie -> movie.getFilmGenre().equals(genre));
        List<Movie> newList = movies.collect(Collectors.toCollection(ArrayList::new));
        printMovies(newList);
    }

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
        out.println("1 - add an empty movie");
        out.println("2 - add a movie with parameters");
        out.println("3 - edite a movie information");
        out.println("4 - print a movies information");
        out.println("5 - sort movies by a parameter");
        out.println("6 - delete a movie by a number");
        out.println("7 - filter movies by genre");
        out.println("8 - delete all duplicates");
        out.println("9 - exit");
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