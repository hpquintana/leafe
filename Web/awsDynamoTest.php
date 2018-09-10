<?php 
$path = $_SERVER['DOCUMENT_ROOT'];
$path .= "/aws/aws-autoloader.php";
require $path;

$url = "tupledevelopment.com";
	
$sharedConfig = [
    'region'  => 'us-east-1',
    'version' => 'latest'
];

$sdk = new Aws\Sdk($sharedConfig);
$dynamodb = $sdk->createDynamoDb();

//$response = $dynamodb->getItem([
//    'TableName' => 'AssignedForm',
//    'Key' => [
//        'FormID' => [ 'S' => '18' ]
//    ]
//]);

$response = $dynamodb->updateItem([
    'TableName' => 'AssignedForm',
    'Key' => [
        'FormID' => [ 'S' => '18' ]
    ],
    'ExpressionAttributeValues' =>  [
        ':url' => [ 'S' => $url]
    ] ,
    'UpdateExpression' => 'set pdfUrl = :url'
]);

print_r ($response);
?>