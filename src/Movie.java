import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс Movie реализует хранение информации о фильме и методы её изменения
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
        filmGenre = "empty";
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

    /**
     * Устанавливает значение статического поля movieGenres
     * @param array - массив строк, содержащий возможные названия жанров
     */
    public static void setAvailableGenres(String[] array){
        movieGenres = new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Получение доступа к статическому полю movieGenres
     * @return значение movieGenres
     */
    public static ArrayList<String> getAvailableGenres(){
        return movieGenres;
    }

    /**
     * Получение доступа к полю averageUserRating
     * @return averageUserRating
     */
    public double getAverageUserRating() {
        return averageUserRating;
    }

    /**
     * Устанавливает значение поля movieGenres
     * @param averageUserRating - средняя оценка фильма пользователями
     */
    public void setAverageUserRating(double averageUserRating) {
        if(averageUserRating >= 0 && averageUserRating <= 5)
            this.averageUserRating = averageUserRating;
    }
    /**
     * Получение доступа к полю boxOffice
     * @return boxOffice
     */
    public long getBoxOffice() {
        return boxOffice;
    }

    /**
     * Устанавливает значение поля boxOffice
     * @param boxOffice - кассовый сбор фильма
     */
    public void setBoxOffice(long boxOffice) {
        if(boxOffice >= 0){
            this.boxOffice = boxOffice;
        }

    }

    /**
     * Получение доступа к полю filmName
     * @return filmName
     */
    public String getFilmName() {
        return filmName;
    }

    /**
     * Устанавливает значение поля filmName
     * @param filmName - название нового фильма
     */
    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    /**
     * Получение доступа к полю filmGenre
     * @return filmGenre
     */
    public String getFilmGenre() {
        return filmGenre;
    }

    /**
     * Устанавливает значение поля currGenre в одно из допустимых значений
     * @param currGenre - жанр текущего фильма
     */
    public void setFilmGenre(String currGenre) {
        if(! movieGenres.isEmpty() && movieGenres.contains(currGenre)){
            this.filmGenre = currGenre;
        }
        else {
            this.filmGenre = "empty";
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
