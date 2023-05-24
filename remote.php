<?php
$servername = "localhost";
$database = "student";
$username = "root"; 
$password = "";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $database);

// Check connection
if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully \n";

//Retrieving data from database
$sql = "SELECT * FROM student";
$result = mysqli_query($conn, $sql);


//encoding result to json format
$json = array();
if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($r = mysqli_fetch_assoc($result)) {
        $json["data"][] = array("StudentId" => $r["StudentId"], 
                                "FirstName" => $r["FirstName"], 
                                "LastName" => $r["LastName"], 
                                "Gender" => $r["Gender"],
                                "Address" => $r["Address"],
                                "University" => $r["University"],
                                "Department" => $r["Department"],
                                "Program" => $r["Program"],
                            );
    }
} else {
    echo "0 results";
}

//encoding result to json format
echo json_encode($json);

mysqli_close($conn);

?>