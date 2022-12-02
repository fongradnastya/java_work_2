import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс ConsoleInput реализует считывание данных из консоли
 */
public class ConsoleInput {
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Считывает строку, содержащую одно число типа int
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
     * Считывает комманду из консоли
     * @return - комманда в виде числа типа int
     */
    public static int getCommand(){
        System.out.print("Enter your command: ");
        return ConsoleInput.getIntString();
    }

    /**
     * Считывает из консоли положительное целое число
     * @return полученное значение
     */
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

    /**
     * Считывает жанр фильма и проверяет его на корректность
     * @return - строка с именем жанра, либо значение empty
     */
    public static String inputMovieGenre(){
        ArrayList<String> movieGenres = Movie.getAvailableGenres();
        System.out.println("Available movie genres:");
        System.out.println(movieGenres);
        String currGenre;
        while (true){
            System.out.print("Your genre: ");
            currGenre = scanner.nextLine();
            if(movieGenres.contains(currGenre)){
                break;
            }
            else{
                System.out.println("Incorrect genre!");
                return "empty";
            }
        }
        return currGenre;
    }

    /**
     * Считывает из консоли среднюю оценку фильма пользователями
     * @return дробное значение в промежутке от 0 до 5
     */
    public static double inputUserRating(){
        double rating = 0;
        while (rating == 0)
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
                if (rating < 0 || rating > 5){
                    rating = 0;
                    System.out.println("Rating should be between zero and five");
                }
            }
        }
        return rating;
    }

    /**
     * Считывает из консоли кассовые сборы фильма
     * @return размер сбора (положительное число типа double)
     */
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

    /**
     * Считывает из консоли название фильма
     * @return название фильма
     */
    public static String inputFilmName(){
        System.out.print("Please, enter film name: ");
        return scanner.nextLine();
    }

    /**
     * Заканчивает работу сканера
     */
    public static void close(){
        scanner.close();
    }
}
