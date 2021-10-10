<div id="top"></div>

# TimeTableHelper
This system is design to help student especially student in University Malaysia Pahang(UMP) to make a time table before going register [OpenCourse Registration System(OR)](https://or.ump.edu.my/or/index.jsp)

*This system is run in terminal, please using the CMD on Windows.*

Theare are two major function
 1. Directly create a time table. `DirectOpenCreateB.jar`
 2. Select course as a simulation to register course in OR. `OpenChooseB.jar`

### Click here to know how to use it. (<a href="#use">Step Of Usage</a>)

For other university student, the method of registration may different. However, it still can use for "Direct Generate Time Table", view `DirectOpenCreateA.jar` OR `DirectOpenCreateB.jar`


 ## Getting Started


 ### Prerequisites

* TXT file

   * Data of Course Detail 
   * Data of Course Section

* Java enviroment

  * At least java 1.8 jre

* Operating System
  
  * at least Windows 10 (other version not sure)
  * not sure for other OS (such as linux and macOS)

<div id="use"></div>  

### Step of Usage

1. Prepare CourseDetail.txt (<a href="#format">Format of txt</a>)
2. Prepare CourseSection.txt (<a href="#format">Format of txt</a>) 
3. Download XXX.jar

4. 
   * `DirectOpenCreateA.jar` OR `DirectOpenCreateB.jar` (<a href="#file1">More Detail</a>)
     * Desription: Directly generate Time Table
     * Save CourseDetail.txt and CourseSection.txt in SAME DIRECTORY of XXX.jar
     * Open CMD in the drectory of XXX.jar and use Command
     
       ```sh
       java -jar XXX.jar
       ```
       ![image](https://drive.google.com/uc?export=view&id=1zV5ViPgZQpCD7XPn1KxfVke3VavSaJkX)
       
     * The time table should be come out if all thing correct		
	
  
   * `OpenChooseA.jar` OR `OpenChooseB.jar`  (<a href="#file2">More Detail</a>)
     * Desription: Simualtion of select course
     * Open CMD in the drectory of XXX.jar and use Command
     
       ```sh
       java -jar XXX.jar
       ```
       ![image](https://drive.google.com/uc?export=view&id=1zV5ViPgZQpCD7XPn1KxfVke3VavSaJkX)
       
     * Follow the instruction
     * If there are any unavailable section, please try other combination of section
     * The time table should be come out if all thing correct
		
		
<p align="right">(<a href="#top">back to top</a>)</p>

<div id="format"></div> 

## Manual of Usage

Goto (<a href="#formatdetail">CourseDetail.txt</a>)

Goto (<a href="#formatsection">CourseSection.txt</a>)
    
## Format of txt file
  
  * Every "part" separate by a space bar(" ") 
  * Next line for new course / new section  
  * No extra empty line / "part"
  * The order of course will be the order of select section for course
     * For example: In CourseDetail.txt, start form BPA11303 and follow by BPB14203. 
     * Therefore it will start to select Lecture of BPA11303 and lab of BPA11303(if any) from CourseDetail.txt. 
     * After that will continue to select lecture and lab BPB14203
    
  <div id="formatdetail"></div> 
    
  ### Format of Content CourseDetail.txt
  
	  Course: Mathematic
	  
	  Code: BPA11303 
	  
	  Credit Hour: 3
	  
	  isExam: Y (Yes=Y)
	  
	  Exam: 2022-02-16(yyyy/mm/dd)
	  
	  DayTimeExam: AM (AM/PM)
  
  
	  Course: Sciences
	  
	  Code: BPB14203 
	  
	  Credit Hour: 3
	  
	  isExam: N (No=N)
  

  
   #### Content in CourseDetail.txt
   
   The basic information for a course
   
   The example below show a course have exam and a course does not have exam
  
	Order(Have exame): CourseCode CourseName CreditHour isExam DateExam DayTimeExam
	Order(No exam): CourseCode CourseName CreditHour isExam
  ___
	BPA11303 Mathematic 3 Y 2022-02-16 AM
	BPB14203 Sciences 3 N
  ___
  
  <div id="formatsection"></div> 
  
  ### Format of Content CourseSection.txt
  
	  Code: BPB14203 
	  
	  Section: 01
	  
	  Type: Lecture (1 for lecture; 0 for lab)
	  
	  Day Of Week: Tuesday (0 to 4 reprensent Monday To Friday for Group B) (0 to 4 reprensent Sunday To Thursday for Group A) 
	  
	  Time Start: 1400
	  
	  Time End: 1700
  
  
	  Code: BPB14203 
	  
	  Section: 01A
	  
	  Type: Lab (1 for lecture; 0 for lab)
	  
	  Day Of Week: Tuesday ( 0 to 4 reprensent Monday To Friday for Group B) ( 0 to 4 reprensent Sunday To Thursday for Group A) 
	  
	  Time Start: 1400
	  
	  Time End: 1700
  
  #### Content in CourseDetail.txt:
  
  The lecture and lab section for the course in CourseDetail.txt
  
  For example, BPA11303 have lecture section 01 02, for lecture 01, have lab session 01A and 01B
  For Day Of Week, 0 to 4 reprensent Monday To Friday for Group B , 0 to 4 reprensent Sunday To Thursday for Group A
  
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
 
 ## Explaination of jar file
 
   <div id="file1"></div> 
 
 * `DirectOpenCreateA.jar` OR `DirectOpenCreateB.jar`
	 * This is design for generate time table only
	 * Put the lecture section and lab session that choosen, for example BPA11303 Matematik put in CourseDetail.txt,  BPA11303 01 and 01B put in CourseSection.txt
	 * If the time for all session are not repeated, it will assign time table sucessfully
	 * it will auto export timetable.txt that save the time table generated.
	 * if timetable.txt exist, it will auto detele and create new.
	 * if failed to assign please check the section time and format of data file.
	 * can refer OpenCreateExample.zip as a example
  
   <div id="file2"></div> 
   
 * `OpenChooseA.jar` OR `OpenChooseB.jar`
	 * This is design for simulate choosing course
	 * Make sure put all the posible lecture section and lab session that can choose, for example BPA11303 Matematik put in CourseDetail.txt,  BPA11303 01 01A 01B 02 02A 02B put in CourseSection.txt
	 * This will help you to disable show repeated time during select section.
	 * For example, BPA11303 have lecture section 01 02 03, chosen 01
	 * if lab section 01A 01B exist for lecture section 01, it will display
	 * if time of lab is repeated, it will also not display the section.
	 * Next lecture section course also will check the repeat time.
	 * If there are empty section available, the time table will not export. You should change another combination of section.
	 * If the time for all session are not repeated, it will assign time table sucessfully
	 * it will ask for export timetable.txt that save the time table generated, default is yes and press enter, if no please enter "n"
	 * if timetable.txt exist, it will ask for detele old and create new one, default is yes and press enter, if no please enter "n"
	 * can refer directCreateExample.zip as a example

  <p align="right">(<a href="#top">back to top</a>)</p>


## Chategory of state
  
### Group A
  
Negeri Johor, Kedah, Kelantan & Terenganu

The Day of week will start from Sunday to Thursday
	
Download : `DirectOpenCreateA.jar` | `OpneChooseA.jar`
	
<div id="group-b"></div>  

### Group B
  
Negeri Perlis, Pulau Pinang, Perak, Selangor, Negeri Sembilan, Melaka, Pahang, Sabah, Sarawak, Wilayah Persekutuan Kuala Lumpur, Labuan & Putrajaya

The Day of week will start from Monday to Friday

Download : 	`DirectOpenCreateB.jar` | `OpneChooseB.jar`
	
<p align="right">(<a href="#top">back to top</a>)</p>
  
