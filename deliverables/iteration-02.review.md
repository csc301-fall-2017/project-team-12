# Stackd


## Iteration 02 - Review & Retrospect

 * When: November 15, 2017
 * Where: Gerstein Library, B173

## Process - Reflection

This iteration was very interesting because our teamwork, communication, and dedication were put to the test. We split off into our subteams and assigned the tasks according to our plans, hit rough spots and high spots, but also experienced great pride when we saw our app running successfully. We met several times during reading week, reviewed each other's code, and communicated across subteams as well to make use of the skills of each of our team members.

#### Decisions that turned out well

1. **Splitting into subteams based on experience and interest**. This made our team more efficient because we did not have to discuss with the entire team decisions that were irrelevant to other subteams. For example, Musa and Tanveer had previous experience with databases, so they worked on our back-end. Angelo and Dmitry expressed interest in working with the Resume Stack and had previous experience working with adapters, list view and layouts. Justine and Sonata found the APIs and Parsers needed for camera view. Lana worked on the edit resume screen.
2. **Working on each feature in a separate branch and merging with master frequently**. Creating [separate branches](https://github.com/csc301-fall-2017/project-team-12/network) for each subteam/feature kept code more organized and minimized conflicts. Each member of each subteam would be responsible for implementing a specific feature of the application on a separate branch from master. A developer would have to make a pull request and one other team member from the same subteam would have to review the code before merging with master. This ensured that only quality code would be in the master, thus, minimizing bugs. Merging implemented features with master as soon as they were finished kept master up to date so that other subteams can use a feature as soon as possible. 
3. **[Designing architecture/interfaces together](https://docs.google.com/document/d/154vbClCN_BcPWM_iJ9u5pJBstpkhumHQqY8ZJ25W5po/edit?usp=sharing)**. During the planning meeting we designed our ER diagram. Since we didn't modify our database diagram (which substituted UML diagrams) everyone had a clear picture of how different components of the app would work together and good understanding of the needs of other subteams. 
4. **Organizing coding sessions together**. During reading week we organized a meeting where we all worked together on our tasks. This helped to see everyone's progress and we were able to help each other out when needed. During that coding session we implemented the significant amount of work which boosted our productivity. 


#### Decisions that did not turn out as well as we hoped

1. **Test Driven Development**. TDD did not work out at all, because most of us were developing a non-trivial Android app for the first time, we didn’t have a clear idea of the requirements and how features should be implemented. So we naturally explored how to implement the features one by one and ended up writing code without test cases (since we were not sure whether we would keep it or not). We ended up writing tests after implementing features and by actually using the app.
2. **Meeting once a week for one hour**. Only one hour of meetings per week was not enough because the whole team is only updated on everyone’s progress once a week. Some changes made by one subteam may affect another subteam but a lot of code may have already been written between the stand-up meetings. Very time expensive to revert changes.


[Lana]		Splitting into concrete subteams fragmented our decision making and tried to take on difficult decisions, that concerned the architecture and thus the whole team, on our own as subteam instead of bringing it forward to the rest of the group.  Initially, we decided to model our database so it uses a local room and would use tables for all our values. Although this decision was made by all of us as a team, it was wrong of us to not properly research and question those who had come up with their idea before going on board. Although the room was a great successful effort when implemented by one of the subteams, we realized later on that this approach wasn't optimal if we wanted to link our database to a web server (for our MVP). After several meetings within our own subteam, we realized this approach was incorrect. The decision to use Room was not successful, but since we were in our own subteams we did not talk to the rest of the team about such a grave architectural flaw. After members did more (hours and hours) of research for the best way to implement our database, we went to the whole team with a better explanation of our game-plan. We decided to use JSON objects because it would allow for flexibility later on. 	
I believe that not starting with JSON objects from the start made us lose some time writing the queries and DAOs, lose human resources when working on something we eventually scrapped, and made us a bit less productive. However, it also pushed us to find a better solution to our problem! And it taught us that when struggling with a road block, we must seek help. Hence, discussing the new plan with the members put us back on the right track.


#### Planned changes

1. **3 stand-up meetings per week**. One meeting will be after the tutorial on Monday, the other one on Wednesday as usual and the last one on Friday night over Skype/Hangouts. This will help us make sure that everyone is up-to-date with eveyone else's progress. Subteams will be able to inform other subteams what they need from other teams to implement their own features. Also, team organization concerns will also be brought up during these stand-up meetings.
2. **Switch tasks between subteams**. Those who worked on the back-end will optimize UI and people who worked on fornt-end will work on the server. This will provide everyone an opportunity to work on the full-stack of technologies for this project and will provide a more holistic view of how application development works. It will also allow to even out contribution between different team members, as there were some concerns about it.  
3. **Integrating Trello into Slack**. We want to use Trello more efficiently, by integrating it with Slack to better assign tasks and keeps track of them. (This will help us better understand what we’re doing and what else need to be done). 

## Product - Review

#### Goals and/or tasks that were met/completed:

 * From most to least important.
 * Refer/link to artifact(s) that show that a goal/task was met/completed.
 * If a goal/task was not part of the original iteration plan, please mention it.

[Justine]:
Being able to annotate a resume
(Almost) being able to capture a resume in a photo scan 

I think these two are most important because they make our application what it is. 

[Musa]:
Implement DataManager, since this is the backbone of the backend to communicate with the other views (link github)
All the views were almost fully implemented, this is crucial because we can demo the usability of the product and the workflow of it to collect feedback from users before committing more time and effort on optimizing it and fully integrate it with the backend.

[Angelo]
·       The ability to annotate a resume, i.e., highlighting a resume, adding a brief description about the candidate, and adding tags such as PEY, Full-time, Internship, etc.
·       A virtual stack of resumes that the user can filter using a search bar and tags. The user upon login will be able to see thumbnails of resumes labeled by name of candidate, similar to a photo gallery. Furthermore, the recruiter will be able to search through the resumes by name or tags.
·       The ability to take an image of the resume using the camera.


#### Goals and/or tasks that were planned but not met/completed:

 * From most to least important.
 * For each goal/task, explain why it was not met/completed.      
   e.g. Did you change your mind, or did you just not get to it yet?


[Justine]: 
Originally, we wanted to parse bits of the resume but we realized the name would be extremely difficult to parse. The emails are more possible and so are tags but some scans of the resume would vary from others.

[Musa]:
Implementing Room database, we changed our mind since we discovered it’s only a local database storage and won’t be sufficient for our final MVP, so working on it would be a waste of time
Test cases, since we wrote and deleted a lot of code, we weren’t 100% aware of the requirements until we tried them out (we were learning as we go) so following TDD without previous experience was impossible.



[Angelo]
·       Real-time parsing of the resume was not fully completed due to difficulty of finding an API that can accurately parse information we want, i.e., name of candidate. We are considering trying to parse other information instead such as email because it might be easier for the API to pick up during the scan.
·       Test cases were not completed because methods to be implemented were not planned in advance. Thus, some sub-teams did not write unit tests for these methods because they did not plan them in advance. Instead, testing was done by simply running the activity. Sub-teams would be able to focus on other tasks if activities did what were expected (even without unit tests).


## Meeting Highlights

Going into the next iteration, our main insights are:

 * 2 - 4 items
 * Short (no more than one short paragraph per item)
 * High-level concepts that should guide your work for the next iteration.
 * These concepts should help you decide on where to focus your efforts.
 * Can be related to product and/or process.

[Justine]:
Finishing our MVP
Polishing the UI if we have time

[Musa]:
Putting more work on making the UI stands out and aesthetically pleasing for the users, since this what will make them use our product and enables them to easily navigate through it.
Do more research on the architecture before building it, since we’re building the server for the next iteration it’s important to not repeat same error (working on Room database before fully understanding its usability). To avoid wasting time and resources.
Focus more on communication, use Slack more often and makes sure the whole team knows what’s going on, and what are the required tasks and who’s working on them to avoid potential conflicts and/or misunderstandings.

[Angelo]
Enhance the user interface of the mobile application to create a better user experience, i.e., ensure product stands out and the user has a better flow when navigating between different views.
 Ensure better communication between sub-teams. For example, two “Resume” classes were created. We need to ensure each team member are assigned specific tasks and understand what they are responsible for delivering at the end of the sprint to avoid duplicate work.

