public class Movie {
    private double averageUserRating;
    private long boxOffice;
    private String filmName;
    private String filmGenre;

    public Movie() {
        filmName = "movie";
    }

    public Movie(String filmName, double averageUserRating, long boxOffice, String filmGenre) {
        this.averageUserRating = averageUserRating;
        this.boxOffice = boxOffice;
        this.filmName = filmName;
        this.filmGenre = filmGenre;
    }

    public double getAverageUserRating() {
        return averageUserRating;
    }

    public void setAverageUserRating(double averageUserRating) {
        this.averageUserRating = averageUserRating;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(long boxOffice) {

        this.boxOffice = boxOffice;
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

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public double countCommonRating(){
        long maxBoxOffice = 1_000_000_000L;
        double cinemaRating = (double) (boxOffice) / (double) maxBoxOffice * 4;
        double rating = averageUserRating + cinemaRating;
        rating = (double) Math.round(rating * 10) / 10;
        if(rating < 5) return rating;
        return 5.0;
    }
}
