package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

/**
 * 
 * @author Hiroyuki
 *
 */
public class AnswerHistoryModelService implements ModelService<AnswerHistory> {

    public static AnswerHistoryModelService use() {
        return new AnswerHistoryModelService();
    }
    
    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<AnswerHistory> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, AnswerHistory> find = ModelUtil.getFinder(AnswerHistory.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<AnswerHistory>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<AnswerHistory> save(AnswerHistory entry) {
        Option<AnswerHistory> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<AnswerHistory>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<AnswerHistory>> findWithPage(Integer pageSource) {
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

