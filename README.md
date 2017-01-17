# APCS-Final-Project-1
<ul> <li>Our project: Daily planner: the user inputs information about their daily schedule, such as start time, ideal stop time,
subjects they have & tasks they must complete, and the program returns (prints & writes to a text file) the optimum schedule. </li>
<li> Working features: <ul><li>the backend: takes a text file with schedule information and returns the ideal schedule </li>
<li>Utilities.java: converts AM/PM to military time; note that this hasn't been implemented in the backend (it was made for the user interface program to use)</ul></li>
<li> Non-Functional Work in Progress: <ul><li>The GUI general layout is setted up but we were unable to coordinate it with algorithmic part because of difficulty transfering data to text file in a way readable for the classes. This can be seen in GuiRetry/TabPanes where TabPanes.java has the general planner and survey format but is nonfunctional while TabPanesTry.java has work with transferring data to textfile.<li>
<li> Things to test: <ul><li>making the list of activities really long to see how long it takes (the program can handle ~15 at least, but runs slowly)</li> <li>if the schedule goes into multiple days, doesn't specify the day</li></ul>
<li> Bugs: <ul><li>breaks with 0 time show up, but don't take up any time</li><li>it is not guaranteed that a break will 
be placed within its time slot; in this case, it will go as early as possible for that particular schedule</li>
<li>end task difficulty must be > 0, but it doesn't get factored in</li><li>energy deterioration is not necessarily
very accurate, since I couldn't find human energy equations online</li><li>break names can be blank</li><li>negative utility 
is possible, and in this case the program chooses the least negative, so I'm sure how accurate that is. This negative utility is probably due to subtracting a lot from not sleeping enough</li><li>some error messages are inaccurate, like 'missing " at end' can be inaccurate: it can mean that a line doesn't have enough information or too much</li><li>a lot of input in the text file is required but also useless</li><li>end task was intended to be a fun activity, like "sleep", so the answers would probably be less accurate if you had very low enjoyment of the task</li>
</ul></li>
<li>How to compile & run: <ul><li>backend: compile & run ScheduleDriver.java (it must have access to scheduleTest.txt, so you may
have to move the file to the same folder as ScheduleDriver). This will display error messages to the Terminal and will print the schedule output to the Terminal and write it to schedule.txt. You can also run TestMethods.java for small task lists (will run through all permutations & display energy, times, & utility). TestMethod's main can crash; the main is just for debugging purposes </li></ul></li>
<li>How to use: input text directly into scheduleText.txt: Sample format (note that this order should be preserved; subjects must come before tasks with that subject and the energy, start time, stop time & ideal stop time must be on the top line)
10 1600 2300 2400<br>
'precalc' 80 80<br>
'history' 80 30<br><br>
"'precalc' hw" 50 0<br>
"'history' 14.3 cornell notes" 10 0<br>
break 'dinner' 1700 1730 10<br>
break 'take a shower' 1600 1800 200<br>
endtask 'go to sleep!' 0.8 100<br>
<br><br>In general:<br>
<b>initialEnergy startTime idealStoptime stopTime</b> //military time; stoptime is irrelevant, just make it > idealStopTime<br>
<b>'subjectName' difficulty enjoyment</b> //0 < difficulty, enjoyment <= 100<br>
//... (subject list separated by line breaks)<br>
<b>"'subjectName' taskName" averageTaskTime 0 </b>///averageTaskTime is how long it should take the average student; 0 was the time variability but now it has no impact<br>//... (task list separated by line breaks) <br>
<b>break 'breakName' earliestStartTime latestStartTime totalTime</b><br>//... (break list separated by line breaks)<br>
<b>endtask 'endTaskName' difficulty enjoyment </b> //0 < difficulty, enjoyment <= 100
<!--<ul><li>the subjects they have</li><li>how difficult each subject is</li><li>the enjoyment of each subject</li>
<li>the tasks they have</li><li>how long it should take the average student to complete that task</li><li>the breaks they have 
(note: these are required for the user to complete, so things like dinner)</li><li>the time interval that break can start within
</li><li>how long the break takes</li>
<li>the end task, like sleeping</li><li>the difficulty of the end task (> 0)</li></ul>-->
</ul>
