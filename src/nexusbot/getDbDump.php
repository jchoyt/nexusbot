<?php

//if t=1 dumps the data, otherwise the structure
$data=$_GET['t'];
require(”class_mysqldump.php”);

//Instantiate the class: host name, user name, and password
$dump = new MySQLDump(”localhost”, “root”, “”);

//If you want to compress the output uncomment the follow line
//$dump = new MySQLDump(”localhost”, “root”, “”, False);

if ($data==”1″) {
        $dump->dumpDatabaseData(”dbname”, $filename, 100);
        //If you don’t want binary fields saved in hexadecimal
        //format uncomment the follow line
        //$dump->dumpDatabaseData(”nomedb”, $filename, 100, False);
}
else {
        //dump the structure
        $dump->dumpDatabaseStructure(”nomedb”, $filename);
}

//send file to standard output
header (’Content-Type: application/octet-stream’);
header(’Content-Disposition: attachment; filename=”‘.$filename.’”‘);
$file=fopen($filename,”r”);
fpassthru($file);
fclose($file);

//delete temporary files
unlink($filename);

?>
