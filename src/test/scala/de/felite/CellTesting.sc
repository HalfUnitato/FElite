import de.felite._
val i = Array(1,2,3)
//val field = Array.ofDim[Cell](3,3)
val arr = Array(Char,Char)
val fullArr = Array.ofDim[Char](20, 20)
for (i <- 0 until 20) {
 for (j <- 0 until 20) {
  fullArr(i)(j) = '*'
 }
}

var k = 5
for (i <- 0 until 7) {
 for (j <- 0 until k) {
  fullArr(i)(j) = 't'
 }
 k -= 1
}


 val field = Array.ofDim[Char](2,2)
for (i <- 0 to 1) {
 for (j <- 0 to 1) {
  //  println(i,j)
  field(i)(j) = 20
 }
}


for (i <- 0 until 20) {
 for (j <- 0 until 20) {
  print(fullArr(i)(j) + " ")
 }
 println()
}

/* {{'a','b'}
  for(x <-  field.){

  }*/