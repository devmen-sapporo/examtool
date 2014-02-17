package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class AnswerSheetModelService  implements ModelService<AnswerSheet> {

    public static AnswerSheetModelService use() {
        return new AnswerSheetModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<AnswerSheet> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, AnswerSheet> find = ModelUtil.getFinder(AnswerSheet.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<AnswerSheet>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<AnswerSheet> save(AnswerSheet entry) {
        Option<AnswerSheet> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<AnswerSheet>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<AnswerSheet>> findWithPage(Integer pageSource) {
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
