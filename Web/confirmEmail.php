<?php
header("Access-Control-Allow-Origin: *");

//Input: 	$email - a valid email
//			$date - a date in readable string form
//			$personName - name of the person being reminded
//			$description - short description about the service to be performed
//			$companyName - name of the company performing the service
//			$formId - ID of the form relating to the confirmation
//
//Output: 	1 if email was successfuly sent
//			0 if email was not sent
//Sample Call
//sendConfirmationEmail("hi@hi.com", "10/5/2016", "Guy", "kitchen install", "Kitchen Masters" ,"123213");

function sendConfirmationEmail($email, $date, $personName, $description, $companyName , $formId){
	if(!isset($email) || empty($email) ) {
		  echo '0';
	} else {
		$headers = "MIME-Version: 1.0" . "\r\n";
		$headers .= "Content-type:text/html;charset=UTF-8" . "\r\n";
		
		$email = strip_tags($email);
		
		$messageScript = "Hello " . $personName; 
		$messageScript .= ",<br/><br/>This email is to remind you about that ";
		$messageScript .= $description . " will be performed on " . $date;
		$messageScript .= ".<br/>Please click the link below to confirm or give us a call if you want to make any changes.<br/>" . $companyName;
		$messageScript .= "<br/><br/><a href='google.com?id=" . $formId ."'>CONFIRM</a>";
		
		$messageSubject = "Subject";
		
		echo mail($email, $messageSubject, $messageScript, $headers); 
	}
}
?>