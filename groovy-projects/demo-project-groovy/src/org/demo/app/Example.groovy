class Example {

	static void main(String[] args) {      
      println("program started ...");
      def range = 5..10; 
      println(range); 
      println(range.get(2)); 
      if (range.get(2) == 7){
            println("bongo!");
      }
   }

}