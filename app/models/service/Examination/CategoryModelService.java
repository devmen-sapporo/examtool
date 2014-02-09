package models.service.Examination;

import java.util.*;

import models.entity.*;
import models.service.Model.*;
import play.db.ebean.*;
import play.libs.F.None;
import play.libs.F.Option;
import utils.*;

public class CategoryModelService implements ModelService<Category> {

	    public static CategoryModelService use() {
	        return new CategoryModelService();
	    }

	    /**
	     * IDで検索
	     * @param id
	     * @return
	     */
	    @Override
	    public Option<Category> findById(Long id) {
	        Option<Long> idOps = OptionUtil.apply(id);
	        if(idOps.isDefined()) {
	            Model.Finder<Long, Category> find = ModelUtil.getFinder(Category.class);
	            return OptionUtil.apply(find.byId(id));
	        }
	        return new None<Category>();
	    }

	    /**
	     * 保存
	     * @param entry
	     * @return
	     */
	    @Override
	    public Option<Category> save(Category entry) {
	        Option<Category> entryOps = OptionUtil.apply(entry);
	        if(entryOps.isDefined()) {
	            entry.save();
	            if(OptionUtil.apply(entry.id).isDefined()) {
	                return OptionUtil.apply(entry);
	            }
	        }
	        return new None<Category>();
	    }

	    /**
	     * ページ番号で取得
	     * @param pageSource
	     * @return
	     */
	    @Override
	    public Option<List<Category>> findWithPage(Integer pageSource) {
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
