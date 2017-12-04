# Stackd


## Iteration 03

 * Start date: November 20th 2017
 * End date: November 30th 2017

## Process

The past iteration was interesting because our teamwork, communication, and dedication were put to the test. However, now that we are starting a new iteration, we plan on continuing to implement more features and deploy adjustments to our process practices to facilitate more efficient development during the upcoming phase. This is because there is a strict time requirement and we have ambitious plans to build more advanced features. Those adjustments are explained in detail below.



#### Changes from previous iteration

* **We are reshuffling subteams**. We will form new sub-teams so that people get to work with different teammates and work on different parts of the app. For example, Dmitry worked on the front-end in the previous iteration and Musa worked on the database. In this iteration, Dmitry will work on back-end and Musa will work on adding export functionality to the front-end app. After splitting the tasks, we made sure that everyone understood their role and was satisfied with it. We gave more responsibilities to teammates who had less coding contributions during previous iterations, to ensure equal contribution from all members. This was a concern previously, so this way one solution we implemented to address it.
* **We will have more stand-up meetings**. During previous iterations we have realized that communication between subteams was lacking. People from one subteam often had very vague idea of what other subteam had been doing and what progress they had made. This led to slower progress and, sometimes, duplicate work. For example, 2 subteams implemented a Resume class because it was not made clear who is supposed to do that. Updating each other on progress made more often should help us avoid any issues like that in the future. 
* **Working continuously and making persistence progress**. We noticed that last iteration we did the bulk of the work on the coding jam day and that last two days before the deadline, so this iteration we’re making sure to commit very often and make a consistent progress.
* **We are moving away from TDD**. The previous iteration has shown that writing tests before implementation is hard when working with new technologies, so we ended up not following TDD. This iteration, we are short on time, so we will focus on having main functionality implemented, and test everything afterwards.

#### Roles & responsibilities

* Scrum master: Tanveer
* Commit master: Dmitry
* UI/UX & Analytics : Sonata & Angelo
* Amazon AWS storage and integration: Dmitry
* Sending HTTP requests to server from the app : Musa & Sonata
* Web server for interacting with database: Justine & Tanveer
* MongoDB database : Justine & Tanveer & Dmitry 
* Export: Lana & Musa

#### Events

1. General Team Meeting: @BA2135 - Wednesday November 22nd
    * Purpose: Planning meeting

