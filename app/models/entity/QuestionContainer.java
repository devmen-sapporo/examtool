package models.entity;

import java.util.*;

/**
 * 問題コンテナ
 * @author Hiroyuki
 *
 */
public class QuestionContainer {
	/**
	 * 問題のリスト
	 */
	private List<Question> questions = new ArrayList<Question>();
	
	/**
	 * 既定の問題シート
	 */
	private List<QuestionSheet> questionSheets = new ArrayList<>();

	/**
	 * コンストラクタ
	 */
	public QuestionContainer() { 
		this.readQuestionFile();
		this.createDefaultQuestionSheet();
	}

	private void createDefaultQuestionSheet() {
		for(String examName : this.getAllExamName()) {
			QuestionSheet sheet = new QuestionSheet(this.getQuestions(examName));
			this.questionSheets.add(sheet);
		}
	}

	private List<Question> getQuestions(String examName) {
		List<Question> list = new ArrayList<>();
		
		for (Question question : this.questions) {
			if (question.getExamName().equals(examName)){
				list.add(question);
			}
		}
		
		return list;
	}
	
	/**
	 * 問題の件数を返します。
	 * @return
	 */
    public int size() {
    	return this.questions.size();
    }

	/**
	 * 問題データを書き込みます
	 */
//	public final void writeQuestionFile() {
//		Gson gson = new Gson();
//		// JSONファイルの書き出し
//		try (JsonWriter writer = 
//		     new JsonWriter(new BufferedWriter(new FileWriter("output2.json")))) {     
//		    // UserオブジェクトからJSONへの変換
//		    gson.toJson(this.questions, ArrayList.class, writer);
//		} catch (IOException ex) {
//		    ex.printStackTrace();
//		} 
//	}
	
	/**
	 * 問題データを読み込みます
	 * @return 問題のリスト
	 */
	void readQuestionFile() {
//		Gson gson = new Gson();
//		// JSONファイルからの読み込み
//	    String jsonFilePath = QuestionContainer.class.getClassLoader().getResource("Questions.json").getPath();
//		try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader(jsonFilePath)))) { 
//			// JSONからCollectionオブジェクトへの変換
//		    Type type = new TypeToken<ArrayList<Question>>() { } .getType();
//		    System.out.println(reader.peek());
//		    questions = gson.fromJson(reader, type);
//		} catch (IOException ex) {
//		    ex.printStackTrace();
//		}		
	}
	
	/**
	 * 問題データを追加します。
	 * @param question
	 */
	public void add(Question question) {
		this.questions.add(question);
	}

	/**
	 * 格納されているすべての試験名を取得します。
	 * @return
	 */
	public List<String> getAllExamName() {
		ArrayList<String> examNames = new ArrayList<>();

		for (Question question : this.questions) {
			String examName = question.getExamName();
			if (!examNames.contains(examName)){
				examNames.add(question.getExamName());
			}
		}

		return examNames;
	}
	
	/**
	 * 問題シートを取得します。
	 * @param examName 試験名
	 * @return 問題シート
	 */
	public QuestionSheet getQuestionSheet(String examName) {
		for (QuestionSheet questionSheet : this.questionSheets) {
			if (questionSheet.questions.get(0).getExamName().equals(examName)) {
				return questionSheet;
			}
		}
		
		return null;
	}
	

}
