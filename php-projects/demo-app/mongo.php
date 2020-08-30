<?php

// Manager Class
$manager = new MongoDB\Driver\Manager("mongodb://localhost:27017");

// Query Class
$query = new MongoDB\Driver\Query(array('item' => 'canvas'));

// Output of the executeQuery will be object of MongoDB\Driver\Cursor class
$cursor = $manager->executeQuery('dms.inventory', $query);

// Convert cursor to Array and print result
print_r($cursor->toArray());
?>