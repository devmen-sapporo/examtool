package models.service.Model;

import play.db.ebean.Model;

import java.util.List;

import static play.libs.F.*;

/**
 * モデル向けサービスのインターフェース
 *
 * @author harakazuhiro
 * @since 2013/08/14 21:14
 */
public interface ModelService<T extends Model> {

    public Option<T> findById(Long id);
    public Option<T> save(T entry);
    public Option<List<T>> findWithPage(Integer pageSource);

}
