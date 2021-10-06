<div id="top"></div>


# TimeTableHelper
A system can help student especially University Malaysia Pahang(UMP) to make a time table before going to OpenCourse Registration System(OR)

 ## Getting Started

 ### Prerequisites

* TXT file

   Data of Course Detail and Course Section

* Java enviroment

  At least install java 1.8 

* Operating System
  
  Windows 10
 
 
 
## Chategory of state
  
### Group A
  
Negeri Johor Kedah Kelantan Terenganu
	
Download : TTH_DirectOpen(A).jar  OR  TTH_InputPath(A).jar
	
  
### Group B
  
Other than Group B
	
Download : TTH_DirectOpen(B).jar  OR  TTH_InputPath(B).jar
	
<p align="right">(<a href="#top">back to top</a>)</p>

 
 
 ## Usage
  
  You can use this system to select the course that will register in OR. 

1. Ready CourseDetail.txt
2. Ready CourseSection.txt
3. Download XXX.jar

4. * For TTH_DirectOpen(A).jar && TTH_DirectOpen(B).jar
		
		* CourseDetail.txt and CourseSection.txt in SAME DIRECTORY
			
		* Open CMD go to drectory of XXX.jar use Command
			
		```sh
		 java -jar XXX.jar
		```
		   
	* For TTH_DirectOpen(A).jar && TTH_DirectOpen(B).jar
		
		* Open CMD go to drectory of XXX.jar use Command
			
		```sh
		 java -jar XXX.jar
		```
		 
		* Enter absolute path of CourseDetail.txt
		
		```sh
		C:\Users\user\Document\CourseDetail.txt
		```
  
		* Enter absolute path of CourseSection.txt
		
		```sh
		C:\Users\user\Document\CourseSection.txt
		```
		
	   	
5. Choose Lecture Section for a course
6. Choose Lab Section for a course(if any)
7. Repeat until done select all course.
8. Reminder: There are posible not availble section, that's mean you need to choose other section for previous course.
   
<p align="right">(<a href="#top">back to top</a>)</p>

  

  ## Format of txt
  
  * Every part separate by a "space bar"
  
  * Next line for new course
  
  * No extra empty line
  
  * The order of course will be the order of select section for course
  
  
  ### Format of Content CourseDetail.txt
  
	  Course: Mathematic
	  
	  Code: BPA11303 
	  
	  Credit Hour: 3
	  
	  Exam: 2020/3/13
  
  
	  Course: Sciences
	  
	  Code: BPB14203 
	  
	  Credit Hour: 3
	  
	  Exam: 2020/3/14
  
  
   #### Content in CourseDetail.txt:
  
	Order: CourseCode CourseName CreditHour YearExam MonthExam DateExam
  ___
  BPA11303 Mathematic 3 2021 3 13
  
  BPB14203 Sciences 3 2021 3 14
  ___
  
  
  ### Format of Content CourseSection.txt
  
	  Code: BPB14203 
	  
	  Section: 01
	  
	  Type: Lecture (1 for lecture; 0 for lab)
	  
	  Day Of Week: Tuesday (0 to 4 reprensent Monday To Friday for Group B) (0 to 4 reprensent Sunday To Thursday for Group B) 
	  
	  Time Start: 1400
	  
	  Time End: 1700
  
  
	  Code: BPB14203 
	  
	  Section: 01A
	  
	  Type: Lab (1 for lecture; 0 for lab)
	  
	  Day Of Week: Tuesday ( 0 to 4 reprensent Monday To Friday for Group B) ( 0 to 4 reprensent Sunday To Thursday for Group B) 
	  
	  Time Start: 1400
	  
	  Time End: 1700
  
  #### Content in CourseDetail.txt:
  
	Order: SectionName/Number CourseCode Type DayOfWeek HourStart HourEnd
  ___
  01 BPA11303 1 1 14 17
  
  02 BPA11303 1 2 8 11
  
  01 BPB14203 1 1 8 10
  
  02 BPB14203 1 2 14 17
  
  01A BPB14203 0 1 10 12
  
  01B BPB14203 0 1 12 14
  
  02A BPB14203 0 2 12 14
  
  02A BPB14203 0 2 10 12
   ___
  
  <p align="right">(<a href="#top">back to top</a>)</p>
  
  
## License 


Distributed under the MIT License. See `LICENSE.txt` for more information.


  <p align="right">(<a href="#top">back to top</a>)</p>



## Contact

Liew Chun Kit- liewchunkit99@gmail.com

Project Link: [https://github.com/ChunKit99/TimeTableHelper](https://github.com/ChunKit99/TimeTableHelper)

<p align="right">(<a href="#top">back to top</a>)</p>
