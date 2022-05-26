let editor;

window.onload = function() {
	editor = ace.edit("editor");
	editor.setTheme("ace/theme/monokai");
	editor.session.setMode("ace/mode/c_cpp");
	codeEditor();
}
function codeEditor() {
jQuery.get('js/sampleCProgram.txt', function(txt) {
				editor.setValue(txt)
			});
}

function changeLanguage() {

	let language = $("#languages").val();
    console.log(language);
	if (language == "python") {
		console.log("python");
		jQuery.get('js/samplePythonProgram.txt', function(txt) {
			editor.setValue(txt);
		
			});
		}
    if (language == "java") {
    console.log("java");
			jQuery.get('js/sampleJavaProgram.txt', function(txt) {
				editor.setValue(txt)
				
			});
		}
		if(language =="cpp"){
	console.log("CPP");
			jQuery.get('js/sampleCPPProgram.txt', function(txt) {
				editor.setValue(txt)
			});
		}
		 if ((language) =="c"){
	      console.log("C");
			jQuery.get('js/sampleCProgram.txt', function(txt) {
				editor.setValue(txt)
			});
		}
		
		if (language == 'c' || language == 'cpp') editor.session.setMode("ace/mode/c_cpp");
		else if (language == 'python') editor.session.setMode("ace/mode/python");
		else if ((language) == 'java') editor.session.setMode("ace/mode/java");
	}



	function executeCode() {
	
		let language = $("#languages").val();
		if (language == 'c' ) {
			executeCodeOfc()
		}
		if (language == 'cpp'){
			executeCodeOfcpp()
	}
	if (language == 'java' ){
       executeCodeOfjava();
    }
    if (language == 'python'){
	   executeCodeofpython()
    }
}   

function executeCodeOfc() {
	console.log($("#languages").val());
	console.log(editor.getSession().getValue());
	var questionId = 3;
	var d = { 'language': $("#languages").val(), 'code': editor.getSession().getValue(), 'questionId': questionId };
	$.ajax({
		url: "/ccompiler",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		method: "POST",
		data: JSON.stringify(d),
		success: function(response) {
			//console.log(response.status);
			console.log(response.totalsent);
			$(".output").text(response.totalSent)
		},
		error: function(error){
	          console.log(error);
	},
	    });
}

function executeCodeOfcpp(){
	console.log($("#languages").val());
	console.log(editor.getSession().getValue());
	var questionId = 2;
	var d = { 'language': $("#languages").val(), 'code': editor.getSession().getValue(), 'questionId': questionId };
	$.ajax({
		url: "/cppcompiler",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		method: "POST",
		data: JSON.stringify(d),
		success: function(response) {
			//console.log(response.status);
			console.log(response.totalSent);
			$(".output").text(response.totalSent)
		},
		error: function(error){
	          console.log(error);
	},
	    });
   }

function executeCodeOfjava() {
	console.log($("#languages").val());
	console.log(editor.getSession().getValue());
	var questionId = 3;
	var d = { 'language': $("#languages").val(), 'code': editor.getSession().getValue(), 'questionId': questionId };
	$.ajax({
		url: "/javacompiler",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		method: "POST",
		data: JSON.stringify(d),
		success: function(response) {
			//console.log(response.status);
			console.log(response.totalSent);
			$(".output").text(response.totalSent)
		},
		error: function(error) {
			console.log(error);
		},
	});
} 
function executeCodeofpython(){
	console.log($("#languages").val());
	var questionId = 2;
	console.log(editor.getSession().getValue());
	var d = { 'language': $("#languages").val(), 'code': editor.getSession().getValue() };
	$.ajax({
		url: "/pythoncompiler",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		method: "POST",
		data: JSON.stringify(d),
		success: function(response) {
			//console.log(response.status);
			console.log(response.totalsent);
			$(".output").text(response.totalSent)
		},
		error: function(error) {
			console.log(error);
		},
	});
}














