<div id="top"></div>


# TimeTableHelper
A system can help student especially University Malaysia Pahang(UMP) to make a time table before going to OpenCourse Registration System(OR)

 ## Getting Started

 ### Prerequisites

* txt file

  2 TXT file for save the data of Course Detail and Course Section

* java enviroment

  At least install java 1.8 

* Operating System
  
  Windows 10
  
 ## Usage
  
  You can use this system to select the course that will register in OR. 

1. Ready CourseDetail.txt
2. Ready CourseSection.txt
3. Download XXX.jar
4. Command
   ```sh
   java -jar XXX.jar
   ```
3. Enter absolute path of CourseDetail.txt

    For example:
  ```sh
   C:\Users\user\Document\CourseDetail.txt
   ```
4. Enter absolute path of CourseSection.txt
5. Select Lecture Section for a course
6. Select Lab Section for a course(if any)
7. Repeat until done select all course.
8. Reminder: There are posible not availble section, that mean you need to select other section of course.
   
   

<p align="right">(<a href="#top">back to top</a>)</p>
  
  ## Usage
  
  ### Format of Content CourseDetail.txt
  
  
  CourseCode CourseName CreditHour YearExam MonthExam DateExam
  ___
  BPA11303 PM 3 2021 3 13
  
  BPB14203 BFM 3 2021 3 14
  ___
  
  ### Format of Content CourseSection.txt
  
  SectionName/Number CourseCode Type(1 for lecture 0 for lab) DayOfWeek(0 to 4 reprensent Monday To Thursday) HourStart HourEnd
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
