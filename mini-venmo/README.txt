To run the application, you can execute the provided 'mini-venmo' script that invokes the MiniVenmo.jar file

I have chosen to implement the solution for this challenge in Java because I have the most experience in this language.
While there are some aspects such as dynamic handling of arrays and regular expressions that are somewhat clunky, the approach for solving this challenge was pretty simple.

The idea is to create an API that would represent the application. This interface can then be implemented along with some basic commands in order to register users, credit cards and payments while also keeping track of balances and user specific feeds.
While the entire application could easily have been written in just a couple of classes, I have chosen to break it out into smaller pieces, each representing a specific concrete functionality.
This allows for easy maintenance, extensibility and modularization in case any of the functionality needs to be changed or overriden.

The unit tests added to this project ensure that the validations specified in the requirements work as expected.

-NOTE-
The Luhn-10 algorithm is a well known procedure and has been taken from an open source location online.
While I would be quite comfortable implementing something similar myself, this solution is definitely the most elegant that I have seen.