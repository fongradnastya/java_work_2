import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {


    static ArrayList<Movie> movieArray = new ArrayList<>();

    public static void main(String[] args) {
        boolean end = false;
        while (! end) {
            printMenu();
            int command = ConsoleInput.getCommand();
            switch (command) {
                case 1: {
                    Movie emptyMovie = new Movie();
                    movieArray.add(emptyMovie);
                    System.out.println("Successfully added new movie");
                    break;
                }
                case 2: {
                    String name = ConsoleInput.inputFilmName();
                    double userRating = ConsoleInput.inputUserRating();
                    long boxOffice = ConsoleInput.inputBoxOffice();
                    String genre = ConsoleInput.inputMovieGenre();
                    Movie movie = new Movie(name, userRating, boxOffice, genre);
                    movieArray.add(movie);
                    System.out.println("Successfully added new movie");
                    break;
                }
                case 3: {
                    changeMovieInformation();
                    break;
                }
                case 4: {
                    printMovies(movieArray);
                    break;
                }
                case 5: {
                    sortMovies();
                    printMovies(movieArray);
                    break;
                }
                case 6: {
                    end = true;
                    ConsoleInput.close();
                    break;
                }
            }
        }
    }
    public static void printMovies(@NotNull ArrayList<Movie> movies){
        if(movies.isEmpty()){
            System.out.println("No movies yet");
        }
        else {
            System.out.println("                Movies");
            for (int i = 0; i < movies.size(); i++) {
                System.out.printf("._____________.    Number: %d\n", i + 1);
                System.out.printf("|^-----------^|    Movie name: %s\n", movies.get(i).getFilmName());
                System.out.printf("||..0.....0..||    Genre: %s\n", movies.get(i).getFilmGenre());
                System.out.printf("||..0.....0..||    User rating: %.1f\n", movies.get(i).getAverageUserRating());
                System.out.printf("|^-----------^|    Box office: %d$\n", movies.get(i).getBoxOffice());
                System.out.printf("|_____________|    Common rating: %.1f\n\n", movies.get(i).countCommonRating());
            }
        }
    }
    public static void printMenu(){
        System.out.println("----------------------MENU------------------------");
        System.out.println("1 - add an empty movie");
        System.out.println("2 - add a movie with parameters");
        System.out.println("3 - edite a movie information");
        System.out.println("4 - print a movie list");
        System.out.println("5 - sort movies by a parameter");
        System.out.println("6 - exit");
        System.out.println("--------------------------------------------------");
    }
    public static void changeMovieInformation(){
        System.out.print("Please enter the number of the movie to be changed: ");
        int movieNumber = ConsoleInput.getPositiveNumber();
        if (movieNumber > movieArray.size()){
            System.out.println("There is no movie with that number!");
        }
        else {
            printChangeMenu();
            int command = 0;
            command = ConsoleInput.getCommand();
            switch (command) {
                case 1: {
                    String name = ConsoleInput.inputFilmName();
                    movieArray.get(movieNumber - 1).setFilmName(name);
                    break;
                }
                case 2: {
                    String genre = ConsoleInput.inputMovieGenre();
                    movieArray.get(movieNumber - 1).setFilmGenre(genre);
                    break;
                }
                case 3: {
                    double rating = ConsoleInput.inputUserRating();
                    movieArray.get(movieNumber - 1).setAverageUserRating(rating);
                    break;
                }
                case 4: {
                    long boxOffice = ConsoleInput.inputBoxOffice();
                    movieArray.get(movieNumber - 1).setBoxOffice(boxOffice);
                    break;
                }
                default:{
                    System.out.println("Wrong command!");
                }
            }
        }
    }
    public static void printChangeMenu() {
        System.out.println("----------------------CHANGE----------------------");
        System.out.println("1 - to change a movie name");
        System.out.println("2 - to change a movie genre");
        System.out.println("3 - to change a user rating");
        System.out.println("4 - to change a box office");
        System.out.println("--------------------------------------------------");
    }

    public static void sortMovies(){
        printChangeMenu();
        int command = 0;
        command = ConsoleInput.getCommand();
        switch (command) {
            case 1: {
                Collections.sort(movieArray, new Comparator<Movie>() {
                    public int compare(Movie movie1, Movie movie2) {
                        return (movie1.getFilmName()).compareTo(movie2.getFilmName());
                    }
                });
                break;
            }
            case 2: {
                Collections.sort(movieArray, new Comparator<Movie>() {
                    public int compare(Movie movie1, Movie movie2) {
                        return (movie1.getFilmGenre()).compareTo(movie2.getFilmGenre());
                    }
                });
                break;
            }
            case 3: {
                Collections.sort(movieArray, new Comparator<Movie>() {
                    public int compare(Movie movie1, Movie movie2) {
                        return Double.compare(movie1.getAverageUserRating(), movie2.getAverageUserRating());
                    }
                });
                break;
            }
            case 4: {
                Collections.sort(movieArray, new Comparator<Movie>() {
                    public int compare(Movie movie1, Movie movie2) {
                        return Long.compare(movie1.getBoxOffice(), movie2.getBoxOffice());
                    }
                });
                break;
            }
            default:{
                System.out.println("Wrong command!");
            }
        }
    }
}