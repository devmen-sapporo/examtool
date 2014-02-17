package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class AnswerColumnModelService  implements ModelService<AnswerColumn> {

    public static AnswerColumnModelService use() {
        return new AnswerColumnModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<AnswerColumn> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, AnswerColumn> find = ModelUtil.getFinder(AnswerColumn.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<AnswerColumn>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<AnswerColumn> save(AnswerColumn entry) {
        Option<AnswerColumn> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<AnswerColumn>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<AnswerColumn>> findWithPage(Integer pageSource) {
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
