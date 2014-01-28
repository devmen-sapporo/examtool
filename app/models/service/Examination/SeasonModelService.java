package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class SeasonModelService implements ModelService<Season> {

    public static SeasonModelService use() {
        return new SeasonModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<Season> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, Season> find = ModelUtil.getFinder(Season.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<Season>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<Season> save(Season entry) {
        Option<Season> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<Season>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<Season>> findWithPage(Integer pageSource) {
        return null;
    }

    /**
     * 最大ページ数を取得
     * @return
     */
    public Option<Integer> getMaxPage() {
        return null;
    }

}
