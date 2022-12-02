import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Objects;

/**
 * Класс Movie реализует хранение информации о фильме и методы её изменения
 */
public class Movie implements Comparable{
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
                "filmName='" + filmName + '\'' +
                ", filmGenre='" + filmGenre + '\'' +
                ", averageUserRating=" + averageUserRating +
                ", boxOffice=" + boxOffice + "$" +
                ", commonRating=" + countCommonRating() +
                '}';
    }

    /**
     * Проверяет, являются ли объекты равными друг другу
     * @param o объект для сравнения
     * @return результат проверки на равенство
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Double.compare(movie.getAverageUserRating(), getAverageUserRating()) == 0 &&
                getBoxOffice() == movie.getBoxOffice() && getFilmName().equals(movie.getFilmName()) &&
                getFilmGenre().equals(movie.getFilmGenre());
    }

    /**
     * Вычисляет хеш код для экземпляра данного класса
     * @return числовое значение хеша
     */
    @Override
    public int hashCode() {
        return Objects.hash(getAverageUserRating(), getBoxOffice(), getFilmName(), getFilmGenre());
    }

    /**
     * Сравнивает 2 фильма между собой по их пользовательскому рейтингу
     * @param o объект для сравнения
     * @return результат сравнения
     */
    @Override
    public int compareTo(@NotNull Object o) {
        if(o instanceof Movie){
            Movie movie = (Movie) o;
            return Double.compare(getAverageUserRating(), movie.getAverageUserRating());
        }
        else{
            throw new IllegalArgumentException("Impossible to compare objects");
        }
    }
}
