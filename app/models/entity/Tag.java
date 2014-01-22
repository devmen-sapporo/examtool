package models.entity;

import java.util.*;

/**
 * タグ
 * @author A.Suzuki
 *
 */
public final class Tag {

	private int id;

	private String name;

	private Tag parent;

	private List<Tag> children = new ArrayList<>();

	/**
	 * コンストラクタです。
	 * @param id ID
	 * @param name 名前
	 */
	public Tag(int id, String name) {
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
	public Tag(int id, String name, Tag parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
		parent.addChildren(this);
	}

	/**
	 * IDを取得します。
	 * @return ID
	 */
	public int getId() {
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
	public List<Tag> getChildren() {
		return children;
	}

	private void addChildren(Tag child) {
		this.children.add(child);
	}

}
