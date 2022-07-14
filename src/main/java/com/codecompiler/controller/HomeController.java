package com.codecompiler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codecompiler.entity.Contest;
import com.codecompiler.entity.Question;
import com.codecompiler.entity.SampleTestCase;
import com.codecompiler.entity.TestCases;
import com.codecompiler.service.CommonService;
import com.codecompiler.service.ContestService;

@Controller
public class HomeController {
	
	@Autowired
	private CommonService commonService;	
	@Autowired
	private ContestService contestService;
	
	public String contestId="";
	public String contestLevel="";
	
	SampleTestCase sampleTestCase = new SampleTestCase();
	
	int questionID;
	
	Question q = new Question();
	
	List<Question> allQuestionsspecificcontest = new ArrayList<>();
	
	@RequestMapping("/adminhome")
	public String adminHome(Model model){
		List<Contest> con=new ArrayList<>();
		con = contestService.getAllContest();
		model.addAttribute("contest",con);		
		return "adminHome";		
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<Question> question = commonService.getQuestionFromDataBase("40");
		Question localQuestion = new Question();
		List <SampleTestCase> sampleTestCase = new ArrayList<>();
		List<TestCases> testCasesList = new ArrayList<>();
		for (Question q : question) {
			sampleTestCase = q.getSampleTestCase();
			testCasesList = q.getTestcases();
			localQuestion = q;
		}
		SampleTestCase localSampleTestCase = new SampleTestCase();
		
		 for(SampleTestCase s : sampleTestCase) localSampleTestCase = s; 
		 
        System.out.println("Que. 1 => "+localQuestion);
		System.out.println("Que. 2 => "+localSampleTestCase);

		model.addAttribute("question",localQuestion);
		model.addAttribute("stc",localSampleTestCase);
		
		return "compilerIDE";
	}

	@RequestMapping("/contest") 
	private String addContest() {
	    return "contest";
	}
		
	@RequestMapping("/studenttestscreen") 
	private String studentTestScreen() {
	    return "studentTestScreen";
	}

	@RequestMapping("/contestlist") 
	private String contestList(Model model) {
		List<Contest> contestList = contestService.getAllContest();
		model.addAttribute("contestList", contestList);		
		return "listOfContest";
	}
	
	@RequestMapping("/findcontest") 
	private ResponseEntity<Contest> findContest(@RequestBody Contest contest,Model model) {
		System.out.println(contest.getContestId());		
		System.out.println(contest.getContestLevel());
		Contest con=new Contest();
		con = contestService.getContestBasedOnLevel(contest.getContestId(), contest.getContestLevel());		
			System.out.println(con);
			if(con!=null) {
				contestId = con.getContestId();
				contestLevel = con.getContestLevel();
			}
				return ResponseEntity.ok(con);
	}
	
	@RequestMapping("/returnquestionlistforspecificcontest")
	public String QuestionsList(Model model) {				
		ArrayList<Question> allQuestionsspecificcontest = new ArrayList<>();
		System.out.println(">>>"+contestId+"   "+contestLevel);
		//if(contestId.equals("") && contestLevel.equals(""))
		  allQuestionsspecificcontest = commonService.findByContestIdAndContestLevel(contestId, contestLevel);
		//System.out.println(">>>"+allQuestionsspecificcontest);
		model.addAttribute("contestId",contestId);
		model.addAttribute("contestLevel",contestLevel);
		model.addAttribute("questions", allQuestionsspecificcontest);
		return "questionListAndAddNewQuestion";
	}
	
		
	@RequestMapping("/addcontest") 
	private ResponseEntity<Contest> addContest(@RequestBody Contest contest,Model model) {
		System.out.println(contest.getContestName());
		System.out.println(contest.getContestDescription());
		System.out.println(contest.getContestLevel());
		Contest contestToSave = new Contest();
		contestToSave.setContestName(contest.getContestName());
		contestToSave.setContestDescription(contest.getContestDescription());
		contestToSave.setContestLevel(contest.getContestLevel());
		Contest con = contestService.saveContest(contest);
		System.out.println("con : "+con);
		//Contest allContest = 
				return ResponseEntity.ok(con);
	}
	
	@PostMapping("/savequestion")
	public ResponseEntity<String> saveQuestion(@RequestBody Question question, Model model) throws IOException {
        System.out.println("Obj : "+question);
        System.out.println("Obj q id : "+question.getQuestionId());
        String tempQid = question.getQuestionId();
        if(tempQid == ("")) {
        	System.out.println(" inside if condition ");
        question.setQuestionId(UUID.randomUUID().toString());
        }
        //System.out.println(">>>"+commonService.getAllQuestionFromDataBase());
        model.addAttribute("questions",commonService.getAllQuestionFromDataBase());
        commonService.saveUpdatedQuestion(question);
		return ResponseEntity.ok("");
	}

	@RequestMapping("/deletequestion") 
	private ResponseEntity<String> deleteQuestion(@RequestBody String questionId) {
		System.out.println("delete.........."+questionId);
		System.out.println(commonService.deleteQuestion(questionId));
				return ResponseEntity.ok("Done");
	}
	
