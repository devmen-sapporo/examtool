package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class QuestionModelService implements ModelService<Question> {

    public static QuestionModelService use() {
        return new QuestionModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<Question> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, Question> find = ModelUtil.getFinder(Question.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<Question>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<Question> save(Question entry) {
        Option<Question> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<Question>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<Question>> findWithPage(Integer pageSource) {
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

