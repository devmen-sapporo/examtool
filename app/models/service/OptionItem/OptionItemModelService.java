package models.service.OptionItem;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

/**
 * Checkモデルのサービスクラス
 *
 * @author harakazuhiro
 * @since 2013/08/12 23:28
 */
public class OptionItemModelService implements ModelService<OptionItem> {

    public static OptionItemModelService use() {
        return new OptionItemModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<OptionItem> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, OptionItem> find = ModelUtil.getFinder(OptionItem.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<OptionItem>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<OptionItem> save(OptionItem entry) {
        Option<OptionItem> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.getId()).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<OptionItem>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<OptionItem>> findWithPage(Integer pageSource) {
//        Integer page                   = PageUtil.rightPage(pageSource);
//        Model.Finder<Long, OptionItem> find = ModelUtil.getFinder(OptionItem.class);
        return null;
    }

    /**
     * 最大ページ数を取得
     * @return
     */
    public Option<Integer> getMaxPage() {
//        Model.Finder<Long, Check> find = ModelUtil.getFinder(Check.class);
        return null;
    }

}
