package main

import (f "fmt"
	    "net/http")

func main() {
	f.Println("file server program started ...")
	var p string = "9876"
	f.Println("port provided ...", p )
	http.ListenAndServe(":9876", http.FileServer(http.Dir( ".")))
	f.Println("file server started successfully ...")
}