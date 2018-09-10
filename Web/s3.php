<?php
header("Access-Control-Allow-Origin: *");
require 'PHPMailer/PHPMailerAutoload.php';
require 'aws/aws-autoloader.php';
// Use the us-west-2 region and latest version of each client.
$sharedConfig = [
    'region'  => 'us-east-1',
    'version' => 'latest'
];

// Create an SDK class used to share configuration across clients.
$sdk = new Aws\Sdk($sharedConfig);

// Create an Amazon S3 client using the shared configuration data.
$s3Client = $sdk->createS3();

// Send a PutObject request and get the result object.
$result = $s3Client->putObject([
    'Bucket' => 'tupledev-s3sample',
    'Key'    => 'test.txt',
    'Body'   => 'this is the body!'
]);

// Download the contents of the object.
$result = $s3Client->getObject([
    'Bucket' => 'tupledev-s3sample',
    'Key'    => 'test.txt'
]);

// Print the body of the result by indexing into the result object.
echo $result['Body'];
?>