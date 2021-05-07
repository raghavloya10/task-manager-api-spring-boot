# A Task Manager API using Spring BOOT 

A basic Task Manager REST API that logs in **users** and allows them to Create, Read, Update and Delete (CRUD) **tasks** using Spring BOOT and JWT

* Authentication at `/authenticate`, sends back JWT (JSON Web Token) that should be sent in authorization header in every subsequent request
* `/user/**` for all user related CRUD operations (Can only be performed by user with role 'admin')
* `/task/**` for all task related CRUD operations (Can only be performed by user with role 'user')
