call gradlew build

call runcrud
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:runbrowser
start https://www.google.com
if "%ERRORLEVEL%" == "0" goto openpage
echo.
echo Cannot run browser
goto fail

:openpage
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open page

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.