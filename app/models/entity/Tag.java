package models.entity;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

/**
 * タグ
 * @author A.Suzuki
 *
 */
@Entity
public final class Tag extends Model{

	@Id
	public String id;

	public String name;

	@ManyToOne
	public Tag parent;

	@OneToMany
	public List<Tag> childrens = new ArrayList<>();

	/**
	 * コンストラクタ
	 */
	public Tag(String id) {
		this.id = id;
	}

	/**
	 * コンストラクタです。
	 * @param id ID
	 * @param name 名前
	 */
	public Tag(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * コンストラクタです。
	 * @param id ID
	 * @param name 名前
	 * @param parent 親タグ
	 */
	public Tag(String id, String name, Tag parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}

	/**
	 * IDを取得します。
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 名前を取得します。
	 * @return 名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * トップレベルのタグかどうか判別します。
	 * @return トップレベルの場合にtrueを返します。
	 */
	public boolean isTopLevel() {
		return (this.parent == null);
	}

	/**
	 * 親タグを取得します。
	 * @return 親タグ
	 */
	public Tag getParent() {
		return this.parent;
	}

	/**
	 * 子どもタグを取得します。
	 * @return
	 */
	public List<Tag> getChildrens() {
		return childrens;
	}

	private void addChildren(Tag child) {
		this.childrens.add(child);
	}

}
