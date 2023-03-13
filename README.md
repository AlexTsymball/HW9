# HW9
### Run
* в файлі .env задати 
>spring.mail.username=\<login user to smtp server\>
>
>spring.mail.password=\<login password to smtp server\>
* mvn clean install
* docker-compose build
* docker-compose up

### Link
>POST http://localhost:8080/mail 
>
>{
>
>   "subject": "test",
>
>    "content": "test",
>
>    "emailTo": ["email1","email2"]
>
>}