2. General Team meeting: @BA3200 [November 25th](https://docs.google.com/document/d/1zpxJw_5Rh-sNkZpV7aS_lyUibgVlktk2ZC9Gcx4qyIg/edit?usp=sharing )
    * Purpose:  Stand-up/Coding session

3. General Team meeting / Stand-up: @Gerstein B173 [November 27th](https://docs.google.com/document/d/14_ZQn6WCaMMmkcbTuD5rE31I18zguMaSwHoEtZNN8T4/edit?usp=sharing )
    * Purpose:  Stand-up/Coding session

4. General Team meeting / Stand-up: @Gerstein B173 [Novermber 29th](https://docs.google.com/document/d/16ivgaXq7y4pi2HUKoes_8DG6c-tEmA2QIIeJ5avPO2A/edit?usp=sharing )
    * Purpose:  Stand-up/ Coding session

5. Angelo/Sonata Subteam meeting discussion about UI: @Robarts -no minutes available-
    * Purpose:  Discussion about UI strategies and decisions 
6. Lana/Musa Subteam meeting about Export feature: @BA3175 -no minutes available-
*Purpose: debugging the export functionality and exchanging solution approaches. 

7. Justine/Tanveer subteam meeting: @Bahen atrium [November 29 2017]
    * Purpose: to implement REST API for communicating with a database

8. General Team Meeting / Stand-up: @B025 [November 30 2017] 
    * Purpose: Finalizing the deliverables, fixing merge conflicts and preparing for filming the final iteration video! 


#### Artifacts

* All work is done in **subteams**. Subteams are formed for each task, and members are assigned to a team based on personal interest and skill set.
* **[Trello Board](https://trello.com/b/uOa2sSLp)**: our main organization tool for assigning tasks and keeping track of our progress. For each task, a card on Trello will be created. Every card will have a subteam assigned to it, as described above. All cards will be in one of the following lists: to be done, in progress, testing, review and done. All cards will start in the "to be done" list and move along the pipeline, as subteams make progress on the tasks. Task priority is discussed on individual basis. Tasks are pretty general (for example, "implement log in activity"), so one subteam will probably work on the same task for the entire iteration.
* **Slack**: main communication channel. All discussion regarding the project should be there. We will create new threads per discussion to have an organized discussion process. We will have a separate channel for each subteam. 
https://drive.google.com/open?id=1NMttu1qgQ5jVZ8wuvPdquEKVlM3bDXma
* **Facebook Messenger**: used as a notification tool for group meetings. We chose this over doing everything in Slack, because everybody checks Messenger often, which may not be true for Slack.
* **[Commits graph](https://github.com/csc301-fall-2017/project-team-12/graphs/commit-activity)**. We will monitor our commitment graph to make sure we maintain a steady pace in working on our project.
* **[Meeting minutes](https://drive.google.com/drive/folders/0B2FyRSw68u7UZEhJbGNBUXRjNEE?usp=sharing)**: we keep track of what is discussed during our meetings so that important points mentioned during meetings are not forgotten, and we can refer to them later. During our meetings people raise a lot of good points but it's hard to remember them all. Meeting minutes help us remember a good idea and possibly discuss it more.

#### Git / GitHub workflow


* Every subteam checks out a [separate branch](https://github.com/csc301-fall-2017/project-team-12/branches/active) for the feature they will work on. Within a subteam, code conflicts are avoided by a splitting a task in a number of subtasks between teammates, where each person works on a separate subtask. For example, members of the same subteam work on different functions within the same activity. Our subteams are small (2-3 people), so that is the most convenient solution. Once a working version of a feature is ready, subteams review their code and make a pull request to the master branch. If a pull request has any conflicts, it's the responsibility of a subteam making a request to resolve any conflicts without breaking the master branch. Reviewers (any 2 people from a different subteam) review the request and merge it with master, if they are satisfied with the quality. If reviewers are not satisfied, they provide feedback to the subteam and they fix the issue. This helps us achieve good quality of code in the master branch, and it also ensures that the master branch always contains a working version of the app.
* We are using camelcase naming convention, [Google java style](https://google.github.io/styleguide/javaguide.html). We are adopting this convention, because this is an industry standard and it will make our code uniform and easily readable.
* Minimal documentation for each function/class (JavaDocs), explain what parameters and return value are. In 1-2 sentences explain what a function/class does. This will ensure that people from different subteams can understand each other's code, without writing extensive/redundant documentation.


## Product

#### Goals and tasks

By the end of this iteration, the following user stories for our recruiter [Jane](https://app.xtensio.com/folio/ndwhpubh) will be implemented, in addition to stories implemented for the previous iteration. 
* As Jane, I want to be able to sign in into the app using my actual email and password.
* As Jane, I should not be able to sign in, if I enter invalid/wrong email and/or password.
* As Jane, I want any new resumes and my tags/comments saved to the cloud storage, so that I can access them at any time, from any device.
* As Jane, I want to view resumes and comments/tags that were added by me in the past, but I do not want to be able to modify them.

To implement these user stories we will complete the following tasks:

* Modify our database schema so that it is MongoDB compliant, move it to mLab servers.
* Create Amazon AWS S3 bucket and integrate our app with it, so that resume images are uploaded/downloaded to/from the cloud.
* Make improvements to UI to enhance user experience. 
* Add some data analytics to our app, for example, the percentage of people who have “Java” as a skill, or a percentage of candidates recruiters liked. This will allow recruiters to have a better understanding of the applicant pool and set their future requirements/expectations accordingly.
* Add export feature, user should be able to export resume images and resume notes to email. 
* Implement sign-in, that will actually check that the user credentials are valid (in the database).


#### Artifacts
TA’s advice: 
    Exporting the resumes to phone memory instead of email was great advice to our team because emailing would have taken too much time.

[Progress Snapshots](https://drive.google.com/open?id=1NMttu1qgQ5jVZ8wuvPdquEKVlM3bDXma )
    
	
### Final Video:
[https://youtu.be/77kuhSEkY50](https://youtu.be/77kuhSEkY50)




