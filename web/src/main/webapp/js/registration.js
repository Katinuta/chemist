
			function addfield() {
				var butAdd = document.getElementById("addfields");
			    var button = document.createElement("input");
			    button.type="button";
			    var emailDiv = document.getElementById("emailDiv");
			    var newEmailDiv=emailDiv.cloneNode(true);
				button.value="Delete e-mail";
			    button.addEventListener("click",deleteField);
			  	newEmailDiv.appendChild(button);
			  	var label=newEmailDiv.querySelector("label");
			  	label.style.top="-10%";
			   	document.getElementById("myform").insertBefore(newEmailDiv,butAdd);
			}

			function deleteField() {
				var currentDiv=this.parentNode;
				currentDiv.parentNode.removeChild(currentDiv);
			}

			function checkPasswords(){
				validate();
				var password=inputPassword.value;
				var repPassword=document.getElementById("repPassword").value;
				if(password!=repPassword){
					document.getElementById("labelRepPassword").classList.add("error");
				}else{
					document.getElementById("labelRepPassword").classList.remove("error");
				}
			}

			
		   function validate(){

			   var input=document.querySelector("input:focus");
			   if(input.type=="text"&&input.name!="login"){
                   input.value=input.value.toUpperCase();

			   }

			   if(!input.validity.valid){
				   document.querySelector("input:focus ~ span.helper").classList.add("error");
				   document.querySelector("input:focus ~ label").classList.add("invalid");
				   if(input.value==""){
					   document.querySelector("input:focus ~ span.helper").classList.remove("error");
					   document.querySelector("input:focus ~ label").classList.remove("invalid");
				   }
			   }else{
				   document.querySelector("input:focus ~ span.helper").classList.remove("error");
			   }
			    
			}

		    var inputPassword=document.getElementById("password");
		    