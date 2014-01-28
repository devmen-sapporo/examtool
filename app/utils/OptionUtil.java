package utils;

import play.libs.F;
import scala.*;
import scala.None;
import java.util.List;

/**
 * Option用ユーティリティクラス
 */
public class OptionUtil {

    public static <A> F.Option<A> apply(A value) {
        if(value != null) {
            return F.Option.Some(value);
        } else {
            return F.Option.None();
        }
    }

    public static <A> F.Option<List<A>> apply(List<A> value) {
        if(value != null && value.size() != 0) {
            return F.Option.Some(value);
        } else {
            return F.Option.None();
        }
    }

    public static <String> F.Option<String> applyWithString(String value) {
        if(value != null && !value.equals("")) {
            return F.Option.Some(value);
        } else {
            return F.Option.None();
        }
    }

    public static <T> F.None<T> none() {
        return new F.None<T>();
    }

    public static <T> Option<T> asScala(F.Option<T> value) {
        if(value.isDefined()) {
            return Option$.MODULE$.apply(value.get());
        } else {
            return Option$.MODULE$.empty();
        }
    }

}
