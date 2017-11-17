# Stackd


## Iteration 02

 * Start date: 10/20/17
 * End date: 11/15/17

## Process

#### Roles & responsibilities
* **Software Developers**: everyone
	* Developing and testing front-end Android app
	* Developing and testing back-end database
	* The exact tasks will be assigned later, for details see Artifacts section
* **Scrum Master**: Tanveer
	* Keeping the Trello boards updated
	* Making sure everyone knows about meeting times 
	* Clear knowledge on deadlines and meeting dates
	
* **Commit Master**: Dmitry
	* Organizing all files for our project, including deliverables, meeting minutes, etc.
	* Making sure all files are formatted correctly for submission
	* Checks all submissions for correctness
* **Product Manager**:  Angelo & Musa
	* Collecting customer feedback
	* Making sure customer features are implemented
* **Visual design**: Lana, Justine & Sonata
	* Finalizing mock-ups
	* Working on UI
* **Meeting Minutes**: Rotated to a different person with each meeting
	* Log discussion during meetings


#### Events

Describe meetings (and other events) you are planning to have:

All of our meetings will be in person, if somebody cannot attend, he/she will join the team on appear.in. 

Planned meetings:

 * Friday October 27th: Gerstein B173;
 	* Planning session, splitting roles, and agreeing on architecture
 * Saturday/Sunday October 28th/29th: BA2270
	* Finishing the mockup designs in order to proceed for CRC cards
 * Every wednesdays 12-1: Gerstein B173. Stand up Meetings in person
 	* Update each other on our progress, address concerns, and decide what to do next
 * Sometime during reading week. Coding session
 	* Connect each team's individual work to create the first working version of our MVP

#### Artifacts

* All work is done in **subteams**. Subteams are formed for each task, and members are assigned to a team based on personal interest and skill set.
* **[Trello Board](https://trello.com/b/uOa2sSLp)**: our main organization tool for assigning tasks and keeping track of our progress. For each task, a card on Trello will be created. Every card will have a subteam assigned to it, as described above. All cards will be in one of the following lists: to be done, in progress, testing, review and done. All cards will start in the "to be done" list and move along the pipeline, as subteams make progress on the tasks. Task priority is discussed on individual basis. Tasks are pretty general (for example, "implement log in activity"), so one subteam will probably work on the same task for the entire iteration.
* **Slack**: main communication channel. All discussion regarding the project should be there. We will create new threads per discussion to have an organized discussion process. We will have a separate channel for each subteam. 
* **Facebook Messenger**: used as a notification tool for group meetings. We chose this over doing everything in Slack, because everybody checks Messenger often, which may not be true for Slack. 
* **[Meeting minutes](https://drive.google.com/drive/folders/0B2FyRSw68u7UZEhJbGNBUXRjNEE?usp=sharing)**: we keep track of what is discussed during our meetings so that important points mentioned during meetings are not forgotten, and we can refer to them later. During our meetings people raise a lot of good points but it's hard to remember them all. Meeting minutes help us remember a good idea and possibly discuss it more.

#### Git / GitHub workflow

* Every subteam checks out a separate branch for the feature they will work on. Within a subteam, code conflicts are avoided by a splitting a task in a number of subtasks between teammates, where each person works on a separate subtask. For example, members of the same subteam work on different functions within the same activity. Our subteams are small (2-3 people), so that is the most convenient solution. Once a working version of a feature is ready, subteams review their code and make a pull request to the master branch. If a pull request has any conflicts, it's the responsibility of a subteam making a request to resolve any conflicts without breaking the master branch. Reviewers (any 2 people from a different subteam) review the request and merge it with master, if they are satisfied with the quality. If reviewers are not satisfied, they provide feedback to the subteam and they fix the issue. This helps us achieve good quality of code in the master branch, and it also ensures that the master branch always contains a working version of the app.
* We are using camelcase naming convention, [Google java style](https://google.github.io/styleguide/javaguide.html). We are adopting this convention, because this is an industry standard and it will make our code uniform and easily readable.
* Minimal documentation for each function/class (JavaDocs), explain what parameters and return value are. In 1-2 sentences explain what a function/class does. This will ensure that people from different subteams can understand each other's code, without writing extensive/redundant documentation.


## Product

#### Goals and tasks

By the end of this iteration, the following user stories for our recruiter [Jane](https://app.xtensio.com/folio/ndwhpubh) will be implemented or mocked up: 

* As Jane, I want to be able to sign in into the app.
* As Jane, I want to see the list of resumes I currently have and I want to only see resumes of people looking for a PEY internship.
* As Jane, I want to take a picture of student's resume and I want name and email of the candidate extracted from the picture.
* As Jane, I want to be able to tag the newly taken resume as "PEY internship".
* As Jane, I want to write some quick comments about the impression a candidate made on me.

To implement these user stories we will complete the following tasks:

* Finalize the flow/UI of our app. We are prioritizing this goal, because it is not possible to proceed with implementation without solid understanding of how our product looks like and what it can do. We need to discuss with the entire team some points of contention about the design and functionality of the app. For example, should the resume stack be a list or a grid of image thumbnails? Based on the results of a discussion, we will finalize our mock up.

* Formalize the structure/architecture of our activities, database, and other abstractions. We need to decide what kind of database we will be using (SQL/NoSQL), what information it needs to store, design database schema, queries needed, etc., come up with classes and methods needed to abstract away database access. Based on the mock up we need to decide which Android activities we need and what they should do
* Split our team into smaller subteams to work on different parts of the application. 

* Implement a basic prototype of our app, ready for the end of iteration demo. All main features should be functional, but database will be local, instead of remote, some features might be mocked up. To achieve that, multiple subteams will implement activities, classes and database as described above.


#### Artifacts

* Final version of InVision mock up
	* This is necessary, because we cannot proceed with implementing UI and activities in Android without having a clear vision of how everything should look like and what the flow between screens should be.

* Diagrams for every class, database relation and show relationships between different project components. 
	* This will facilitate better understanding of the entire project, make members of different teams aware of the interfaces they will have to share with other teams, and will make our project well-designed, allowing us to scale it up or add more features in the future, if deemed necessary.

* Completed UI for activities: Login, Stack, Review and Camera
	* This represents the core interface/flow of our app, so we cannot have our product demo without having UIs for all of these activities. 
* Implementation of basic functionality for Login, Stack, Review and Camera activities. 
	* Login activity will be present, as users (recruiters) are important part of our app, but the log in process will be mocked up. Everything else will be implemented at least on some basic level, as all of these activities are essential for our app. A recruiter should be able to view resumes, add new ones and put notes/tags on them.
* Implementation of basic functionality for database, connect app to a local/mock database on the device.
	*  All resumes, recruiter profiles and their notes/tags have to be stored in an organized manner, so implementing database is crucial. However, for this deliverable it is not necessary to place a database on a remote server, it will suffice to have a database locally. 

* Write basic tests for all of the implemented classes, if time permits, write some more advanced tests.
	* We need to make sure that our code works correctly, and will not crash in the simplest situations. 

* Complete a short video, presenting our product (as specified in the requirements for this deliverable).
	* We will film a video presenting the problem of resume collection/organization at career fairs and show how our app solves this problem. Besides being a required artifact for this deliverable, it will help us see how our product would be used in practice. 



