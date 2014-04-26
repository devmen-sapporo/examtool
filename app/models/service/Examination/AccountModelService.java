package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class AccountModelService implements ModelService<Account> {

    public static AccountModelService use() {
        return new AccountModelService();
    }

    /**
     * IDで検索
     * @param id
     * @return
     */
    @Override
    public Option<Account> findById(Long id) {
        Option<Long> idOps = OptionUtil.apply(id);
        if(idOps.isDefined()) {
            Model.Finder<Long, Account> find = ModelUtil.getFinder(Account.class);
            return OptionUtil.apply(find.byId(id));
        }
        return new None<Account>();
    }

    /**
     * 保存
     * @param entry
     * @return
     */
    @Override
    public Option<Account> save(Account entry) {
        Option<Account> entryOps = OptionUtil.apply(entry);
        if(entryOps.isDefined()) {
            entry.save();
            if(OptionUtil.apply(entry.id).isDefined()) {
                return OptionUtil.apply(entry);
            }
        }
        return new None<Account>();
    }

    /**
     * ページ番号で取得
     * @param pageSource
     * @return
     */
    @Override
    public Option<List<Account>> findWithPage(Integer pageSource) {
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

