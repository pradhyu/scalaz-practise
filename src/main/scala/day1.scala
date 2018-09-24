// what is polymorphism?
object day1 {
  /*
   Parametric polymorphism
   Nick says:
   Define a function head takes a list of A's and returns an A which is first element of the list
   */
  def head[A](xs: List[A]): A = xs(0)
  head(List(1,2,3,4))
  head(List ("A","B","C"))
  case class Car(make: String)
  head(List(Car("Honda"),Car("Toyota"),Car("Ford")))

  /*
   Subtype polymorphism
   def plus[A](a1:A, a2: A)A = ???
   A plus function that can add two values of type A
   Depending on type A we need to provide different definition for whatit means to add them
   One way to achieve this is to use subtyping
   */
// add-hoc polymorphism
  trait Plus[A] {
    def plus(a1: A, a2: A): A
  }
  // <: means A must define type Plus[A] ie A is subtype of Plus[A]

  object PlusInt extends Plus[Int] {
    def plus(a1: Int, a2: Int): Int= a1+a2
  }

  def plus[A: Plus](a1:A, a2:A): A= implicitly[Plus[A]].plus(a1,a2)
  // implicitly ?? not working how to provide implicit conversion from Plus[A] to Plus[Int] ??
  // plus(1,2) // doesn't work


  // Sum function

  // ad-hoc polymorphism making sum function 
  // for int 
  // def sum(xs: List[Int]): Int = xs.foldLeft(0)(_ + _)
  //sum(List(1,2,3,4))


  // Using Monoid 
  // Simple Monoid for sum function 
  object IntMonoid {
    def mappend(a: Int, b: Int): Int = a+ b
    def mzero: Int = 0
  }

  def sum(xs: List[Int]): Int = xs.foldLeft(IntMonoid.mzero)(IntMonoid.mappend)

  sum(List(1,2,3,4))

  // abstract the Monoid 
  trait Monoid[A] {
    def mappend(a: A, b: A): A = ???
    def mzero: Int= ???
  }
}
