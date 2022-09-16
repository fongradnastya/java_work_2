import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 */
public class Movie {
    private double averageUserRating;
    private long boxOffice;
    private String filmName;
    private String filmGenre;

    private static ArrayList<String> movieGenres;

    /**
     * Конструктор объекта поумолчанию
     */
    public Movie() {
        filmName = "movie";
    }

    /**
     * Конструктор объекта с параметрами
     * @param filmName - название фильма
     * @param averageUserRating - средняя оценка пользователей от 0 до 5
     * @param boxOffice - количество $, заработанных на кинопоказах
     * @param filmGenre - жанр фильма
     */
    public Movie(String filmName, double averageUserRating, long boxOffice, String filmGenre) {
        this.averageUserRating = averageUserRating;
        this.boxOffice = boxOffice;
        this.filmName = filmName;
        this.filmGenre = filmGenre;
    }

    public static void setAvailableGenres(String[] array){
        movieGenres = new ArrayList<>(Arrays.asList(array));
    }

    public static ArrayList<String> getAvailableGenres(){
        return movieGenres;
    }

    public double getAverageUserRating() {
        return averageUserRating;
    }

    public void setAverageUserRating(double averageUserRating) {
        if(averageUserRating >= 0 && averageUserRating <= 5)
            this.averageUserRating = averageUserRating;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(long boxOffice) {
        if(boxOffice >= 0){
            this.boxOffice = boxOffice;
        }

    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String currGenre) {
        if(! movieGenres.isEmpty()){
            if(movieGenres.contains(currGenre)){
                this.filmGenre = currGenre;
            }
        }
    }

    /**
     * вычисляет финальный рейтинг фильма, учитывая оценки пользователей и кассовые сборы
     * @return rating - дробное значение в промежутке от 0 до 5
     */
    public double countCommonRating(){
        long maxBoxOffice = 1_000_000_000L;
        double cinemaRating = (double) (boxOffice) / (double) maxBoxOffice * 4;
        double rating = averageUserRating + cinemaRating;
        rating = (double) Math.round(rating * 10) / 10;
        if(rating < 5) return rating;
        return 5.0;
    }
}
