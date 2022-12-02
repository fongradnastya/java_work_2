/**
 * Реализует энумератор для выбора команд в основном меню приложения
 */
interface MenuEnum {

    int ADD_MOVIE = 1,
            CHANGE_MOVIE = 2,
            PRINT_MOVIES = 3,
            SORT_MOVIES = 4,
            DELETE_MOVIE = 5,
            GROUP_MOVIES = 6,
            FILTER_MOVIES = 7,
            DELETE_DUPLICATES = 8,
            GET_STATISTIC = 9,
            TOTAL_BOX_OFFICE = 10,
            FIND_TOP = 11,
            EXIT = 12;

    int CHANGE_NAME = 1,
            CHANGE_GENRE = 2,
            CHANGE_RATING = 3,
            CHANGE_BOX_OFFICE = 4;


    int SORT_BY_NAME = 1,
            SORT_BY_GENRE = 2,
            SORT_BY_USER_RATING = 3,
            SORT_BY_BOX_OFFICE = 4,
            SORT_BY_COMMON_RATING = 5;

    int ADD_EMPTY = 1,
            ADD_WITH_PARAMETERS = 2;
}
