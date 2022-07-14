package com.codecompiler.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.codecompiler.dao.QuestionRepository;
import com.codecompiler.entity.Question;
import com.codecompiler.entity.SampleTestCase;
import com.codecompiler.entity.TestCases;

@Service
public class CommonService {

	@Autowired private QuestionRepository questionRepository;
	
	@Autowired private MongoTemplate mongoTemplate;
	//@Autowired private BinaryDataController binaryDataController;
	public Question saveQuestion(Question question) {		
		return questionRepository.save(question);		
	}
	
	public void saveUpdatedQuestion(Question question) {
		System.out.println("Commaon service : "+question);
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(question.getQuestionId()));
		Question q = mongoTemplate.findOne(query, Question.class);
		mongoTemplate.save(question);
		System.out.println("**** >"+q);
		
	}
	
	public int numberOfQuestionsCount() {
		return (int) questionRepository.count();
		
	}
		
	public List<Question> getQuestionFromDataBase(String questionId) {
		List<Question> question= questionRepository.findByQuestionId(questionId);
		return question;
	}

	public List<Question> getAllQuestionFromDataBase() {
		List<Question> question = questionRepository.findAll();		
		return question;
	}

	
	public List<TestCases> getTestCase(String questionId){
		List<Question>  question= getQuestionFromDataBase(questionId);	
		List<TestCases> testCasesCollection = null;
		for (Question q : question) {
			//testCasesCollection.addAll(q.getTestcases());
			testCasesCollection = q.getTestcases();
			System.out.println(testCasesCollection);
		}
		return testCasesCollection;
	}
	public List<SampleTestCase> getSampleTestCase(String questionId){
		List<Question>  question= getQuestionFromDataBase(questionId);	
		
		List<SampleTestCase> sampleTestCaseCollection = null;
		for (Question q : question) {
			sampleTestCaseCollection = q.getSampleTestCase();
			System.out.println(sampleTestCaseCollection);
		}
		return sampleTestCaseCollection;
	}
	
	public ArrayList<Question> getQuestionByContestId(String contestId) {
		System.out.println("String  : "+contestId);
		ArrayList<Question> question = questionRepository.findByContestId(contestId);
        System.out.println("q   :    "+question);
		return question;
	}
	
	public ArrayList<Question>  findByContestIdAndContestLevel(String contestId, String contestLevel){
		System.out.println("CID  : "+contestId+"cLevel"+contestLevel);
		ArrayList<Question> question = questionRepository.findByContestIdAndContestLevel(contestId, contestLevel);
       // System.out.println("qList  :    "+question);        
		return question;
	}
	
	public String deleteQuestion(String questionId){
		questionId=questionId.subSequence(1, questionId.length()-1).toString();
		questionRepository.deleteByQuestionId(questionId);
		return "Deleting....";
	}
		
	
// Currently not using :-	
//	public void storeFileToFolder(String studentId, String code) throws IOException {
//		System.out.println("ID => :"+studentId);
//		FileWriter fl = new FileWriter(
//				"C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\" + studentId + "." + "txt");
//		PrintWriter pr = new PrintWriter(fl);
//		pr.write((String) code);
//		
//		System.out.println("id of file : => "+binaryDataController.saveFile(studentId));
//		pr.flush();
//		pr.close();
//	//	deleteFileFromFolder(studentId);
//	}
	
	
//	public Boolean deleteFileFromFolder(String fileName) throws IOException {
//		System.out.println("going to delete =>  ....");
//		System.out.println(fileName.getClass());
//		File f= new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+fileName+".txt");		
//		return f.delete(); 
//	}
	

	
}

//	@PostMapping("/savequestion")
//	public ResponseEntity<Question> saveQuestion() throws IOException {
//		List<TestCases> testCases = new ArrayList<>();		
//		String table = ""; 
//		table="2" + "\r\n" + "4"+ "\r\n" + "6"+ "\r\n" + "8"+ "\r\n" + "10"+ "\r\n" + "12"+ "\r\n" + "14"+ "\r\n" + "16"+ "\r\n" + "18"+ "\r\n" + "20";		
//		TestCases t1=new TestCases(1,"2",table);
//		testCases.add(t1);
//		table="3" + "\r\n" + "6"+ "\r\n" + "9"+ "\r\n" + "12"+ "\r\n" + "15"+ "\r\n" + "18"+ "\r\n" + "21"+ "\r\n" + "24"+ "\r\n" + "27"+ "\r\n" + "30";		
//		TestCases t2=new TestCases(2,"3",table);
//        testCases.add(t2);
//       table="4" + "\r\n" + "8"+ "\r\n" + "12"+ "\r\n" + "16"+ "\r\n" + "20"+ "\r\n" + "24"+ "\r\n" + "28"+ "\r\n" + "32"+ "\r\n" + "36"+ "\r\n" + "40";		
//		TestCases t3=new TestCases(3,"4",table);
//        testCases.add(t3); 
//        
//        File inputFile = new File("C:\\Users\\Public\\Montrix\\CodeCompiler\\src\\main\\resources\\temp\\"+"output.txt");	
//		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
//		table = ""; 
//		String input = "";
//		while((input = inputReader.readLine()) != null){
//			table=table+"\r\n"+input;
//			}
//		table=table.substring(2,table.length());
//		TestCases t4=new TestCases(4,"5",table);
//        testCases.add(t4); 
//		//System.out.println(table);
//        
//        Question question = new Question(7,"Write a program to print table ?",testCases);
//		saveQuestion(question);
//		return ResponseEntity.ok(question);
//	}
//	
//	@GetMapping("/getquestion/{questionId}")
//	public ResponseEntity<List<TestCases>> getQuestion(@PathVariable int questionId) {
//		List<Question> question = getQuestionFromDataBase(questionId);
//		//System.out.println(question);
//		List<TestCases> testCasesCollection = null;
//		for (Question q : question) {
//			testCasesCollection = q.getTestcases();
//		}
//		for (TestCases tastCases : testCasesCollection) {			
//           System.out.println(tastCases.getOutput());
//		}
//		return ResponseEntity.ok(testCasesCollection);
//		
//	}
//}
//
