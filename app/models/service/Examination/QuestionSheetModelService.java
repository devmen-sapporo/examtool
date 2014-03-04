package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class QuestionSheetModelService implements ModelService<QuestionSheet> {

    public static QuestionSheetModelService use() {
        return new QuestionSheetModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<QuestionSheet> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, QuestionSheet> find = ModelUtil.getFinder(QuestionSheet.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<QuestionSheet>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<QuestionSheet> save(QuestionSheet entry) {
        Option<QuestionSheet> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<QuestionSheet>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<QuestionSheet>> findWithPage(Integer pageSource) {
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

