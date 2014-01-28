package utils;

import play.db.ebean.Model;

/**
 * モデルのFinderユーティリティ
 *
 * @author harakazuhiro
 * @since 2013/08/14 23:09
 */
public class ModelUtil {

    public static <T> Model.Finder<Long, T> getFinder(Class<T> t) {
        return new Model.Finder<Long, T>(Long.class, t);
    }

}
