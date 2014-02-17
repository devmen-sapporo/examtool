package controllers;

public class AnswerColumnChangeData {
	
	public AnswerColumnChangeData(){ }
	
	/**
	 * 選択した選択肢のインデックス
	 */
	public int selectedOptionIndex;
	
	/**
	 * 問題番号
	 */
	public int questionNo;
	
	// TODO: enum の使い方わからないので
	/**
	 * 進む or 戻る
	 * 0: 戻る
	 * 1: 進む
	 */
	public int nextOrPrev;
	
}
