# Backend Engineer Assessment

### Technology to be used

* Java (preferrably version 17+)
* Springboot
* Mysql
* Redis


### Guides
The following guides illustrate how to run the springboot app, mysql and redis:

#### Redis

change `your-container-name` to your desire name for redis and execute to terminal.

note: check if you already have docker in your local machine before you run the code below.

`docker run --name your-container-name -p 6379:6379 -d redis`

#### MySQL

check if already installed MySQL from you local machine before you run the springboot app.

#### Springboot App 

Check the installed version of java in your local machine before you run the app.

Open your terminal and check the directory if you are inside the assessment folder.

Run to execute the code below

`./gradlew bootRun`



## API guides

#### Register username and password before you access the APIs.
POST - http://localhost:8080/api/register

json: 
`{
    "username": "james",
    "password": "bond007"
}`

#### Authentication login to generate token.
POST - http://localhost:8080/api/auth-login


QueryParam username and password

`username` :`james`

`password` :`bond007`

#### Get github users with redis cache

GET - http://localhost:8080/api/github-users

RequestBody type with list of string json

1. Execute the list of strings below, and it will save from the redis cache.

   * RequestBody : `["a","b"]`

2. Execute the 2nd request with `a` and `b` in the list of strings, 
and it will retrieve the `a` and `b` from the redis cache, and the `d` will be retrieved from github user API.

   * RequestBody : `["a","b","d"]`


