package models.service.OptionItem;

import utils.ConfigUtil;
import utils.OptionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static play.libs.F.*;

/**
 * 診断用サービスクラス
 *
 * @author harakazuhiro
 * @since 2013/08/12 23:28
 */
public class OptionItemService {

    /**
     *
     * @param name
     * @return
     */
    public Option<String> getResult(String name) {
        List<String> versions = ConfigUtil.getByList("checkyou.setting.answer").getOrElse(new ArrayList<String>(){{add("2.1.3 Java");}});
        Collections.shuffle(versions);
        return getResultText(name, versions.get(0));
    }

    /**
     *
     * @param name
     * @param version
     * @return
     */
    public Option<String> getResultText(String name, String version) {
        StringBuilder result = new StringBuilder();
        result.append(name);
        result.append(ConfigUtil.get("checkyou.setting.message.result").getOrElse("-"));
        result.append(version);
        result.append(ConfigUtil.get("checkyou.setting.message.resultSuffix").getOrElse("."));
        return OptionUtil.apply(result.toString());
    }

}
