@echo off

rem %~dp0 is expanded pathname of the current script under NT
rem because of problem with %cd% was used instead of %~dp0..\..\ 
rem in configuration.java --> getparent of %~dp0..\..\ is %~dp0..\ which does not seem to work

@echo off

rem %~dp0 is expanded pathname of the current script under NT

setlocal

set VCT_HOME=%~dp0..\..\

call %VCT_HOME%\deps\sbt\0.13.11\bin\sbt.bat  %*

