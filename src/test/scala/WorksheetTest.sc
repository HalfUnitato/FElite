val i = Array(Array(1, 2, 3, 4, 5),
  Array(6, 7, 8, 9, 0))

for (y <- i) {
  for (x <- y) {
    print(x)
  }
  println()
}