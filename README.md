# code-run-tracker

Code Run Tracker is designed to verify and log the execution of programming assignments on students' local machines. It ensures that learners not only read the source code, but also compile and run it in their own development environments.

The system consists of a Spring Boot REST API, integrated with a PostgreSQL database, and a Java client library (JAR) that can be embedded into any student project. Upon execution, the client automatically registers the student's ID, task identifier, local user, institution, course, subject, semester, timestamp, and IP address.

This platform provides instructors with reliable insights into student engagement and reinforces academic integrity in programming courses.