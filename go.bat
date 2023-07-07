@REM compile the project of the user 

set tempFilePath = "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\


@REM Compile Framework
cd Framework
javac Annotation/*.java
javac utilities/*.java
javac etu1811/framework/servlet/*.java
javac etu1811/framework/*.java

cd "./../"

@REM create tempFolder
mkdir %tempFilePath%tempProject"
mkdir %tempFilePath%tempProject\WEB-INF"
mkdir %tempFilePath%tempProject\WEB-INF\lib"
mkdir %tempFilePath%tempProject\WEB-INF\classes"

cd Framework

@REM Build the jar file and place it in the temp lib folder
jar cvf framework.jar *
copy framework.jar  "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\tempProject/WEB-INF/lib/framework.jar"

@REM .jsp file to the temp folder
copy "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\Test\*.jsp" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\tempProject

@REM copy  web.xml of user to temp folder
copy "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\Test\WEB-INF\web.xml" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\tempProject/WEB-INF/web.xml"

@REM copy classes of user to temp classes folder 
echo Tous | Xcopy "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\Test\WEB-INF\classes\" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\tempProject/WEB-INF/classes/" /E/H/C/I 




@REM create war file
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\tempProject\"
jar cvf test_framework.war *
copy test_framework.war "../"
del test_framework.war

@REM go back to Root and delete temporary folder
cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\"
echo O | rmdir "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\tempProject" /s



