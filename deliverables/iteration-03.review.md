# Stackd

## Iteration 3 - Review & Retrospect

 * When: November 30th 2017
 * Where: BA B25
 
## Process - Reflection

Despite the brevity of this iteration, we managed to meet most of our goals. Our process seemed to be a lot smoother because we learned from previous missteps and tried to implement solutions that would stop us from making the same mistakes. For example, we had more stand-up meetings to prevent miscommunication between subteams. Subteams also met individually to work on features that needed implementation. While having our Deliverable 2 review and deliverable 3 planning meeting, members gave each other feedback regarding how they felt about the work flow and contribution metrics of the team. Since we listened to each other's feedback, we assigned each other work that was a good fit for all members, and in line with their contribution goals. This was crucial to making sure everyone was getting the most that they could out of the experience, and contributing in the most productive manner.   
These improvements made a noticeable effect on our process because we were relatively more productive in a shorter timespan. 

#### Decisions that turned out well


* **Made sure all team members understood and were satisfied with the work portion assigned.** 
This is important because during the previous iteration, some members were not satisfied with the task that they were given because it seemed disconnected from the app and was an extension to it. Other members simply did not have enough work assigned because most coding tasks were already taken, so they contributed more towards perfecting the video and helping out other subteams with minor implementation tasks so there was an imbalance in terms of the code contributed during the code iteration. During this iteration, however,  we made sure this mismatch would not be repeated and therefore distributed tasks by letting the members with less contributions take on more key roles, and thus balance our members' input in the development process across both iterations.
* **Reshuffled most subteams so that different members get to work on different parts of the application**.
The application has many different components and sections, so during the last iteration, not all of us really knew what the other subteam was exactly doing, unless it affected our own work. During this iteration, we rotated subteams which gave everyone a chance to try out a different part of the application to get a sense of how the product was developed as a whole. For example, Sonata and Angelo, who had previously worked on the Scanner and Stack screen respectively, worked on upgrading the Edit Screen while Tanveer and Justine worked on the server. This shuffling of roles was also important for the development of the app itself. Many aspects of the app grew with the contributions that different developers made. For instance, the creativity of new members improved the Edit Screen layout greatly, and that of others changed the colours of the stack view to better suit the function of the activity. Since different components of our app gained input from a set of fresh eyes, this greatly contributed to its success.
* **Making regular and consistent [commits] (https://github.com/csc301-fall-2017/project-team-12/graphs/commit-activity)**. We managed to maintain a steady commit history which led us to find more bugs, fix them earlier, seek help from eachother, and get feedback from TAs and instructors regularly. Instead of committing in chunks, we commit minor changes on a steady basis, and therefore maintained better github workflow.
 
#### Decisions that did not turn out as well as we hoped

*  **Trello**. Even though we planned to use Trello more efficiently and consistently, we were unable to do that. Most people kept forgetting to move cards between lists and put any updates on them. We integrated Trello with Slack but nobody was using that feature. We believe this is due to slack’s power to communicate information at a higher speed in comparison to Trello. On slack, you can view who’s online, talk to them directly about tasks and have more in depth discussions. This is the one main reason why Trello wasn’t used very frequently during this iteration.

* **Waiting to implement HTTP requests after database was completed** There were some delays in organizing the database because we wanted to find the most efficient way to store data in relation to what our MVP was trying to accomplish. Therefore, waiting until this was completed did not leave us enough time to connect our mobile app with the server. We started implementing the connection but faced network problems with the Android Emulator, pushing our progress backwards and making us incapable of meeting this deliverable deadline. 

* **Meeting productivity**. Despite meeting 3 times a week, most of our meetings were not very productive, as people were often late due to transit delays, or were unable to participate in person. We spent too much time just chatting and catching up, instead of focusing on a meeting’s agenda. Although the standups kept us updated, we should have been more on task in order to reach optimal productivity at the meetings. 

#### Planned changes
 
We are not going to have 3 meetings next week like every iteration because there is no time left for that. Overall, there is no time left to change anything, as this is the last iteration, and we only have 2 days before the final presentation. As for future plans beyond the demo date, we are planning on meeting once again and discussing our next steps towards completing the database requests feature which allows our data to persist. We will be working on our Demo presentation in order to ensure a succssful summative to all the work we have done this semester.

## Product - Review

#### Goals and/or tasks that were met/completed:

* We integrated our app with Amazon AWS S3 bucket. All images are now stored on the cloud. Our app downloads them and uploads a new image, whenever a new resume is reviewed.
* We moved our database to the mLab cloud server. As a consequence, we had to remodel our schema, since mLab is using MongoDB. All data, except images, is now stored in that database. 
* We made improvements to UI, especially to the view for commenting/tagging a new resume. 
* We implemented export feature, so now images and notes of resumes can be exported to the phone's memory. 
 
#### Goals and/or tasks that were planned but not met/completed:
* We were not able to connect the app to the server. This prevented all changes/updates to app data from persisting across multiple runs of the app. This happened because it required the subteam responsible to learn many new technologies such as MongoDB, NodeJs, and MLab in almost a week. Unfortunately, after many unsuccessful attempts we have decided to leave this out of the MVP.
* We decided not to export to an XML or email format. Instead, we're exporting the resumes as images to the tablet's storage and a report with the candidate's email, comments, and tags in csv format. This is easier to implement given time constraints, while still providing users with the ability to export their data somewhere for use outside of the app. 
* We realized that analytics are not an essential feature for our MVP, so due to time constraints we have decided not to implement them.

## Meeting Highlights

Seeing as this is the final iteration, we have finished our Minimum Viable Product. If we have time, our group might develop this product further over winter holidays and summer break as a side project. We would want to be able to parse the resume so that the recruiter may not even need to manually input tags, or there could be automated tags. We would also like to improve export so that on top of being able to save files to phone memory we could condense them and email them to the recruiter. We are also considering completing the project as a product and publishing it on the Google Play Store during the winter break as a side project. 
