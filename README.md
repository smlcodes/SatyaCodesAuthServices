# SatyaCodesAuthServices
SpringBoot+JWT+OAuth2+ Hibernate+MySQL+MongoDB


## Authentication URL's
POST	[http://localhost:8080/auth](http://localhost:8080/auth)  
<code>
{
    "username":"admin",
    "password":"admin"
}

</code>



GET		[http://localhost:8080/dashboard](http://localhost:8080/dashboard)


## Users Controller
Get User By ID    
http://localhost:8080/api/users/101  

GetAllUsers  : GET  
http://localhost:8080/api/users  

Add User : POST  
http://localhost:8080/api/users  
<code>
    {
        "name": "SATYA",
        "age": 30,
        "address": "HYDERABAD"
    }
<code>



