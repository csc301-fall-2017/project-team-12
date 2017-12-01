# Stackd

## Iteration 3 - Review & Retrospect

 * When: November 30th 2017
 * Where: BA B25
 
## Process - Reflection

Despite the brevity of this iteration, we managed to meet most of our goals. Our process seemed to be a lot smoother because we learned from previous missteps and tried to implement solutions that would stop us from making the same mistakes. An example of that is being able to give feedback on the work assigned and whether we felt that it was a good fit for all members. We also made sure to implement a more frequent update system, i.e. we had more stand-up meetings to prevent miscommunication between subteams. These improvements made a noticeable effect on our process because we were relatively more productive in a shorter timespan. 

#### Decisions that turned out well


* **Made sure all team members understood and were satisfied with the work portion assigned.** 
This is important because during the previous iteration, some members were not satisfied with the task that they were given because it seemed disconnected from the app and was an extension to it. Other members simply did not have enough work assigned because most coding tasks were already taken, so they contributed more towards perfecting the video and helping out other subteams with minor tasks so there was an imbalance in terms of the code contributed. During this iteration, however,  we made sure this would not repeat and therefore we distributed tasks by first deciding what the tasks are, then letting the members with less contributions take on more key roles which would balance their input in the development process across both iterations.
* **Reshuffled most subteams so that different members get to work on different parts of the application**.
The application has many different components and sections, so during the last iteration, not all of us really knew what the other subteam was exactly doing, unless it affected our own work. During this iteration, we rotated subteams which gave everyone a chance to try out a different part of the application to get a sense of how the product was developed as a whole.

#### Decisions that did not turn out as well as we hoped

*  **Trello**. Even though we planned to use Trello more efficiently and consistently, we were unable to do that. Most people kept forgetting to move cards between lists and put any updates on them. We integrated Trello with Slack but nobody was using that feature. 
* **Meeting productivity**. Despite meeting 3 times a week, most of our meetings were not very productive, as people were often late due to transit delays, or were unable to participate in person. We spent too much time just chatting and catching up, instead of focusing on a meeting’s agenda. 

#### Planned changes
 
We are not going to have 3 meetings next week because there is no time left for that. Overall, there is no time left to change anything, as this is the last iteration, and we only have a few days before the final presentation.

## Product - Review

#### Goals and/or tasks that were met/completed:

* We integrated our app with Amazon AWS S3 bucket. All images are now stored on the cloud. Our app downloads them and uploads a new image, whenever a new resume is reviewed.
* We moved our database to the mLab cloud server. As a consequence, we had to remodel our schema, since mLab is using MongoDB. All data, except images, is now stored in that database. Thus, all changes/updates to app data now persist across multiple runs of the app and multiple devices. 
* We made improvements to UI, especially to the view for commenting/tagging a new resume. 
* We implemented export feature, so now images  and notes of resumes can be exported to the phone's SD card. 
 
#### Goals and/or tasks that were planned but not met/completed:
    
* We decided not to export to XML file. Instead, we export images and emails to phone's SD card. This is easier to implement given time constraints, while still providing users with the ability to export their data somewhere for use outside of the app. 
* We realized that analytics is not an essential feature for our MVP, so due to time constraints we decided not to implement it. 

## Meeting Highlights

Seeing as this is the final iteration, we have finished our Minimum Viable Product. If we have time, our group might develop this product further over winter holidays and summer break as a side project. We would want to be able to parse the resume so that the recruiter may not even need to manually input tags, or there could be automated tags. We would also like to improve export so that on top of being able to save files to an SD card we could condense them and email them to the recruiter. 