	@RequestMapping("/getsavedquestion") 
	private ResponseEntity<Question> getSavedQuestion(@RequestBody String questionId) {
		questionId = questionId.subSequence(1, questionId.length()-1).toString();
		System.out.println("1 =>"+questionId);
		List<Question>  question = commonService.getQuestionFromDataBase(questionId);
		Question questionObj = new Question();
		for (Question q : question) {
			questionObj = q;
		}
		System.out.println("2 =>"+questionObj);
	
				return ResponseEntity.ok(questionObj);
	}
	
	
	@PostMapping("/add-test-cases-api") 
	public ResponseEntity<Question> saveTestCases(@RequestBody ArrayList<TestCases> testCasesobject,Model model) {
		//TestCases test = new TestCases();
		List <TestCases> test = new ArrayList<>();
		int testCaseId = 1;
		for(TestCases testCase : testCasesobject) {
			System.out.println("it is qid from FE : "+testCase.getId());
			testCase.setId(testCaseId++);			
			System.out.println(testCase.getInput());
			System.out.println(testCase.getOutput());
			test.add(testCase);
		}
		q.setTestcases(testCasesobject);
		System.out.println(q);
		 Question qsave=commonService.saveQuestion(q);		
		return ResponseEntity.ok(qsave);
	}
	
	@RequestMapping("/questions")
	public String redirectToQuestionsList(Model model) {
		List<Question> allQuestions = commonService.getAllQuestionFromDataBase();
		model.addAttribute("question", allQuestions);
		return "questions";
	}
	
	@RequestMapping("/viewparticipators") 
	private String viewParticipators() {
	    return "participators";
	}
	
	@RequestMapping("/participatordetail") 
	private String participatorDetail() {
	    return "participatorDetail";
	}
	
	@RequestMapping("/compiler") 
	private String IDECompiler() {
	    return "IDECompiler";
	}
	
	@RequestMapping("/level1questions") 
	private String level1Questions() {
	    return "level1Questions";
	}
	
	@RequestMapping("/level2questions") 
	private String level2Questions() {
	    return "level2Questions";
	}
	
	@RequestMapping("/allquestions") 
	private String allQuestions() {
	    return "allQuestions";
	}
	
	
	@RequestMapping("/idforquestiondetail")
	public ResponseEntity<Integer> idForQuestionDetail(@RequestBody int quesionId) {
		System.out.println("idforquestiondetail BE"+quesionId);
		questionID = quesionId;
		return ResponseEntity.ok(questionID);
	}
	
	@RequestMapping("/deletecontest") 
	private void deleteContest(String contestId) {
		System.out.println("@@@@@@@@"+contestId);
		contestService.deleteContest(contestId);
	}
	
//	IDECompiler.html
//	@RequestMapping("/questiondetail")
//	public String questionDetail(Model model) {
//		List<Question> question = commonService.getQuestionFromDataBase(questionID);
//		Question localQuestion = new Question();
//		List <SampleTestCase> sampleTestCase = new ArrayList<>();
//		List<TestCases> testCasesList = new ArrayList<>();
//		for (Question q : question) {
//			sampleTestCase = q.getSampleTestCase();
//			testCasesList = q.getTestcases();
//			localQuestion = q;
//		}
//		SampleTestCase localSampleTestCase = new SampleTestCase();
//		for(SampleTestCase s : sampleTestCase){
//			localSampleTestCase = s;			
//		}	
//		System.out.println("outside for each loop : "+localQuestion);
//		model.addAttribute("question",localQuestion);
//		model.addAttribute("stc",localSampleTestCase);		
//		model.addAttribute("tc",testCasesList);
//		return "QuestionDetail";
//	}
//	
//	@RequestMapping("/questionlistforspecificcontest")
//	public ResponseEntity<List<Question>> questionsListOfContest(@RequestBody String contestId, Model model) {
//		allQuestionsspecificcontest = commonService.getQuestionByContestId(contestId.substring(1, contestId.length()-1));
//		System.out.println("question list : "+allQuestionsspecificcontest);
//		model.addAttribute("question", allQuestionsspecificcontest);
//		return ResponseEntity.ok(allQuestionsspecificcontest);
//	}
//	
//	
//	
//	@RequestMapping("/editquestiondetails")
//	public String editQuestions(Model model) {
//		List<Question> question = commonService.getQuestionFromDataBase(questionID);
//		Question localQuestion = new Question();
//		List <SampleTestCase> sampleTestCase = new ArrayList<>();
//		List<TestCases> testCasesList = new ArrayList<>();
//		for (Question q : question) {
//			sampleTestCase = q.getSampleTestCase();
//			testCasesList = q.getTestcases();
//			localQuestion = q;
//		}
//		SampleTestCase localSampleTestCase = new SampleTestCase();
//		for(SampleTestCase s : sampleTestCase){
//			localSampleTestCase = s;			
//		}			
//		model.addAttribute("question",localQuestion);
//		model.addAttribute("stc",localSampleTestCase);		
//		model.addAttribute("tc",testCasesList);
//        return "editQuestion";
//	}
//	
//	@PostMapping("/addupdatedquestion")
//	public ResponseEntity<String> addUpdatedQuestion(@RequestBody Question q) {
//		commonService.saveQuestion(q);		
//		return ResponseEntity.ok("ok");
//	}	
//	
//	
//	
//	
//	
//	@GetMapping("/getquestion/{questionId}")
//	public ResponseEntity<List<TestCases>> getQuestion(@PathVariable int questionId) {
//		List<Question> question = commonService.getQuestionFromDataBase(questionId);
//		//System.out.println(question);
//		List<TestCases> testCasesCollection = null;
//		for (Question q : question) {
//			testCasesCollection = q.getTestcases();
//		}
//		for (TestCases tastCases : testCasesCollection) {			
//           System.out.println(tastCases.getOutput());
//		}
//		return ResponseEntity.ok(testCasesCollection);		
//	}
}


	

