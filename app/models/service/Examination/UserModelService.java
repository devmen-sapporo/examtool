package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class UserModelService implements ModelService<User> {

    public static UserModelService use() {
        return new UserModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<User> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, User> find = ModelUtil.getFinder(User.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<User>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<User> save(User entry) {
        Option<User> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<User>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<User>> findWithPage(Integer pageSource) {
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

