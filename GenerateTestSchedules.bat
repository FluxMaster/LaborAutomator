@echo off
set /p num= How Many Schedules do you want to Generate?

FOR /L %%A IN (1,1,%num%) DO (
  java -cp "src" ScheduleGenerator
)
echo Complete! Press any key to quit.
pause