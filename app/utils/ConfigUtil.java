package utils;

import play.Play;
import java.util.List;
import static play.libs.F.*;

/**
 * Play config系のユーティリティ
 *
 * @author harakazuhiro
 * @since 2013/08/14 23:02
 */
public class ConfigUtil {

    /**
     * Configから取得
     *
     * @param key
     * @return
     */
    public static Option<String> get(String key) {
        return OptionUtil.apply(Play.application().configuration().getString(key));
    }

    /**
     * Configからリスト形式で取得
     * @param key
     * @return
     */
    public static Option<List<String>> getByList(String key) {
        return OptionUtil.apply(Play.application().configuration().getStringList(key));
    }

}
