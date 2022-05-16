let editor;

window.onload = function() {
    editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.session.setMode("ace/mode/c_cpp");
    codeEditor();
}

function changeLanguage() {

    let language = $("#languages").val(); 
    
     if(language=="python")
      editor.setValue("def execute(): \n\t for i in range(10):\n\t\t print(i) \nexecute()")
  //java
  if(language=="java"){

      let javacode = `public class Main{
  public static void main(String args[]){
    System.out.println("hello");
  }
}
`;

  editor.setValue(javacode)

  }if(language=="cpp"){
      let cppcode = `#include <iostream>
using namespace std;
  int main() {
      cout<<"Hello World"; \n
      return 0;\n
}`
      editor.setValue(cppcode)
  }if(language=="c"){
     let c = `#include <stdio.h>
  int main() {
       printf("Hello World"); \n
      return 0;\n
}`
      editor.setValue(c)	
  }
    
    
    if(language == 'c' || language == 'cpp')editor.session.setMode("ace/mode/c_cpp");
    else if(language == 'python')editor.session.setMode("ace/mode/python");
    else if(language == 'java')editor.session.setMode("ace/mode/java");
}



function executeCode(){
console.log($("#languages").val());
console.log(editor.getSession().getValue());

	var d = { 'language': $("#languages").val(), 'code': editor.getSession().getValue() };
	 $.ajax({
	        url: "/compiler",
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        method: "POST",
	        data: JSON.stringify(d),

          success: function(response) {
               //console.log(response.status);
               console.log(response.totalsent);
				$(".output").text(response.totalsent)
	        },

	      error: function(error){
	          console.log(error);
	        },

	    });
   }
   
   
function codeEditor(){
 let c = `#include <stdio.h>
  int main() {
       printf("Hello World"); \n
      return 0;\n
}`
      editor.setValue(c)	
}













