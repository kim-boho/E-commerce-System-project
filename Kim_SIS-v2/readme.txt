Setting
1. download zip file
2. unzip it
3. In Eclipse, file -> import -> Maven [Existing Maven Projects], select the unziped folder for Root Directory, finish.

4. Change Some Path
1) In Kim_SIS-v2/src/main/webapp/META-INF/context.xml,
change path of database in line 8.

2) In Kim_SIS-v2/src/main/webapp/SisApp.html,
change path for onclick function in line 23 if the project name was changed

5. Run SisApp.html in on server


Lab5 functionality

(Assume we have the same database that we used in Lab5)

SisApp.html has the same function as lab5

curl -X GET "http://localhost:8080/Kim_SIS-v2/SisApp.html"
Shows HTML codes

curl -X POST "http://localhost:8080/Kim_SIS-v2/Sis?prefix=Rodrig&minCredit=30"
This shows students who meet the conditions in the table

curl -X POST "http://localhost:8080/Kim_SIS-v2/Sis"
This prints all students list in eclipse console



Lab6
curl -X GET "http://localhost:8080/Kim_SIS-v2/rest/students?namePrefix=Rodr&credits=80"
Same function like lab5

curl -X POST "http://localhost:8080/Kim_SIS-v2/rest/students?sid=cse12345&givenName=Boho&surName=Kim&creditTaken=45&creditGraduate=90"
Insert student information to database

curl -X GET "http://localhost:8080/Kim_SIS-v2/rest/students?namePrefix=Ki&credits=20"
Search the student that I inserted above

curl -X DELETE "http://localhost:8080/Kim_SIS-v2/rest/students/cse12345"
Deleted stduents that I inserted above


If there is no matching student information, it prints empty string (nothing)