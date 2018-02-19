# Muzima Core Module

A module that provides core muzima functionalities. 

These include:

1. Muzima Forms management, offering
    1. Uploading Muzima HTML forms to the module
    2. Uploading ODK/JavaRosa XML forms to the module
2. Muzima Data Source Management
3. Muzima Setup Configuration
4. Muzima Queue Management
5. Muzima Registration Management
6. Muzima Error Resolution Portal
7. Muzima RESTful services

Setting up Scheduled Task
1. On OpenMRS administration page, locate 
2. Click on Manage Scheduler and on the page add new scheduler
3. Under task configuration task put the following information:
    Name: Muzima Queue Data Processor
    Schedulable Class: org.openmrs.module.muzima.task.ProcessQueueDataTask
    Description: Processes mUzima queue data
4. Under Schedule tab check option for start on startup leaving other fields with defaults
5. Save
6. To enable the task, click on checkbox adjacent to the task you just created and click on Start button below