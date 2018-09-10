<?php
header("Access-Control-Allow-Origin: *");
require 'PHPMailer/PHPMailerAutoload.php';

//Input: 	$email - a valid email
//			$date - a date in readable string form
//			$personName - name of the person being reminded
//			$description - short description about the service to be performed
//			$companyName - name of the company performing the service
//			$pdfPath - the url of the pdf invoiceNumber
//			$pdfFileName - the file name of the pdf, e.g. invoice.pdf
//
//Output: 	1 if email was successfuly sent
//			0 if email was not sent
//Sample Call
echo sendInvoice("gen.cfw@gmail.com", "10/5/2016", "Guy", "kitchen install", "Kitchen Masters" , "https://s3.amazonaws.com/tupledev-s3sample/invoice.pdf", "invoice.pdf");

function sendInvoice($email, $date, $personName, $description, $companyName , $pdfPath, $pdfFileName){
	if(!isset($email) || empty($email) ) {
		  return '0';
	} else {
		
		$email = strip_tags($email);
		
		$messageScript = "Hello " . $personName; 
		$messageScript .= ",<br/><br/>Attached to this email is a copy of the invoice for the ";
		$messageScript .= $description . " performed on " . $date;
		$messageScript .= ".<br/>We hope you had a good experience.<br/>" . $companyName;
		
		$messageSubject = "Invoice for " . $companyName;

		$mail = new PHPMailer(); // create a new object
		$mail->IsSMTP(); // enable SMTP
		$mail->SMTPDebug = 0; // debugging: 1 = errors and messages, 2 = messages only
		$mail->SMTPAuth = true; // authentication enabled
		$mail->SMTPSecure = 'ssl'; // secure transfer enabled REQUIRED for Gmail
		$mail->Host = "smtp.gmail.com";
		$mail->Port = 465; // or 587
		$mail->IsHTML(true);
		$mail->Username = "tupledevmailer@gmail.com";
		$mail->Password = "w@1LerActTPL";
		$mail->SetFrom("helloworld@tupledevelopment.com", "Leafe Admin");
		$mail->Subject = $messageSubject;
		$mail->Body = $messageScript;
		$mail->AddAddress($email);
		//$mail->AddAttachment( $pdfPath , 'invoice.pdf' );
		$mail->addStringAttachment(file_get_contents($pdfPath), $pdfFileName);
		
		 if(!$mail->Send()) {
			//echo "Mailer Error: " . $mail->ErrorInfo;
			//$response_array["status"] = "error"; 
			return "0";
		 } else {
			//echo "Message has been sent";
			//$response_array["status"] = "success"; 
			return "1";
		 }
	}
}
?>