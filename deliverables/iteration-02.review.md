# Stackd


## Iteration 02 - Review & Retrospect

 * When: November 15, 2017
 * Where: Gerstein Library, B173

## Process - Reflection

This iteration was very interesting because our teamwork, communication, and dedication were put to the test. We split off into our subteams and assigned the tasks according to our plans, hit rough spots and high spots, but also experienced great pride when we saw our app running successfully. We met several times during reading week, reviewed each other's code, and communicated across subteams as well to make use of the skills of each of our team members.

#### Decisions that turned out well

1. **Splitting into subteams based on experience and interest**.

This made our team more efficient because we did not have to discuss with the entire team decisions that were irrelevant to other subteams. For example, Musa and Tanveer had previous experience with databases, so they worked on our back-end. Angelo and Dmitry expressed interest in working with the Resume Stack and had previous experience working with adapters, list view and layouts. Justine and Sonata found the APIs and Parsers needed for camera view. Lana worked on the edit resume screen.

2. **Working on each feature in a separate branch and merging with master frequently**. 

Creating [separate branches](https://github.com/csc301-fall-2017/project-team-12/network) for each subteam/feature kept code more organized and minimized conflicts. Each member of each subteam would be responsible for implementing a specific feature of the application on a separate branch from master. A developer would have to make a pull request and one other team member from the same subteam would have to review the code before merging with master. This ensured that only quality code would be in the master, thus, minimizing bugs. Merging implemented features with master as soon as they were finished kept master up to date so that other subteams can use a feature as soon as possible. 

3. **[Designing architecture/interfaces together](https://docs.google.com/document/d/154vbClCN_BcPWM_iJ9u5pJBstpkhumHQqY8ZJ25W5po/edit?usp=sharing)**.

During the planning meeting we designed our ER diagram. Since we didn't modify our database diagram (which substituted UML diagrams) everyone had a clear picture of how different components of the app would work together and good understanding of the needs of other subteams. 

4. **Organizing coding sessions together**. 

During reading week we organized a meeting where we all worked together on our tasks. This helped to see everyone's progress and we were able to help each other out when needed. During that coding session we implemented the significant amount of work which boosted our productivity. 


#### Decisions that did not turn out as well as we hoped

1. **Test Driven Development**. 

TDD did not work out at all, because most of us were developing a non-trivial Android app for the first time, we didn’t have a clear idea of the requirements and how features should be implemented. So we naturally explored how to implement the features one by one and ended up writing code without test cases (since we were not sure whether we would keep it or not). We ended up writing tests after implementing features and by actually using the app.


2. **Meeting once a week for one hour**. 

Only one hour of meetings per week was not enough because the whole team is only updated on everyone’s progress once a week. Some changes made by one subteam may affect another subteam but a lot of code may have already been written between the stand-up meetings. Very time expensive to revert changes.


#### Planned changes

1. **3 stand-up meetings per week**. 

One meeting will be after the tutorial on Monday, the other one on Wednesday as usual and the last one on Friday night over Skype/Hangouts. This will help us make sure that everyone is up-to-date with eveyone else's progress. Subteams will be able to inform other subteams what they need from other teams to implement their own features. Also, team organization concerns will also be brought up during these stand-up meetings.

2. **Switch tasks between subteams**. 

Those who worked on the back-end will optimize UI and people who worked on fornt-end will work on the server. This will provide everyone an opportunity to work on the full-stack of technologies for this project and will provide a more holistic view of how application development works. It will also allow to even out contribution between different team members, as there were some concerns about it. 

3. **Integrating Trello into Slack**. 

We want to use Trello more efficiently, by integrating it with Slack to better assign tasks and keeps track of them. (This will help us better understand what we’re doing and what else need to be done). 

## Product - Review

#### Goals and/or tasks that were met/completed:

1. **Finished the interactive [mock-up](https://marvelapp.com/51ea43d) of our app**. 

This allowed us to start implementing views knowing exactly what they should be. 

2. **Designed the database and [ER diagram](https://drive.google.com/file/d/0B_k0GN496C_0azV1TlpzMnJoMmc/view?usp=sharing) for it**. 

This enabled everyone to have a good idea of what kind of data to expect from a database, how to interact with it, and what methods they will need for their features.  

3. **Implemented most of the [front-end views](https://docs.google.com/presentation/d/1xYqzDtxW5LoD-0PNnPX27Dl2MdutonBh0i8pdaoWmkw/edit?usp=sharing)**. 

The user upon login will be able to see thumbnails of resumes labeled by candidate name, similar to Google Docs. This stack of resumes can be filtered using a search bar and/or tags. A user can annotate a resume, i.e., highlight a part of the resume, add a brief description of the candidate, and add tags such as PEY, Full-time, Internship, etc. This is crucial because we can demo the usability of the product and its workflow to collect feedback from users before committing more time and effort on optimizing it and fully integrating it with the backend.

4. **Designed and implemented most of the [interface](https://github.com/csc301-fall-2017/project-team-12/tree/master/Stackd/app/src/main/java/com/stackd/stackd/db) between database and the app**.  

This is the backbone of the backend to communicate with the other views. It enabled everyone to understand how to communicate with the data manager to get and store data.

5. **Making a [short video](https://youtu.be/hIHGR2ZjrjE) (with captions available) about our product**. 

The video allowed us to better explain users what is our app and how to use it. This will make user feedback more meaningful, since they will see the entire product in action. 

#### Goals and/or tasks that were planned but not met/completed:


1. **Changing back-end architecture**. 

Initially, we wanter to use Room database, but we changed our mind since we discovered it’s only a local database storage and won’t be sufficient for our final MVP, so working on it would be a waste of time. Instead, we created JSON files faking database behaviour for this deliverable. This enabled us to focus on main features without worrying about the actual implementation of the database.

2. **Removing parsing resume functionality**. 

Originally, we wanted to parse resume picture to extract information about candidates (email and name), but we realized the name would be extremely difficult to extract. Parsing emails and tags is more feasible but different scans of the same resume would sometimes provide inconsistent results. Since parsing the resume is just an additional feature and does not contribute significantly to the value of our app, we decided to remove it. 

3. **Did not complete UML diagrams**. 

We wanted to make a UML diagram for each class we are going to implement. We changed the initial architecture and were not sure that we would stick with the current architecture, so making UML diagrams would potentially be a lot of wasted work. Instead, we defined interfaces and worked according to them.


## Meeting Highlights

1. Focus more on communication, use Slack more often and makes sure the whole team knows what’s going on, and what are the required tasks and who’s working on them to avoid potential conflicts and/or misunderstandings.  For example, two “Resume” classes were created because of lack of communication between team members and not assigning specific tasks.

2. Implementing a web server, which will store our data. Do more research before building it, making sure not repeat the same error (working on Room database before fully understanding its usability limits), to avoid wasting time and resources.

3. Polish the UI of the mobile application to create a better user experience, i.e., ensure product stands out and the user has a better flow when navigating between different views.

