import java.util.ArrayList;
import java.util.Comparator;

/**
 * Класс Main содержит основной функционал консольного приложения
 */
public class Main {
    static ArrayList<Movie> movieArray = new ArrayList<>();

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
            printMenu();
            int command = ConsoleInput.getCommand();
            switch (command) {
                case 1 -> {
                    Movie emptyMovie = new Movie();
                    movieArray.add(emptyMovie);
                    System.out.println("Successfully added new movie");
                }
                case 2 -> {
                    String name = ConsoleInput.inputFilmName();
                    double userRating = ConsoleInput.inputUserRating();
                    long boxOffice = ConsoleInput.inputBoxOffice();
                    String genre = ConsoleInput.inputMovieGenre();
                    Movie movie = new Movie(name, userRating, boxOffice, genre);
                    movieArray.add(movie);
                    System.out.println("Successfully added new movie");
                }
                case 3 -> changeMovieInformation();
                case 4 -> printMovies();
                case 5 -> {
                    sortMovies();
                    printMovies();
                }
                case 6 -> deleteMovie();
                case 7 -> {
                    end = true;
                    ConsoleInput.close();
                }
            }
        }
    }

    /**
     * Выводит в консоль список всех фильмов
     */
    public static void printMovies(){
        if(movieArray.isEmpty()){
            System.out.println("No movies yet");
        }
        else {
            System.out.println("                Movies");
            for (int i = 0; i < movieArray.size(); i++) {
                System.out.printf("._____________.    Number: %d\n", i + 1);
                System.out.printf("|^-----------^|    Movie name: %s\n", movieArray.get(i).getFilmName());
                System.out.printf("||..0.....0..||    Genre: %s\n", movieArray.get(i).getFilmGenre());
                System.out.printf("||..0.....0..||    User rating: %.1f\n", movieArray.get(i).getAverageUserRating());
                System.out.printf("|^-----------^|    Box office: %d$\n", movieArray.get(i).getBoxOffice());
                System.out.printf("|_____________|    Common rating: %.1f\n\n", movieArray.get(i).countCommonRating());
            }
        }
    }

    /**
     * Выводит в консоль основное меню пользователя
     */
    public static void printMenu(){
        System.out.println("----------------------MENU------------------------");
        System.out.println("1 - add an empty movie");
        System.out.println("2 - add a movie with parameters");
        System.out.println("3 - edite a movie information");
        System.out.println("4 - print a movie list");
        System.out.println("5 - sort movies by a parameter");
        System.out.println("6 - delete a movie by a number");
        System.out.println("7 - exit");
        System.out.println("--------------------------------------------------");
    }

    /**
     * Меняет одно из свойств фильма на введённое пользователем значение
     */
    public static void changeMovieInformation(){
        System.out.print("Please enter the number of the movie to be changed: ");
        int movieNumber = ConsoleInput.getPositiveNumber();
        if (movieNumber > movieArray.size()){
            System.out.println("There is no movie with that number!");
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
        System.out.println("----------------------CHANGE----------------------");
        System.out.println("1 - to change a movie name");
        System.out.println("2 - to change a movie genre");
        System.out.println("3 - to change a user rating");
        System.out.println("4 - to change a box office");
        System.out.println("--------------------------------------------------");
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
        System.out.println("----------------------SORT-----------------------");
        System.out.println("1 - to sort by a movie name");
        System.out.println("2 - to sort by a movie genre");
        System.out.println("3 - to sort by a user rating");
        System.out.println("4 - to sort by a box office");
        System.out.println("5 - to sort by a common rating");
        System.out.println("--------------------------------------------------");
    }

    public static void deleteMovie(){
        System.out.print("Please enter the number of the movie to be deleted: ");
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