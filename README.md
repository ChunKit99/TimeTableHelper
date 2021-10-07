<div id="top"></div>


# TimeTableHelper
A system can help student especially student in University Malaysia Pahang(UMP) to make a time table before going register [OpenCourse Registration System(OR)](https://or.ump.edu.my/or/index.jsp)

As a UMP Student, please download in [Group B](#group-b)


 ## Getting Started

 ### Prerequisites

* TXT file

   * Data of Course Detail 
   * Data of Course Section

* Java enviroment

  * At least java 1.8 jre

* Operating System
  
  * Windows 10
 
 
 
## Chategory of state
  
### Group A
  
Negeri Johor, Kedah, Kelantan & Terenganu
	
Download : [DirectOpenChooseA.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenChooseA.jar) | [DirectOpenCreateA.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenCreateA.jar) | [InputPathChooseA.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/InputPathChooseA.jar)
	
  
### Group B
  
Negeri Perlis, Pulau Pinang, Perak, Selangor, Negeri Sembilan, Melaka, Pahang, Sabah, Sarawak, Wilayah Persekutuan Kuala Lumpur, Labuan & Putrajaya

Download : [DirectOpenChooseB.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenChooseB.jar) | [DirectOpenCreateB.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenCreateB.jar) | [InputPathChooseB.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/InputPathChooseB.jar)
	
<p align="right">(<a href="#top">back to top</a>)</p>

 
 
## Usage
  
 You can use this system to select the course that will register in OR. 
  
 There are 2 group of state, and 3 type of usage provide


### Step of Usage
1. Prepare CourseDetail.txt
2. Prepare CourseSection.txt
3. Download XXX.jar

4. 
   * [DirectOpenCreateA.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenCreateA.jar) OR [DirectOpenCreateB.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenChooseB.jar)
     * Desription: Directly generate Time Table
     * Only put section(including Lecture or Lab) that need to create time table in CourseDetail.txt and CourseSection.txt
     * Save CourseDetail.txt and CourseSection.txt in SAME DIRECTORY of XXX.jar
     * Open CMD in the drectory of XXX.jar and use Command
     
       ```sh
       java -jar XXX.jar
       ```
     * The time table should be come out if all thing correct		
	
  
   * [DirectOpenChooseA.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenChooseA.jar) OR [DirectOpenChooseB.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/DirectOpenChooseA.jar)
     * Desription: Without enter any path and can start to select course
     * Put all section(including Lecture or Lab) that posible to create time table in CourseDetail.txt and CourseSection.txt
     * Open CMD in the drectory of XXX.jar and use Command
     
       ```sh
       java -jar XXX.jar
       ```
     * Follow the instruction
     * If there are any unavailable section, please try other combination of section
     * The time table should be come out if all thing correct


   * [InputPathChooseA.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/InputPathChooseA.jar) OR [InputPathChooseB.jar](https://github.com/ChunKit99/TimeTableHelper/blob/main/InputPathChooseB.jar)
     * Desription: Need to enter path of CourseDetail.txt and CourseSection.txt to select course
       * For other directory: absolute path for txt file
       * Same Directory: filename like: "CourseDetail.txt"
     * Open CMD in the drectory of XXX.jar and use Command
     
       ```sh
       java -jar XXX.jar
       ```
      * Enter path of file Course Detail
      * Enter path of file Course Section
	
<p align="right">(<a href="#top">back to top</a>)</p>

  

  ## Format of txt file
  
  * Every "part" separate by a space bar(" ") 
  * Next line for new course / new section  
  * No extra empty line / "part"
  * The order of course will be the order of select section for course
     * For example: In CourseDetail.txt, BPA11303 follow by BPB14203
     * therefore it will start to select Lecture of BPA11303 and lab of BPA11303(if any) from CourseDetail.txt
     * and next to BPB14203
     
  
  
  ### Format of Content CourseDetail.txt
  
	  Course: Mathematic
	  
	  Code: BPA11303 
	  
	  Credit Hour: 3
	  
	  Exam: 2020/3/13
  
  
	  Course: Sciences
	  
	  Code: BPB14203 
	  
	  Credit Hour: 3
	  
	  Exam: 2020/3/14
  
  
   #### Content in CourseDetail.txt
  
	Order: CourseCode CourseName CreditHour YearExam MonthExam DateExam
  ___
	BPA11303 Mathematic 3 2021 3 13
	BPB14203 Sciences 3 2021 3 14
  ___
  
  
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
