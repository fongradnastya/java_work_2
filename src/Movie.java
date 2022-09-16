import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

/**
 * Класс Movie реализует хранение информации о фильме и методы её изменения
 */
public class Movie {
    /**
     * Средняя оценка фильма пользователями
     */
    private double averageUserRating;

    /**
     * Кассовые сборы фильма
     */
    private long boxOffice;

    /**
     * Название фильма
     */
    private String filmName;

    /**
     * Жанр фильма
     */
    private String filmGenre;

    /**
     * Список допустимых жанров фильма
     */
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
     * Получение доступа к списку возможных жанров фильма
     * @return movieGenres - жанр фильма
     */
    public static ArrayList<String> getAvailableGenres(){
        return movieGenres;
    }

    /**
     * Получение доступа к средней оценке пользователей
     * @return averageUserRating - средняя оценка
     */
    public double getAverageUserRating() {
        return averageUserRating;
    }

    /**
     * Устанавливает значение средней оценки фильма пользователями
     * @param averageUserRating - средняя оценка фильма пользователями
     */
    public void setAverageUserRating(double averageUserRating) {
        if(averageUserRating >= 0 && averageUserRating <= 5)
            this.averageUserRating = averageUserRating;
    }
    /**
     * Получение доступа к кассовым сборам фильма
     * @return boxOffice - кассовые сборы
     */
    public long getBoxOffice() {
        return boxOffice;
    }

    /**
     * Устанавливает значение кассовых сборов фильма
     * @param boxOffice - кассовый сбор фильма
     */
    public void setBoxOffice(long boxOffice) {
        if(boxOffice >= 0){
            this.boxOffice = boxOffice;
        }

    }

    /**
     * Получение доступа к названию фильма
     * @return filmName - название фильма
     */
    public String getFilmName() {
        return filmName;
    }

    /**
     * Устанавливает значение для имени фильма
     * @param filmName - название нового фильма
     */
    public void setFilmName(String filmName) {
        if(!filmName.isEmpty() && !filmName.equals("")){
            this.filmName = filmName;
        }
    }

    /**
     * Получение доступа к рейтингу фильма
     * @return filmGenre - рейтинг фильма
     */
    public String getFilmGenre() {
        return filmGenre;
    }

    /**
     * Устанавливает значение жанра фильма в одно из допустимых
     * @param currGenre - жанр текущего фильма
     */
    public void setFilmGenre(String currGenre) {
        if(!movieGenres.isEmpty() && movieGenres.contains(currGenre)){
            this.filmGenre = currGenre;
        }
    }

    /**
     * Вычисляет финальный рейтинг фильма, учитывая оценки пользователей и кассовые сборы
     * @return rating - дробное значение в промежутке от 0 до 5
     */
    public double countCommonRating(){
        final long MAX_VALUE = 1_000_000_000L;
        final long INCREASE_FACTOR = 4;
        double cinemaRating = (double) (boxOffice) / (double) MAX_VALUE * INCREASE_FACTOR;
        double rating = averageUserRating + cinemaRating;
        if(rating < 5) return rating;
        return 5.0;
    }

    /**
     * Формирует строку с данными о фильме
     * @return строковое представление фильма
     */
    @Override
    public String toString() {
        return "Movie{" +
                "averageUserRating=" + averageUserRating +
                ", boxOffice=" + boxOffice +
                ", filmName='" + filmName + '\'' +
                ", filmGenre='" + filmGenre + '\'' +
                '}';
    }
}
