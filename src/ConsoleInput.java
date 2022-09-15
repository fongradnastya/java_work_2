import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleInput {
    /**
     *
     */
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Метод getIntString считывает строку, содержащую одно число типа int
     * @return number - считанное целое число
     */
    public static int getIntString() {
        int number = 0;
        while (number == 0)
        {
            if(scanner.hasNext()){
                String line = scanner.nextLine();
                try
                {
                    number = Integer.parseInt(line);
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Wrong number format!");
                    return 0;
                }
            }
        }
        return number;
    }

    /**
     * Метод getCommand считывает комманду из консоли
     * @return - комманда в виде числа типа int
     */
    public static int getCommand(){
        System.out.print("Enter your command: ");
        return ConsoleInput.getIntString();
    }
    public static int getPositiveNumber(){
        int number = 0;
        while(number < 1){
            number = getIntString();
            if(number < 1){
                System.out.println("This value should be positive!");
            }
        }
        return number;
    }
    public static String inputMovieGenre(){
        String[] array = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"};
        ArrayList<String> movieGenres = new ArrayList<>(Arrays.asList(array));
        System.out.println("Available movie genres:");
        System.out.println(movieGenres);
        String currGenre = "";
        while (true){
            System.out.print("Your genre: ");
            currGenre = scanner.nextLine();
            if(movieGenres.contains(currGenre)){
                break;
            }
            else{
                System.out.println("Incorrect genre!");
            }
        }
        return currGenre;
    }

    public static double inputUserRating(){
        double rating = 0;
        while (rating == 0.0)
        {
            System.out.print("Please, enter a movie rating: ");
            if(scanner.hasNext()){

                String line = scanner.nextLine();
                try
                {
                    rating = Double.parseDouble(line);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Wrong number format!");
                }
                if (rating <= 0 || rating > 5){
                    rating = 0;
                    System.out.println("Rating should be between zero and five");
                }
            }
        }
        return rating;
    }
    public static long inputBoxOffice(){
        long number = -1;
        while (number < 0)
        {
            System.out.print("Please, enter a film box office: ");
            if(scanner.hasNext()){
                String line = scanner.nextLine();
                try
                {
                    number = Long.parseLong(line);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Wrong number format!");
                }
                if(number < 0){
                    System.out.println("This value can't be negative");
                }
            }
        }
        return number;
    }

    public static String inputFilmName(){
        System.out.print("Please, enter film name: ");
        return scanner.nextLine();
    }
    public static void close(){
        scanner.close();
    }
}
