module samples.Fibonacci
 
function fib = |n| {
  if n < 2 {
    return n
  } else {
    return fib(n - 1) + fib(n - 2)
  }
}
 
function main = |args| {
  println(fib(10))
}

