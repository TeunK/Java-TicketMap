# TicketMap

Graduate Developer Test

##Table of Contents
- [Assumptions](#Assumptions)
- [NearestEvents](#nearestevents)

##Assumptions
-
-
-

##Questions
- **How might you change your program if you needed to support multiple events at the same location?**

Basically instead of mapping single events in the grid (Grid.java), replace this with a list of events.
replace:

`private final Map<Location, Event> locationEventMap;`

with:

`private final Map<Location, List<Event>> locationEventsMap;`

This would be followed up some refactoring as now we have to work with a list, which may be empty, contain 1 event, or >1 events.
size 0 or 1 would still be easy to work with, however >1 would require more assumptions to be made, for "nearest 5 events" could already be found in a single nearest neighboring point on the grid. In fact, even more than 5 may be there simultaneously.

- **How would you change your program if you were working with a much larger world size?**

This would not change anything to the program, as only the events are stored with a specific location when they exist.
Having a larger world size would only change the potential locations of these Events.
In [NearestEvents](#nearestevents) I mention 2 options to solve the find-nearest-events method, and I chose the one mentioned in Example 1 for this reason.


##NearestEvents
There are several ways to implement the task of returning a list of the five closest events

Example 1
```
**Initial thought**
Bruteforce solution through all events, keep track of their associated distances from the given location and pick those with the lowest distance.
This is very inefficient if you think of this visually.
However this would still be a pragmatic solution in the real world, as we can partition the data into subsets in a database for example by country or continent.
(US / EUROPE / ..) or (UK / BELGIUM / THE NETHERLANDS / GERMANY / ...)
And realistically, the number of events within these subsets won't nearly exceed the amount of possible locations on a map masked with a grid (where many of which won't even contain any events).
```

Example 2
```
Since this task is taken as a grid between (-10,-10) and (10,10), we could also follow some Dijkstra pathfinding-related algorithm.
Basically we would start at the given location, and recursively check the location's manhattan-neighbors (i.e. no diagonal steps allowed)
Every recursive round would return an event if one is located at this position on the grid, until either we have found 5 in total or have covered the entire grid.
This would mean that we don't have to cover events that we won't be using anyway.
However this would not really scale either as in a real-world scenario there are probably many more cells in the grid than there are events.
For example when the grid model scales from (-1000000, -1000000) to (1000000, 1000000) and only hosting about 20 dispersed events in total.
For this reason, the method in example 1 sounds more realistic.
```
