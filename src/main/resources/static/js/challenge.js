
 let editor;

	window.onload = function() {
	    editor = ace.edit("editor");
	    editor.setTheme("ace/theme/monokai");
	    editor.session.setMode("ace/mode/c_cpp");
	    codeEditor();
	}
    function execute(){
    	var language = $("#languages").val();
    	var code     = editor.getSession().getValue();
    	var questionId = $("#questionId").val();
    	console.log(language);
    	console.log(code);

    	if(language == 'java'){

    	 var d = { 'language': language, 'code': editor.getSession().getValue(), questionId: parseInt(questionId) };

    	 $.ajax({
    	        url: "/java-compiler-db-api",
    	        contentType: "application/json; charset=utf-8",
    	        dataType: "json",
    	        method: "POST",
    	        data: JSON.stringify(d),

    	      success: function(response) {
    	           //console.log(response.status);
    	           console.log(response.output);
    				$(".output1").text(response.output)
    	        },

    	      error: function(error){
    	          console.log(error);
    	        },

    	    });
    	  }
      if(language == 'python') {
    	  var d = { 'language': language, 'code': editor.getSession().getValue(), questionId: parseInt(questionId) };

     	 $.ajax({
     	        url: "/python-compiler-db-api",
     	        contentType: "application/json; charset=utf-8",
     	        dataType: "json",
     	        method: "POST",
     	        data: JSON.stringify(d),

     	      success: function(response) {
     	           //console.log(response.status);
     	           console.log(response.output);
     				$(".output1").text(response.output)
     	        },

     	      error: function(error){
     	          console.log(error);
     	        },

     	    });
     	  }
      if(language == 'cpp') {
    	  var d = { 'language': language, 'code': editor.getSession().getValue(), questionId: parseInt(questionId) };

      	 $.ajax({
      	        url: "/cpp-compiler-db-api",
      	        contentType: "application/json; charset=utf-8",
      	        dataType: "json",
      	        method: "POST",
      	        data: JSON.stringify(d),

      	      success: function(response) {
      	           //console.log(response.status);
      	           console.log(response.output);
      				$(".output1").text(response.output)
      	        },

      	      error: function(error){
      	          console.log(error);
      	        },

      	    });
        }
      if(language == 'c') {
    	  var d = { 'language': language, 'code': editor.getSession().getValue(), questionId: parseInt(questionId) };

      	 $.ajax({
      	        url: "/c-compiler-db-api",
      	        contentType: "application/json; charset=utf-8",
      	        dataType: "json",
      	        method: "POST",
      	        data: JSON.stringify(d),

      	      success: function(response) {
      	           //console.log(response.status);
      	           console.log(response.output);
      				$(".output1").text(response.output)
      	        },

      	      error: function(error){
      	          console.log(error);
      	        },

      	    });
      	  }
      }