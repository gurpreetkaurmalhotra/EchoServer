# EchoServer
A modified version of echo server, that returns the number of times the server has seen a particular string.

* Run EchoSever.java file, this will create a new server.
* Run Client.java file, pass a string to it, the server will echo the number of times it has seen that string.
* You can create multiple clients and test this.
* Note that this is a multithreaded application, supports creation of 5 threads at a time.

Features -
* Given applications is thread safe.
  1. ConcurrentHashMap is used to ensure that it is thread safe.
  2. Used AtomicLong to maintain the count of each string passed as a key, its incrementAndGet method ensures the increment of counters correctly.
  
* Optimised.
