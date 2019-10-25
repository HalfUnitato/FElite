var odds = List(1,3,5,7,9)
var all= (2 to 10 by 2).toList
println((2 to 10).toList)
all.foreach(println)
odds.foreach{println}


// a little different comment for conflict
var list = List(1,2,3,4,5,6,7,8,9)			// Creating a list
for( i <- list){							// Iterating the list
  println(i)
}
// masterbranch commit
//some code cooomments
var result = for( a <- 1 to 10 by 3) yield a
for(i<-result){
  println(i)
}

System.getProperty("file.encoding")
print(8364.toChar)
print(0xC2A5.toChar)
print(203.toChar)
print(0x2603.toChar)
print(0xa5.toChar)
print(0xff.toChar)