# BirdLifeListApp

## Introduction

A birding life list is a collection of the bird species you have seen in the wild. This app would provide an array of pre entered birds and an opportunity to add your own custom bird species for birds not already included in the database in order to keep an accurate record of the birds you’ve seen. Common name, species name, descriptions, location and date tracking, as well as pre existing or user added images are all possible data points you could add to individual bird species. We will start by adding birds from the Ohio region, then will potentially expand to surrounding areas.

## Storyboard

[Storyboard Link](https://github.com/advaitparab/BirdLifeListApp/blob/main/Winged%20Waypoints%20(2).pdf)

## Requirements

1) (Search Birds) As a birder, I want to search for bird species by name, So that I can quickly find the bird I am interested in.

### Example

**Given**: the database contains a bird called "Northern Cardinal"

**When**: I search for "Cardinal"

**Then**: the results should include "Northern Cardinal"

### Example

**Given**: I enter a random string with no matches

**When**: I search

**Then**: the results should display "No birds found"

2) (Filter Birds by Attributes) As a birder, I want to filter birds by color or location, So that I can narrow my list to birds I’ve likely seen.

### Example

**Given**: birds with color “red” exist in the database

**When**: I apply the color filter = “red”

**Then**: only red-colored birds should be displayed

### Example

**Given**: I select “Ohio” as location

**When**: I apply the filter

**Then**: only birds recorded in Ohio should be shown

3) (Maintain My Bird List) As a birder, I want to add birds to a personal list, So that I can track which birds I have seen.

### Example
   
**Given**: I am viewing a bird on the main list

**When**: I click “Add to My List”

**Then**: the bird should appear on the "My List" page

### Example

**Given**: a bird is already on my list

**When**: I choose “Delete”

**Then**: the bird should be removed from my list

4) (Record Observations) As a birder, I want to edit details of when and where I saw a bird, So that I can keep accurate records of my sightings.

### Example

**Given**: a bird exists on my list

**When**: I add a location and date seen

**Then**: the updated details should be saved and displayed

### Example

**Given**: I leave required fields blank

**When**: I try to save

**Then**: the system should show a validation error

## Class Diagram 

![MyWingedWaypointsClassDiagram](https://github.com/advaitparab/BirdLifeListApp/blob/main/Class%20Diagram%20-%20Winged%20Waypoints.png)

### Class Digram Description

### Model Classes:
- Bird is the class where the overarching database of birds is set up
- MyList is the class where the user-made life list is created and stored
- User holds user information
- Waypoint holds data on locations the user creates, so they can reuse the same location without putting in the same information everytime

### Service Classes:
- BirdService contains methods like search and filter for the whole bird database
- MyListService has those same methods but only searches the user made list

### UI Classes:
- HomePage has the UI elements for the home page
- MyListPage has the UI elements for the page displaying the user made list
- BirdDetailsPage has the UI elements for the page that displays all the information on a specific bird

### DAO Classes:
- BirdDAO has database tools for the Bird database
- MyListDAO has database tools for the MyList database

## JSON Schema
> {
>   "$schema": "http://json-schema.org/draft-07/schema#",
>   "title": "Bird",
>   "type": "object",
>   "properties": {
>     "birdId": { "type": "integer" },
>     "commonName": { "type": "string" },
>     "speciesName": { "type": "string" },
>     "color": { "type": "string" },
>     "defaultLocation": { "type": "string" },
>     "description": { "type": "string" },
>     "images": {
>       "type": "array",
>       "items": { "type": "string", "format": "uri" }
>     },
>     "userData": {
>       "type": "object",
>       "properties": {
>         "dateSeen": { "type": "string", "format": "date" },
>        "locationSeen": { "type": "string" },
>         "notes": { "type": "string" }
>       }
>     }
>   },
>   "required": ["birdId", "commonName", "speciesName", "color"]
> }

## Team Members and Roles
*All roles will help/share the workload for all roles*
- UI Specialist: Harsh Patel
- Business Logic/Persistence: Sydney Schalk
- DevOps/Github Admin: Advait Parab
- Product Owner/Scrum Master: Grace Holscher

## Milestones
- [Milestone 1](https://github.com/advaitparab/BirdLifeListApp/milestone/1)
- [Milestone 2](https://github.com/advaitparab/BirdLifeListApp/milestone/2)
- [Milestone 3](https://github.com/advaitparab/BirdLifeListApp/milestone/3)

##Standup
[We meet 6:00pm Eastern on Wenesday on Teams](https://teams.microsoft.com/l/meetup-join/19%3ameeting_NTA3YzdhOGYtZWFmMS00ZTAyLWFiODMtZTEzZTEzMDcxMDU1%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%226e65e3a8-fa49-4627-86d6-1467671124b9%22%7d)
