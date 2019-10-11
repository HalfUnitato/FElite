var odds = List(1,3,5,7,9)
var all= (2 to 10 by 2).toList
println((2 to 10).toList)
all.foreach(println)
odds.foreach{println}

// a little bit different comments for conflict
var list = List(1,2,3,4,5,6,7,8,9)			// Creating a list
for( i <- list){							// Iterating the list
  println(i)
}

var result = for( a <- 1 to 10 by 3) yield a
for(i<-result){
  println(i)
}