import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Main {

    static String[] movieGenres = {
        "Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"
    };
    static ArrayList<Movie> movieArray = new ArrayList<>();

    public static void main(String[] args) {
        printMenu();
        // System.out.println(ConsoleInput.getMovieGenre());
        boolean end = false;
        while (! end) {
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
                    break;
                }
                case 4: {
                    printMovies(movieArray);
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
            for (Movie movie : movies) {
                System.out.print("._____________.\n");
                System.out.printf("|^-----------^|    Movie: %s\n", movie.getFilmName());
                System.out.printf("||..0.....0..||    Genre: %s\n", movie.getFilmGenre());
                System.out.printf("||..0.....0..||    User rating: %.1f\n", movie.getAverageUserRating());
                System.out.printf("|^-----------^|    Box office: %d$\n", movie.getBoxOffice());
                System.out.print("|_____________|\n");
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
}